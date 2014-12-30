package com.ibooking.vo;

import java.util.ArrayList;

public class IndexMenuBean {
	private ArrayList<MenuTypeBean> lst;
	private int startPage;
	private int endPage;
	private int maxPage;

	public ArrayList<MenuTypeBean> getLst() {
		return lst;
	}
	public void setLst(ArrayList<MenuTypeBean> lst) {
		this.lst = lst;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
