package cn.kuroko.faq.service.impl;

import java.util.List;

import org.hibernate.Query;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.dao.IDao;
import cn.kuroko.faq.service.IService;

/**
 * @author Kuroko
 * @time 2013-12-13 下午4:52:33
 */

public abstract class ServiceImp<T extends BaseBean> implements IService<T> {

	protected IDao<T> dao;

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public T find(Class<T> clazz, int id) {
		return this.dao.find(clazz, id);
	}

	@Override
	public abstract void create(T baseBean);

	@Override
	public void save(T baseBean) {
		this.dao.save(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		this.dao.delete(baseBean);
	}

	@Override
	public List<T> list(String hql) {
		return this.dao.list(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... args) {
		return this.dao.getTotalCount(hql, args);
	}

	@Override
	public List<T> list(String hql, int firstResult, int maxSize,
			Object... args) {
		return this.dao.list(hql, firstResult, maxSize, args);
	}

	@Override
	public Query createQuery(String hql) {
		return this.dao.createQuery(hql);
	}

	@Override
	public void update(T baseBean) {
		this.dao.update(baseBean);
	}

}
