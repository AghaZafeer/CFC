package com.ibm.cfc.vaers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.ValidateAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateBeneficiaryRefIDObjOut;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.repository.UserVaccineRepository;
import com.ibm.cfc.vaers.repository.VaccineDoseMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	VaccineMasterRepository vaccineMasterRepository;
	
	@Autowired
	VaccineDoseMasterRepository vaccineDoseMasterRepository;
	
	@Autowired
	UserVaccineRepository userVaccineRepository;
	
	@Override
	public ValidateAadhaarObjOut validateAadhaar(String aadhaarNumber) {
		ValidateAadhaarObjOut returnObj = new ValidateAadhaarObjOut();
		User userToCheck = new User();
		userToCheck = userMasterRepository.findByUserAadhaarNo(aadhaarNumber.trim());
		if(userToCheck != null && userToCheck.getUserId() > 0) {
			returnObj.setValid(false);
		}else {
			returnObj.setValid(true);
		}
		return returnObj;
	}

	@Override
	public ValidateBeneficiaryRefIDObjOut validateBeneficiaryRefID(String beneficiaryRefID, String vaccineName, String vaccineDose) {
		ValidateBeneficiaryRefIDObjOut returnObj = new ValidateBeneficiaryRefIDObjOut();
		User userToCheck = new User();
		userToCheck = userMasterRepository.findByUserBeneficiaryRefId(beneficiaryRefID.trim());
		if(userToCheck != null && userToCheck.getUserId() > 0) {
			VaccineMaster vaccineMaster = new VaccineMaster();
			vaccineMaster  = vaccineMasterRepository.findByVaccineName(vaccineName);
			
			VaccineDoseMaster vaccineDoseMaster = new VaccineDoseMaster(); 		
			vaccineDoseMaster = vaccineDoseMasterRepository.findByVaccineMasterAndVaccineDoseName(vaccineMaster, vaccineDose);
			
			UserVaccine userVaccine = userVaccineRepository.findByUserAndVaccineMasterAndVaccineDoseMaster(userToCheck, vaccineMaster, vaccineDoseMaster);
			
			if(userVaccine != null && userVaccine.getUsvacId() > 0) {
				returnObj.setValid(false);
			}else {
				returnObj.setValid(true);
			}
		}else {
			returnObj.setValid(true);
		}
		
		return returnObj;
	}

}
