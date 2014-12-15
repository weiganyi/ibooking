package com.ibooking.vo;

import java.util.ArrayList;

public class MenuTypeBean {
	private String name;
	private ArrayList<MenuBean> lst;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MenuBean> getLst() {
		return lst;
	}
	public void setLst(ArrayList<MenuBean> lst) {
		this.lst = lst;
	}
}
