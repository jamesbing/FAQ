package cn.kuroko.faq.struts.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.bean.ExpertType;
import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.constant.Constant;
import cn.kuroko.faq.service.IExpertTypeService;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.service.ITypeService;
import cn.kuroko.faq.struts.form.PersonForm;
import cn.kuroko.faq.util.PersonUtil;

/**
 * @author Kuroko
 * @time 2013-12-23 下午12:08:21
 */

public class PersonAction extends BaseAction {

	@Override
	public ActionForward output(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	private IPersonService<Person> personService;
	private IQuestionService<Question> questionService;
	private ITypeService<Type> typeService;
	private IExpertTypeService<ExpertType> expertTypeService;
	private IReplyService<Reply> replyService;

	public ActionForward initAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PersonForm personForm = (PersonForm) form;
		
		
		//下面这段代码的作用是初始化每日给管理员发送邮件的线程。
		if(StartServer.getMark() == 0){
			Timer timer = new Timer();
			System.out.println("开始执行");
			Calendar calendar = Calendar.getInstance();  
			calendar.set(Calendar.HOUR_OF_DAY, 13);  
			calendar.set(Calendar.MINUTE, 50);  
			calendar.set(Calendar.SECOND, 0);  
			Date time = calendar.getTime();
			timer.schedule(new TimerTaskDailyEmail(questionService, personService),time);			
			StartServer.setMark();
		}
		
		
		personForm.setTitle("用户注册");
		request.setAttribute("qtype1", this.questionService.getQuestionType1());
		return mapping.findForward("add");
	}
	
	public ActionForward initAdd_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PersonForm personForm = (PersonForm) form;
		
		if(StartServer.getMark() == 0){
			Timer timer = new Timer();
			System.out.println("开始执行");
			Calendar calendar = Calendar.getInstance();  
			calendar.set(Calendar.HOUR_OF_DAY, 13);  
			calendar.set(Calendar.MINUTE, 50);  
			calendar.set(Calendar.SECOND, 0);  
			Date time = calendar.getTime();
			timer.schedule(new TimerTaskDailyEmail(questionService, personService),time);			
			StartServer.setMark();
		}
		
		
		
