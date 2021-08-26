package com.ibm.cfc.vaers.service.impl;

import java.util.List;

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
import com.ibm.cfc.vaers.utils.VaersConstants;

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
				returnObj.setMessage(VaersConstants.BEN_REF_ID_MESSAGE_ERROR_RECORD_EXIST);
				returnObj.setValid(false);
			}else {
				List<UserVaccine> userVaccines = userToCheck.getUserVaccines();
				boolean checkVaccine = true;
				for(UserVaccine thisUserVaccine : userVaccines) {
					if(thisUserVaccine.getVaccineMaster() != vaccineMaster) {
						returnObj.setMessage(VaersConstants.BEN_REF_ID_MESSAGE_ERROR_VACCINE_MISMATCH);
						returnObj.setValid(false);
						checkVaccine = false;
					}
				}
				if(checkVaccine) {
					returnObj.setMessage(VaersConstants.BEN_REF_ID_MESSAGE_VALIDATION_OK);
					returnObj.setValid(true);
				}
			}
		}else {
			returnObj.setMessage(VaersConstants.BEN_REF_ID_MESSAGE_VALIDATION_OK);
			returnObj.setValid(true);
		}
		
		return returnObj;
	}

}
