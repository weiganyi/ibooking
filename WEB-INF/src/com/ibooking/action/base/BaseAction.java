package com.ibooking.action.base;

import com.ibooking.service.*;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String RET_SUCC = "success";
	protected static final String RET_FAIL = "failure";
	
	protected DaoService daoService;

	protected String failReason;

	public void setDaoService(DaoService service) {
		this.daoService = service;
	}
	
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
}
