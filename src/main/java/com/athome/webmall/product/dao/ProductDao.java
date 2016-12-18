package com.athome.webmall.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.athome.webmall.base.SessionProvider;
import com.athome.webmall.product.entities.Product;

/**
 * 商品持久层
 */
public class ProductDao extends SessionProvider {

	@SuppressWarnings("unchecked")
	public List<Product> getHotProducts() {
		// 使用标准查询
		Criteria criteria = getSession().createCriteria(Product.class);
		// 查询条件为isHot为1
		criteria.add(Restrictions.eq("isHot", 1));
		// 按倒序输出
		criteria.addOrder(Order.desc("launchDate"));
		// 执行查询
		List<Product> list = criteria.setFirstResult(0).setMaxResults(10).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getLatestProducts() {
		// 使用标准查询
		Criteria criteria = getSession().createCriteria(Product.class);
		// 按倒序输出
		criteria.addOrder(Order.desc("launchDate"));
		// 执行查询
		List<Product> list = criteria.setFirstResult(0).setMaxResults(10).list();
		return list;
	}

	public Product getById(Integer id) {
		return (Product) getSession().get(Product.class, id);
	}

	public long getCountByCId(Integer cId) {
		String hql = "SELECT COUNT(*) FROM Product p WHERE p.secondCategory.firstCategory.cId = ?";
		return (long) getSession().createQuery(hql).setInteger(0, cId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getListByCId(Integer cId, int begin, int pageSize) {
		String hql = "SELECT p FROM Product p join p.secondCategory sc join sc.firstCategory fc WHERE fc.cId = ?";
		return getSession().createQuery(hql).setInteger(0, cId).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	public long getCountByScId(Integer scId) {
		String hql = "SELECT COUNT(*) FROM Product p WHERE p.secondCategory.scId = ?";
		return (long) getSession().createQuery(hql).setInteger(0, scId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getListByScId(Integer scId, int begin, int pageSize) {
		String hql = "SELECT p FROM Product p join p.secondCategory sc WHERE sc.scId = ?";
		return getSession().createQuery(hql).setInteger(0, scId).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	public long getCount() {
		String hql = "SELECT COUNT(*) FROM Product";
		return (long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAll(int begin, int pageSize) {
		String hql = "FROM Product p ORDER BY p.launchDate DESC";
		return getSession().createQuery(hql).setFirstResult(begin).setMaxResults(pageSize).list();
	}

	public void saveOrUpdate(Product product) {
		getSession().saveOrUpdate(product);
	}

	public void delete(Product product) {
		getSession().delete(product);
	}
}
