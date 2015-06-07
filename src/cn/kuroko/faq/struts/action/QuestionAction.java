package cn.kuroko.faq.struts.action;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.struts.form.QuestionForm;
import cn.kuroko.faq.struts.form.ReplyForm;
import cn.kuroko.faq.util.PersonInfo;
import cn.kuroko.faq.util.PersonUtil;
import cn.kuroko.faq.util.StringUtil;


/**
 * @author Kuroko
 * @time 2014-1-5 上午10:58:32
 */

public class QuestionAction extends BaseAction {

	private IQuestionService<Question> questionService;
	private IPersonService<Person> personService;
	private IReplyService<Reply> replyService;
	
	
//	
//	public void startAutoMachine(){
//		Timer timer = new Timer();
//		System.out.println("开始执行");
//		timer.schedule(new TimerTaskDailyEmail(questionService, replyService, personService), 1000);
//	}
//	

	@Override
	public ActionForward output(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public ActionForward getQtype2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String qtype1 = request.getParameter("qtype1");
		List<Type> lstType = this.questionService.getQuestionType2(qtype1);
		StringBuilder sb = new StringBuilder();
		sb.append("<select onchange=\"getType('type3')\" name=\"question.qtype2\" style=\"height:22px\">");
		sb.append("<option value=\"\" />");
		for (Type type : lstType) {
			sb.append("<option value=\"" + type.getName() + "\">"
					+ type.getName() + "</option>");
		}
		sb.append("</select>");
		request.setAttribute("html", sb.toString());
		return mapping.findForward("ajax");
	}

	public ActionForward getQtype3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String qtype2 = request.getParameter("qtype2");
		List<Type> lstType = this.questionService.getQuestionType3(qtype2);
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"question.qtype3\" style=\"height:22px\">");
		sb.append("<option value=\"\" />");
		for (Type type : lstType) {
			sb.append("<option value=\"" + type.getName() + "\">"
					+ type.getName() + "</option>");
		}
		sb.append("</select>");
		request.setAttribute("html", sb.toString());
		return mapping.findForward("ajax");
	}

	public ActionForward getExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Person> lstExpert = this.personService.getExpert(null);
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"question.qtype3\" style=\"height:22px\">");
		for (Person p : lstExpert) {
			sb.append("<option value=\"" + p.getId() + "\">" + p.getName()
					+ "</option>");
		}
		sb.append("</select>");
		request.setAttribute("html", sb.toString());
		return mapping.findForward("ajax");
	}

	public ActionForward initAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("提交问题");
		request.setAttribute("qtype1", this.questionService.getQuestionType1());
		if (null != request.getParameter("expert"))
			request.setAttribute(
					"expert",
					this.personService.find(Person.class,
							Integer.parseInt(request.getParameter("expert"))));
		else {
			List<Person> lstExpert = this.personService.getExpert(null);
			request.setAttribute("lstExpert", lstExpert);
		}
		return mapping.findForward("add");
	}
	
	
	public ActionForward initAdd_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("Submit Question");
		request.setAttribute("qtype1", this.questionService.getQuestionType1());
		if (null != request.getParameter("expert"))
			request.setAttribute(
					"expert",
					this.personService.find(Person.class,
							Integer.parseInt(request.getParameter("expert"))));
		else {
			List<Person> lstExpert = this.personService.getExpert(null);
			request.setAttribute("lstExpert", lstExpert);
		}
		return mapping.findForward("add_ENG");
	}

	
//	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		QuestionForm questionForm = (QuestionForm) form;
//		FormFile file = questionForm.getFile();
//		String filename = file.getFileName();
//		FileOutputStream fos = new FileOutputStream("c:\\"+filename);
//		fos.write(file.getFileData());
//		fos.flush();
//		fos.close();
//	}
//	
//	
//	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		 request.setCharacterEncoding("utf-8");  
//	     response.setCharacterEncoding("utf-8");  
//	        response.setContentType("text/html");  
		QuestionForm questionForm = (QuestionForm) form;
		Question q = questionForm.getQuestion();
		questionForm.setTitle("提交问题");
