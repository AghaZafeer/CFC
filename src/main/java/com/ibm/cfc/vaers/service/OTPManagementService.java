package com.ibm.cfc.vaers.service;

import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;


public interface OTPManagementService {
	GenerateOTPObjOut getOTP(String emailID);
	
	ValidateOTPObjOut validateOTP(String otpEmailID, String otpValue);
}
