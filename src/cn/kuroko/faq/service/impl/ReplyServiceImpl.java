package cn.kuroko.faq.service.impl;

import java.util.List;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:48:51
 */

public class ReplyServiceImpl<T extends BaseBean> extends ServiceImp<T> implements
		IReplyService<T> {

	

	@Override
	public void create(T baseBean) {
		this.dao.create(baseBean);
	}

	@Override
	public List<T> getQuestionReply(Question q) {
		return this.dao.list("select r from Reply r where r.question.id=" + q.getId()
				+ " and r.deleted=0 and r.ok=1");
	}

	@Override
	public List<T> getUnCheckedReply() {
			return this.dao
					.list("select r from Reply r where r.ok=0 and r.deleted=0");
		}

	@Override
	public List<T> getMyAnswers(Person p) {
		return this.dao.list("select r from Reply r where r.author.id =" + p.getId()
				+ " and r.deleted=0 and r.ok=0");	
		}
	@Override
	public List<T> getAllUOKReply() {
		return this.dao.list("select r from Reply r where r.deleted=0 and r.ok=0");
	}


}