//		String folder="../temp";
//		java.io.File f = new java.io.File(folder);
//		//判断路径是否存在，不存在则创建
//		if(!f.exists()){
//			f.mkdir();
//		}

		
		FormFile file = questionForm.getFile();
		String fileType = file.getFileName();
		fileType = fileType.substring(fileType.lastIndexOf(".")+1).toLowerCase();
		System.out.println(fileType);
		
		
		if(file.getFileSize()/1024>2000){
			request.setAttribute("message", "您上传的附件过大，请您重新选择！");
			return this.initAdd(mapping, questionForm, request, response);
		}

		String filepath = request.getSession().getServletContext().getRealPath("/attachment");
		java.io.File f = new java.io.File(filepath);
		if(!f.exists()){
			f.mkdir();
		}
//		filepath = filepath+"/attachment";

		
        System.out.println("测试路径"+filepath);  
		String fileName = filepath+"/"+System.currentTimeMillis()+"."+fileType;

		//this filepath is used to sending a path value to the Question Bean and load it into DB
		String filePath = fileName;
	
		FileOutputStream fos = new FileOutputStream(fileName);
		System.out.println("存储附件地址："+fileName);
		fos.write(file.getFileData());
		fos.flush();
		fos.close();
		
		
		if (null == PersonUtil.getPersonInfo(request, response))
			throw new AccountException("请先登录");
		PersonInfo pi = PersonUtil.getPersonInfo(request, response);
		if (pi.isNeedCheck())
			throw new AccountException("您的账号处于未审核状态，不能进行此操作...");
		if (null == q.getContent() || "".equals(q.getContent())) {
			request.setAttribute("message", "请输入问题描述");
			return this.initAdd(mapping, questionForm, request, response);
		}
		if (null == q.getTitle() || "".equals(q.getTitle())) {
			request.setAttribute("message", "请输入题目");
			return this.initAdd(mapping, questionForm, request, response);
		}
		if (null != this.personService)
			q.setAuthor(this.personService.findPersonByAccount(PersonUtil
					.getPersonInfo(request, response).getAccount()));
		Question question = questionForm.getQuestion();
		if (question.getQtype1().equals("意见建议")) {
			question.setPub(1);
		} else
			question.setOk(false);
		
		question.setFilePath(filePath);
		question.setDeleted(false);
		question.setPopular(false);
		question.setDateCreated(new Date());
		question.setTimeCreated(new Date());
		this.questionService.create(question);
		//本行实现的是，每天的问题数统计。每增加一个问题就在此处加1，以便每天给管理员发邮件的时候发送相关统计数据。ENGlish版的于此相同。
		if(StartServer.getTodayFirstQuestion() == 0){
			StartServer.SetTodayFirstQuestion(question.getId());
		}
		StartServer.addTodayTotalQuestions();

//若需要添加一个对问题的审核时限，则开放下面注释种的代码即可。
//		Timer timer = new Timer();
//		List<Person> lstPerson = this.personService.getAllAdmin();
//		int timeCount = Integer.parseInt(lstPerson.get(0).getSetAutoPermit());
//		long timeCountToExe = (long)(timeCount*3600000);
//		timer.schedule(new TimerTaskQuestion(this.questionService, question), timeCountToExe);
//		
		
		request.setAttribute("message", "提交成功");
		return new ActionForward("success", "/success.jsp", false);
		}

	public ActionForward add_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		 request.setCharacterEncoding("utf-8");  
//	     response.setCharacterEncoding("utf-8");  
//	        response.setContentType("text/html");  
		QuestionForm questionForm = (QuestionForm) form;
		Question q = questionForm.getQuestion();
		questionForm.setTitle("Add Question");
