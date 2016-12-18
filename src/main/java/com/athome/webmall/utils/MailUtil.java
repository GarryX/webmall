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
 * ���Է��ͼ����ʼ��Ĺ�����
 */
public class MailUtil {
	public static void sendActivatingMail(String to, String code) {
		Properties props = new Properties();
		// ���÷���������
		//props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.host", "localhost");
		// ��ȡ���Ӷ���
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@webmall.com", "123");
			}
		});
		// �����������
		Message message = new MimeMessage(session);
		try {
			// ���÷�����
			message.setFrom(new InternetAddress("service@webmall.com"));
			// �����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���ñ���
			message.setSubject("����񴻪���ŵĹٷ������ʼ�");
			// ��������
			message.setContent(
					"<h2>񴻪�̳ǹٷ������ʼ��������·���������˻�����</h2>"
							+ " <h4><a href='http://localhost:8080/webmall/user_activate?code=" + code
							+ "'>http://localhost:8080/webmall/user_activate?code=" + code + "</a></h4>",
					"text/html;charset=UTF-8");
			// �����ʼ�
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void sendInternetActivatingMail(String to, String code) {
		Properties props = new Properties();
		// ���÷���������
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		// ��ȡ���Ӷ���
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("x402691569@163.com", "4inlovewithX");
			}
		});
		// �����������
		Message message = new MimeMessage(session);
		try {
			// ���÷�����
			message.setFrom(new InternetAddress("x402691569@163.com"));
			// �����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���ñ���
			message.setSubject("����񴻪���ŵĹٷ������ʼ�");
			// ��������
			message.setContent(
					"<h2>񴻪�̳ǹٷ������ʼ��������·���������˻�����</h2>"
							+ " <h4><a href='http://localhost:8080/webmall/user_activate?code=" + code
							+ "'>http://localhost:8080/webmall/user_activate?code=" + code + "</a></h4>",
					"text/html;charset=UTF-8");
			// �����ʼ�
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
