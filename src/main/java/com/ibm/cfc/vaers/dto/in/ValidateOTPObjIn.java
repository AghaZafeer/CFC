package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class ValidateOTPObjIn implements Serializable {
	
	public String otpEmailID;
	
	public String otpValue;

	public String getOtpEmailID() {
		return otpEmailID;
	}

	public void setOtpEmailID(String otpEmailID) {
		this.otpEmailID = otpEmailID;
	}

	public String getOtpValue() {
		return otpValue;
	}

	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}
}
