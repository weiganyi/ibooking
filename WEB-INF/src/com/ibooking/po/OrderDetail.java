package com.ibooking.po;

public class OrderDetail {
	private Integer id;
	private Integer orderId;
	private String menuName;
	private Integer menuPrice;
	private Integer amount;
	private String remark;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