//		String folder="../temp";
//		java.io.File f = new java.io.File(folder);
//		//判断路径是否存在，不存在则创建
//		if(!f.exists()){
//			f.mkdir();
//		}

		
		FormFile file = questionForm.getFile();
		String fileType = file.getFileName();
		fileType = fileType.substring(fileType.lastIndexOf(".")+1).toLowerCase();
		System.out.println(fileType);
		
		
		if(file.getFileSize()/1024>2000){
			request.setAttribute("message", "Please make sure that your file size is less than 2MB！");
			return this.initAdd_ENG(mapping, questionForm, request, response);
		}

		String filepath = request.getSession().getServletContext().getRealPath("/attachment");
		java.io.File f = new java.io.File(filepath);
		if(!f.exists()){
			f.mkdir();
		}
//		filepath = filepath+"/attachment";

		
        System.out.println("测试路径"+filepath);  
		String fileName = filepath+"/"+System.currentTimeMillis()+"."+fileType;

		//this filepath is used to sending a path value to the Question Bean and load it into DB
		String filePath = fileName;
	
		FileOutputStream fos = new FileOutputStream(fileName);
		System.out.println("存储附件地址："+fileName);
		fos.write(file.getFileData());
		fos.flush();
		fos.close();
		
		
		if (null == PersonUtil.getPersonInfo(request, response))
			throw new AccountException("Please login");
		PersonInfo pi = PersonUtil.getPersonInfo(request, response);
		if (pi.isNeedCheck())
			throw new AccountException("Sorry,you can not do this because your account is waiting for approval...");
		if (null == q.getContent() || "".equals(q.getContent())) {
			request.setAttribute("message", "Please enter the description of your question");
			return this.initAdd_ENG(mapping, questionForm, request, response);
		}
		if (null == q.getTitle() || "".equals(q.getTitle())) {
			request.setAttribute("message", "Please enter a title");
			return this.initAdd_ENG(mapping, questionForm, request, response);
		}
		if (null != this.personService)
			q.setAuthor(this.personService.findPersonByAccount(PersonUtil
					.getPersonInfo(request, response).getAccount()));
		Question question = questionForm.getQuestion();
		if (question.getQtype1().equals("意见建议")) {
			question.setPub(1);
		} else
			question.setOk(false);
		
		question.setFilePath(filePath);
		question.setDeleted(false);
		question.setPopular(false);
		question.setDateCreated(new Date());
		question.setTimeCreated(new Date());
		this.questionService.create(question);
		
		if(StartServer.getTodayFirstQuestion() == 0){
			StartServer.SetTodayFirstQuestion(question.getId());
		}
		StartServer.addTodayTotalQuestions();
		
