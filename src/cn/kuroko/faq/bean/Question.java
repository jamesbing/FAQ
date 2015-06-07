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
 * @time 2013-12-13 下午3:09:07
 */

@Entity
@Table
public class Question extends BaseBean {

	private String qtype1;
	private String qtype2;
	private String qtype3;
	private String remarks;
	private String title;
	private Boolean ok = false;
	private Boolean popular = false;
	private Integer pub;
	private Boolean solved = false;
	private String prefix_ID;
	private String filePath;

	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "longtext")
	private String content;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Person author;

	private int expertId;

	public int getExpertId() {
		return expertId;
	}

	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}

	public String getQtype1() {
		return qtype1;
	}

	public void setQtype1(String qtype1) {
		this.qtype1 = qtype1;
	}

	public String getQtype2() {
		return qtype2;
	}

	public void setQtype2(String qtype2) {
		this.qtype2 = qtype2;
	}

	public String getQtype3() {
		return qtype3;
	}

	public void setQtype3(String qtype3) {
		this.qtype3 = qtype3;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public Boolean getPopular() {
		return popular;
	}

	public void setPopular(Boolean popular) {
		this.popular = popular;
	}
	
	public Boolean getSolved() {
		return solved;
	}
	
	public void setSolved(Boolean solved){
		this.solved = solved;
	}

	public Integer getPub() {
		return pub;
	}

	public void setPub(Integer pub) {
		this.pub = pub;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPrefix_ID() {
		return prefix_ID;
	}

	public void setPrefix_ID(String prefix_ID) {
		this.prefix_ID = prefix_ID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public boolean isNotOK(){
		return this.ok == true;
	}

}
