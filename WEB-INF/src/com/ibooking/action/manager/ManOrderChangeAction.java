package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class ManOrderChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private int id;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("orderUnAccept")){
			daoService.updateOrderAccept(id, false);
		}else if (opt.equals("orderAccept")){
			daoService.updateOrderAccept(id, true);
		}else if (opt.equals("orderDel")){
			daoService.deleteOrder(id);
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
