package com.athome.webmall.home.controllers;

import java.util.List;

import com.athome.webmall.category.entities.FirstCategory;
import com.athome.webmall.category.services.FirstCategoryService;
import com.athome.webmall.category.services.SecondCategoryService;
import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * 注入FirstCategoryService实例
	 */
	private FirstCategoryService firstCategoryService;

	public void setFirstCategoryService(FirstCategoryService firstCategoryService) {
		this.firstCategoryService = firstCategoryService;
	}

	/**
	 * 注入SecondCategoryService实例
	 */
	private SecondCategoryService secondCategoryService;

	public void setSecondCategoryService(SecondCategoryService secondCategoryService) {
		this.secondCategoryService = secondCategoryService;
	}

	/**
	 * 注入ProductService实例
	 */
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 转发到首页
	 */
	public String execute() {
		//查询所有一级分类
		List<FirstCategory> fclist = firstCategoryService.getAll();
		//将查询到的一级分类集合保存到session域中
		ActionContext.getContext().getSession().put("fclist", fclist);
		//查询热门商品
		List<Product> hotList = productService.getHotProudcts();
		//将查询到的热门商品保存到值栈中
		ActionContext.getContext().getValueStack().set("hotList", hotList);
		//查询最新商品
		List<Product> latestList = productService.getLatestProducts();
		//将查询到的最新商品保存到值栈中
		ActionContext.getContext().getValueStack().set("latestList", latestList);
		return "home";
	}

}
