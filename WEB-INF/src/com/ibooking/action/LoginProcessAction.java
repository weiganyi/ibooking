package com.ibooking.action;

import com.ibooking.action.base.*;
import com.ibooking.po.*;
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
		User user = daoService.getUserByName(userName);
		
		if (user != null && user.getPasswd().endsWith(userPasswd)) {
			String userAuth = user.getAuth();

			//save the user info
			ctx.getSession().put(WebConstant.LOGIN_USER, userName);
			ctx.getSession().put(WebConstant.LOGIN_AUTH, userAuth);

			return RET_SUCC;
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