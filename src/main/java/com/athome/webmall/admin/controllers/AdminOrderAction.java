package com.athome.webmall.admin.controllers;

import java.util.List;

import com.athome.webmall.order.entities.Order;
import com.athome.webmall.order.entities.OrderItem;
import com.athome.webmall.order.services.OrderService;
import com.athome.webmall.product.entities.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理controller
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	private static final long serialVersionUID = 1L;
	private Order order = new Order();
	private Integer pageNo = 1;
	private OrderService orderService;
	private Integer oId;

	public void setOId(Integer oId) {
		this.oId = oId;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public Order getModel() {
		return order;
	}

	public String getAllInPage() {
		Page<Order> page = orderService.getOrdersInPage(pageNo);
		ActionContext.getContext().getValueStack().set("page", page);
		return "home";
	}

	public String getOrderItem() {
		List<OrderItem> items = orderService.getOrderItems(oId);
		ActionContext.getContext().getValueStack().set("items", items);
		return "return";
	}
	
	public String updateState(){
		Order order2 = orderService.getOrderByOId(oId);
		order2.setState(SHIPPED);
		orderService.saveOrUpdateOrder(order2);
		return "updated";
	}
	private final Integer SHIPPED = 3;// 已发货，但买家还未确认收货

}
