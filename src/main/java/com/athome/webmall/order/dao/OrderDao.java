package com.athome.webmall.order.dao;

import java.util.List;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.order.entities.Order;
import com.athome.webmall.order.entities.OrderItem;

/**
 * OrderÄ£¿é³Ö¾Ã²ã
 */
public class OrderDao extends SessionProvider {

	public void saveOrUpdateOrder(Order order) {
		getSession().saveOrUpdate(order);
	}

	public long getCountByUid(Integer uid) {
		String hql = "SELECT COUNT(*) FROM Order o WHERE o.user.id = ?";
		return (long) getSession().createQuery(hql).setInteger(0, uid).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrdersByUid(Integer uid, int begin, int pageSize) {
		String hql = "FROM Order o WHERE o.user.id = ? ORDER BY o.orderDate DESC";
		return getSession().createQuery(hql).setInteger(0, uid).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	public Order getOrderByOId(Integer oId) {
		return (Order) getSession().get(Order.class, oId);
	}

	public long getCount() {
		String hql = "SELECT COUNT(*) FROM Order";
		return (long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrdersInPage(int begin, int pageSize) {
		String hql = "FROM Order o ORDER BY o.orderDate DESC";
		return getSession().createQuery(hql).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	public List<OrderItem> getOrderItemsByOId(Integer oId) {
		String hql = "FROM OrderItem o WHERE o.order.oId = ?";
		return getSession().createQuery(hql).setInteger(0, oId).list();
	}
	
}
