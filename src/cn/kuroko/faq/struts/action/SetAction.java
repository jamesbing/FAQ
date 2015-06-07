package cn.kuroko.faq.struts.action;

import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.struts.form.QuestionForm;
import cn.kuroko.faq.util.PersonInfo;
import cn.kuroko.faq.util.PersonUtil;
import cn.kuroko.faq.util.StringUtil;

/**
 * @author Kuroko
 * @time 2014-1-5 上午10:58:32
 */

public class SetAction extends BaseAction {

	private IQuestionService<Question> questionService;
	private IPersonService<Person> personService;
	private IReplyService<Reply> replyService;

	@Override
	public ActionForward output(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	
}
