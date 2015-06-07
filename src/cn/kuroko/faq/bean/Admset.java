package cn.kuroko.faq.bean;

import javax.persistence.Entity;

import javax.persistence.Table;

/**
 * @author james
 * @time 2014-5-13 上午10:24:07
 */

@Entity
@Table
public class Admset extends BaseBean {

	private int setId;
	private String prefix_QId;
	
	public int getSetId() {
		return setId;
	}
	
	public void setSetId(int setId) {
		this.setId = setId;
	}
	
	public String getPrefix_QId() {
		return prefix_QId;
	}
	
	public void setPrefix_QId(String prefix_QId) {
		this.prefix_QId = prefix_QId;
	}


}
