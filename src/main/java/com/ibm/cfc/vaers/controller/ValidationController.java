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
import com.ibm.cfc.vaers.dto.in.ValidateAadhaarObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateBeneficiaryRefIDObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateOTPObjIn;
import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.ValidateAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateBeneficiaryRefIDObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;
import com.ibm.cfc.vaers.service.OTPManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;
import com.ibm.cfc.vaers.service.ValidationService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class ValidationController {

	private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);
	
	@Autowired
	ValidationService validationService;
	
	@PostMapping("/validateAadhaar")
	public ValidateAadhaarObjOut validateAadhaar(@RequestBody ValidateAadhaarObjIn validateAadhaarObjIn) {
		return validationService.validateAadhaar(validateAadhaarObjIn.getAadhaarNumber());
	}
	
	@PostMapping("/validateBeneficiaryRefID")
	public ValidateBeneficiaryRefIDObjOut validateBeneficiaryRefID(@RequestBody ValidateBeneficiaryRefIDObjIn validateBeneficiaryRefIDObjIn) {
		return validationService.validateBeneficiaryRefID(validateBeneficiaryRefIDObjIn.getBeneficiaryRefID(), validateBeneficiaryRefIDObjIn.getVaccineName(), validateBeneficiaryRefIDObjIn.getVaccineDose());
	}
}
