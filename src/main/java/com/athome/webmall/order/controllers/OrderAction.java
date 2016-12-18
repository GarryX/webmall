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
 * Orderģ��Controller
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private static final long serialVersionUID = 1L;
	private Order order = new Order();
	private OrderService orderService;
	private Integer pageNo = 1;
	private String pd_FrpId;
	//����ɹ��󷵻صĲ���
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
			this.addActionError("����û�й�����ȹ��");
			return "message";
		}
		order.setOrderDate(new Date());
		order.setState(PRE_PAYMENT);
		order.setTotalBill(cart.getTotalBill());
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (loginUser == null) {
			this.addActionError("�û�δ��½�����½������ύ������");
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

	// ����
	public String payOrder() throws IOException {
		// ���¶����������ϵ�ˣ���ϵ��ʽ����Ϣ
		Order orderToPay = orderService.getOrderByOId(order.getOId());
		orderToPay.setName(order.getName());
		orderToPay.setAddress(order.getAddress());
		orderToPay.setPhone(order.getPhone());
		orderService.saveOrUpdateOrder(orderToPay);
		// ���и������,�����õ����ױ�֧���ӿ�
		String p0_Cmd = "Buy";// ��������
		String p1_MerId = "10001126856";// �̻����
		String p2_Order = orderToPay.getOId().toString();// Ҫ֧���Ķ�����
		String p3_Amt = "0.01";// ���˴�����0.01���ڲ���
		String p4_Cur = "CNY";// ����
		String p5_Pid = "";// ��Ʒid
		String p6_Pcat = "";// ��Ʒ����
		String p7_Pdesc = "";// ��Ʒ����
		String p8_Url = "http://localhost:8080/webmall/order_callBack";// ����ɹ���Ļش�ҳ��
		String p9_SAF = "";// �ͻ���ַ
		String pa_MP = "";// �̻���չ��Ϣ
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1";// Ӧ�����
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";// ˽Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);// hmacֵ
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
		// �ض���֧��ҳ��
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	public String callBack() {
		Order order = orderService.getOrderByOId(Integer.parseInt(r6_Order));
		order.setState(PRE_SHIPMENT);
		orderService.saveOrUpdateOrder(order);
		this.addActionMessage("֧���ɹ����������Ϊ: " + r6_Order + "  \n������Ϊ: " + r3_Amt);
		return "message";
	}
	
	//�ͻ�ȷ���ջ�����¶���״̬Ϊ�����
	public String updateState(){
		Order order2 = orderService.getOrderByOId(order.getOId());
		order2.setState(FINISHED);
		orderService.saveOrUpdateOrder(order2);
		return "updated";
	}

	private final Integer PRE_PAYMENT = 1;// ����δ����
	private final Integer PRE_SHIPMENT = 2;// �Ѹ�����̼һ�δ����
	private final Integer FINISHED = 4; // �������
}
