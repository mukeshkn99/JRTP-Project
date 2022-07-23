package com.ashokit.utils;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
 
	private Logger logger=LoggerFactory.getLogger(EmailUtils.class);
	@Autowired
	private JavaMailSender mailsender;
	
	public boolean sendmail(String to,String subject,String body) {
		boolean isSent=false;
		try {
		MimeMessage message=mailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		mailsender.send(message);
		isSent=true;
		}
	catch(Exception e) {
		logger.error(e.getMessage(),e);
	}
		return isSent;
	}
}
