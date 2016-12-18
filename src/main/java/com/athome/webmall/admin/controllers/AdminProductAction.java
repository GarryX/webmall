package com.athome.webmall.admin.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.athome.webmall.category.entities.SecondCategory;
import com.athome.webmall.category.services.SecondCategoryService;
import com.athome.webmall.product.entities.Page;
import com.athome.webmall.product.entities.Product;
import com.athome.webmall.product.services.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理controller
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	private static final long serialVersionUID = 1L;
	private Product product = new Product();
	private ProductService productService;
	private SecondCategoryService secondCategoryService;
	private Integer pageNo = 1;
	private Integer id;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setSecondCategoryService(SecondCategoryService secondCategoryService) {
		this.secondCategoryService = secondCategoryService;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	public String getAllInPage() {
		Page<Product> page = productService.getAllInPage(pageNo);
		ActionContext.getContext().getValueStack().set("page", page);
		return "home";
	}

	public String toAddPage() {
		List<SecondCategory> scList = secondCategoryService.getAll();
		ActionContext.getContext().getValueStack().set("scList", scList);
		return "addPage";
	}

	public String save() throws IOException {
		product.setLaunchDate(new Date());
		if (upload != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			String diskPath = ServletActionContext.getRequest().getServletContext().getInitParameter("diskPath");
			File destFile = new File(realPath + "/2/" + uploadFileName);
			File diskFile = new File(diskPath + "/WebContent/products/2/" + uploadFileName);
			FileUtils.copyFile(upload, destFile);
			FileUtils.copyFile(upload, diskFile);
			System.out.println(diskPath);
			System.out.println("***************************************" + diskFile);
			product.setImage("products/2/" + uploadFileName);
		}
		productService.saveOrUpdate(product);
		return "saved";
	}

	public String delete() {
		product = productService.getById(product.getId());
		String deletePath = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
		String diskPath = ServletActionContext.getRequest().getServletContext().getInitParameter("diskPath");
		if (deletePath != null) {
			File file = new File(deletePath);
			file.delete();
		}
		if(diskPath != null){
			File file = new File(diskPath + "/WebContent/" + product.getImage());
			file.delete();
		}
		productService.delete(product);
		return "deleted";
	}

	public String edit() {
		product = productService.getById(product.getId());
		List<SecondCategory> scList = secondCategoryService.getAll();
		ActionContext.getContext().getValueStack().set("scList", scList);
		return "editPage";
	}

	public String update() throws IOException {
		product.setLaunchDate(new Date());
		if (upload != null) {
			//将商品原图片删除
			String image = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/" + image));
			String diskPath = ServletActionContext.getRequest().getServletContext().getInitParameter("diskPath");
			File diskFile = new File(diskPath + "/WebContent/" + image);
			file.delete();
			diskFile.delete();
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// String realPath = this.getClass().getResource("/").getPath();
			File destFile = new File(realPath + "/2/" + uploadFileName);
			File newDiskFile = new File(diskPath + "/WebContent/products/2/" + uploadFileName);
			
			FileUtils.copyFile(upload, destFile);
			FileUtils.copyFile(upload, newDiskFile);
			product.setImage("products/2/" + uploadFileName);
		}
		productService.saveOrUpdate(product);
		return "updated";
	}

}
