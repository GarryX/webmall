package com.athome.webmall.product.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * …Ã∆∑controller
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ProductAction.class);
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
		log.info("will return the product information for productId " + product.getId());
		return "product";
	}
}
