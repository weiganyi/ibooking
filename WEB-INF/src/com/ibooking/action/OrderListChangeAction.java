package com.ibooking.action;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class OrderListChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private String orderId;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("orderDel")){
			daoService.deleteOrderTrans(orderId);
		}
		
		return fillOrderListPage();
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
