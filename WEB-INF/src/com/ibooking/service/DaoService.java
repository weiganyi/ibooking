package com.ibooking.service;

import java.util.ArrayList;

import com.ibooking.vo.MenuTypeBean;

public interface DaoService {
	boolean validatePasswd(String userName, String userPasswd);

	String getUserAuthByName(String userName);
	
	boolean insertUser(String userName, 
					String userPasswd, 
					String userAuth, 
					String userTel, 
					String userAddr);
	
	ArrayList<MenuTypeBean> getAllMenuTypeBean();
}
