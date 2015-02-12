package com.ibooking.action.manager;

import com.ibooking.action.base.*;

public class ManOrderPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		fillTitle();
		
		return fillManOrderPage();
	}
}
