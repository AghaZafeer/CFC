package com.ibm.cfc.vaers.service.impl;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.service.EmailService;
import com.ibm.cfc.vaers.utils.MailDetailDTO;


@Service
public class EmailServiceImpl implements EmailService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	@Override	
	public void sendSimpleMessage(MailDetailDTO mailDetailDTO){
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom(new InternetAddress(mailDetailDTO.getMailFromEmail(),mailDetailDTO.getMailFromName()));
			helper.setTo(new InternetAddress(mailDetailDTO.getMailTo()));
	        helper.setSubject(mailDetailDTO.getMailSubject());
	        helper.setText(mailDetailDTO.getMailBody(),true);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        javaMailSender.send(msg);
    }
   
}
