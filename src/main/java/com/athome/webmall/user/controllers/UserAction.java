package com.athome.webmall.user.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.athome.webmall.user.entities.User;
import com.athome.webmall.user.services.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends ActionSupport implements ModelDriven<User>, Preparable {
	private static final long serialVersionUID = 1L;
	private User user;
	private UserService userService;
	private InputStream inputStream;
	private String verifyCode;

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ��ת��ע��ҳ��
	 */
	public String registPage() {
		return "regist";
	}

	/**
	 * Ajax�����û����Ƿ����
	 * 
	 * @throws IOException
	 */
	public String validateUserName() throws IOException {
		String userName = ServletActionContext.getRequest().getParameter("userName");
		System.out.println(userName);
		User registeredUser = userService.getUserByUserName(userName);
		System.out.println(registeredUser);
		if (registeredUser != null) {
			// �û����Ѵ���
			inputStream = new ByteArrayInputStream("<font color='red'>�û����Ѵ���</font>".getBytes("UTF-8"));
		} else {
			// �û���������
			inputStream = new ByteArrayInputStream("<font color='green'>�û�������</font>".getBytes("UTF-8"));
		}
		return "Ajax_validate";
	}

	/**
	 * �û�ע��
	 */
	public String register() {
		String verifyCodeImgText = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");
		if(!this.verifyCode.equalsIgnoreCase(verifyCodeImgText)){
			this.addActionError("��֤��������������룡");
			return "regist";
		}
		userService.save(user);
		return "message";
	}

	/**
	 * �û�����
	 */
	public String activate() {
		// ���ݼ������ѯ�û�
		String code = user.getCode();
		User existUser = userService.getUserByCode(code);
		if (existUser == null) {// ��Ϊ�գ�����������򴫴�����Ϣ
			this.addActionError("����ʧ�ܣ����������");
			return "message";
		}
		// �����ڣ���ı�state��ֵ��������������Ϊ�գ�Ȼ������û�
		existUser.setState(1);
		existUser.setCode(null);
		userService.update(existUser);
		this.addActionMessage("����ɹ������½��");
		return "message";
	}

	/**
	 * ��ת����½ҳ��
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * �û���½
	 */
	public String login() {
		User loginUser = userService.login(user);
		if (loginUser == null) {// �û�������
			this.addActionError("�û�����������󣬻����û�δ���");
			return "loginPage";
		}
		// �û����ڣ���½�ɹ������û���Ϣ���浽session����,����ת����ҳ
		ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
		return "login";
	}

	/**
	 * �û��˳���½
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();// ����session
		return "logout";
	}

	public void prepareLogin() {
		this.user = new User();
	}

	public void prepareActivate() {
		this.user = new User();
	}

	public void prepareRegister() {
		this.user = new User();
	}

	@Override
	public User getModel() {
		return user;
	}

	@Override
	public void prepare() throws Exception {
	}
}
