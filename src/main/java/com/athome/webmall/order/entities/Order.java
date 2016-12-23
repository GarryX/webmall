package com.athome.webmall.order.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.athome.webmall.user.entities.User;

/**
 * ����ʵ����
 */
public class Order {
	private Integer oId;
	private Double totalBill;
	private Date orderDate;
	private Integer state;
	private String name;
	private String address;
	private String phone;
	private User user;
	private Set<OrderItem> items = new HashSet<>();

	//get,set������ĸ��Сд���������������쳣��һ��Ҫע�ⲻҪ���ô�
	public Integer getOId() {
		return oId;
	}

	public void setOId(Integer oId) {
		this.oId = oId;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [oId=" + oId + ", totalBill=" + totalBill + ", orderDate=" + orderDate + ", state=" + state
				+ ", name=" + name + ", address=" + address + ", phone=" + phone + ", user=" + user + ", items=" + items
				+ "]";
	}

}
