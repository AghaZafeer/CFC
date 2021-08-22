package com.ibm.cfc.vaers.service;

import com.ibm.cfc.vaers.dto.out.ValidateAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateBeneficiaryRefIDObjOut;



public interface ValidationService {
	ValidateAadhaarObjOut validateAadhaar(String aadhaarNumber);
	
	ValidateBeneficiaryRefIDObjOut validateBeneficiaryRefID(String beneficiaryRefID, String vaccineName, String vaccineDose);
}
