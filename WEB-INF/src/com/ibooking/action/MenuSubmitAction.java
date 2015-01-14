package com.ibooking.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;
import com.ibooking.util.WebConstant;
import com.opensymphony.xwork2.ActionContext;

public class MenuSubmitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String menuName;
	private String menuPrice;
	
	private InputStream inputStream;

	@Override
	public String execute() throws UnsupportedEncodingException {
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		int amount = daoService.changeShoppingAmount(userName, menuName, menuPrice, true);
		if (amount == WebConstant.INVALID_VALUE) {
			amount = 0;
		}

		String result = String.valueOf(amount);
		inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));

		return RET_SUCC;
	}

	public InputStream getResult() {
		return inputStream;
	}
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}
}
