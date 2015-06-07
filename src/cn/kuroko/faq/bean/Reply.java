package cn.kuroko.faq.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Kuroko
 * @time 2013-12-13 下午3:23:44
 */

@Entity
@Table
public class Reply extends BaseBean {

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	private String filePath;

	
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "longtext")
	private String content;
	
	//排错测试，无实际意义
//	private String title;

	private Boolean ok = false;

	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Person author;

	private int floor;

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


}
