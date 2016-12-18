package com.athome.webmall.order.services;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.athome.webmall.order.dao.OrderDao;
import com.athome.webmall.order.entities.Order;
import com.athome.webmall.order.entities.OrderItem;
import com.athome.webmall.product.entities.Page;

/**
 * Order模块业务层
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存或更新订单
	public void saveOrUpdateOrder(Order order) {
		orderDao.saveOrUpdateOrder(order);
	}

	public Page<Order> getPageByUid(Integer pageNo, Integer uid) {
		Page<Order> page = new Page<>(pageNo);
		int pageSize = 5;
		int begin = pageSize * (pageNo - 1);
		long total = orderDao.getCountByUid(uid);
		page.setPageSize(pageSize);
		page.setTotalItemNumber(total);
		List<Order> orders = orderDao.getOrdersByUid(uid, begin, pageSize);
		page.setList(orders);
		return page;
	}

	public Order getOrderByOId(Integer oId) {
		return orderDao.getOrderByOId(oId);
	}

	public void payOrder(Order order, String pdFrpId) throws IOException {
	
	}

	public Page<Order> getOrdersInPage(Integer pageNo) {
		Page<Order> page = new Page<>(pageNo);
		int pageSize = 8;
		int begin = pageSize * (pageNo - 1);
		long total = orderDao.getCount();
		List<Order> list = orderDao.getOrdersInPage(begin, pageSize);
		page.setPageSize(pageSize);
		page.setTotalItemNumber(total);
		page.setList(list);
		
		return page;
	}

	public List<OrderItem> getOrderItems(Integer oId) {
		return orderDao.getOrderItemsByOId(oId);
	}
}
