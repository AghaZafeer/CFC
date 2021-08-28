package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class ValidateOTPInSearchObjIn implements Serializable {

	public String aadhaarNumber;
	
	public String otpCode;

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
}
