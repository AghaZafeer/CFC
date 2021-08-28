package com.ibm.cfc.vaers.service;


import com.ibm.cfc.vaers.dto.out.SearchAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPInSearchObjOut;

public interface SearchService {
	SearchAadhaarObjOut searchByAadhaar(String aadhaarNumber);
	
	ValidateOTPInSearchObjOut validateOTPInSearch(String aadhaarNumber, String otpCode);
}
