package com.athome.webmall.cart.entities;

import com.athome.webmall.product.entities.Product;

/**
 * ������ʵ����
 */
public class CartItem {
	private Product product; // �������е���Ʒ����
	private Integer quantity; // �����������
	@SuppressWarnings("unused")
	private Double totalSum; // ��������ܽ��

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalSum() {
		return this.product.getShopPrice() * quantity;
	}

}
