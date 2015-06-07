package cn.kuroko.faq.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.kuroko.faq.bean.ExpertType;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.constant.Constant;
import cn.kuroko.faq.dao.IDao;
import cn.kuroko.faq.hibernate.HibernateSessionFactory;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.util.MD5Util;

/**
 * @author Kuroko
 * @time 2013-12-23 上午11:36:50
 */

public class PersonServiceImpl<T extends Person> extends ServiceImp<T>
		implements IPersonService<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T findPersonByAccount(String account) {
		IDao dao = this.getDao();
		Query q = dao
				.createQuery(
						"select p from Person p where p.account=:account and p.deleted=0")
				.setParameter("account", account);
		List<T> person = q.list();
		if (null != person && 0 < person.size())
			return person.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getPerson(String account, String password) {
		List<T> person = this
				.getDao()
				.createQuery(
						"select p from Person p "
								+ "where lower(p.account) = lower(:account) "
								+ "and p.password = :password "
								+ "and p.deleted=false")
				.setParameter("account", account.trim())
				.setParameter("password", MD5Util.calc(password)).list();
		if (null != person && 0 < person.size())
			return person.get(0);
		return null;
	}

	@Override
	public void create(T person) {
		if (null == this.findPersonByAccount(person.getAccount())) {
			person.setPassword(MD5Util.calc(person.getPassword()));
			this.getDao().create(person);
		} else
			throw new RuntimeException("用户" + person.getAccount() + "已存在!");
	}

	@Override
	public List<ExpertType> getExpertType(T expert) {
		Query q = this.dao
				.createQuery("select type from ExpertType type where "
						+ "type.deleted=0 and type.expert.id=" + expert.getId());
		return q.list();
	}

	@Override
	public List<T> getExpert(String type) {
		if (null == type) {
			List<T> lstResult = this.dao
					.list("select p from Person p where p.deleted=0");
			for (Iterator<T> i = lstResult.iterator(); i.hasNext();) {
				T person = i.next();
				if ((person.getPower() & Constant.POWER_EXPERT) != Constant.POWER_EXPERT)
					i.remove();
			}
			return lstResult;
		} else {
			List<ExpertType> lstType = this.dao.createQuery(
					"select et from ExpertType et where et.type.name like '%"
							+ type + "%'").list();
			List<T> lstResult = new ArrayList<T>();
			for (ExpertType et : lstType) {
				Person p = et.getExpert();
				if (!lstResult.contains(p))
					lstResult.add((T) p);
			}
			return lstResult;
		}
	}
	
	//*************************3.14 quinn
	public String getExpertNameById(Integer id){
		if( null != id){
			List<Person> lstp = this.dao.createQuery("select p from Person p where p.id = " + id.toString()).list();
			if (0 == lstp.size()) return "未指定专家";
			Person p = lstp.get(0);
			return p.getName();
		}
		return null;
	}
	//*************************3.14 quinn

	@SuppressWarnings("unchecked")
	@Override
	public void setExpertType(Person person, String type1, String type2,
			String type3) {
		Type t1 = null, t2 = null, t3 = null;
		if (null != type1) {
			List<Type> lstType = this.getDao()
					.createQuery("select type from Type type").list();
			if (null != lstType && !lstType.isEmpty())
				t1 = lstType.get(0);
			ExpertType et = new ExpertType();
			et.setExpert(person);
			et.setType(t1);
			Session s = HibernateSessionFactory.getSession();
			s.save(et);
		}
		if (null != type2) {
			List<Type> lstType = this.getDao()
					.createQuery("select t from Type t where t.name=" + type1)
					.list();
			if (null != lstType && !lstType.isEmpty())
				t2 = lstType.get(0);
			ExpertType et = new ExpertType();
			et.setExpert(person);
			et.setType(t2);
			Session s = HibernateSessionFactory.getSession();
			s.save(et);
		}
		if (null != type3) {
			List<Type> lstType = this.getDao()
					.createQuery("select t from Type t where t.name=" + type1)
					.list();
			if (null != lstType && !lstType.isEmpty())
				t3 = lstType.get(0);
			ExpertType et = new ExpertType();
			et.setExpert(person);
			et.setType(t3);
			Session s = HibernateSessionFactory.getSession();
			s.save(et);
		}
	}

	@Override
	public T getTypeByName(String name) {
		List<T> lstType = this.dao.list("select t from Type t where t.name=" + name);
		if(null != lstType && !lstType.isEmpty())
			return lstType.get(0);
		return null;
	}

	@Override
	public List<T> getAllAdmin() {
		return this.dao.list("select p from Person p where p.power=1");
	}

	
}