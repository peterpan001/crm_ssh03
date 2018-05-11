package com.crm_ssh02.web.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.crm_ssh02.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 用户拦截器，如果用户没有登录就进行拦截
 * @author Peter
 */
public class UserInterceptor extends MethodFilterInterceptor {

	
	private static final long serialVersionUID = -2655033743686994283L;
	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null){
			return "login";
		}
		return invocation.invoke();
	}

}
