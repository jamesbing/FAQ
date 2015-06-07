package cn.kuroko.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.constant.Constant;
import cn.kuroko.faq.service.IQuestionService;

/**
 * @author Kuroko
 * @time 2014-1-5 上午11:36:30
 */

public class QuestionServiceImpl<T extends BaseBean> extends ServiceImp<T>
		implements IQuestionService<T>{

	@Override
	public void create(T baseBean) {
		this.getDao().create(baseBean);
	}

	@Override
	public List<T> getAllQuestion() {
		List<T> question = this.getDao().list(
				"select q from Question q where q.deleted=false");
		return question;
	}

	@Override
	public List<T> getQuestionByType(String t1, String t2, String t3) {
		@SuppressWarnings("unchecked")
		List<T> question = this
				.getDao()
				.createQuery(
						"select q from Question q "
								+ "where q.qtype1.name = :t1 "
								+ "and q.qtype2.name = :t2"
								+ "and q.qtype3.name = :t3"
								+ "and q.deleted=false").setParameter("t1", t1)
				.setParameter("t2", t2).setParameter("t3", t3).list();
		if (null == question)
			return new ArrayList<T>();
		return question;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getQuestionType1() {
		return (List<Type>) this.getDao().list(
				"select type from Type type where type.level = 1");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getQuestionType2(String qtype1) {
		if (null == qtype1)
			return (List<Type>) this.getDao().list(
					"select type from Type type where type.level = 2");
		else
			return (List<Type>) this.getDao().list(
					"select type from Type type where type.level = 2 and type.parent='"
							+ qtype1 + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getQuestionType3(String qtype2) {
		if (null == qtype2)
			return (List<Type>) this.getDao().list(
					"select type from Type type where type.level = 3");
		else
			return (List<Type>) this.getDao().list(
					"select type from Type type where type.level = 3 and type.parent = '"
							+ qtype2 + "'");
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Person> getExpert() {
	// return (List<Person>) this.getDao().list(
	// "select p from Person p where p.deleted=0 and p.power = "
	// + Constant.POWER_EXPERT);
	// }

	@Override
	public List<T> search(String[] lstTitle, String[] lstType,
			String[] lstContent, Person p, boolean my) {
		StringBuilder sb = new StringBuilder();
		sb.append("select q from Question q where q.deleted=false");
		if (null != lstTitle)
			for (String str : lstTitle)
				sb.append(" and q.title like '%" + str + "%'");
		if (null != lstType)
			for (String str : lstType)
				sb.append(" and (q.qtype1 like '%" + str
						+ "%' or q.qtype2 like '%" + str
						+ "%' or q.qtype2 like '%" + str + "%')");
		if (null != lstContent)
			for (String str : lstContent)
				sb.append(" and q.content like '%" + str + "%'");
		if (null != p) {
			if ((p.getPower() & Constant.POWER_ADMIN) == Constant.POWER_ADMIN) {
				if (my)
					sb.append(" and q.ok=false");
			} else if ((p.getPower() & Constant.POWER_EXPERT) == Constant.POWER_EXPERT) {
				if (my)
					sb.append(" and (q.expertId=NULL or q.expertId="
							+ p.getId() + ")");
				else
					sb.append(" and q.pub=" + Constant.PUB_TRUE);
				sb.append(" and (q.ok=true or q.qtype1='意见建议')");
			} else if ((p.getPower() & Constant.POWER_USER) == Constant.POWER_USER) {
				if (my)
					sb.append(" and q.author.id=" + p.getId());
				else
					sb.append(" and q.pub = " + Constant.PUB_TRUE);
				sb.append(" and (q.ok=true or q.qtype1='意见建议')");
			}
		} else
			sb.append(" and q.pub=" + Constant.PUB_TRUE + " and q.ok=1");
		return this.getDao().list(sb.toString());
	}

	@Override
	public List<T> getQuestion(int start, int max) {
		return this.dao.list("select q from Question q where q.deleted=false",
				start, max);
	}

	@Override
	public void permit(T q) {
		Question question = (Question) q;
		question.setOk(true);
		this.dao.update(q);
	}

	@Override
	public void popular(T q, boolean popular) {
		Question question = (Question) q;
		question.setPopular(popular);
		this.dao.update(q);
	}
	@Override
	public List<T> getPopularQuestion() {
		return this.dao
				.list("select q from Question q where q.deleted=0 and q.popular=true");
	}

	@Override
	public List<T> getUnsolvedQuestion() {
		return this.dao
				.list("select q from Question q where q.solved=0 and q.deleted=0");
	}

	@Override
	public void solved(T q, boolean solved) {
		Question question = (Question) q;
		question.setSolved(solved);
		this.dao.update(q);
	}

	@Override
	public List<T> getAllUOKQuestion() {
		return this.dao.list("select q from Question q where q.deleted=0 and q.ok=0");
	}

	@Override
	public List<T> getUnCheckedQuestions() {
		return this.dao
				.list("select q from Question q where q.ok=0 and q.deleted=0");
	}
	
}
