package com.ibm.cfc.vaers.utils;

import java.io.Serializable;

public class MailDetailDTO implements Serializable {

	public String mailTo;
	
	public String mailFromEmail;
	
	public String mailFromName;
	
	public String mailSubject;
	
	public String mailBody;

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFromEmail() {
		return mailFromEmail;
	}

	public void setMailFromEmail(String mailFromEmail) {
		this.mailFromEmail = mailFromEmail;
	}

	public String getMailFromName() {
		return mailFromName;
	}

	public void setMailFromName(String mailFromName) {
		this.mailFromName = mailFromName;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
}