//如果需要设置问题的审核时限，则开放以下注释的代码
//		Timer timer = new Timer();
//		List<Person> lstPerson = this.personService.getAllAdmin();
//		int timeCount = Integer.parseInt(lstPerson.get(0).getSetAutoPermit());
//		long timeCountToExe = (long)(timeCount*3600000);
//		timer.schedule(new TimerTaskQuestion(this.questionService, question), timeCountToExe);
//		
//		
		request.setAttribute("message", "Submit Successfully.");
		return new ActionForward("success", "/success_ENG.jsp", false);
		}

	
	
	
	public ActionForward initList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Person p = null;
		boolean my = false;
		
		if (null != PersonUtil.getPersonInfo(request, response)) {
			p = this.personService.findPersonByAccount(PersonUtil
					.getPersonInfo(request, response).getAccount());
		}
		String strMy = request.getParameter("my");
		if (null != strMy && strMy.equals("on")) {
			if (null == p) {
				request.setAttribute("message", "查看与我有关的问题前请先登录");
				
			}
			my = true;
		}
		String[] lstTitle = null;
		String[] lstType = null;
		String[] lstContent = null;
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_title")))
			lstTitle = request.getParameter("s_title").trim().split(" ");
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_type")))
			lstType = request.getParameter("s_type").trim().split(" ");
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_content")))
			lstContent = request.getParameter("s_content").trim().split(" ");
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题列表");
		// request.setAttribute("qtype1",
		// this.questionService.getQuestionType1());
		// String ep = request.getParameter("ep");
		// String s = request.getParameter("s");
		// int maxQuestionEveryPage = Constant.MAX_QUESTION_EVERY_PAGE;
		// int start = 0;
		// try {
		// maxQuestionEveryPage = Integer.parseInt(ep);
		// } catch (Exception e) {
		// maxQuestionEveryPage = Constant.MAX_QUESTION_EVERY_PAGE;
		// }
		// try {
		// start = Integer.parseInt(s);
		// } catch (Exception e) {
		// start = 0;
		// }
		// int questionCount = this.questionService
		// .getTotalCount("select q from Question q");
		// request.setAttribute("mp", questionCount / maxQuestionEveryPage + 1);
		// request.setAttribute("np", start / maxQuestionEveryPage + 1);
		//TODO
		
		
		request.setAttribute("prefix", this.personService.getAllAdmin());		
		request.setAttribute("lstQuestion", this.questionService.search(
				lstTitle, lstType, lstContent, p, my));
		
		
		return mapping.findForward("list");
	}
	
	
	public ActionForward initList_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Person p = null;
		boolean my = false;
		if (null != PersonUtil.getPersonInfo(request, response)) {
			p = this.personService.findPersonByAccount(PersonUtil
					.getPersonInfo(request, response).getAccount());
		}
		String strMy = request.getParameter("my");
		if (null != strMy && strMy.equals("on")) {
			if (null == p) {
				request.setAttribute("message", "Please login before you check the questions!");
				return mapping.findForward("list_ENG");
			}
			my = true;
		}
		String[] lstTitle = null;
		String[] lstType = null;
		String[] lstContent = null;
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_title")))
			lstTitle = request.getParameter("s_title").trim().split(" ");
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_type")))
			lstType = request.getParameter("s_type").trim().split(" ");
		if (!StringUtil.isNullOrEmpty(request.getParameter("s_content")))
			lstContent = request.getParameter("s_content").trim().split(" ");
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("Question List");
		// request.setAttribute("qtype1",
		// this.questionService.getQuestionType1());
		// String ep = request.getParameter("ep");
		// String s = request.getParameter("s");
		// int maxQuestionEveryPage = Constant.MAX_QUESTION_EVERY_PAGE;
		// int start = 0;
		// try {
		// maxQuestionEveryPage = Integer.parseInt(ep);
		// } catch (Exception e) {
		// maxQuestionEveryPage = Constant.MAX_QUESTION_EVERY_PAGE;
		// }
		// try {
		// start = Integer.parseInt(s);
		// } catch (Exception e) {
		// start = 0;
		// }
		// int questionCount = this.questionService
		// .getTotalCount("select q from Question q");
		// request.setAttribute("mp", questionCount / maxQuestionEveryPage + 1);
		// request.setAttribute("np", start / maxQuestionEveryPage + 1);
		//TODO
		
		
		request.setAttribute("prefix", this.personService.getAllAdmin());		
		request.setAttribute("lstQuestion", this.questionService.search(
				lstTitle, lstType, lstContent, p, my));
		return mapping.findForward("list_ENG");
	}
	
	
	

	public ActionForward initPopular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("常见问题");
		List<Question> lstQuestion = this.questionService.getPopularQuestion();
		request.setAttribute("prefix", this.personService.getAllAdmin());	
		request.setAttribute("s", "stringtest");
		request.setAttribute("lstQuestion", lstQuestion);
		return mapping.findForward("popularList");
	}
	
	public ActionForward initPopular_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("Popular Questions");
		List<Question> lstQuestion = this.questionService.getPopularQuestion();
		request.setAttribute("prefix", this.personService.getAllAdmin());	
		request.setAttribute("s", "stringtest");
		request.setAttribute("lstQuestion", lstQuestion);
		return mapping.findForward("popularList_ENG");
	}

	public ActionForward initQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题详细信息");
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		List<Reply> lstReply = this.replyService.getQuestionReply(q);
		
		request.setAttribute("lstReply", lstReply);
		request.setAttribute("question", q);
		//*************************3.14
		request.setAttribute("expername", this.personService.getExpertNameById(q.getExpertId()));
		//*************************3.14
		request.setAttribute("lstExpert", this.personService.getExpert(null));
		String path = q.getFilePath();
		System.out.println(path);
		request.setAttribute("attachment", q.getFilePath());

		return mapping.findForward("get");
	}
	public ActionForward initQuestion_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("The question detail");
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		List<Reply> lstReply = this.replyService.getQuestionReply(q);
		
		request.setAttribute("lstReply", lstReply);
		request.setAttribute("question", q);
		//*************************3.14
		request.setAttribute("expername", this.personService.getExpertNameById(q.getExpertId()));
		//*************************3.14
		request.setAttribute("lstExpert", this.personService.getExpert(null));
		String path = q.getFilePath();
		System.out.println(path);
		request.setAttribute("attachment", q.getFilePath());

		return mapping.findForward("get_ENG");
	}

	//use the next permit function.
