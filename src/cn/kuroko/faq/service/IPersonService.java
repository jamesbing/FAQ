package cn.kuroko.faq.service;

import java.util.List;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.ExpertType;
import cn.kuroko.faq.bean.Person;

/**
 * @author Kuroko
 * @time 2013-12-23 上午11:34:28
 */

public interface IPersonService<T extends BaseBean> extends IService<T> {

	public T findPersonByAccount(String account);
	

	public T getPerson(String account, String password);

	public List<ExpertType> getExpertType(T expert);

	public List<T> getExpert(String type);

	public void setExpertType(Person person, String type1, String type2,
			String type3);
	
	public T getTypeByName(String name);
	
	//*************************3.14 quinn
	public String getExpertNameById(Integer id);

	public List<T> getAllAdmin();

	
}
