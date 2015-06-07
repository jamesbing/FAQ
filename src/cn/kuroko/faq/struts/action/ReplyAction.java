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
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.struts.form.QuestionForm;
import cn.kuroko.faq.struts.form.ReplyForm;
import cn.kuroko.faq.util.MailSendUtil;
import cn.kuroko.faq.util.PersonInfo;
import cn.kuroko.faq.util.PersonUtil;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:52:27
 */

public class ReplyAction extends BaseAction {

	private IPersonService<Person> personService;
	private IQuestionService<Question> questionService;
	private IReplyService<Reply> replyService;

	public IReplyService<Reply> getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService<Reply> replyService) {
		this.replyService = replyService;
	}

	public IPersonService<Person> getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService<Person> personService) {
		this.personService = personService;
	}

	public IQuestionService<Question> getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService<Question> questionService) {
		this.questionService = questionService;
	}

	@Override
	public ActionForward output(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public ActionForward addReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonInfo pi = PersonUtil.getPersonInfo(request, response);
		if (null == pi)
			throw new AccountException("您还没有登录");
		if (pi.isNeedCheck())
			throw new AccountException("您的账号处于未审核状态，不能进行此操作...");
		ReplyForm rf = (ReplyForm) form;
		Reply reply = new Reply();
		
		

//		String folder="../temp";
//		java.io.File f = new java.io.File(folder);
//		//判断路径是否存在，不存在则创建
//		if(!f.exists()){
//			f.mkdir();
//		}

		
		FormFile file = rf.getFile();
		String fileType = file.getFileName();
		fileType = fileType.substring(fileType.lastIndexOf(".")+1).toLowerCase();
		System.out.println(fileType);
		
		
		if(file.getFileSize()/1024>2000){
			request.setAttribute("message", "您上传的附件过大，请您重新选择！");
			return this.initReply(mapping, rf, request, response);
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
		
		
		
		
		reply.setAuthor(this.personService.find(Person.class, rf.getExpertId()));
		reply.setQuestion(this.questionService.find(Question.class,
				rf.getQuestionId()));
		Question question = reply.getQuestion();
		if(question.getSolved()==false){
		question.setSolved(true);
		this.questionService.update(question);
		}
		reply.setContent(rf.getContent());
		reply.setDateCreated(new Date());
		reply.setTimeCreated(new Date());
		
		
		reply.setFilePath(filePath);
		
		
		this.replyService.create(reply);
		Timer timer = new Timer();
		//该次输出用于测试
//		System.out.println(reply.getId());
		//timer用来实现指定延时以后执行某个动作
		List<Person> lstPerson = this.personService.getAllAdmin();
		Person admin = lstPerson.get(0);
		double timeCount = Double.parseDouble(admin.getSetAutoPermit());
		long timeCountToExe = (long)(timeCount*3600000);
		timer.schedule(new TimerTaskReply(this.replyService,admin, reply), timeCountToExe);
		rf.setTitle("回答成功");
		request.setAttribute("message", "回答问题成功!");
		return new ActionForward("success", "/success.jsp", false);
	}
	

	public ActionForward addReply_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonInfo pi = PersonUtil.getPersonInfo(request, response);
		if (null == pi)
			throw new AccountException("Please login");
		if (pi.isNeedCheck())
			throw new AccountException("Sorry,you can not do this because your account is waiting for approval...");
		ReplyForm rf = (ReplyForm) form;
		Reply reply = new Reply();
		
		

//		String folder="../temp";
//		java.io.File f = new java.io.File(folder);
//		//判断路径是否存在，不存在则创建
//		if(!f.exists()){
//			f.mkdir();
//		}

		
		FormFile file = rf.getFile();
		String fileType = file.getFileName();
		fileType = fileType.substring(fileType.lastIndexOf(".")+1).toLowerCase();
		System.out.println(fileType);
		
		
		if(file.getFileSize()/1024>2000){
			request.setAttribute("message", "Your file's size is too big, please change a file.");
			return this.initReply_ENG(mapping, rf, request, response);
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
		
		
		
		
		reply.setAuthor(this.personService.find(Person.class, rf.getExpertId()));
		reply.setQuestion(this.questionService.find(Question.class,
				rf.getQuestionId()));
		Question question = reply.getQuestion();
		if(question.getSolved()==false){
		question.setSolved(true);
		this.questionService.update(question);
		}
		reply.setContent(rf.getContent());
		reply.setDateCreated(new Date());
		reply.setTimeCreated(new Date());
		
		
		reply.setFilePath(filePath);
		
		
		this.replyService.create(reply);
		Timer timer = new Timer();
		//该次输出用于测试
//		System.out.println(reply.getId());
		//timer用来实现指定延时以后执行某个动作
		List<Person> lstPerson = this.personService.getAllAdmin();
		Person admin = lstPerson.get(0);
		int timeCount = Integer.parseInt(admin.getSetAutoPermit());
		long timeCountToExe = (long)(timeCount*3600000);
		timer.schedule(new TimerTaskReply(this.replyService,admin,reply), timeCountToExe);
		rf.setTitle("Reply success");
		request.setAttribute("message", "Submit successfully!");
		return new ActionForward("success", "/success_ENG.jsp", false);
	}
	

	
	
	public ActionForward initReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("回复详细信息");
		Reply r = this.replyService.find(Reply.class, 
				Integer.parseInt(request.getParameter("id")));
		Question q = r.getQuestion();
		request.setAttribute("reply", r);
		request.setAttribute("question", q);
		request.setAttribute("questionContent", r.getQuestion().getContent());
		request.setAttribute("questionType", r.getQuestion().getQtype2());
		request.setAttribute("erperName", this.personService.getExpertNameById(r.getAuthor().getId()));
		request.setAttribute("replyContent", r.getContent());
		return mapping.findForward("showReply");
	}
	
	public ActionForward initReply_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("回复详细信息");
		Reply r = this.replyService.find(Reply.class, 
				Integer.parseInt(request.getParameter("id")));
		Question q = r.getQuestion();
		request.setAttribute("reply", r);
		request.setAttribute("question", q);
		request.setAttribute("questionContent", r.getQuestion().getContent());
		request.setAttribute("questionType", r.getQuestion().getQtype2());
		request.setAttribute("erperName", this.personService.getExpertNameById(r.getAuthor().getId()));
		request.setAttribute("replyContent", r.getContent());
		return mapping.findForward("showReply_ENG");
	}
	
