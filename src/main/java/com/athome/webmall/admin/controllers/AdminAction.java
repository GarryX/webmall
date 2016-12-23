package com.athome.webmall.admin.controllers;

import org.apache.struts2.ServletActionContext;

import com.athome.webmall.admin.entities.AdminUser;
import com.athome.webmall.admin.services.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨��½controller
 */
public class AdminAction extends ActionSupport implements ModelDriven<AdminUser> {
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private AdminUser adminUser = new AdminUser();
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//����Ա��½
	public String login(){
		AdminUser user = adminService.login(adminUser);
		if(user == null){
			this.addActionError("�û������������");
			return "index";
		}
		ServletActionContext.getRequest().getSession().setAttribute("admin", user);
		return "home";
	}
}
