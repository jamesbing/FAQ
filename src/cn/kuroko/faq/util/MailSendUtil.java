package cn.kuroko.faq.util;

import cn.kuroko.faq.util.mail.MailSenderInfo;
import cn.kuroko.faq.util.mail.SimpleMailSender;

public class MailSendUtil {

	/**
	 * @param args
	 */
	public static void sendMail(String serverHostName, String serverHostPort, String fromName, String fromPassword, 
			String toAddress, String subject, String content) {
		// TODO Auto-generated method stub
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost(serverHostName);    
     mailInfo.setMailServerPort(serverHostPort);    
     mailInfo.setValidate(true);    
     mailInfo.setUserName(fromName);    
     mailInfo.setPassword(fromPassword);//您的邮箱密码    
     mailInfo.setFromAddress(fromName);    
     mailInfo.setToAddress(toAddress);    
     mailInfo.setSubject(subject);    
     mailInfo.setContent(content);    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
	}
	
	public static void sendMail(String serverHostName, String fromName, String fromPassword, 
			String toAddress, String subject, String content) {
		// TODO Auto-generated method stub
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost(serverHostName);    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName(fromName);    
     mailInfo.setPassword(fromPassword);//您的邮箱密码    
     mailInfo.setFromAddress(fromName);    
     mailInfo.setToAddress(toAddress);    
     mailInfo.setSubject(subject);    
     mailInfo.setContent(content);    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
	}

}
