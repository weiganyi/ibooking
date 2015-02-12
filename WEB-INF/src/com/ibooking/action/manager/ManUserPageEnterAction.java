package com.ibooking.action.manager;

import com.ibooking.action.base.*;

public class ManUserPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		fillTitle();
		
		return fillManUserPage();
	}
}
