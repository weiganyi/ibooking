package com.ibooking.po;

import java.util.Date;

public class Order {
	private Integer id;
	private String userName;
	private Date time;
	private String adminName;
	private Integer accept;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getAccept() {
		return accept;
	}
	public void setAccept(Integer accept) {
		this.accept = accept;
	}
}
