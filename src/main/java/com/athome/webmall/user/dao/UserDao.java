package com.athome.webmall.user.dao;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.user.entities.User;

public class UserDao extends SessionProvider{
	//查询用户
	public User getUser(String userName){
		String hql = "FROM User u WHERE u.userName = ?";//注意：hql字符串中的映射属性需与实体类一致
		return (User) getSession().createQuery(hql).setString(0, userName).uniqueResult();
	}
	//用户注册，插入到数据表中
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
