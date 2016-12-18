package com.athome.webmall.order.entities;

import com.athome.webmall.product.entities.Product;

/**
 * 订单项实体类
 */
public class OrderItem {
	private Integer oiId;
	private Integer quantity;
	private Double totalSum;
	private Product product;
	private Order order;

	public Integer getOiId() {
		return oiId;
	}

	public void setOiId(Integer oiId) {
		this.oiId = oiId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
