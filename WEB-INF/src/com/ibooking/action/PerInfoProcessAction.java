package com.ibooking.action;

import com.ibooking.action.base.*;
import com.ibooking.util.WebConstant;
import com.opensymphony.xwork2.ActionContext;

public class PerInfoProcessAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userOldPasswd;
	private String userNewName;
	private String userNewPasswd;
	private String userNewTel;
	private String userNewAddr;

	@Override
	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		String userOldName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);
		String userOldAuth = (String)ctx.getSession().get(WebConstant.LOGIN_AUTH);

		if (daoService.validatePasswd(userOldName, userOldPasswd)) {
			boolean ret = daoService.updateUserByName(userOldName, userNewName, userNewPasswd, userOldAuth, 
					userNewTel, userNewAddr);
			if (ret) {
				fillTitle();
				return fillIndexPage();
			} else {
				failReason = getText("perinfoFailure2");
				return RET_FAIL;
			}
		}else {
			failReason = getText("perinfoFailure");
			return RET_FAIL;
		}
	}

	public String getUserOldPasswd() {
		return userOldPasswd;
	}

	public void setUserOldPasswd(String userOldPasswd) {
		this.userOldPasswd = userOldPasswd;
	}

	public String getUserNewName() {
		return userNewName;
	}

	public void setUserNewName(String userNewName) {
		this.userNewName = userNewName;
	}

	public String getUserNewPasswd() {
		return userNewPasswd;
	}

	public void setUserNewPasswd(String userNewPasswd) {
		this.userNewPasswd = userNewPasswd;
	}

	public String getUserNewTel() {
		return userNewTel;
	}

	public void setUserNewTel(String userNewTel) {
		this.userNewTel = userNewTel;
	}

	public String getUserNewAddr() {
		return userNewAddr;
	}

	public void setUserNewAddr(String userNewAddr) {
		this.userNewAddr = userNewAddr;
	}
}
