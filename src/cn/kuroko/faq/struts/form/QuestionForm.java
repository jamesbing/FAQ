package cn.kuroko.faq.struts.form;

import cn.kuroko.faq.bean.Question;
import org.apache.struts.upload.FormFile;

/**
 * @author Kuroko
 * @time 2014-1-5 上午11:01:38
 */

public class QuestionForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String title;
	private String type;
	private String content;
	private boolean my;
	private String replyContent;
	private FormFile file;
	

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public boolean isMy() {
		return my;
	}

	public void setMy(boolean my) {
		this.my = my;
	}

	private Question question = new Question();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

}
