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
	 * ע��FirstCategoryServiceʵ��
	 */
	private FirstCategoryService firstCategoryService;

	public void setFirstCategoryService(FirstCategoryService firstCategoryService) {
		this.firstCategoryService = firstCategoryService;
	}

	/**
	 * ע��SecondCategoryServiceʵ��
	 */
	private SecondCategoryService secondCategoryService;

	public void setSecondCategoryService(SecondCategoryService secondCategoryService) {
		this.secondCategoryService = secondCategoryService;
	}

	/**
	 * ע��ProductServiceʵ��
	 */
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * ת������ҳ
	 */
	public String execute() {
		//��ѯ����һ������
		List<FirstCategory> fclist = firstCategoryService.getAll();
		//����ѯ����һ�����༯�ϱ��浽session����
		ActionContext.getContext().getSession().put("fclist", fclist);
		//��ѯ������Ʒ
		List<Product> hotList = productService.getHotProudcts();
		//����ѯ����������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hotList", hotList);
		//��ѯ������Ʒ
		List<Product> latestList = productService.getLatestProducts();
		//����ѯ����������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("latestList", latestList);
		return "home";
	}

}
