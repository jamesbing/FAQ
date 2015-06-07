package cn.kuroko.faq.util;

import java.io.Serializable;

import cn.kuroko.faq.constant.Constant;

/**
 * @author Kuroko
 * @time 2013-12-23 下午12:29:10
 */

public class PersonInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String account;
	private Integer power;
	private String sex;

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isExpert() {
		if (null == this.power)
			return false;
		return (this.power & Constant.POWER_EXPERT) == Constant.POWER_EXPERT;
	}

	public boolean isAdmin() {
		if (null == this.power)
			return false;
		return (this.power & Constant.POWER_ADMIN) == Constant.POWER_ADMIN;
	}
	public boolean isNotAdmin(){
		boolean admin = !isAdmin();
//				(this.power==null)||(this.power!=Constant.POWER_ADMIN);
		return admin;
	}
	
	public boolean isNotExpert(){
		boolean expert = !isExpert();
		return expert;
	}

	public boolean isNeedCheck() {
		if (null == this.power)
			return false;
		return (this.power & Constant.POWER_NEED_CHECK) == Constant.POWER_NEED_CHECK;
	}

	public boolean isMale(){
		if(null == this.sex)
			return false;
		return (this.sex == Constant.SEX);
	}
	
	public boolean isFemale(){
		boolean isFemale = !isMale();
		return isFemale;
	}

}
