package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class ManOrderDetailChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private int id;
	private int orderId;
	private String menu;
	private int price;
	private int amount;
	private String remark;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("orderDetailAdd")){
			daoService.insertOrderDetail(orderId, menu, price, amount, remark);
		}else if (opt.equals("orderDetailMod")){
			daoService.updateOrderDetailById(id, orderId, menu, price, amount, remark);
		}else if (opt.equals("orderDetailDel")){
			daoService.deleteOrderDetail(id);
		}
		
		return fillOrderDetailPage(orderId);
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
