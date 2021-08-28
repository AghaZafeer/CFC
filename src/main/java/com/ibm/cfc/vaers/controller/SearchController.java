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
import com.ibm.cfc.vaers.dto.in.SearchAadhaarObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateAadhaarObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateBeneficiaryRefIDObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateOTPInSearchObjIn;
import com.ibm.cfc.vaers.dto.in.ValidateOTPObjIn;
import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.SearchAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.ValidateAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateBeneficiaryRefIDObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPInSearchObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;
import com.ibm.cfc.vaers.service.OTPManagementService;
import com.ibm.cfc.vaers.service.SearchService;
import com.ibm.cfc.vaers.service.VaccineManagementService;
import com.ibm.cfc.vaers.service.ValidationService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	SearchService searchService;
	
	@PostMapping("/searchByAadhaar")
	public SearchAadhaarObjOut searchByAadhaar(@RequestBody SearchAadhaarObjIn searchAadhaarObjIn) {
		return searchService.searchByAadhaar(searchAadhaarObjIn.getAadhaarNumber());
	}
	
	
	@PostMapping("/validateOTPInSearch") 
	public ValidateOTPInSearchObjOut validateOTPInSearch(@RequestBody ValidateOTPInSearchObjIn validateOTPInSearchObjIn) { 
		return searchService.validateOTPInSearch(validateOTPInSearchObjIn.getAadhaarNumber(), validateOTPInSearchObjIn.getOtpCode());
	}
	 
}