		personForm.setTitle("User Registration");
		request.setAttribute("qtype1", this.questionService.getQuestionType1());
		return mapping.findForward("add_ENG");
	}
	
	
	//每日邮件的服务的初始化工作设定在此处，第一次有人登录或者注册的时候，进行此工作。也就是说，如果没有人登录过，则默认该系统不在运行状态，就不进行邮件发送。
		public ActionForward initLogin(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			PersonForm personForm = (PersonForm) form;
			personForm.setTitle("用户登录");
			
			
			
			if(StartServer.getMark() == 0){
				Timer timer = new Timer();
				System.out.println("开始执行");
				Calendar calendar = Calendar.getInstance();  
				calendar.set(Calendar.HOUR_OF_DAY, 13);  
				calendar.set(Calendar.MINUTE, 50);  
				calendar.set(Calendar.SECOND, 0);  
				Date time = calendar.getTime();
				timer.schedule(new TimerTaskDailyEmail(questionService, personService),time);			
				StartServer.setMark();
			}
			
			
			return mapping.findForward("login");
		}

		public ActionForward initLogin_ENG(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			PersonForm personForm = (PersonForm) form;
			personForm.setTitle("Login");
			
			
			
			if(StartServer.getMark() == 0){
				Timer timer = new Timer();
				System.out.println("开始执行");
				Calendar calendar = Calendar.getInstance();  
				calendar.set(Calendar.HOUR_OF_DAY, 13);  
				calendar.set(Calendar.MINUTE, 50);  
				calendar.set(Calendar.SECOND, 0);  
				Date time = calendar.getTime();
				timer.schedule(new TimerTaskDailyEmail(questionService, personService),time);			
				StartServer.setMark();
			}
			
			
			
			return mapping.findForward("login_ENG");
		}

		

	

	/**
	 * @deprecated Do not create a bean before saving
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户注册");
		Person person = personForm.getPerson();
		if (null == person.getAccount() || "".equals(person.getAccount())) {
			request.setAttribute("message", "请输入账号");
			return this.initAdd(mapping, personForm, request, response);
		}
		if (null == person.getPassword() || "".equals(person.getPassword())
				|| !person.getPassword().equals(personForm.getPassword())) {
			request.setAttribute("message", "密码输入有误");
			return this.initAdd(mapping, personForm, request, response);
		}
		if (null != personForm.getIsExpert() && personForm.getIsExpert()) {
			person.setPower(Constant.POWER_EXPERT | Constant.POWER_NEED_CHECK);
		} else
			person.setPower(Constant.POWER_USER);
		Pattern p = Pattern.compile(".*?@.*?\\..*");
		Matcher m = p.matcher(person.getEmail());
		if (!m.find()) {
			request.setAttribute("message", "email输入有误");
			return this.initAdd(mapping, personForm, request, response);
		}
		person.setIpCreated(request.getRemoteAddr());
		person.setIpLastActived(request.getRemoteAddr());
		person.setDateCreated(new Date());
		person.setTimeCreated(new Date());
		person.setDateLastActived(new Date());
		try {
			personService.create(person);
			Type type1 = this.typeService.getTypeByName(request
					.getParameter("type1"));
			Type type2 = this.typeService.getTypeByName(request
					.getParameter("type2"));
			Type type3 = this.typeService.getTypeByName(request
					.getParameter("type3"));
			// this.personService.setExpertType(p,
			// request.getParameter("type1"),
			// request.getParameter("type2"), request.getParameter("type3"));
			if (null != type1) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type1);
				this.expertTypeService.create(et);
			}
			if (null != type2) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type2);
				this.expertTypeService.create(et);
			}
			if (null != type3) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type3);
				this.expertTypeService.create(et);
			}
			PersonUtil.setPersonInfo(request, response, person);
			request.setAttribute("message", "注册成功");
			return new ActionForward("success", "/success.jsp", false);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			return this.initAdd(mapping, personForm, request, response);
		}
	}
	
	
	public ActionForward add_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("User Registration");
		Person person = personForm.getPerson();
		if (null == person.getAccount() || "".equals(person.getAccount())) {
			request.setAttribute("message", "Please input account");
			return this.initAdd_ENG(mapping, personForm, request, response);
		}
		if (null == person.getPassword() || "".equals(person.getPassword())
				|| !person.getPassword().equals(personForm.getPassword())) {
			request.setAttribute("message", "Your password is not correct");
			return this.initAdd_ENG(mapping, personForm, request, response);
		}
		if (null != personForm.getIsExpert() && personForm.getIsExpert()) {
			person.setPower(Constant.POWER_EXPERT | Constant.POWER_NEED_CHECK);
		} else
			person.setPower(Constant.POWER_USER);
		Pattern p = Pattern.compile(".*?@.*?\\..*");
		Matcher m = p.matcher(person.getEmail());
		if (!m.find()) {
			request.setAttribute("message", "Your Email is not correct");
			return this.initAdd_ENG(mapping, personForm, request, response);
		}
		person.setIpCreated(request.getRemoteAddr());
		person.setIpLastActived(request.getRemoteAddr());
		person.setDateCreated(new Date());
		person.setTimeCreated(new Date());
		person.setDateLastActived(new Date());
		person.setLanguage("ENG");
		try {
			personService.create(person);
			Type type1 = this.typeService.getTypeByName(request
					.getParameter("type1"));
			Type type2 = this.typeService.getTypeByName(request
					.getParameter("type2"));
			Type type3 = this.typeService.getTypeByName(request
					.getParameter("type3"));
			// this.personService.setExpertType(p,
			// request.getParameter("type1"),
			// request.getParameter("type2"), request.getParameter("type3"));
			if (null != type1) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type1);
				this.expertTypeService.create(et);
			}
			if (null != type2) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type2);
				this.expertTypeService.create(et);
			}
			if (null != type3) {
				ExpertType et = new ExpertType();
				et.setExpert(person);
				et.setType(type3);
				this.expertTypeService.create(et);
			}
			PersonUtil.setPersonInfo(request, response, person);
			request.setAttribute("message", "Congratulations,the register is success");
			return new ActionForward("success", "/success_ENG.jsp", false);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			return this.initAdd_ENG(mapping, personForm, request, response);
		}
	}
	
	
	

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户登录");
		Person person = this.personService.getPerson(personForm.getPerson()
				.getAccount(), personForm.getPerson().getPassword());
		if (null == person)
			throw new AccountException("用户名或密码错误");
		person.setIpLastActived(request.getRemoteAddr());
		person.setDateLastActived(new Date());
		PersonUtil.setPersonInfo(request, response, person);
		request.setAttribute("message", "欢迎回来," + person.getName());
		return new ActionForward("success", "/success.jsp", false);
	}

	public ActionForward login_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("Login");
		Person person = this.personService.getPerson(personForm.getPerson()
				.getAccount(), personForm.getPerson().getPassword());
		if (null == person)
			throw new AccountException("The account or password is wrong.");
		person.setIpLastActived(request.getRemoteAddr());
		person.setDateLastActived(new Date());
		PersonUtil.setPersonInfo(request, response, person);
		request.setAttribute("message", "Welcome back," + person.getName());
		return new ActionForward("success", "/success_ENG.jsp", false);
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("用户注销");
		request.getSession(true).setAttribute(Constant.KEY_PERSON_INFO, null);
		return new ActionForward("success", "/", true);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("查看用户资料");
		Person person = null;
		if (request.getParameter("pName") != null)
			person = this.personService.findPersonByAccount(request
					.getParameter("pName"));
		else
			person = this.personService.find(Person.class,
					Integer.parseInt(request.getParameter("id")));
		if (null == person) {
			request.setAttribute("message", "没有这个用户");
			return mapping.findForward("exception");
		}
		//************************3.14
		if ( true == person.isExpert()){
			List<ExpertType> lstExpertType = this.expertTypeService
					.list("select et from ExpertType et where et.expert.id=" + person.getId().toString() );
			request.setAttribute("etlst", lstExpertType);
		}
		//************************3.14
		
		request.setAttribute("person", person);
		
		List<Type> lstType = this.questionService.getQuestionType1();
		request.setAttribute("qtype1", lstType);
		
		return new ActionForward("view", "/person/viewPerson.jsp", false);
	}
	
	public ActionForward view_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("Show user's message");
		Person person = null;
		if (request.getParameter("pName") != null)
			person = this.personService.findPersonByAccount(request
					.getParameter("pName"));
		else
			person = this.personService.find(Person.class,
					Integer.parseInt(request.getParameter("id")));
		if (null == person) {
			request.setAttribute("message", "This user is not exist");
			return mapping.findForward("exception_ENG");
		}
		//************************3.14
		if ( true == person.isExpert()){
			List<ExpertType> lstExpertType = this.expertTypeService
					.list("select et from ExpertType et where et.expert.id=" + person.getId().toString() );
			request.setAttribute("etlst", lstExpertType);
		}
		//************************3.14
		
		request.setAttribute("person", person);
		
		List<Type> lstType = this.questionService.getQuestionType1();
		request.setAttribute("qtype1", lstType);
		
		return new ActionForward("view", "/person/viewPerson_ENG.jsp", false);
	}
	

	public ActionForward searchExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("专家列表");
		List<Person> lstExpert = this.personService.getExpert(personForm
				.getExpertType());
		if (null == lstExpert || 0 == lstExpert.size())
			return mapping.findForward("expert");
		HashMap<Person, List<ExpertType>> hmExpertType = new HashMap<Person, List<ExpertType>>();
		for (Person p : lstExpert)
			hmExpertType.put(p, this.personService.getExpertType(p));
		request.setAttribute("lstExpert", lstExpert);
		request.setAttribute("hmExpertType", hmExpertType);
		return mapping.findForward("expert");
	}
	
	public ActionForward searchExpert_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("Expert List");
		List<Person> lstExpert = this.personService.getExpert(personForm
				.getExpertType());
		if (null == lstExpert || 0 == lstExpert.size())
			return mapping.findForward("expert_ENG");
		HashMap<Person, List<ExpertType>> hmExpertType = new HashMap<Person, List<ExpertType>>();
		for (Person p : lstExpert)
			hmExpertType.put(p, this.personService.getExpertType(p));
		request.setAttribute("lstExpert", lstExpert);
		request.setAttribute("hmExpertType", hmExpertType);
		return mapping.findForward("expert_ENG");
	}
	

	public ActionForward initExpert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("专家列表");
		List<Person> lstExpert = this.personService.getExpert(null);
		if (null == lstExpert || 0 == lstExpert.size())
			return mapping.findForward("expert");
		HashMap<Person, List<ExpertType>> hmExpertType = new HashMap<Person, List<ExpertType>>();
		for (Person p : lstExpert)
			hmExpertType.put(p, this.personService.getExpertType(p));
		request.setAttribute("lstExpert", lstExpert);
		request.setAttribute("hmExpertType", hmExpertType);
		return mapping.findForward("expert");
	}
	
	public ActionForward initExpert_ENG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm personForm = (PersonForm) form;
		personForm.setTitle("Expert List");
		List<Person> lstExpert = this.personService.getExpert(null);
		if (null == lstExpert || 0 == lstExpert.size())
			return mapping.findForward("expert");
		HashMap<Person, List<ExpertType>> hmExpertType = new HashMap<Person, List<ExpertType>>();
		for (Person p : lstExpert)
			hmExpertType.put(p, this.personService.getExpertType(p));
		request.setAttribute("lstExpert", lstExpert);
		request.setAttribute("hmExpertType", hmExpertType);
		return mapping.findForward("expert_ENG");
	}

	public ActionForward addAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("id");
		Person p = this.personService.findPersonByAccount(name);
		String result = "";
		if (null == p)
			result = "添加管理员失败:未找到用户";
		else {
			p.setPower(p.getPower() | Constant.POWER_ADMIN);
			this.personService.update(p);
			result = "添加管理员成功";
		}
		request.setAttribute("html", result);
		return mapping.findForward("ajax");
	}

	public ActionForward removeAdmin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("id");
		Person p = this.personService.findPersonByAccount(name);
		String result = "";
		if (null == p)
			result = "失败:未知错误";
		else {
			p.setPower(p.getPower() & (~Constant.POWER_ADMIN));
			this.personService.update(p);
			result = "取消管理员成功";
		}
		request.setAttribute("html", result);
		return mapping.findForward("ajax");
	}

	public ActionForward check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(name);
		} catch (Exception e) {
			request.setAttribute("html", "参数非法...");
			return mapping.findForward("ajax");
		}
		Person p = this.personService.find(Person.class, id);
		String result = "失败:未知错误";
		if (null == p)
			result = "失败:未找到用户";
		else {
			p.setPower(p.getPower() & (~Constant.POWER_NEED_CHECK));
			this.personService.update(p);
			result = "审核用户成功";
		}
		request.setAttribute("html", result);
		return mapping.findForward("ajax");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("id");
		Person p = this.personService.findPersonByAccount(name);
		String result = "失败:未知错误";
		if (null == p)
			result = "失败:未找到用户";
		else {
			p.setPower(p.getPower() & (~Constant.POWER_NEED_CHECK));
			this.personService.delete(p);
			result = "删除用户成功";
		}
		request.setAttribute("html", result);
		return mapping.findForward("ajax");
	}

	public ActionForward initCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm pf = (PersonForm) form;
		pf.setTitle("用户管理");
		List<Person> lstPerson = this.personService
				.list("select p from Person p where p.deleted=0");
		List<Person> lstResult = new ArrayList<Person>();
		for (Person p : lstPerson)
			if ((p.getPower() & Constant.POWER_NEED_CHECK) == Constant.POWER_NEED_CHECK)
				lstResult.add(p);
		request.setAttribute("lstPerson", lstResult);
		return mapping.findForward("check");
	}

	public ActionForward changeType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if (null == id || id.isEmpty())
			throw new RuntimeException("id为空");
		Person p = this.personService.find(Person.class, Integer.parseInt(id));
		String type = request.getParameter("type1");
		Type type1 = this.typeService.getTypeByName(request
				.getParameter("type1"));
		Type type2 = this.typeService.getTypeByName(request
				.getParameter("type2"));
		Type type3 = this.typeService.getTypeByName(request
				.getParameter("type3"));
		// this.personService.setExpertType(p, request.getParameter("type1"),
		// request.getParameter("type2"), request.getParameter("type3"));
		List<ExpertType> lstExpertType = this.expertTypeService
				.list("select et from ExpertType et where et.expert.id=" + id);
		if (null != lstExpertType)
			for (ExpertType et : lstExpertType)
				this.expertTypeService.delete(et);
		if (null != type1) {
			ExpertType et = new ExpertType();
			et.setExpert(p);
			et.setType(type1);
			this.expertTypeService.create(et);
		}
		if (null != type2) {
			ExpertType et = new ExpertType();
			et.setExpert(p);
			et.setType(type2);
			this.expertTypeService.create(et);
		}
		if (null != type3) {
			ExpertType et = new ExpertType();
			et.setExpert(p);
			et.setType(type3);
			this.expertTypeService.create(et);
		}
		request.setAttribute("html", "修改成功");
		return mapping.findForward("ajax");
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

	public ITypeService<Type> getTypeService() {
		return typeService;
	}

	public void setTypeService(ITypeService<Type> typeService) {
		this.typeService = typeService;
	}

	public IExpertTypeService<ExpertType> getExpertTypeService() {
		return expertTypeService;
	}

	public void setExpertTypeService(
			IExpertTypeService<ExpertType> expertTypeService) {
		this.expertTypeService = expertTypeService;
	}

}
