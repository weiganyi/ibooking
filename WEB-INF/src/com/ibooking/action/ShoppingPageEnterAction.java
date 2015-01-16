package com.ibooking.action;


import com.ibooking.action.base.*;

public class ShoppingPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		fillTitle();
		
		return fillShoppingPage();
	}
}
