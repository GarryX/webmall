package com.athome.webmall.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * ������daoʵ����̳���Ϊ���ṩsessionʵ��
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
