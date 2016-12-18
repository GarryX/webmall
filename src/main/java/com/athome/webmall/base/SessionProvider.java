package com.athome.webmall.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 让其它dao实现类继承以为其提供session实例
 */
public class SessionProvider {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
