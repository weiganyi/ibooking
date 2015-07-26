package com.ibooking.vo.manager;

import java.util.ArrayList;

import com.ibooking.po.*;

public class ManMenuPageBean {
	private ArrayList<Menu> lst;
	private ArrayList<MenuType> lst2;
	private ArrayList<ManPicBean> lst3;
	private int startPage;
	private int endPage;
	private int maxPage;

	public ArrayList<Menu> getLst() {
		return lst;
	}
	public void setLst(ArrayList<Menu> lst) {
		this.lst = lst;
	}
	public ArrayList<MenuType> getLst2() {
		return lst2;
	}
	public void setLst2(ArrayList<MenuType> lst2) {
		this.lst2 = lst2;
	}
	public ArrayList<ManPicBean> getLst3() {
		return lst3;
	}
	public void setLst3(ArrayList<ManPicBean> lst3) {
		this.lst3 = lst3;
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
