package com.athome.webmall.category.controllers;

import com.athome.webmall.product.entities.Page;
import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SecondCategoryAction extends ActionSupport implements ModelDriven<Product> {
	private static final long serialVersionUID = 1L;
	private Product product = new Product();
	private Integer scId;
	private Integer pageNo;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public Product getModel() {
		return product;
	}

	public String getById() {
		Page<Product> page = productService.getByScId(scId, pageNo);
		ActionContext.getContext().getValueStack().set("page", page);
		return "productList";
	}


}
