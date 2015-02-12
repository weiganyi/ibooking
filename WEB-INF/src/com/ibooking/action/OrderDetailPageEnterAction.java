package com.ibooking.action;

import com.ibooking.action.base.*;

public class OrderDetailPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderId;

	@Override
	public String execute() {
		fillTitle();

		return fillOrderDetailPage(orderId);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