//	public ActionForward initReply(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("问题详细信息");
//		Question q = this.questionService.find(Question.class,
//				Integer.parseInt(request.getParameter("id")));
//		List<Reply> lstReply = this.replyService.getQuestionReply(q);
//		request.setAttribute("lstReply", lstReply);
//		request.setAttribute("question", q);
//		//*************************3.14
//		request.setAttribute("expername", this.personService.getExpertNameById(q.getExpertId()));
//		//*************************3.14
//		request.setAttribute("lstExpert", this.personService.getExpert(null));
//		return mapping.findForward("get");
//	}
	
	public ActionForward permit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Reply r = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		r.setOk(true);
		this.replyService.update(r);
		List<Person> lstPerson = this.personService.getAllAdmin();
		String serverHostName = lstPerson.get(0).getPubMailHost();
		String fromName = lstPerson.get(0).getPubMailAdr();
		String fromPassword = lstPerson.get(0).getPubMailPsw();
		String toAddress = r.getQuestion().getAuthor().getEmail();
		String subject = "您的问题有新的回答，请登录查看！";
		String content = "您的问题"+r.getQuestion().getTitle()+"已经得到了回答："+r.getContent()+"/n 请登录查看！";
		MailSendUtil.sendMail(serverHostName, fromName, fromPassword, toAddress, subject, content);
		return initUnCheckedReply(mapping, form, request, response);
	}
	
	
