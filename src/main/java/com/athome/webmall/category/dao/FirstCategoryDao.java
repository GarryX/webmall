package com.athome.webmall.category.dao;

import java.util.List;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.category.entities.FirstCategory;
/**
 * 一级分类持久层
 */
public class FirstCategoryDao extends SessionProvider{

	@SuppressWarnings("unchecked")
	public List<FirstCategory> getAll() {
		String hql = "FROM FirstCategory";
		return getSession().createQuery(hql).list();
	}

	public FirstCategory getById(Integer id) {
		return (FirstCategory) getSession().get(FirstCategory.class, id);
	}

	public void saveOrUpdate(FirstCategory firstCategory) {
		getSession().saveOrUpdate(firstCategory);
	}

	public void delete(FirstCategory fc) {
		getSession().delete(fc);
	}
	
}
