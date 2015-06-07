package cn.kuroko.faq.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.kuroko.faq.struts.form.BaseForm;

/**
 * @author Kuroko
 * @time 2013-12-23 上午10:32:49
 */

public abstract class BaseAction extends DispatchAction {
	protected Logger logger = LogManager.getLogger(BaseAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BaseForm baseForm = (BaseForm) form;
		baseForm.setTitle("出错啦");
		if (null == baseForm.getAction() || 0 == baseForm.getAction().length())
			return this.output(mapping, form, request, response);
		return super.execute(mapping, form, request, response);
	}

	public abstract ActionForward output(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response);

}
