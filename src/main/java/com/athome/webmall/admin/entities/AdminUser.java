package com.athome.webmall.admin.entities;

/**
 * ��̨ϵͳ����Աʵ����
 */
public class AdminUser {
	private Integer auId;
	private String auName;
	private String password;
	public Integer getAuId() {
		return auId;
	}
	public void setAuId(Integer auId) {
		this.auId = auId;
	}
	public String getAuName() {
		return auName;
	}
	public void setAuName(String auName) {
		this.auName = auName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
