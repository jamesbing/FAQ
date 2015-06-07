package cn.kuroko.faq.service;

import java.util.List;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:47:39
 */

public interface IReplyService<T extends BaseBean> extends IService<T> {

	public List<T> getQuestionReply(Question q);

	public List<T> getUnCheckedReply();

	public List<T> getMyAnswers(Person p);

	public List<T> getAllUOKReply(); 

}
