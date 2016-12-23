package com.athome.webmall.category.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.athome.webmall.category.dao.FirstCategoryDao;
import com.athome.webmall.category.entities.FirstCategory;

/**
 * 一级分类业务层
 */
@Transactional
public class FirstCategoryService {
	private FirstCategoryDao firstCategoryDao;

	public void setFirstCategoryDao(FirstCategoryDao firstCategoryDao) {
		this.firstCategoryDao = firstCategoryDao;
	}

	public List<FirstCategory> getAll() {
		return firstCategoryDao.getAll();
	}

	public FirstCategory getById(Integer id) {
		return firstCategoryDao.getById(id);
	}

	public void saveOrUpdate(FirstCategory firstCategory) {
		firstCategoryDao.saveOrUpdate(firstCategory);
	}

	public void delete(FirstCategory fc) {
		firstCategoryDao.delete(fc);
	}
}