//	public ActionForward permit(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("问题详细信息");
//		Question q = this.questionService.find(Question.class,
//				Integer.parseInt(request.getParameter("id")));
//		request.setAttribute("question", q);
//		this.questionService.permit(q);
//		return this.initQuestion(mapping, form, request, response);
//	}
//	
	
	public ActionForward permitWithoutLeap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		q.setOk(true);
		this.questionService.update(q);
		//此处统计每天审核通过的问题数。
		
		if(q.getId() > (StartServer.getTodayFirstQuestion()-1))
		{
			StartServer.addTodayCheckedQuestions();
		}
		return getUnCheckedQuestion(mapping, form, request, response);
	}
	
	
	

	public ActionForward permit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		q.setOk(true);
		this.questionService.update(q);
		//此处统计每天审核通过的问题数。
		
		if(q.getId() > (StartServer.getTodayFirstQuestion()-1))
		{
			StartServer.addTodayCheckedQuestions();
		}
		return unsolvedQuestion(mapping, form, request, response);
	}

	
	
	
	//use next delete function.
//	public ActionForward delete(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("问题检索");
//		Question q = this.questionService.find(Question.class,
//				Integer.parseInt(request.getParameter("id")));
//		this.questionService.delete(q);
//		return this.initList(mapping, form, request, response);
//	}
	
	
	public ActionForward deleteWithoutLeap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		q.setDeleted(true);
		this.questionService.update(q);
		
		if(q.getId() > (StartServer.getTodayFirstQuestion()-1))
		{
			StartServer.deleteTodayQuestions();
		}
		
		return getUnCheckedQuestion(mapping, form, request, response);
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		q.setDeleted(true);
		this.questionService.update(q);
		
		if(q.getId() > (StartServer.getTodayFirstQuestion()-1))
		{
			StartServer.deleteTodayQuestions();
		}
		
		return unsolvedQuestion(mapping, form, request, response);
	}

	public ActionForward popular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题详细信息");
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		request.setAttribute("question", q);
		this.questionService.popular(q, true);
		return mapping.findForward("get");
	}

	public ActionForward repopular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题详细信息");
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("id")));
		request.setAttribute("question", q);
		this.questionService.popular(q, false);
		return mapping.findForward("get");
	}

	public ActionForward chooseExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题详细信息");
		Question q = this.questionService.find(Question.class,
				Integer.parseInt(request.getParameter("qid")));
		q.setExpertId(Integer.parseInt(request.getParameter("eid")));
		this.questionService.update(q);
		request.setAttribute("question", q);
		return null;
	}

	
	
	public ActionForward setPrefix(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String prefix = request.getParameter("preString");
//		List<Question> lstQuestion = this.questionService.getPopularQuestion();
		List<Person> lstPerson = this.personService.getAllAdmin();
		for(int i = 0; i < lstPerson.size(); i++){
			lstPerson.get(i).setSetQID(prefix);
			this.personService.update(lstPerson.get(i));
		}
		request.setAttribute("message", "设置成功.");
		return new ActionForward("success", "/success.jsp", false);
	
	}
	
	public ActionForward setAutoPermitReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String autoTime = request.getParameter("autoTime");

	//		List<Question> lstQuestion = this.questionService.getPopularQuestion();
			List<Person> lstPerson = this.personService.getAllAdmin();
			for(int i = 0; i < lstPerson.size(); i++){
				lstPerson.get(i).setSetAutoPermit(autoTime);
				this.personService.update(lstPerson.get(i));
			}
		request.setAttribute("message", "设置成功.");
		
		return new ActionForward("success", "/success.jsp", false);
	}
	
	public ActionForward setEmailAdr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mailAdr = request.getParameter("mailAdr");

	//		List<Question> lstQuestion = this.questionService.getPopularQuestion();
			List<Person> lstPerson = this.personService.getAllAdmin();
			for(int i = 0; i < lstPerson.size(); i++){
				lstPerson.get(i).setPubMailAdr(mailAdr);
				this.personService.update(lstPerson.get(i));
			}
		request.setAttribute("message", "设置成功.");
		
		return new ActionForward("success", "/success.jsp", false);
	}
	
	public ActionForward setEmailPsw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mailPsw = request.getParameter("mailPsw");

	//		List<Question> lstQuestion = this.questionService.getPopularQuestion();
			List<Person> lstPerson = this.personService.getAllAdmin();
			for(int i = 0; i < lstPerson.size(); i++){
				lstPerson.get(i).setPubMailPsw(mailPsw);
				this.personService.update(lstPerson.get(i));
			}
		request.setAttribute("message", "设置成功.");
		
		return new ActionForward("success", "/success.jsp", false);
	}
	
	public ActionForward setEmailHost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mailHost = request.getParameter("mailHost");

	//		List<Question> lstQuestion = this.questionService.getPopularQuestion();
			List<Person> lstPerson = this.personService.getAllAdmin();
			for(int i = 0; i < lstPerson.size(); i++){
				lstPerson.get(i).setPubMailHost(mailHost);
				this.personService.update(lstPerson.get(i));
			}
		request.setAttribute("message", "设置成功.");
		
		return new ActionForward("success", "/success.jsp", false);
	}
	
	// public ActionForward search(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// QuestionForm qf = (QuestionForm) form;
	// if (StringUtil.isNullOrEmpty(qf.getTitle())
	// && StringUtil.isNullOrEmpty(qf.getType())
	// && StringUtil.isNullOrEmpty(qf.getContent())) {
	// request.setAttribute("message", "请填写至少一个查询条件");
	// return this.initList(mapping, form, request, response);
	// }
	// StringBuilder sb = new StringBuilder();
	// String[] lstTitle = null;
	// String[] lstType = null;
	// String[] lstContent = null;
	// if (!StringUtil.isNullOrEmpty(qf.getTitle()))
	// lstTitle = qf.getTitle().trim().split(" ");
	// if (!StringUtil.isNullOrEmpty(qf.getType()))
	// lstType = qf.getType().trim().split(" ");
	// if (!StringUtil.isNullOrEmpty(qf.getContent()))
	// lstContent = qf.getContent().trim().split(" ");
	//
	// }
	
	public IQuestionService<Question> getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService<Question> questionService) {
		this.questionService = questionService;
	}

	public IPersonService<Person> getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService<Person> personService) {
		this.personService = personService;
	}

	public IReplyService<Reply> getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService<Reply> replyService) {
		this.replyService = replyService;
	}
	
	public ActionForward unsolvedQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("问题管理");
		List<Question> lstQuestion = this.questionService.getUnsolvedQuestion();
		request.setAttribute("lstQuestion", lstQuestion);
		return mapping.findForward("unsolvedList");
	}
	
	public ActionForward unsolvedQuestion_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("Unsolved Questons");
		List<Question> lstQuestion = this.questionService.getUnsolvedQuestion();
		request.setAttribute("lstQuestion", lstQuestion);
		return mapping.findForward("unsolvedList_ENG");
	}
	
