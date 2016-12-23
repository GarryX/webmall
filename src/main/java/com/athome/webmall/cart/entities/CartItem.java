package com.athome.webmall.cart.entities;

import com.athome.webmall.product.entities.Product;

/**
 * 购物项实体类
 */
public class CartItem {
	private Product product; // 购物项中的商品对象
	private Integer quantity; // 购物项的数量
	@SuppressWarnings("unused")
	private Double totalSum; // 购物项的总金额

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
