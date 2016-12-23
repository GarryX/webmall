package com.athome.webmall.admin.services;

import com.athome.webmall.admin.dao.AdminDao;
import com.athome.webmall.admin.entities.AdminUser;

/**
 * 后台登陆业务层
 */
public class AdminService {
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return adminDao.login(adminUser);
	}
}
