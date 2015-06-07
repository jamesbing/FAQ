package cn.kuroko.faq.service;

import java.util.List;

import org.hibernate.Query;

/**
 * @author Kuroko
 * @time 2013-12-13 下午4:50:40
 */

public interface IService<T> {
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
