package com.ibooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.ibooking.dao.*;
import com.ibooking.dao.impl.*;
import com.ibooking.po.*;
import com.ibooking.service.*;
import com.ibooking.vo.*;

public class DaoServiceImpl implements DaoService {
	private MenuDao menuDaoHbm;
	private MenuTypeDao menuTypeDaoHbm;
	private UserDao userDaoHbm;
	private OptionDao optionDaoHbm;
	
	private MenuDao menuDaoRds;
	private MenuTypeDao menuTypeDaoRds;
	private UserDao userDaoRds;
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
			//get the user from redis
			lstUser = userDaoRds.findByName(userName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getUserByName() userDaoRds.findByName() catch exception: " + e.getMessage());
		}
		
		if (lstUser == null || lstUser.size() == 0) {
			try {
				//get the user from hibernate
				lstUser = userDaoHbm.findByName(userName);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.getUserByName() userDaoHbm.findByName() catch exception: " + e.getMessage());
				return null;
			}
			
			if (lstUser == null || lstUser.size() == 0) {
				return null;
			}
			
			try {
				//save the user into redis
				userDaoRds.save(lstUser.get(0));
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.getUserByName() userDaoRds.save() catch exception: " + e.getMessage());
			}
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
		user.setId(((UserDaoRedis)userDaoRds).getNextId());

		try {
			//save the user into redis
			userDaoRds.save(user);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.insertUser() userDaoRds.save() catch exception: " + e.getMessage());
			return false;
		}
		
		OptInfo oi = new OptInfo();
		oi.setOpt(OPT.SAVE_USER);
		oi.setNewUser(user);
		try {
			//put the user into queue to save into hibernate
			q.put(oi);
		}catch (InterruptedException e) {
			System.out.println("DaoServiceImpl.insertUser() q.put() catch exception: " + e.getMessage());
			try {
				//if fail, need restore the redis
				userDaoRds.delete(user);
			}catch (Exception e2) {
				System.out.println("DaoServiceImpl.insertUser() userDaoRds.delete() catch exception: " + e.getMessage());
				//redis and mysql will be inconsistency in here
			}
			return false;
		}
		
		return true;
	}

	@Override
	public IndexMenuBean getIndexMenuBean(int iCurrPage) {
		List<Menu> lstMenu = null;
		List<MenuType> lstMenuType = null;
		List<Option> lstOption = null;

		MenuTypeBean menuTypeBean;
		ArrayList<MenuTypeBean> lstMenuTypeBean = new ArrayList<MenuTypeBean>();
		IndexMenuBean clsIndexMenuBean = new IndexMenuBean();

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
			System.out.println("DaoServiceImpl.getIndexMenuBean() menuDaoRds.findAll() catch exception: " + e.getMessage());
		}
		
		try {
			//get the menutype from redis
			lstMenuType = menuTypeDaoRds.findAll();
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getIndexMenuBean() menuTypeDaoRds.findAll() catch exception: " + e.getMessage());
		}
		
		try {
			//get the idx_menu_lines from redis
			lstOption = optionDaoRds.findByName(optionName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getIndexMenuBean() optionDaoRds.findByName() catch exception: " + e.getMessage());
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
			clsIndexMenuBean.setStartPage(iStartPage);
			clsIndexMenuBean.setEndPage(iEndPage);
			clsIndexMenuBean.setMaxPage(iPageNum);

			clsIndexMenuBean.setLst(lstMenuTypeBean);
			
			return clsIndexMenuBean;
		}
		
		return null;
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

	public UserDao getUserDaoRds() {
		return userDaoRds;
	}

	public void setUserDaoRds(UserDao userDaoRds) {
		this.userDaoRds = userDaoRds;
	}

	public OptionDao getOptionDaoRds() {
		return optionDaoRds;
	}

	public void setOptionDaoRds(OptionDao optionDaoRds) {
		this.optionDaoRds = optionDaoRds;
	}

	private class OptInfo {
		private OPT opt;
		private User oldUser;
		private User newUser;

		public OPT getOpt() {
			return opt;
		}
		public void setOpt(OPT opt) {
			this.opt = opt;
		}
		public User getOldUser() {
			return oldUser;
		}
		public void setOldUser(User oldUser) {
			this.oldUser = oldUser;
		}
		public User getNewUser() {
			return newUser;
		}
		public void setNewUser(User newUser) {
			this.newUser = newUser;
		}
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
	
	private class Consumer implements Runnable {
		@Override
		public void run() {
			OptInfo oi;
			User oldUser;
			User newUser;
			
			while(!Thread.interrupted()) {
				try {
					oi = q.take();
				}catch (InterruptedException e) {
					System.out.println("DaoServiceImpl.Consumer.run() q.take() catch exception: " + e.getMessage());
					continue;
				}
				
				if (oi.getOpt() == OPT.INVALID) {
					continue;
				}else if (oi.getOpt() == OPT.SAVE_USER) {
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
				}/*
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
