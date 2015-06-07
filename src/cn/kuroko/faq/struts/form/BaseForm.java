package cn.kuroko.faq.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * @author Kuroko
 * @time 2013-12-23 上午10:23:59
 */

public class BaseForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	protected String title;
	protected String action;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
