package cn.kuroko.faq.service;

import java.util.List;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.bean.Type;

/**
 * @author Kuroko
 * @time 2014-1-5 上午11:35:51
 */

public interface IQuestionService<T extends BaseBean> extends IService<T> {

	public List<T> getQuestionByType(String t1, String t2, String t3);

	public List<Type> getQuestionType1();

	public List<Type> getQuestionType2(String qtype1);

	public List<Type> getQuestionType3(String qtype2);

	// public List<Person> getExpert();

	public List<T> getAllQuestion();

	public List<T> getPopularQuestion();
	
	public List<T> getUnsolvedQuestion();

	public List<T> getQuestion(int start, int max);

	public List<T> search(String[] lstTitle, String[] lstType,
			String[] lstContent, Person person, boolean my);

	public void permit(T q);

	public void popular(T q, boolean popular);
	 
//	public void unsolved(T q, boolean unsolved);

	void solved(T q, boolean solved);

	public List<T> getAllUOKQuestion();

	public List<T> getUnCheckedQuestions(); 

}
