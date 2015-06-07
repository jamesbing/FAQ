package cn.kuroko.faq.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Kuroko
 * @time 2014-1-23 上午11:18:19
 */

@Entity
@Table
public class ExpertType extends BaseBean {

	@ManyToOne
	@JoinColumn(name = "expert_id")
	private Person expert;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;

	public Person getExpert() {
		return expert;
	}

	public void setExpert(Person expert) {
		this.expert = expert;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
