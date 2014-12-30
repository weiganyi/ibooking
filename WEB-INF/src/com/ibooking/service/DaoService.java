package com.ibooking.service;

import com.ibooking.vo.*;

public interface DaoService {
	boolean validatePasswd(String userName, String userPasswd);

	String getUserAuthByName(String userName);
	
	boolean insertUser(String userName, 
					String userPasswd, 
					String userAuth, 
					String userTel, 
					String userAddr);
	
	String getTitle();
	IndexMenuBean getIndexMenuBean(int iCurrPage);
}
