package com.ibooking.action.base;

import java.util.ArrayList;

import com.ibooking.service.*;
import com.ibooking.vo.IndexMenuBean;
import com.ibooking.vo.MenuTypeBean;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String RET_SUCC = "success";
	protected static final String RET_FAIL = "failure";
	
	protected DaoService daoService;

	protected String failReason;

	protected String strTitle;

	private static final Integer defaultMinPageNum = 1;
	private String page;
	private ArrayList<MenuTypeBean> lstMenuTypeBean;
	private int currPage;
	private int startPage;
	private int endPage;
	private int maxPage;

	public String procTitle() {
		strTitle = daoService.getTitle();
		if (strTitle == null || strTitle.isEmpty()) {
			return RET_FAIL;
		}
		
		return RET_SUCC;
	}

	public String procIndex() {
		IndexMenuBean clsIndexMenuBean;

		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		clsIndexMenuBean = daoService.getIndexMenuBean(currPage);
		if (clsIndexMenuBean != null && clsIndexMenuBean.getLst().size() != 0) {
			lstMenuTypeBean = clsIndexMenuBean.getLst();
			startPage = clsIndexMenuBean.getStartPage();
			endPage = clsIndexMenuBean.getEndPage();
			maxPage = clsIndexMenuBean.getMaxPage();

			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public void setDaoService(DaoService service) {
		this.daoService = service;
	}
	
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public ArrayList<MenuTypeBean> getLstMenuTypeBean() {
		return lstMenuTypeBean;
	}

	public void setLstMenuTypeBean(ArrayList<MenuTypeBean> lstMenuTypeBean) {
		this.lstMenuTypeBean = lstMenuTypeBean;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
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
