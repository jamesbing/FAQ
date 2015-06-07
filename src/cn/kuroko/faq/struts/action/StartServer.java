package cn.kuroko.faq.struts.action;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;

public class StartServer extends HttpServlet implements ServletContextListener{
	//服务器关闭时执行

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int markOfEmail = 0;
	private static int todayTotalQuestions;
	private static int todayCheckedQuestions;
	private static int todayFirstQuestion;
	
	public static void  dailyParReset(){
		
		RsetTodayFirstQuestion();
		System.out.println("初始化问题序号成功。。。");

		setTodayCheckedQuestions();
		System.out.println("审核计数器复位成功。。。");

		setTodayTotalQuestions();
		System.out.println("问题计数器复位成功。。。");

	}
	
	public static int getTodayFirstQuestion() {
		return todayFirstQuestion;
	}
	
	public static void deleteTodayQuestions(){
		StartServer.todayTotalQuestions = StartServer.todayTotalQuestions - 1;
	}

	public static void RsetTodayFirstQuestion() {
		StartServer.todayFirstQuestion = 0;
	}
	public static void SetTodayFirstQuestion(int questionNumber){
		StartServer.todayFirstQuestion = questionNumber;
	}
	//每增加一个问题，就在这儿增加1以计数。
	public static void addTodayTotalQuestions(){
		StartServer.todayTotalQuestions = StartServer.todayTotalQuestions + 1;
	}
	
	//每增加一个审核通过的问题，就在此处+1，最后可以统计每天审核通过了多少个问题。
	public static void addTodayCheckedQuestions(){
		StartServer.todayCheckedQuestions = StartServer.todayCheckedQuestions + 1;
	}
	
	public static int getTodayTotalQuestions() {
		return todayTotalQuestions;
	}

	public static void setTodayTotalQuestions() {
		StartServer.todayTotalQuestions = 0;
	}

	public static int getTodayCheckedQuestions() {
		return todayCheckedQuestions;
	}

	public static void setTodayCheckedQuestions() {
		StartServer.todayCheckedQuestions = 0;
	}
	
	public static void setMark(){
		markOfEmail = 1;
	}
	
	public static int getMark(){
		return markOfEmail;
	}
	
	public static void reSetMark(){
		markOfEmail = 0;
	}
		
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("*********************************************************************************");
		System.out.println("服务器关闭，各系统参数复位");
		reSetMark();
		System.out.println("控制位复位成功...");
		setTodayCheckedQuestions();
		System.out.println("问题计数器复位成功。。。");
		setTodayTotalQuestions();
		System.out.println("审核计数器复位成功...");
	}
	//服务器启动时执行
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("*********************************************************************************");
		System.out.println("服务器启动，用户进程可以对邮件服务初始化。");
		
		
//		IQuestionService<Question> qs = this.questionService;
//		IPersonService<Person> ps = this.personService;
//		IReplyService<Reply> rs = this.replyService;
//		Timer timer = new Timer();
//		System.out.println("开始执行");
//		QuestionServiceImpl qsi = new QuestionServiceImpl 
//		System.out.println(qs.getAllQuestion().get(0).getContent());
//		timer.schedule(new TimerTaskDailyEmail(qs, rs, ps), 1000);
//		timer.schedule(new TimerTaskDailyEmail(qs, rs, ps), 1000);

	}

	

	
	
}