package com.athome.webmall.category.entities;

import java.util.HashSet;
import java.util.Set;

import com.athome.webmall.product.entities.Product;

/**
 * 二级分类实体类
 */
public class SecondCategory {
	private Integer scId;
	private String scName;
	private FirstCategory firstCategory;
	private Set<Product> products = new HashSet<>();
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}
	
}
