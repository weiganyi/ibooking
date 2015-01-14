package com.ibooking.action;


import com.ibooking.action.base.*;

public class IndexPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		getTitle();
		return getIndexMenu();
	}
}
