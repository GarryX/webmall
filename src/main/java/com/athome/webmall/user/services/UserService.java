package com.athome.webmall.user.services;

import com.athome.webmall.user.dao.UserDao;
import com.athome.webmall.user.entities.User;
import com.athome.webmall.utils.MailUtil;
import com.athome.webmall.utils.UUIDUtil;

public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByUserName(String userName) {
		return userDao.getUser(userName);
	}

	public void save(User user) {
		user.setState(0);//0����δ�����û���1�����Ѽ����û�
		String code = UUIDUtil.getUUID() + UUIDUtil.getUUID();
		user.setCode(code);
		userDao.save(user);
		MailUtil.sendActivatingMail(user.getEmail(), code);//���ͼ����ʼ�
	}

	public User getUserByCode(String code) {
		return userDao.getUserByCode(code);
	}

	public void update(User existUser) {
		userDao.update(existUser);
	}

	public User login(User user) {
		return userDao.login(user);
	}
}
