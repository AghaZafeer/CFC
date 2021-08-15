package com.ibm.cfc.vaers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.in.GenerateOTPObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateOTPObjIn;
import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;
import com.ibm.cfc.vaers.service.OTPManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class OTPManagamentController {

	private static final Logger logger = LoggerFactory.getLogger(OTPManagamentController.class);
	
	@Autowired
	OTPManagementService otpManagementService;
	
	@PostMapping("/generateOTP")
	public GenerateOTPObjOut getOTP(@RequestBody GenerateOTPObjIn generateOTPObjIn) {
		return otpManagementService.getOTP(generateOTPObjIn.getEmailID());
	}
	
	@PostMapping("/validateOTP")
	public ValidateOTPObjOut validateOTP(@RequestBody ValidateOTPObjIn validateOTPObjIn) {
		return otpManagementService.validateOTP(validateOTPObjIn.getOtpEmailID(), validateOTPObjIn.getOtpValue());
	}
}
