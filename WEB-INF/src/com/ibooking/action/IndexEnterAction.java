package com.ibooking.action;


import com.ibooking.action.base.*;

public class IndexEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		procTitle();
		return procIndex();
	}
}
