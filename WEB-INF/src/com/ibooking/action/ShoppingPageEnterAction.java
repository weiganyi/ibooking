package com.ibooking.action;


import java.util.ArrayList;

import com.ibooking.action.base.*;
import com.ibooking.po.*;
import com.ibooking.util.WebConstant;
import com.ibooking.vo.*;
import com.opensymphony.xwork2.ActionContext;

public class ShoppingPageEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Shopping> lstShoppingBean;
	private String strAddress;
	
	@Override
	public String execute() {
		ShoppingPageBean clsShoppingPageBean;

		getTitle();
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		strAddress = daoService.getUserAddrByName(userName);

		clsShoppingPageBean = daoService.getShoppingPageBean(currPage, userName);
		if (clsShoppingPageBean != null && clsShoppingPageBean.getLst().size() != 0) {
			lstShoppingBean = clsShoppingPageBean.getLst();
			startPage = clsShoppingPageBean.getStartPage();
			endPage = clsShoppingPageBean.getEndPage();
			maxPage = clsShoppingPageBean.getMaxPage();

			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public ArrayList<Shopping> getLstShoppingBean() {
		return lstShoppingBean;
	}

	public void setLstShoppingBean(ArrayList<Shopping> lstShoppingBean) {
		this.lstShoppingBean = lstShoppingBean;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
}
