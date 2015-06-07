package cn.kuroko.faq.struts.action;

import java.util.List;
import java.util.TimerTask;

import org.springframework.format.Printer;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.service.IPersonService;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.util.MailSendUtil;


/**
 * @author James
 * @time 2014-5-22 上午10:39:20
 */
public class TimerTaskDailyEmail extends TimerTask{
	private IQuestionService<Question> questionService;
	private IPersonService<Person> personService;

	
//	public TimerTaskReply(Reply reply) {
//		this.reply = reply;		
//	}
	
	
	public TimerTaskDailyEmail(IQuestionService<Question> questionService, 
			IPersonService<Person> personService) {
		this.questionService = questionService;
		this.personService = personService;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		System.out.println("用于测试");
		List<Question> lstQuestion = questionService.getAllUOKQuestion();
		List<Person> lstAdmin = personService.getAllAdmin();
		int countUnOkQuestion = lstQuestion.size();
		int totalQuestion = questionService.getAllQuestion().size();
		System.out.println("每日问题状态汇报");
		
		for(int i = 0; i < lstAdmin.size(); i++){
		String serverHostName = lstAdmin.get(0).getPubMailHost();
		String fromName = lstAdmin.get(0).getPubMailAdr();
		String fromPassword = lstAdmin.get(0).getPubMailPsw();
		String toAddress = lstAdmin.get(i).getEmail();
		String subject = "每日报告";
		String content = "今天在HXMT共新增了"+
				StartServer.getTodayTotalQuestions()+
				"条问题，其中已经审核通过"+
				StartServer.getTodayCheckedQuestions()+
				"条问题，还有"+
				(StartServer.getTodayTotalQuestions()-StartServer.getTodayCheckedQuestions())+
				"条问题没有审核。 在HXMT共有"+totalQuestion+"条问题，其中共有"+
				countUnOkQuestion+
				"条待审核问题，请及时审核问题，以解答用户的疑问。";
		MailSendUtil.sendMail(serverHostName, fromName, fromPassword, toAddress, subject, content);
		}
	
		StartServer.dailyParReset();
	}
}
