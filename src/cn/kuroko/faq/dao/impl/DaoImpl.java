package cn.kuroko.faq.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.dao.IDao;

/**
 * @author Kuroko
 * @time 2013-12-13 下午4:20:46
 */

public class DaoImpl<T extends BaseBean> extends HibernateDaoSupport implements IDao<T> {

	@Override
	public T find(Class<T> clazz, int id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public void create(T baseBean) {
		this.getHibernateTemplate().persist(baseBean);
	}

	@Override
	public void save(T baseBean) {
		this.getHibernateTemplate().save(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		this.getHibernateTemplate().delete(baseBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... args) {
		Query query = this.createQuery(hql);
		for (int i = 0; i < args.length; i++)
			query.setParameter(i + 1, args[i]);
		Object obj = query.uniqueResult();
		return ((Long) obj).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(String hql, int firstResult, int maxSize,
			Object... args) {
		Query query = this.createQuery(hql);
		for (int i = 0; i < args.length; i++)
			query.setParameter(i + 1, args[i]);
		return query.setFirstResult(firstResult).setMaxResults(maxSize).list();
	}

	@Override
	public Query createQuery(String hql) {
		return this.getSession().createQuery(hql);
	}

	@Override
	public void update(T baseBean) {
		this.getHibernateTemplate().update(baseBean);
	}

}
