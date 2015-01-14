package com.ibooking.action.base;

import java.util.ArrayList;

import com.ibooking.service.*;
import com.ibooking.util.WebConstant;
import com.ibooking.vo.IndexPageBean;
import com.ibooking.vo.MenuTypeBean;
import com.opensymphony.xwork2.ActionContext;
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

	protected String page;

	protected ArrayList<MenuTypeBean> lstMenuTypeBean;

	protected static final Integer defaultMinPageNum = 1;
	protected int currPage;
	protected int startPage;
	protected int endPage;
	protected int maxPage;
	protected String destPage;

	public String getTitle() {
		strTitle = daoService.getTitle();
		if (strTitle == null || strTitle.isEmpty()) {
			return RET_FAIL;
		}
		
		return RET_SUCC;
	}

	public String getIndexMenu() {
		IndexPageBean clsIndexPageBean;

		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);
		
		clsIndexPageBean = daoService.getIndexPageBean(currPage, userName);
		if (clsIndexPageBean != null && clsIndexPageBean.getLst().size() != 0) {
			lstMenuTypeBean = clsIndexPageBean.getLst();
			startPage = clsIndexPageBean.getStartPage();
			endPage = clsIndexPageBean.getEndPage();
			maxPage = clsIndexPageBean.getMaxPage();
			destPage = "indexPageEnter";

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

	public String getDestPage() {
		return destPage;
	}

	public void setDestPage(String destPage) {
		this.destPage = destPage;
	}
}
