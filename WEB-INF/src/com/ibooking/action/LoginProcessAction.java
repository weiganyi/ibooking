package com.ibooking.action;

import com.ibooking.action.base.*;
import com.ibooking.util.*;

import com.opensymphony.xwork2.ActionContext;

public class LoginProcessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String userPasswd;

	@Override
	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		if (daoService.validatePasswd(userName, userPasswd)) {
			String userAuth = daoService.getUserAuthByName(userName);

			//save the user info
			ctx.getSession().put(WebConstant.LOGIN_USER, userName);
			ctx.getSession().put(WebConstant.LOGIN_AUTH, userAuth);

			procTitle();
			return procIndex();
		}else {
			failReason = getText("loginFailure");
			return RET_FAIL;
		}
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
}
