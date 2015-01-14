package com.ibooking.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.ibooking.action.base.*;
import com.ibooking.po.Shopping;
import com.ibooking.util.WebConstant;
import com.ibooking.vo.ShoppingPageBean;
import com.opensymphony.xwork2.ActionContext;

public class ShoppingChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private String menuName;
	private String remark;
	private String address;
	
	private ArrayList<Shopping> lstShoppingBean;
	private String strAddress;

	@Override
	public String execute() throws UnsupportedEncodingException {
		ShoppingPageBean clsShoppingPageBean;
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		//analysis and process the opt param
		if (opt.equals("amountInc")){
			daoService.changeShoppingAmount(userName, menuName, "", true);
		}else if (opt.equals("amountDec")){
			daoService.changeShoppingAmount(userName, menuName, "", false);
		}else if (opt.equals("amountDel")){
			daoService.deleteShoppingAmount(userName, menuName);
		}else if (opt.equals("remarkChange")){
			daoService.changeShoppingRemark(userName, menuName, remark);
		}else if (opt.equals("addrChange")){
			daoService.changeUserAddress(userName, address);
		}
		
		//fetch the content of this page
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}

		strAddress = daoService.getUserAddrByName(userName);

		clsShoppingPageBean = daoService.getShoppingPageBean(currPage, userName);
		if (clsShoppingPageBean != null && clsShoppingPageBean.getLst().size() != 0) {
			lstShoppingBean = clsShoppingPageBean.getLst();
			startPage = clsShoppingPageBean.getStartPage();
			endPage = clsShoppingPageBean.getEndPage();
			maxPage = clsShoppingPageBean.getMaxPage();
			destPage = "shoppingPageEnter";

			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
