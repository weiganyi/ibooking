package com.ibooking.action.manager;

import java.io.UnsupportedEncodingException;

import com.ibooking.action.base.*;

public class ManUserChangeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opt;
	private int id;
	private String user;
	private String passwd;
	private String auth;
	private String tel;
	private String addr;

	@Override
	public String execute() throws UnsupportedEncodingException {
		//analysis and process the opt param
		if (opt.equals("userAdd")){
			daoService.insertUser(user, passwd, auth, tel, addr);
		}else if (opt.equals("userMod")){
			daoService.updateUserById(id, user, passwd, auth, tel, addr);
		}else if (opt.equals("userDel")){
			daoService.deleteUser(id);
		}
		
		return fillManUserPage();
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