//reference:the delete function of the Question serveice.
//	public ActionForward delete(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		QuestionForm qf = (QuestionForm) form;
//		qf.setTitle("问题检索");
//		Question q = this.questionService.find(Question.class,
//				Integer.parseInt(request.getParameter("id")));
//		this.questionService.delete(q);
//		return this.initList(mapping, form, request, response);
//	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Reply r = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		r.setDeleted(true);
		this.replyService.update(r);
		return initUnCheckedReply(mapping, form, request, response);
	}
	
	public ActionForward deleteMyAnswer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Reply r = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		r.setDeleted(true);
		this.replyService.update(r);
		return showMyAnswer(mapping, form, request, response);
	}	
	
	public ActionForward deleteMyAnswer_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Reply r = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		r.setDeleted(true);
		this.replyService.update(r);
		return showMyAnswer_ENG(mapping, form, request, response);
	}	
	
	

	public ActionForward initUnCheckedReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReplyForm rf = (ReplyForm) form;
		rf.setTitle("未审核专家回复");
		List<Question> lstReply = this.questionService.getUnCheckedQuestions();
		request.setAttribute("lstUnCheckedReply", lstReply);
		return mapping.findForward("uncheckedReplyList");
	}
	
//	
//	public ActionForward addReply(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		PersonInfo pi = PersonUtil.getPersonInfo(request, response);
//		if (null == pi)
//			throw new AccountException("您还没有登录");
//		if (pi.isNeedCheck())
//			throw new AccountException("您的账号处于未审核状态，不能进行此操作...");
//		ReplyForm rf = (ReplyForm) form;
//		Reply reply = new Reply();
//		reply.setAuthor(this.personService.find(Person.class, rf.getExpertId()));
//		reply.setQuestion(this.questionService.find(Question.class,
//				rf.getQuestionId()));
//		Question question = reply.getQuestion();
//		if(question.getSolved()==false){
//		question.setSolved(true);
//		this.questionService.update(question);
//		}
//		reply.setContent(rf.getContent());
//		reply.setDateCreated(new Date());
//		reply.setTimeCreated(new Date());
//		this.replyService.create(reply);
//		rf.setTitle("回答成功");
//		request.setAttribute("message", "回答问题成功!");
//		return mapping.findForward("success");
//	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Reply reply = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		//test if the function got the right reply
		System.out.println("帖子编号为"+reply.getId()+"   帖子内容为"+reply.getContent());
		Question question = reply.getQuestion();
		request.setAttribute("reply", reply);
		request.setAttribute("question", question);
		return mapping.findForward("replyedit");
	}
	
	public ActionForward edit_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Reply reply = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		//test if the function got the right reply
		System.out.println("帖子编号为"+reply.getId()+"   帖子内容为"+reply.getContent());
		Question question = reply.getQuestion();
		request.setAttribute("reply", reply);
		request.setAttribute("question", question);
		return mapping.findForward("replyedit_ENG");
	}
	
	
	public ActionForward showMyAnswer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReplyForm rf = (ReplyForm) form;
		rf.setTitle("回复详细信息");
		Person p = this.personService.findPersonByAccount(PersonUtil
				.getPersonInfo(request, response).getAccount());
//		Person p =this.personService.find(Person.class, rf.getExpertId());
		List<Reply> lstReply = this.replyService.getMyAnswers(p);
		request.setAttribute("answerlist", lstReply);
		return mapping.findForward("myanswerreplylist");

	}
	
	public ActionForward showMyAnswer_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReplyForm rf = (ReplyForm) form;
		rf.setTitle("Show Reply");
		Person p = this.personService.findPersonByAccount(PersonUtil
				.getPersonInfo(request, response).getAccount());
//		Person p =this.personService.find(Person.class, rf.getExpertId());
		List<Reply> lstReply = this.replyService.getMyAnswers(p);
		request.setAttribute("answerlist", lstReply);
		return mapping.findForward("myanswerreplylist_ENG");

	}
	
			
	public ActionForward submitEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReplyForm rf = (ReplyForm) form;
		Reply reply = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		reply.setContent(rf.getContent());
		this.replyService.update(reply);
		request.setAttribute("message", "修改成功!");
		return mapping.findForward("success");
	}
	
	public ActionForward submitEdit_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReplyForm rf = (ReplyForm) form;
		Reply reply = this.replyService.find(Reply.class,
				Integer.parseInt(request.getParameter("id")));
		reply.setContent(rf.getContent());
		this.replyService.update(reply);
		request.setAttribute("message", "Successfully updated!");
		return mapping.findForward("success_ENG");
	}
	
	
}
