package com.ibooking.action;


import java.util.ArrayList;

import com.ibooking.action.base.*;
import com.ibooking.po.OrderDetail;

public class OrderDetailPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderId;
	
	private ArrayList<OrderDetail> lstOrderDetail;

	@Override
	public String execute() {
		fillTitle();

		lstOrderDetail = daoService.getOrderDetail(orderId);
		if (!lstOrderDetail.isEmpty()) {
			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ArrayList<OrderDetail> getLstOrderDetail() {
		return lstOrderDetail;
	}

	public void setLstOrderDetail(ArrayList<OrderDetail> lstOrderDetail) {
		this.lstOrderDetail = lstOrderDetail;
	}
}
