package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class ManPicChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private String name;
	private String addr;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("picDel")){
			daoService.deletePic(name, addr);
		}
		
		return fillManPicPage();
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
