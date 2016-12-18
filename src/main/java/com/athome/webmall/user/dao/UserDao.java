package com.athome.webmall.user.dao;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.user.entities.User;

public class UserDao extends SessionProvider{
	//��ѯ�û�
	public User getUser(String userName){
		String hql = "FROM User u WHERE u.userName = ?";//ע�⣺hql�ַ����е�ӳ����������ʵ����һ��
		return (User) getSession().createQuery(hql).setString(0, userName).uniqueResult();
	}
	//�û�ע�ᣬ���뵽���ݱ���
	public void save(User user) {
		getSession().save(user);
	}
	public User getUserByCode(String code) {
		String hql = "FROM User u WHERE u.code = ?";
		return (User) getSession().createQuery(hql).setString(0, code).uniqueResult();
	}
	public void update(User existUser) {
		getSession().update(existUser);
	}
	public User login(User user) {
		String hql = "FROM User u WHERE u.userName = ? AND u.password = ? AND u.state = 1";
		return (User) getSession().createQuery(hql).setString(0, user.getUserName())
				           .setString(1, user.getPassword()).uniqueResult();
	}
}
