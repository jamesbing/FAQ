package cn.kuroko.faq.struts.action;

import java.util.List;
import java.util.TimerTask;

import org.springframework.format.Printer;

import cn.kuroko.faq.bean.Person;
import cn.kuroko.faq.bean.Reply;
import cn.kuroko.faq.service.IReplyService;
import cn.kuroko.faq.util.MailSendUtil;


/**
 * @author James
 * @time 2014-5-22 上午10:39:20
 */
public class TimerTaskReply extends TimerTask{

	private Reply reply;
//	private Integer replyId;
	private IReplyService<Reply> replyService;
	private Person admin;
	
//	public TimerTaskReply(Reply reply) {
//		this.reply = reply;		
//	}
	
	
	public TimerTaskReply(IReplyService<Reply> replyService, Person admin, Reply reply) {
		this.replyService = replyService;
		this.reply = reply;
		this.admin = admin;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		System.out.println("用于测试");
		this.reply.setOk(true);
		this.replyService.update(reply);
		String serverHostName = admin.getPubMailHost();
		String fromName = admin.getPubMailAdr();
		String fromPassword = admin.getPubMailPsw();
		String toAddress = reply.getQuestion().getAuthor().getEmail();
		String subject = "您的问题有新的回答，请登录查看！";
		String content = "您的问题"+reply.getQuestion().getTitle()+"已经得到了回答："+reply.getContent()+"    请登录查看！";
		MailSendUtil.sendMail(serverHostName, fromName, fromPassword, toAddress, subject, content);
		System.out.println("测试结果："+reply.getId()+"号的审核状态是："+reply.getOk());
	}

}
