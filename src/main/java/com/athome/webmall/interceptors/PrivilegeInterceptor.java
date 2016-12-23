package com.athome.webmall.interceptors;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athome.webmall.admin.entities.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PrivilegeInterceptor.class);
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(adminUser == null){
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("����δ��½��û��Ȩ�޷��ʸ�ҳ�棬���½����ʣ�");
			log.info("===> User no loged-in or not a admin user!");
			return "noPrivilege";
		}
		return invocation.invoke();
	}

}
