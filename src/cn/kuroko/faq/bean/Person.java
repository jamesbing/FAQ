package cn.kuroko.faq.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.kuroko.faq.constant.Constant;

/**
 * @author Kuroko
 * @time 2013-12-13 下午2:19:38
 */

@Entity
@Table
public class Person extends BaseBean {

	private Integer power;
	private String name;
	private String account;
	private String password;
	private String email;
	private String sex;
	private String ipCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastActived;
	private String ipLastActived;
	private String tel;
	private String workspace;
	private String setQID;
	private String language;
	private String setAutoPermit;
	private String pubMailAdr;
	private String pubMailPsw;
	private String pubMailHost;
	

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIpCreated() {
		return ipCreated;
	}

	public void setIpCreated(String ipCreated) {
		this.ipCreated = ipCreated;
	}

	public Date getDateLastActived() {
		return dateLastActived;
	}

	public void setDateLastActived(Date dateLastActived) {
		this.dateLastActived = dateLastActived;
	}

	public String getIpLastActived() {
		return ipLastActived;
	}

	public void setIpLastActived(String ipLastActived) {
		this.ipLastActived = ipLastActived;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof Person))
			return false;
		return ((Person) obj).getId() == this.getId();
	}
	
	public boolean isOk(){
		return (this.power & Constant.POWER_NEED_CHECK) != Constant.POWER_NEED_CHECK;
	}
	
	public boolean isExpert() {
		return ((this.power & Constant.POWER_EXPERT) == Constant.POWER_EXPERT);
	}

	public String getSetQID() {
		return setQID;
	}

	public void setSetQID(String setQID) {
		this.setQID = setQID;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSetAutoPermit() {
		return setAutoPermit;
	}

	public void setSetAutoPermit(String setAutoPermit) {
		this.setAutoPermit = setAutoPermit;
	}

	public String getPubMailAdr() {
		return pubMailAdr;
	}

	public void setPubMailAdr(String pubMailAdr) {
		this.pubMailAdr = pubMailAdr;
	}

	public String getPubMailPsw() {
		return pubMailPsw;
	}

	public void setPubMailPsw(String pubMailPsw) {
		this.pubMailPsw = pubMailPsw;
	}

	public String getPubMailHost() {
		return pubMailHost;
	}

	public void setPubMailHost(String pubMailHost) {
		this.pubMailHost = pubMailHost;
	}

}