//该方法已取消
//	public ActionForward unsolvedAndManagement(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("问题设置/未解答问题");
//		List<Question> lstQuestion = this.questionService.getUnsolvedQuestion();
//		request.setAttribute("lstQuestion", lstQuestion);
//		return mapping.findForward("unsolvedListAndManagement");
//	}
	
	public ActionForward adminSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("管理员设置");
		return mapping.findForward("adminSet");
	}
	
	
	public ActionForward getUnCheckedQuestion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		QuestionForm qf = (QuestionForm) form;
		qf.setTitle("未审核问题");
		List<Question> lstQuestion = this.questionService.getUnCheckedQuestions();
		request.setAttribute("lstUnCheckedQuestions", lstQuestion);
		return mapping.findForward("unCheckedQuestions");
	}
	

//	request.setCharacterEncoding("utf-8");  
//    response.setCharacterEncoding("utf-8");  
//    response.setContentType("text/html");  
//
//    PrintWriter out = response.getWriter();  
//
//    // 设置上传文件保持路径  
//    String filepath = "C:\\";
//    System.out.println("测试路径"+filepath);  
//    File uploadDir = new File(filepath);  
//
//    // 如果该文件夹不存在则创建  
//    if (!uploadDir.exists()) {  
//        uploadDir.mkdirs();  
//    }  
//
//    // 获取服务器默认的临时文件夹  
//    String temp = System.getProperty("java.io.tmpdir");  
//    File tempDir = new File(temp);  
//
//    // 如果该文件夹不存在，则创建  
//    if (!tempDir.exists()) {  
//        tempDir.mkdirs();  
//    }  
//    // 创建文件项目工厂对象  
//    DiskFileItemFactory factory = new DiskFileItemFactory();  
//    // 设置临时文件夹  
//    factory.setRepository(new File(temp));  
//    // 设置缓冲区大小为 1M  
//    factory.setSizeThreshold(1024 * 1024);  
//    ServletFileUpload serveltFileUpload = new ServletFileUpload(factory);  
//
//    // 解析文件上传请求，解析结果放在 List中  
//    try {  
//        List<FileItem> list = serveltFileUpload.parseRequest(request);  
//        for (FileItem item : list) {  
//            // 判断某项是否是普通的表单类型。  
//            if (item.isFormField()) { //该表单项是普通类型  
//                // 忽略简单form字段而不是上传域的文件域  
//                continue;  
//                  
//            } else { // 否则文件域  
//                // 获取上传文件名  
//                String fileName = item.getName();  
//                // 此时的文件名包含了完整的路径，得注意加工一下  
//                System.out.println("完整的文件名：" + fileName);   
//                int index = fileName.lastIndexOf("\\");    
//                fileName = fileName.substring(index + 1, fileName.length());  
//                // 创建文件输入流  
//                InputStream in = item.getInputStream();  
//                // 创建一个指向 File 对象所表示的文件中写入数据的文件输出流  
//                FileOutputStream outs = new FileOutputStream(new File(  
//                        filepath, fileName));  
//                int len = 0;  
//                // 创建字节数组 ，大小位 1024字节  
//                byte[] buffer = new byte[1024];  
//                out.println("附件名称：" + fileName + "<br/>");  
//                out.println("附件大小：" + item.getSize() / 1024 + " KB（"  
//                        + item.getSize() + " 字节）<br/>");  
//                while ((len = in.read(buffer)) != -1) {  
//                    // 字节数组从偏移量 0 到len 个字节写入文件输出流  
//                    outs.write(buffer, 0, len);  
//                }  
//                // 关闭输入流  
//                in.close();  
//                // 关闭输出流  
//                outs.close();  
//            }  
//             
//        }  
//    } catch (FileUploadException e) {  
//        // TODO Auto-generated catch block  
//        e.printStackTrace();  
//    } finally {  
////    	request.setAttribute("path", filepath); 
////    	
////        RequestDispatcher de=request.getRequestDispatcher("addQuestion.jsp");  
////        de.forward(request, response);
//    }  
//
	
}
