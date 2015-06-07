package cn.kuroko.faq.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kuroko
 * @time 2014-1-14 下午6:35:26
 */

@Entity
@Table
public class Type extends BaseBean {

	private String name;
	private String parent;
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}
