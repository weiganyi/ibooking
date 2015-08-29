package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class ManMenuTypeChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private int id;
	private String name;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("menuTypeAdd")){
			daoService.insertMenuType(name);
		}else if (opt.equals("menuTypeMod")){
			daoService.updateMenuTypeById(id, name);
		}else if (opt.equals("menuTypeDel")){
			daoService.deleteMenuType(id);
		}
		
		return fillManMenuTypePage();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
