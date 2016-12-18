package com.athome.webmall.admin.dao;

import com.athome.webmall.admin.entities.AdminUser;
import com.athome.webmall.base.SessionProvider;

/**
 * ºóÌ¨µÇÂ½³Ö¾Ã²ã
 */
public class AdminDao extends SessionProvider {

	public AdminUser login(AdminUser adminUser) {
		String hql = "FROM AdminUser a WHERE a.auName = ? AND a.password = ?";
		return (AdminUser) getSession().createQuery(hql).setString(0, adminUser.getAuName())
									   .setString(1, adminUser.getPassword()).uniqueResult();
	}

}
