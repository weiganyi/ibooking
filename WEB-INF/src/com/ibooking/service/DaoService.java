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
	IndexPageBean getIndexPageBean(int iCurrPage, String userName);
	
	int changeShoppingAmount(String userName, 
							String menuName, 
							String menuPrice,
							boolean isInc);
	
	void deleteShoppingAmount(String userName, 
						String menuName);
	void changeShoppingRemark(String userName, 
							String menuName, 
							String remark);
	void changeUserAddress(String userName, 
						String address);
	String getUserAddrByName(String userName);
	ShoppingPageBean getShoppingPageBean(int iCurrPage, String userName);
}
