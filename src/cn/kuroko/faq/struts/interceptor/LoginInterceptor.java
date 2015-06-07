package cn.kuroko.faq.struts.interceptor;

import java.lang.reflect.Method;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.springframework.aop.MethodBeforeAdvice;

import cn.kuroko.faq.struts.action.BaseAction;
import cn.kuroko.faq.struts.action.PersonAction;
import cn.kuroko.faq.struts.form.PersonForm;
import cn.kuroko.faq.util.PersonUtil;

/**
 * @author Kuroko
 * @time 2013-12-23 下午4:01:12
 */

public class LoginInterceptor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// ActionMapping mapping = (ActionMapping) args[0];
		ActionForm form = (ActionForm) args[1];
		HttpServletRequest request = (HttpServletRequest) args[2];
		HttpServletResponse response = (HttpServletResponse) args[3];
		BaseAction baseAction = (BaseAction) target;
		// BaseForm baseForm = (BaseForm) form;
		boolean needsCheck = false;
		if (baseAction instanceof PersonAction) {
			if (form instanceof PersonForm)
				if (((PersonForm) form).getAction().equals("view"))
					needsCheck = true;
		} else if (true) {
			// TODO 加拦截器
		}
		if (needsCheck && PersonUtil.getPersonInfo(request, response) == null) {
			throw new AccountException("您还没有登录,无权查看该页面");
		}
	}
}
