package com.athome.webmall.cart.entities;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ��װ�˹��ﳵ���ݵ�ʵ����
 */
public class Cart {
	// Map��keyΪ��Ʒ��id��valueΪ������
	private Map<Integer, CartItem> items = new LinkedHashMap<>();
	// ���ﳵ�е����˵�
	private double totalBill;// ����totalBill����������ΪDouble����totalBill +=
								// item.getTotalSum()���ֿ�ָ���쳣;
								// ԭ��Ӧ����û�г�ʼ��
	// ���ع��ﳵ�е����˵�

	public double getTotalBill() {
		return totalBill;
	}

	// ���ص��м��ϣ������ڹ��ﳵҳ���н��б���
	public Collection<CartItem> getCartItems() {
		return this.items.values();
	}

	// ���ﳵ�Ĺ���
	// 1.��ӹ�������ﳵ��
	public void addToCart(CartItem item) {
		/*
		 * ���жϹ��ﳵ���Ƿ��Ѿ�����Ҫ��ӵĹ���� 1.�����ڣ�ʹ���������ӣ����ﳵ�ܼ����� 2.�������ڣ������һ����������ӹ��ﳵ�ܼ�
		 */
		Integer id = item.getProduct().getId();
		// ����
		if (items.containsKey(id)) {
			// �����ﳵ���д˹������ȡ�˹�����
			CartItem existItem = items.get(id);
			// ��ù��ﳵ�й������ԭ����
			Integer quantity = existItem.getQuantity();
			// ����������
			existItem.setQuantity(quantity + item.getQuantity());
			// ������
		} else {
			items.put(id, item);
		}
		// ���ﳵ���˵�����
		totalBill += item.getTotalSum();
	}

	// 2.�ӹ��ﳵ��ɾ��������
	public void removeFromCart(Integer id) {
		CartItem item = items.remove(id);
		totalBill -= item.getTotalSum();
	}

	// 3.��չ��ﳵ
	public void clearCart() {
		items.clear();
		totalBill = 0.0;
	}
}
