package com.athome.webmall.admin.controllers;

import java.util.List;

import com.athome.webmall.category.entities.FirstCategory;
import com.athome.webmall.category.entities.SecondCategory;
import com.athome.webmall.category.services.FirstCategoryService;
import com.athome.webmall.category.services.SecondCategoryService;
import com.athome.webmall.product.entities.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理controller
 */
public class AdminSecondCategoryAction extends ActionSupport implements ModelDriven<SecondCategory>{
	private static final long serialVersionUID = 1L;
	private SecondCategoryService secondCategoryService;
	private FirstCategoryService firstCategoryService;
	private SecondCategory secondCategory = new SecondCategory();
	private Integer pageNo = 1;

	public void setFirstCategoryService(FirstCategoryService firstCategoryService) {
		this.firstCategoryService = firstCategoryService;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setSecondCategoryService(SecondCategoryService secondCategoryService) {
		this.secondCategoryService = secondCategoryService;
	}

	public String getAllInPage() {
		Page<SecondCategory> page = secondCategoryService.getInPage(pageNo);
		ActionContext.getContext().getValueStack().set("page", page);
		return "home";
	}

	public String toAddPage() {
		List<FirstCategory> fcList = firstCategoryService.getAll();
		ActionContext.getContext().getValueStack().set("fcList", fcList);
		return "addPage";
	}
	public String save(){
		secondCategoryService.saveOrUpdate(secondCategory);
		return "saved";
	}
	public String delete(){
		secondCategoryService.delete(secondCategory);
		return "deleted";
	}
	public String edit(){
		secondCategory = secondCategoryService.getById(secondCategory.getScId());
		List<FirstCategory> fcList = firstCategoryService.getAll();
		ActionContext.getContext().getValueStack().set("fcList", fcList);
		return "editPage";
	}
	public String update(){
		secondCategoryService.saveOrUpdate(secondCategory);
		return "updated";
	}

	@Override
	public SecondCategory getModel() {
		return secondCategory;
	}
}
