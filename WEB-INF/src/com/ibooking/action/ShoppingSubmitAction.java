package com.ibooking.action;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;
import com.ibooking.util.WebConstant;
import com.opensymphony.xwork2.ActionContext;

public class ShoppingSubmitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws UnsupportedEncodingException {
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		if (daoService.submitShoppingTrans(userName)) {
			fillTitle();

			return fillOrderListPage();
		}else {
			failReason = getText("shopSubmitFailure");
			return RET_FAIL;
		}
	}
}
