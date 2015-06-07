package cn.kuroko.faq.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.constant.Constant;

/**
 * @author Kuroko
 * @time 2013-12-23 下午12:26:47
 */

public class PersonUtil {

	public static PersonInfo getPersonInfo(HttpServletRequest request,
			HttpServletResponse response) {
		return (PersonInfo) request.getSession(true).getAttribute(
				Constant.KEY_PERSON_INFO);
	}

	public static void setPersonInfo(HttpServletRequest request,
			HttpServletResponse response, PersonInfo personInfo) {
		request.getSession(true).setAttribute(Constant.KEY_PERSON_INFO,
				personInfo);
	}

	public static void setPersonInfo(HttpServletRequest request,
			HttpServletResponse response, Person person) {
		PersonInfo pi = new PersonInfo();
		pi.setId(person.getId());
		pi.setAccount(person.getAccount());
		pi.setPower(person.getPower());
		setPersonInfo(request, response, pi);
	}

}
