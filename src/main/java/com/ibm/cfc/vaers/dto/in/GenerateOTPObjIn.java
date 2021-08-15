package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class GenerateOTPObjIn implements Serializable {

	public String emailID;

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
}
