package com.ibooking.service.impl;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.ibooking.dao.*;
import com.ibooking.dao.impl.*;
import com.ibooking.po.*;
import com.ibooking.service.*;

public class DaoServiceImpl implements DaoService {
	private MenuDao menuDaoHbm;
	private MenuTypeDao menuTypeDaoHbm;
	private UserDao userDaoHbm;
	
	private UserDao userDaoRds;
	
	enum OPT {INVALID, SAVE_USER, UPDATE, DELETE};
	private LinkedBlockingQueue<OptInfo> q;
	private Thread thd;

	@Override
	public User getUserByName(String userName) {
		List<User> lstUser = null;
		
		try {
			//get from redis
			lstUser = userDaoRds.findByName(userName);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.getUserByName() userDaoRds.findByName() catch exception: " + e.getMessage());
		}
		
		if (lstUser == null || lstUser.size() == 0) {
			try {
				//get from hibernate
				lstUser = userDaoHbm.findByName(userName);
			}catch (Exception e) {
				System.out.println("DaoServiceImpl.getUserByName() userDaoHbm.findByName() catch exception: " + e.getMessage());
				return null;
			}
			
			if (lstUser == null || lstUser.size() == 0) {
				return null;
			}
			
			try {
				//save into redis
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
		User user = new User();
		
		user.setName(userName);
		user.setPasswd(userPasswd);
		user.setAuth(userAuth);
		user.setTel(userTel);
		user.setAddr(userAddr);
		user.setId(((UserDaoRedis)userDaoRds).getNextId());

		try {
			//save into redis
			userDaoRds.save(user);
		}catch (Exception e) {
			System.out.println("DaoServiceImpl.insertUser() userDaoRds.save() catch exception: " + e.getMessage());
			return false;
		}
		
		OptInfo oi = new OptInfo();
		oi.setOpt(OPT.SAVE_USER);
		oi.setNewUser(user);
		try {
			//put into queue to save into hibernate
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

	public UserDao getUserDaoRds() {
		return userDaoRds;
	}

	public void setUserDaoRds(UserDao userDaoRds) {
		this.userDaoRds = userDaoRds;
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
						//save into hibernate
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
				}else if (oi.getOpt() == OPT.UPDATE) {
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
				}
			}
		}
	}
}
