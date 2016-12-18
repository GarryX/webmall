package com.athome.webmall.order.controllers;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.athome.webmall.cart.entities.Cart;
import com.athome.webmall.cart.entities.CartItem;
import com.athome.webmall.order.entities.Order;
import com.athome.webmall.order.entities.OrderItem;
import com.athome.webmall.order.services.OrderService;
import com.athome.webmall.product.entities.Page;
import com.athome.webmall.user.entities.User;
import com.athome.webmall.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Order模块Controller
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private static final long serialVersionUID = 1L;
	private Order order = new Order();
	private OrderService orderService;
	private Integer pageNo = 1;
	private String pd_FrpId;
	//付款成功后返回的参数
	private String r3_Amt;
	private String r6_Order;

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return this.order;
	}

	public String generateOrder() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("您还没有购物，请先购物！");
			return "message";
		}
		order.setOrderDate(new Date());
		order.setState(PRE_PAYMENT);
		order.setTotalBill(cart.getTotalBill());
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (loginUser == null) {
			this.addActionError("用户未登陆，请登陆后继续提交订单！");
			return "loginPage";
		}
		order.setUser(loginUser);
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotalSum(item.getTotalSum());
			orderItem.setOrder(order);
			order.getItems().add(orderItem);
		}
		orderService.saveOrUpdateOrder(order);
		cart.clearCart();
		// ServletActionContext.getRequest().getSession().setAttribute("order",
		// order);
		return "orderPage";
	}

	public String getOrdersByUid() {
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		Page<Order> page = orderService.getPageByUid(pageNo, loginUser.getId());
		ActionContext.getContext().getValueStack().set("page", page);
		return "myOrders";
	}

	public String getOrderByOId() {
		order = orderService.getOrderByOId(order.getOId());
		return "orderPage";
	}

	// 付款
	public String payOrder() throws IOException {
		// 更新订单，添加联系人，联系方式等信息
		Order orderToPay = orderService.getOrderByOId(order.getOId());
		orderToPay.setName(order.getName());
		orderToPay.setAddress(order.getAddress());
		orderToPay.setPhone(order.getPhone());
		orderService.saveOrUpdateOrder(orderToPay);
		// 进行付款操作,这里用的是易宝支付接口
		String p0_Cmd = "Buy";// 交易类型
		String p1_MerId = "10001126856";// 商户编号
		String p2_Order = orderToPay.getOId().toString();// 要支付的订单号
		String p3_Amt = "0.01";// 金额，此处设置0.01用于测试
		String p4_Cur = "CNY";// 币种
		String p5_Pid = "";// 商品id
		String p6_Pcat = "";// 商品种类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = "http://localhost:8080/webmall/order_callBack";// 付款成功后的回传页面
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 商户扩展信息
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1";// 应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";// 私钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);// hmac值
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		// 重定向到支付页面
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	public String callBack() {
		Order order = orderService.getOrderByOId(Integer.parseInt(r6_Order));
		order.setState(PRE_SHIPMENT);
		orderService.saveOrUpdateOrder(order);
		this.addActionMessage("支付成功！订单编号为: " + r6_Order + "  \n付款金额为: " + r3_Amt);
		return "message";
	}
	
	//客户确认收货后更新订单状态为已完成
	public String updateState(){
		Order order2 = orderService.getOrderByOId(order.getOId());
		order2.setState(FINISHED);
		orderService.saveOrUpdateOrder(order2);
		return "updated";
	}

	private final Integer PRE_PAYMENT = 1;// 订单未付款
	private final Integer PRE_SHIPMENT = 2;// 已付款，但商家还未发货
	private final Integer FINISHED = 4; // 交易完成
}
