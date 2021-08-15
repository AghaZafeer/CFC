package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class GenerateOTPObjOut implements Serializable{

	public boolean otpGenerationSuccessful;

	public boolean isOtpGenerationSuccessful() {
		return otpGenerationSuccessful;
	}

	public void setOtpGenerationSuccessful(boolean otpGenerationSuccessful) {
		this.otpGenerationSuccessful = otpGenerationSuccessful;
	}
	
}
