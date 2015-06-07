package cn.kuroko.faq.struts.exception;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author Kuroko
 * @time 2013-12-25 下午12:28:00
 */

public class BaseExceptionHandler extends ExceptionHandler {

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ex.printStackTrace();
		request.setAttribute("exception", ex);
		if (ex instanceof AccountException) {
			request.setAttribute("message", ex.getMessage());
			return new ActionForward("login", "/person/login.jsp", false);
		}
		return new ActionForward("exception", "/exception.jsp", false);
	}

}
