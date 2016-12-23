package com.athome.webmall.admin.controllers;

import java.util.List;

import com.athome.webmall.category.entities.FirstCategory;
import com.athome.webmall.category.services.FirstCategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理
 */
public class AdminFirstCategoryAction extends ActionSupport implements ModelDriven<FirstCategory> {
	private static final long serialVersionUID = 1L;
	private FirstCategory firstCategory = new FirstCategory();
	private FirstCategoryService firstCategoryService;

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	public void setFirstCategoryService(FirstCategoryService firstCategoryService) {
		this.firstCategoryService = firstCategoryService;
	}

	@Override
	public FirstCategory getModel() {
		return firstCategory;
	}

	public String getAll() {
		List<FirstCategory> fcList = firstCategoryService.getAll();
		ActionContext.getContext().getValueStack().set("fcList", fcList);
		return "home";
	}

	public String save() {
		firstCategoryService.saveOrUpdate(firstCategory);
		return "saved";
	}

	public String delete() {
		FirstCategory fc = firstCategoryService.getById(firstCategory.getCId());
		firstCategoryService.delete(fc);
		return "deleted";
	}

	@SuppressWarnings("unused")
	public String edit() {
		FirstCategory fc = firstCategoryService.getById(firstCategory.getCId());
		return "editPage";
	}
	
	public String update(){
		firstCategoryService.saveOrUpdate(firstCategory);
		return "updated";
	}

}
