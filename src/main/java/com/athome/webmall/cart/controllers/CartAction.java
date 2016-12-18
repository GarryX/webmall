package com.athome.webmall.cart.controllers;

import org.apache.struts2.ServletActionContext;

import com.athome.webmall.cart.entities.Cart;
import com.athome.webmall.cart.entities.CartItem;
import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer quantity;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String addToCart() {
		CartItem cartItem = new CartItem();
		Product product = productService.getById(id);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		Cart cart = this.getCart();
		cart.addToCart(cartItem);
		return "cart";
	}

	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String clearCart(){
		Cart cart = this.getCart();
		cart.clearCart();
		return "cart";
	}
	
	public String removeFromCart(){
		Cart cart = this.getCart();
		cart.removeFromCart(id);
		return "cart";
	}
	
	public String getMyCart(){
		return "cart";
	}

}
