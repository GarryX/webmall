package com.athome.webmall.interceptors;

import org.apache.struts2.ServletActionContext;

import com.athome.webmall.admin.entities.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(adminUser == null){
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还未登陆，没有权限访问该页面，请登陆后访问！");
			return "noPrivilege";
		}
		return invocation.invoke();
	}

}
