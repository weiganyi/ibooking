package com.ibooking.action;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;
import com.ibooking.util.WebConstant;
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

	@Override
	public String execute() throws UnsupportedEncodingException {
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
		
		return fillShoppingPage();
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
