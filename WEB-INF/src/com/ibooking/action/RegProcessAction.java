package com.ibooking.action;

import com.ibooking.action.base.*;

public class RegProcessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String userPasswd;
	private String userTel;
	private String userAddr;

	@Override
	public String execute() {
		boolean ret = daoService.insertUser(userName, userPasswd, "customer", userTel, userAddr);
		if (ret) {
			procTitle();
			return procIndex();
		} else {
			failReason = getText("regFailure");
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

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
}
