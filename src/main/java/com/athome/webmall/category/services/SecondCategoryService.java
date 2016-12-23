package com.athome.webmall.category.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.athome.webmall.category.dao.SecondCategoryDao;
import com.athome.webmall.category.entities.SecondCategory;
import com.athome.webmall.product.entities.Page;

/**
 * 二级分类业务层
 */
@Transactional
public class SecondCategoryService {
	private SecondCategoryDao secondCategoryDao;

	public void setSecondCategoryDao(SecondCategoryDao secondCategoryDao) {
		this.secondCategoryDao = secondCategoryDao;
	}

	public List<SecondCategory> getAll() {
		return secondCategoryDao.getAll();
	}

	public Page<SecondCategory> getInPage(Integer pageNo) {
		Page<SecondCategory> page = new Page<>(pageNo);
		int pageSize = 10;
		long total = secondCategoryDao.getCount();
		int begin = pageSize * (pageNo - 1);
		page.setPageSize(pageSize);
		page.setTotalItemNumber(total);
		List<SecondCategory> list = secondCategoryDao.getListInPage(begin,pageSize);
		page.setList(list);
		return page;
	}

	public void saveOrUpdate(SecondCategory secondCategory) {
		secondCategoryDao.saveOrUpdate(secondCategory);
	}

	public void delete(SecondCategory secondCategory) {
		secondCategoryDao.delete(secondCategory);
	}

	public SecondCategory getById(Integer scId) {
		return secondCategoryDao.getById(scId);
	}
}
