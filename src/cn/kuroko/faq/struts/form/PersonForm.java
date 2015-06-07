package cn.kuroko.faq.struts.form;

import cn.kuroko.faq.bean.Person;

/**
 * @author Kuroko
 * @time 2013-12-23 下午12:06:59
 */

public class PersonForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private Person person = new Person();
	private String password;
	private Boolean isExpert;
	private String expertType;
	private String type1, type2, type3;

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public Boolean getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(Boolean isExpert) {
		this.isExpert = isExpert;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
