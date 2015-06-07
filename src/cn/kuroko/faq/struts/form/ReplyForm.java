package cn.kuroko.faq.struts.form;


import cn.kuroko.faq.bean.Reply;
import org.apache.struts.upload.FormFile;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:42:09
 */

public class ReplyForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private Reply reply = new Reply();

	private String content;

	private Integer questionId;

	private Integer expertId;

	private FormFile file;

	
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getExpertId() {
		return expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}


	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

}
