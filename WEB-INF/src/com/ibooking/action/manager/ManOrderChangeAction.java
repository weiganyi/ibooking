package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;
import com.ibooking.util.WebConstant;
import com.opensymphony.xwork2.ActionContext;

public class ManOrderChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private int id;

	@Override
	public String execute() throws UnsupportedEncodingException {
		ActionContext ctx = ActionContext.getContext();
		String adminName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);
		
		//analysis and process the opt param
		if (opt.equals("orderUnAccept")){
			daoService.updateOrderAccept(id, adminName, false);
		}else if (opt.equals("orderAccept")){
			daoService.updateOrderAccept(id, adminName, true);
		}else if (opt.equals("orderDel")){
			daoService.deleteOrderTrans(id);
		}
		
		return fillManOrderPage();
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
