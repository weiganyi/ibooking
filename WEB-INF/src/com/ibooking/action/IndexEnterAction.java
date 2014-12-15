package com.ibooking.action;

import java.util.ArrayList;

import com.ibooking.action.base.*;
import com.ibooking.vo.MenuTypeBean;

public class IndexEnterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<MenuTypeBean> lstMenuTypeBean;

	@Override
	public String execute() {
		lstMenuTypeBean = daoService.getAllMenuTypeBean();
		
		if (lstMenuTypeBean != null && lstMenuTypeBean.size() != 0) {
			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public ArrayList<MenuTypeBean> getLstMenuTypeBean() {
		return lstMenuTypeBean;
	}

	public void setLstMenuTypeBean(ArrayList<MenuTypeBean> lstMenuTypeBean) {
		this.lstMenuTypeBean = lstMenuTypeBean;
	}
}
