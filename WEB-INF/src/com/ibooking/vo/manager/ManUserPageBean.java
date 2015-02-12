package com.ibooking.vo.manager;

import java.util.ArrayList;

import com.ibooking.po.*;

public class ManUserPageBean {
	private ArrayList<User> lst;
	private int startPage;
	private int endPage;
	private int maxPage;

	public ArrayList<User> getLst() {
		return lst;
	}
	public void setLst(ArrayList<User> lst) {
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
