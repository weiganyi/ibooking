package com.ibooking.service;

import com.ibooking.vo.*;

public interface DaoService {
	//login.jsp
	boolean validatePasswd(String userName, String userPasswd);
	String getUserAuthByName(String userName);
	
	//reg.jsp
	boolean insertUser(String userName, 
					String userPasswd, 
					String userAuth, 
					String userTel, 
					String userAddr);
	
	//index.jsp
	String getTitle();
	IndexPageBean getIndexPageBean(int iCurrPage, String userName);
	int changeShoppingAmount(String userName, 
							String menuName, 
							String menuPrice,
							boolean isInc);
	
	//shopping.jsp
	void deleteShoppingAmount(String userName, 
						String menuName);
	void changeShoppingRemark(String userName, 
							String menuName, 
							String remark);
	void changeUserAddress(String userName, 
						String address);
	String getUserAddrByName(String userName);
	ShoppingPageBean getShoppingPageBean(int iCurrPage, String userName);
	boolean submitShoppingTrans(String userName);

	//
}
