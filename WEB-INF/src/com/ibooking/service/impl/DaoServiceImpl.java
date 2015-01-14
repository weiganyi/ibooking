package com.ibooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.ibooking.dao.*;
import com.ibooking.po.*;
import com.ibooking.service.*;
import com.ibooking.util.WebConstant;
import com.ibooking.vo.*;

public class DaoServiceImpl implements DaoService {
	private MenuDao menuDaoHbm;
	private MenuTypeDao menuTypeDaoHbm;
	private UserDao userDaoHbm;
	private OptionDao optionDaoHbm;
	private ShoppingDao shoppingDaoHbm;
	
	private MenuDao menuDaoRds;
	private MenuTypeDao menuTypeDaoRds;
	private OptionDao optionDaoRds;
	
	enum OPT {INVALID, SAVE_USER, UPDATE, DELETE};
	private LinkedBlockingQueue<OptInfo> q;
	private Thread thd;

	private static final int defaultMaxPagination = 7; 
	
	@Override
	public boolean validatePasswd(String userName, String userPasswd) {
		User user = getUserByName(userName);
		if (user != null && user.getPasswd().endsWith(userPasswd)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String getUserAuthByName(String userName) {
		User user = getUserByName(userName);
		if (user != null) {
			return user.getAuth();
		}else {
			return null;
		}
	}
	
	private User getUserByName(String userName) {
		List<User> lstUser = null;
		
		try {
			//get the user from hibernate
			lstUser = userDaoHbm.findByName(userName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getUserByName() userDaoHbm.findByName() catch exception: " + e.getMessage());
		}
		
		if (lstUser == null || lstUser.size() == 0) {
			return null;
		}
		
		return lstUser.get(0);
	}

	@Override
	public boolean insertUser(String userName, 					
							String userPasswd, 
							String userAuth, 
							String userTel, 
							String userAddr) {
		User user = getUserByName(userName);
		if (user != null) {
			return false;
		}
		
		user = new User();
		
		user.setName(userName);
		user.setPasswd(userPasswd);
		user.setAuth(userAuth);
		user.setTel(userTel);
		user.setAddr(userAddr);
		user.setId(0);

		try {
			//save the user into hibernate
			userDaoHbm.save(user);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.insertUser() userDaoHbm.save() catch exception: " + e.getMessage());
			return false;
		}
		
		return true;
	}

	@Override
	public String getTitle() {
		List<Option> lstOption = null;
		String optionName = "title";
		
		try {
			//get the title from redis
			lstOption = optionDaoRds.findByName(optionName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getTitle() optionDaoRds.findByName() catch exception: " + e.getMessage());
		}
		
		return lstOption.get(0).getValue();
	}
	
	@Override
	public IndexPageBean getIndexPageBean(int iCurrPage, String userName) {
		List<Menu> lstMenu = null;
		List<MenuType> lstMenuType = null;
		List<Option> lstOption = null;

		MenuTypeBean menuTypeBean;
		ArrayList<MenuTypeBean> lstMenuTypeBean = new ArrayList<MenuTypeBean>();
		IndexPageBean clsIndexPageBean = new IndexPageBean();

		String optionName = "idx_menu_lines";

		int iStartPage = 1;
		int iEndPage = 1;
		int iPageNum = 1;

		boolean bIsNewMenuType = false;
		int iMaxLineOnePage = 0;
		int iLineNum = 0;

		int iMaxMenuOneLine = 4;
		int iMenuNum = 0;
		
		try {
			//get the menu from redis
			lstMenu = menuDaoRds.findAll();
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getIndexPageBean() menuDaoRds.findAll() catch exception: " + e.getMessage());
		}
		
		try {
			//get the menutype from redis
			lstMenuType = menuTypeDaoRds.findAll();
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getIndexPageBean() menuTypeDaoRds.findAll() catch exception: " + e.getMessage());
		}
		
		try {
			//get the idx_menu_lines from redis
			lstOption = optionDaoRds.findByName(optionName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getIndexPageBean() optionDaoRds.findByName() catch exception: " + e.getMessage());
		}
		iMaxLineOnePage = Integer.valueOf(lstOption.get(0).getValue());

		//iterator the Menu and MenuType
		if (lstMenu != null && lstMenu.size() != 0 &&
			lstMenuType != null && lstMenuType.size() != 0) {
			for (MenuType menuType : lstMenuType) {
				menuTypeBean = null;
				iMenuNum = 0;
				bIsNewMenuType = false;
				
				for (Menu menu: lstMenu) {
					if (menuType.getName().equals(menu.getType().getName())) {
						iMenuNum++;

						if (!bIsNewMenuType) {
							iLineNum++;
							bIsNewMenuType = true;
						}
						if (iMenuNum > iMaxMenuOneLine) {
							iMenuNum = 0;
							iLineNum++;
						}
						if (iLineNum > iMaxLineOnePage) {
							iLineNum = 1;
							iPageNum++;
						}

						if (iPageNum == iCurrPage) {
							if (menuTypeBean == null) {
								menuTypeBean = new MenuTypeBean();
	
								ArrayList<MenuBean> lst = new ArrayList<MenuBean>();
								menuTypeBean.setLst(lst);
								menuTypeBean.setName(menuType.getName());
							}
	
							MenuBean menuBean = new MenuBean();
							menuBean.setName(menu.getName());
							menuBean.setPrice(String.valueOf(menu.getPrice()));
							menuBean.setAddr(menu.getPicture());
							menuBean.setAmount("");
							//if user had logined, and the amount into this menu
							if (userName != null) {
								Shopping shopping = getShoppingByName(userName, menu.getName());
								if (shopping != null) {
									int amount = shopping.getAmount();
									if (amount > 0) {
										menuBean.setAmount(String.valueOf(amount));
									}
								}
							}
							
							menuTypeBean.getLst().add(menuBean);
						}
					}
				}
				
				if (menuTypeBean != null) {
					lstMenuTypeBean.add(menuTypeBean);
				}
			}
			
			//calc the startpage and endpage
			if (iPageNum <= defaultMaxPagination) {
				iStartPage = 1;
				iEndPage = iPageNum;
			}else {
				if (iCurrPage > defaultMaxPagination/2) {
					iStartPage = iCurrPage - defaultMaxPagination/2;
				}else {
					iStartPage = 1;
				}

				if (iPageNum >= (iCurrPage + defaultMaxPagination/2)) {
					iEndPage = iCurrPage + defaultMaxPagination/2;
				}else {
					iEndPage = iPageNum;
				}
			}
			clsIndexPageBean.setStartPage(iStartPage);
			clsIndexPageBean.setEndPage(iEndPage);
			clsIndexPageBean.setMaxPage(iPageNum);

			clsIndexPageBean.setLst(lstMenuTypeBean);
			
			return clsIndexPageBean;
		}
		
		return null;
	}
	
	@Override
	public int changeShoppingAmount(String userName, 
								String menuName, 
								String menuPrice,
								boolean isInc) {
		int amount = 0;
		
		if (userName == null || menuName == null || 
			userName.length() == 0 || menuName.length() == 0) {
			System.out.println("DaoServiceImpl.changeShoppingAmount() input param is null");
			return WebConstant.INVALID_VALUE;
		}
		
		Shopping shopping = getShoppingByName(userName, menuName);
		if (shopping != null) {
			//increase the number of Shopping already exist
			amount = shopping.getAmount();
			if (isInc) {
				amount++;
			}else {
				if(amount > 0) {
					amount--;
				}
			}
			shopping.setAmount(amount);
	
			try {
				//save the shopping into hibernate
				shoppingDaoHbm.update(shopping);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.changeShoppingAmount() shoppingDaoHbm.update() catch exception: " + e.getMessage());
				return WebConstant.INVALID_VALUE;
			}
		}else {
			//create a new Shopping and insert it
			shopping = new Shopping();
			
			shopping.setId(0);
			shopping.setUserName(userName);
			shopping.setMenuName(menuName);
			shopping.setMenuPrice(Integer.valueOf(menuPrice));
			amount = 1;
			shopping.setAmount(amount);
			shopping.setRemark("");
	
			try {
				//save the shopping into hibernate
				shoppingDaoHbm.save(shopping);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.changeShoppingAmount() shoppingDaoHbm.save() catch exception: " + e.getMessage());
				return WebConstant.INVALID_VALUE;
			}
		}
		
		return amount;
	}
	
	private Shopping getShoppingByName(String userName, String menuName) {
		List<Shopping> lstShopping = null;
		
		try {
			//get the shopping from hibernate
			lstShopping = shoppingDaoHbm.findByName(userName, menuName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getShoppingByName() shoppingDaoHbm.findByName() catch exception: " + e.getMessage());
		}
		
		if (lstShopping == null || lstShopping.size() == 0) {
			return null;
		}
		
		return lstShopping.get(0);
	}
	
	@Override
	public void deleteShoppingAmount(String userName, 
								String menuName) {
		if (userName == null || menuName == null || 
			userName.length() == 0 || menuName.length() == 0) {
			System.out.println("DaoServiceImpl.deleteShoppingAmount() input param is null");
			return;
		}
		
		Shopping shopping = getShoppingByName(userName, menuName);
		if (shopping != null) {
			try {
				//delete the shopping into hibernate
				shoppingDaoHbm.delete(shopping);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.deleteShoppingAmount() shoppingDaoHbm.delete() catch exception: " + e.getMessage());
				return;
			}
		}
		
		return;
	}

	@Override
	public void changeShoppingRemark(String userName, 
								String menuName, 
								String remark) {
		if (userName == null || menuName == null || 
			userName.length() == 0 || menuName.length() == 0) {
			System.out.println("DaoServiceImpl.changeShoppingRemark() input param is null");
			return;
		}
		
		Shopping shopping = getShoppingByName(userName, menuName);
		if (shopping != null) {
			shopping.setRemark(remark);
	
			try {
				//save the shopping into hibernate
				shoppingDaoHbm.update(shopping);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.changeShoppingRemark() shoppingDaoHbm.update() catch exception: " + e.getMessage());
				return;
			}
		}
	}
	
	@Override
	public void changeUserAddress(String userName, 
								String address) {
		if (userName == null || userName.length() == 0) {
			System.out.println("DaoServiceImpl.changeUserAddress() input param is null");
			return;
		}
		
		User user = getUserByName(userName);
		if (user != null) {
			user.setAddr(address);
	
			try {
				//save the user into hibernate
				userDaoHbm.update(user);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.changeUserAddress() userDaoHbm.update() catch exception: " + e.getMessage());
				return;
			}
		}
	}

	@Override
	public String getUserAddrByName(String userName) {
		List<User> lstUser = null;
		
		try {
			//get the user from hibernate
			lstUser = userDaoHbm.findByName(userName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getUserAddrByName() userDaoHbm.findByName() catch exception: " + e.getMessage());
		}
		
		if (lstUser == null || lstUser.size() == 0) {
			return null;
		}
		
		return lstUser.get(0).getAddr();
	}
	
	@Override
	public ShoppingPageBean getShoppingPageBean(int iCurrPage, String userName) {
		List<Shopping> lstShopping = null;
		List<Option> lstOption = null;

		ArrayList<Shopping> lstShoppingBean = new ArrayList<Shopping>();
		ShoppingPageBean clsShoppingPageBean = new ShoppingPageBean();

		String optionName = "tbl_page_lines";

		int iStartPage = 1;
		int iEndPage = 1;
		int iPageNum = 1;

		int iMaxLineOnePage = 0;
		int iLineNum = 0;
		
		try {
			//get the shopping from hibernate
			lstShopping = shoppingDaoHbm.findByUserName(userName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getShoppingPageBean() shoppingDaoHbm.findByUserName() catch exception: " + e.getMessage());
		}
		
		try {
			//get the tbl_page_lines from redis
			lstOption = optionDaoRds.findByName(optionName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getShoppingPageBean() optionDaoRds.findByName() catch exception: " + e.getMessage());
		}
		iMaxLineOnePage = Integer.valueOf(lstOption.get(0).getValue());

		//iterator the Shopping
		for (Shopping shopping : lstShopping) {
			iLineNum++;
			if (iLineNum > iMaxLineOnePage) {
				iLineNum = 1;
				iPageNum++;
			}
			
			if (iPageNum == iCurrPage) {
				Shopping shoppingBean = new Shopping();
				shoppingBean.setId(shopping.getId());
				shoppingBean.setUserName(shopping.getUserName());
				shoppingBean.setMenuName(shopping.getMenuName());
				shoppingBean.setMenuPrice(shopping.getMenuPrice());
				shoppingBean.setAmount(shopping.getAmount());
				shoppingBean.setRemark(shopping.getRemark());
				
				lstShoppingBean.add(shoppingBean);
			}
		}
		clsShoppingPageBean.setLst(lstShoppingBean);

		// calc the startpage and endpage
		if (iPageNum <= defaultMaxPagination) {
			iStartPage = 1;
			iEndPage = iPageNum;
		} else {
			if (iCurrPage > defaultMaxPagination / 2) {
				iStartPage = iCurrPage - defaultMaxPagination / 2;
			} else {
				iStartPage = 1;
			}

			if (iPageNum >= (iCurrPage + defaultMaxPagination / 2)) {
				iEndPage = iCurrPage + defaultMaxPagination / 2;
			} else {
				iEndPage = iPageNum;
			}
		}
		clsShoppingPageBean.setStartPage(iStartPage);
		clsShoppingPageBean.setEndPage(iEndPage);
		clsShoppingPageBean.setMaxPage(iPageNum);

		return clsShoppingPageBean;
	}

	public MenuDao getMenuDaoHbm() {
		return menuDaoHbm;
	}

	public void setMenuDaoHbm(MenuDao menuDaoHbm) {
		this.menuDaoHbm = menuDaoHbm;
	}

	public MenuTypeDao getMenuTypeDaoHbm() {
		return menuTypeDaoHbm;
	}

	public void setMenuTypeDaoHbm(MenuTypeDao menuTypeDaoHbm) {
		this.menuTypeDaoHbm = menuTypeDaoHbm;
	}

	public UserDao getUserDaoHbm() {
		return userDaoHbm;
	}

	public void setUserDaoHbm(UserDao userDaoHbm) {
		this.userDaoHbm = userDaoHbm;
	}

	public OptionDao getOptionDaoHbm() {
		return optionDaoHbm;
	}

	public void setOptionDaoHbm(OptionDao optionDaoHbm) {
		this.optionDaoHbm = optionDaoHbm;
	}
	
	public ShoppingDao getShoppingDaoHbm() {
		return shoppingDaoHbm;
	}

	public void setShoppingDaoHbm(ShoppingDao shoppingDaoHbm) {
		this.shoppingDaoHbm = shoppingDaoHbm;
	}

	public MenuDao getMenuDaoRds() {
		return menuDaoRds;
	}

	public void setMenuDaoRds(MenuDao menuDaoRds) {
		this.menuDaoRds = menuDaoRds;
	}

	public MenuTypeDao getMenuTypeDaoRds() {
		return menuTypeDaoRds;
	}

	public void setMenuTypeDaoRds(MenuTypeDao menuTypeDaoRds) {
		this.menuTypeDaoRds = menuTypeDaoRds;
	}

	public OptionDao getOptionDaoRds() {
		return optionDaoRds;
	}

	public void setOptionDaoRds(OptionDao optionDaoRds) {
		this.optionDaoRds = optionDaoRds;
	}

	public void init() {
		//create the queue
		q = new LinkedBlockingQueue<OptInfo>();
		
		//create the thread
		Consumer worker = new Consumer();
		thd = new Thread(worker, "Consumer");
		thd.start();
	}
	
	public void destory() {
		//close the thread
		thd.interrupt();

		OptInfo oi = new OptInfo();
		oi.setOpt(OPT.INVALID);
		try {
			q.put(oi);
		}catch (InterruptedException e) {
			System.out.println("DaoServiceImpl.destory() q.put() catch exception: " + e.getMessage());
		}
	}
	
	private class OptInfo {
		private OPT opt;

		public OPT getOpt() {
			return opt;
		}
		public void setOpt(OPT opt) {
			this.opt = opt;
		}
	}

	private class Consumer implements Runnable {
		@Override
		public void run() {
			OptInfo oi;
			
			while(!Thread.interrupted()) {
				try {
					oi = q.take();
				}catch (InterruptedException e) {
					System.out.println("DaoServiceImpl.Consumer.run() q.take() catch exception: " + e.getMessage());
					continue;
				}
				
				if (oi.getOpt() == OPT.INVALID) {
					continue;
				}/*else if (oi.getOpt() == OPT.SAVE_USER) {
					newUser = oi.getNewUser();

					try {
						//save the user into hibernate
						userDaoHbm.save(newUser);
					}catch (Exception e) {
						System.out.println("DaoServiceImpl.Consumer.run() userDaoHbm.save() catch exception: " + e.getMessage());
						try {
							//if fail, need restore the redis
							userDaoRds.delete(newUser);
						}catch (Exception e2) {
							System.out.println("DaoServiceImpl.Consumer.run() userDaoRds.delete() catch exception: " + e.getMessage());
							//redis and mysql will be inconsistency in here
						}
					}
				}
				else if (oi.getOpt() == OPT.UPDATE) {
					newUser = oi.getNewUser();
					oldUser = oi.getOldUser();
					
					try {
						//update into hibernate
						userDaoHbm.update(newUser);
					}catch (Exception e) {
						try {
							//if fail, need restore the redis
							userDaoRds.update(oldUser);
						}catch (Exception e2) {
							continue;
						}
					}
				}else if (oi.getOpt() == OPT.DELETE) {
					oldUser = oi.getOldUser();

					try {
						//delete from hibernate
						userDaoHbm.delete(oldUser);
					}catch (Exception e) {
						try {
							//if fail, need restore the redis
							userDaoRds.save(oldUser);
						}catch (Exception e2) {
							continue;
						}
					}
				}*/
			}
		}
	}
}
