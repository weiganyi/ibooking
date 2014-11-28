package com.ibooking.service;

import com.ibooking.po.*;

public interface DaoService {
	User getUserByName(String userName);
	
	boolean insertUser(String userName, 
					String userPasswd, 
					String userAuth, 
					String userTel, 
					String userAddr);
}
