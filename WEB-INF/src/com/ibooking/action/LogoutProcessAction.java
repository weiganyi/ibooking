package com.ibooking.action;

import com.ibooking.action.base.*;
import com.ibooking.util.*;

import com.opensymphony.xwork2.ActionContext;

public class LogoutProcessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);
		
		if (!userName.isEmpty()) {
			//clear the user info
			ctx.getSession().put(WebConstant.LOGIN_USER, null);
			ctx.getSession().put(WebConstant.LOGIN_AUTH, null);
			ctx.getSession().clear();

			procTitle();
			return procIndex();
		}else {
			return RET_FAIL;
		}
	}
}
