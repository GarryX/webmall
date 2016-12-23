package com.athome.webmall.category.dao;

import java.util.List;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.category.entities.SecondCategory;

public class SecondCategoryDao extends SessionProvider {

	@SuppressWarnings("unchecked")
	public List<SecondCategory> getAll() {
		String hql = "FROM SecondCategory";
		return getSession().createQuery(hql).list();
	}

	public long getCount() {
		String hql = "SELECT COUNT(*) FROM SecondCategory";
		return (long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<SecondCategory> getListInPage(int begin, int pageSize) {
		String hql = "FROM SecondCategory sc ORDER BY sc.scId DESC";
		return getSession().createQuery(hql).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	public void saveOrUpdate(SecondCategory secondCategory) {
		getSession().saveOrUpdate(secondCategory);
	}

	public void delete(SecondCategory secondCategory) {
		getSession().delete(secondCategory);
	}

	public SecondCategory getById(Integer scId) {
		return (SecondCategory) getSession().get(SecondCategory.class, scId);
	}
	
}
