package com.athome.webmall.product.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.athome.webmall.product.dao.ProductDao;
import com.athome.webmall.product.entities.Page;
import com.athome.webmall.product.entities.Product;

/**
 * 商品业务层
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> getHotProudcts() {
		return productDao.getHotProducts();
	}

	public List<Product> getLatestProducts() {
		return productDao.getLatestProducts();
	}

	public Product getById(Integer id) {
		return productDao.getById(id);
	}

	public Page<Product> getPageByCId(Integer cId, Integer pageNo) {
		Page<Product> page = new Page<>(pageNo);
		int pageSize = 8;
		int begin = (pageNo - 1)*pageSize;
		page.setPageSize(pageSize);
		long totalItemNumber = productDao.getCountByCId(cId);
		page.setTotalItemNumber(totalItemNumber);
		List<Product> list = productDao.getListByCId(cId, begin, pageSize);
		page.setList(list);
		return page;
	}

	public Page<Product> getByScId(Integer scId, Integer pageNo) {
		Page<Product> page = new Page<>(pageNo);
		int pageSize = 8;
		int begin = (pageNo - 1)*pageSize;
		page.setPageSize(pageSize);
		long totalItemNumber = productDao.getCountByScId(scId);
		page.setTotalItemNumber(totalItemNumber);
		List<Product> list = productDao.getListByScId(scId, begin, pageSize);
		page.setList(list);
		return page;
	}

	public Page<Product> getAllInPage(Integer pageNo) {
		Page<Product> page = new Page<>(pageNo);
		int pageSize = 10;
		int begin = pageSize * (pageNo- 1);
		long total = productDao.getCount();
		List<Product> list = productDao.getAll(begin,pageSize);
		page.setPageSize(pageSize);
		page.setTotalItemNumber(total);
		page.setList(list);
		return page;
	}

	public void saveOrUpdate(Product product) {
		productDao.saveOrUpdate(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}
}
