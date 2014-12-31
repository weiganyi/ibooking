package com.ibooking.action.authority;

import com.ibooking.util.WebConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminAuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String RET_LOGIN = "login";
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String userAuth = (String)ctx.getSession().get(WebConstant.LOGIN_AUTH);
		
		if(userAuth != null && userAuth.equals("admin")) {
			return arg0.invoke();
		}else {
			return RET_LOGIN;
		}
	}
}
