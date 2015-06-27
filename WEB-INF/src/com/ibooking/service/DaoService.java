package com.ibooking.service;

import java.io.File;
import java.util.ArrayList;

import com.ibooking.po.OrderDetail;
import com.ibooking.vo.*;
import com.ibooking.vo.manager.*;

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
	void deleteShopping(String userName, 
						String menuName);
	void changeShoppingRemark(String userName, 
							String menuName, 
							String remark);
	void changeUserAddress(String userName, 
						String address);
	String getUserAddrByName(String userName);
	ShoppingPageBean getShoppingPageBean(int iCurrPage, String userName);
	boolean submitShoppingTrans(String userName);

	//orderlist.jsp
	OrderListPageBean getOrderListPageBean(int iCurrPage, String userName);
	void deleteOrderTrans(int orderId);
	
	//orderdetail.jsp
	ArrayList<OrderDetail> getOrderDetailByOrderId(int orderId);
	
	//perinfo.jsp
	boolean updateUserByName(String userOldName, 
			String userNewName, 
			String userNewPasswd, 
			String userNewAuth, 
			String userNewTel, 
			String userNewAddr);
	
	//man_user.jsp
	ManUserPageBean getManUserPageBean(int iCurrPage);
	boolean updateUserById(int userOldId, 
			String userNewName, 
			String userNewPasswd, 
			String userNewAuth, 
			String userNewTel, 
			String userNewAddr);
	void deleteUser(int id);
	
	//man_order.jsp
	ManOrderPageBean getManOrderPageBean(int iCurrPage);
	boolean updateOrderAccept(int id, String adminName, boolean isAccept);
	
	//man_orderdetail.jsp
	boolean insertOrderDetail(int orderId,
							String menu, 					
							int price, 
							int amount, 
							String remark);
	boolean updateOrderDetailById(int id, 
								int orderId,
								String menu, 					
								int price, 
								int amount, 
								String remark);
	void deleteOrderDetail(int id);
	
	//man_pic.jsp
	ManPicPageBean getManPicPageBean(int iCurrPage);
	void deletePic(String name, String addr);
	void uploadPic(String uploadFileName, File upload) throws Exception;
}
