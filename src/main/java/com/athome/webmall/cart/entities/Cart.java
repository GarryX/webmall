package com.athome.webmall.cart.entities;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 封装了购物车数据的实体类
 */
public class Cart {
	// Map的key为商品的id，value为购物项
	private Map<Integer, CartItem> items = new LinkedHashMap<>();
	// 购物车中的总账单
	private double totalBill;// 曾因将totalBill的类型设置为Double导致totalBill +=
								// item.getTotalSum()出现空指针异常;
								// 原因应该是没有初始化
	// 返回购物车中的总账单

	public double getTotalBill() {
		return totalBill;
	}

	// 返回单列集合，便于在购物车页面中进行遍历
	public Collection<CartItem> getCartItems() {
		return this.items.values();
	}

	// 购物车的功能
	// 1.添加购物项到购物车中
	public void addToCart(CartItem item) {
		/*
		 * 先判断购物车中是否已经存在要添加的购物荐 1.若存在，使其数量增加，购物车总计增加 2.若不存在，新添加一购物项，并增加购物车总计
		 */
		Integer id = item.getProduct().getId();
		// 存在
		if (items.containsKey(id)) {
			// 若购物车有有此购物项，获取此购物项
			CartItem existItem = items.get(id);
			// 获得购物车中购物项的原数量
			Integer quantity = existItem.getQuantity();
			// 设置新数量
			existItem.setQuantity(quantity + item.getQuantity());
			// 不存在
		} else {
			items.put(id, item);
		}
		// 购物车总账单更新
		totalBill += item.getTotalSum();
	}

	// 2.从购物车中删除购物项
	public void removeFromCart(Integer id) {
		CartItem item = items.remove(id);
		totalBill -= item.getTotalSum();
	}

	// 3.清空购物车
	public void clearCart() {
		items.clear();
		totalBill = 0.0;
	}
}
