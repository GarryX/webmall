package com.athome.webmall.category.entities;

import java.util.Set;

/**
 * 一级分类的实体类
 */
public class FirstCategory {
	private Integer cId;
	private String cName;
	private Set<SecondCategory> secondCategorys;

	public Integer getCId() {
		return cId;
	}

	public void setCId(Integer cId) {
		this.cId = cId;
	}

	public String getCName() {
		return cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	public Set<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(Set<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}

}
