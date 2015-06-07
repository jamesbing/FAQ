package cn.kuroko.faq.struts.action;

import java.util.TimerTask;

import cn.kuroko.faq.bean.Question;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.service.IQuestionService;
import cn.kuroko.faq.service.IReplyService;


/**
 * @author James
 * @time 2014-5-22 上午10:39:20
 */
public class TimerTaskQuestion extends TimerTask{

	private Question question;
//	private Integer replyId;
	private IQuestionService<Question> qs;
	
//	public TimerTaskReply(Reply reply) {
//		this.reply = reply;		
//	}
	
	
	public TimerTaskQuestion(IQuestionService<Question> questionService, Question question) {
		this.qs = questionService;
		this.question = question;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("用于测试");
		this.question.setOk(true);
		this.qs.update(question);
		
		System.out.println("测试结果："+question.getId()+"号的审核状态是："+question.getOk());
	}

}
