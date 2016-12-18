package com.athome.webmall.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 用以发送激活邮件的公具类
 */
public class MailUtil {
	public static void sendActivatingMail(String to, String code) {
		Properties props = new Properties();
		// 设置发件人主机
		//props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.host", "localhost");
		// 获取连接对象
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@webmall.com", "123");
			}
		});
		// 创建邮箱对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("service@webmall.com"));
			// 设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置标题
			message.setSubject("来自翊华集团的官方激活邮件");
			// 设置正文
			message.setContent(
					"<h2>翊华商城官方激活邮件！请点击下方链接完成账户激活</h2>"
							+ " <h4><a href='http://localhost:8080/webmall/user_activate?code=" + code
							+ "'>http://localhost:8080/webmall/user_activate?code=" + code + "</a></h4>",
					"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void sendInternetActivatingMail(String to, String code) {
		Properties props = new Properties();
		// 设置发件人主机
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		// 获取连接对象
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("x402691569@163.com", "4inlovewithX");
			}
		});
		// 创建邮箱对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("x402691569@163.com"));
			// 设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置标题
			message.setSubject("来自翊华集团的官方激活邮件");
			// 设置正文
			message.setContent(
					"<h2>翊华商城官方激活邮件！请点击下方链接完成账户激活</h2>"
							+ " <h4><a href='http://localhost:8080/webmall/user_activate?code=" + code
							+ "'>http://localhost:8080/webmall/user_activate?code=" + code + "</a></h4>",
					"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
			System.out.println("Sending a Internet Mail successfully!");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		sendActivatingMail("garry@webmall.com", "1321212");
		sendInternetActivatingMail("402691569@qq.com", "1321212");
	}
}
