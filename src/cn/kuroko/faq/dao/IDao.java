package cn.kuroko.faq.dao;

import java.util.List;

import org.hibernate.Query;

import cn.kuroko.faq.bean.BaseBean;

/**
 * @author Kuroko
 * @time 2013-12-13 下午3:55:02
 */

public interface IDao<T extends BaseBean> {
	public T find(Class<T> clazz, int id);

	public void create(T baseBean);

	public void save(T baseBean);

	public void delete(T baseBean);

	public List<T> list(String hql);

	public int getTotalCount(String hql, Object... args);

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... args);

	public Query createQuery(String hql);
	
	public void update(T baseBean);
}
