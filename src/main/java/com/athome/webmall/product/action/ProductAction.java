package com.athome.webmall.product.action;

import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * …Ã∆∑controller
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Product product = new Product();

	@Override
	public Product getModel() {
		return product;
	}

	public String getById() {
		this.product = productService.getById(product.getId());
		return "product";
	}
}
