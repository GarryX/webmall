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
	 * 跳转到注册页面
	 */
	public String registPage() {
		return "regist";
	}

	/**
	 * Ajax较验用户名是否可用
	 * 
	 * @throws IOException
	 */
	public String validateUserName() throws IOException {
		String userName = ServletActionContext.getRequest().getParameter("userName");
		System.out.println(userName);
		User registeredUser = userService.getUserByUserName(userName);
		System.out.println(registeredUser);
		if (registeredUser != null) {
			// 用户名已存在
			inputStream = new ByteArrayInputStream("<font color='red'>用户名已存在</font>".getBytes("UTF-8"));
		} else {
			// 用户名不存在
			inputStream = new ByteArrayInputStream("<font color='green'>用户名可用</font>".getBytes("UTF-8"));
		}
		return "Ajax_validate";
	}

	/**
	 * 用户注册
	 */
	public String register() {
		String verifyCodeImgText = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");
		if(!this.verifyCode.equalsIgnoreCase(verifyCodeImgText)){
			this.addActionError("验证码错误，请重新输入！");
			return "regist";
		}
		userService.save(user);
		return "message";
	}

	/**
	 * 用户激活
	 */
	public String activate() {
		// 根据激活码查询用户
		String code = user.getCode();
		User existUser = userService.getUserByCode(code);
		if (existUser == null) {// 若为空，激活码错误，则传错误信息
			this.addActionError("激活失败：激活码错误！");
			return "message";
		}
		// 若存在，则改变state的值，并将激活码置为空，然后更新用户
		existUser.setState(1);
		existUser.setCode(null);
		userService.update(existUser);
		this.addActionMessage("激活成功！请登陆！");
		return "message";
	}

	/**
	 * 跳转到登陆页面
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 用户登陆
	 */
	public String login() {
		User loginUser = userService.login(user);
		if (loginUser == null) {// 用户不存在
			this.addActionError("用户名或密码错误，或者用户未激活！");
			return "loginPage";
		}
		// 用户存在，登陆成功，将用户信息保存到session域中,并跳转至首页
		ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
		return "login";
	}

	/**
	 * 用户退出登陆
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();// 销毁session
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
