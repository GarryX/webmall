package com.athome.webmall.category.controllers;

import com.athome.webmall.category.entities.FirstCategory;
import com.athome.webmall.product.entities.Page;
import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 一级分类控制层
 */
public class FirstCategoryAction extends ActionSupport implements ModelDriven<FirstCategory> {
	private static final long serialVersionUID = 1L;
	private FirstCategory firstCategory = new FirstCategory();
	private ProductService productService;
	private Integer cId;
	private Integer pageNo;

	public void setCId(Integer cId) {
		this.cId = cId;
	}
	
	public Integer getCId() {
		return cId;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getById() {
		Page<Product> page = productService.getPageByCId(cId, pageNo);//根据一级分类分页查询商品
		ActionContext.getContext().getValueStack().set("page", page);
		return "productList";
	}

	@Override
	public FirstCategory getModel() {
		return firstCategory;
	}
}
