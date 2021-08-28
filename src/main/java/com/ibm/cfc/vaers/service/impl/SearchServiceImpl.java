package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.in.SearchAadhaarObjIn;
import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.ReportedAdverseEffectRecordOut;
import com.ibm.cfc.vaers.dto.out.SearchAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.SearchUserRecordOut;
import com.ibm.cfc.vaers.dto.out.UserAllergiesOut;
import com.ibm.cfc.vaers.dto.out.UserIllnessOut;
import com.ibm.cfc.vaers.dto.out.UserMasterOut;
import com.ibm.cfc.vaers.dto.out.UserVaccineReporterAdverseEffectRecordOut;
import com.ibm.cfc.vaers.dto.out.ValidateAadhaarObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateBeneficiaryRefIDObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPInSearchObjOut;
import com.ibm.cfc.vaers.model.AdverseEventReported;
import com.ibm.cfc.vaers.model.MailDetail;
import com.ibm.cfc.vaers.model.OtpCode;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.MailDetailRepository;
import com.ibm.cfc.vaers.repository.OtpCodeRepository;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.repository.UserVaccineRepository;
import com.ibm.cfc.vaers.repository.VaccineDoseMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.EmailService;
import com.ibm.cfc.vaers.service.SearchService;
import com.ibm.cfc.vaers.service.ValidationService;
import com.ibm.cfc.vaers.utils.MailDetailDTO;
import com.ibm.cfc.vaers.utils.VaersConstants;
import com.ibm.cfc.vaers.utils.VaersUtilities;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	VaccineMasterRepository vaccineMasterRepository;
	
	@Autowired
	VaccineDoseMasterRepository vaccineDoseMasterRepository;
	
	@Autowired
	UserVaccineRepository userVaccineRepository;
	
	@Autowired
	OtpCodeRepository otpCodeRepository; 
	
	@Autowired
	MailDetailRepository mailDetailRepository;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public SearchAadhaarObjOut searchByAadhaar(String aadhaarNumber) {
		SearchAadhaarObjOut returnObj = new SearchAadhaarObjOut();
		User userToCheck = new User();
		userToCheck = userMasterRepository.findByUserAadhaarNo(aadhaarNumber.trim());
		if(userToCheck != null && userToCheck.getUserId() > 0) {
			returnObj.setValid(true);
			returnObj.setMessage(VaersConstants.SEARCH_MESSAGE_AADHAAR_EXISTS);
			//Send the OTP to the user email
			sendOTP(userToCheck.getUserEmailId());
			
		}else {
			returnObj.setValid(false);
			returnObj.setMessage(VaersConstants.SEARCH_MESSAGE_AADHAAR_NOT_EXISTS);
		}
		return returnObj;
	}

	protected void sendOTP(String emailID){
		String newOTPValue = VaersUtilities.generateOTPValue();
		
		// Check whether OTP exists or not 
		OtpCode otpCode = otpCodeRepository.findByOtpEmailId(emailID);
		if(otpCode != null) {
			if(otpCode.getOtpId() > 0) {
				
				otpCode.setOtpValue(newOTPValue);
				otpCode.setOtpActive(true);
				otpCode.setDateCreated(VaersUtilities.getCurrentDate());
				otpCode.setDateModified(VaersUtilities.getCurrentDate());
				
				otpCodeRepository.saveAndFlush(otpCode);
			}
		}else {
			OtpCode newOtpCode = new OtpCode();
			newOtpCode.setOtpEmailId(emailID);
			newOtpCode.setOtpValue(newOTPValue);
			newOtpCode.setOtpActive(true);
			newOtpCode.setDateCreated(VaersUtilities.getCurrentDate());
			newOtpCode.setDateModified(VaersUtilities.getCurrentDate());
			
			otpCodeRepository.saveAndFlush(newOtpCode);
		}
		
		//Send Email
		MailDetail mailDetail = mailDetailRepository.findByMdCode(VaersConstants.MAIL_CODE_SEARCH);
		MailDetailDTO mailDetailDTO = new MailDetailDTO();
		
		mailDetailDTO.setMailTo(emailID);
		mailDetailDTO.setMailFromEmail(mailDetail.getMdFromEmail());
		mailDetailDTO.setMailFromName(mailDetail.getMdFromEmailName());
		mailDetailDTO.setMailSubject(mailDetail.getMdSubject());
		mailDetailDTO.setMailBody(mailDetail.getMdBody().replace(VaersConstants.MAIL_SEARCH_OTP_STRING_TO_FIND, newOTPValue));
		
		emailService.sendSimpleMessage(mailDetailDTO);
	}
	
	@Override
	public ValidateOTPInSearchObjOut validateOTPInSearch(String aadhaarNumber, String otpCode) {
		ValidateOTPInSearchObjOut returnObj = new ValidateOTPInSearchObjOut();
		User userToCheck = new User();
		userToCheck = userMasterRepository.findByUserAadhaarNo(aadhaarNumber.trim());
		if(userToCheck != null && userToCheck.getUserId() > 0) {
			OtpCode otpCodeModel = otpCodeRepository.findByOtpEmailIdAndOtpValue(userToCheck.getUserEmailId(), otpCode);
			if(otpCodeModel.getOtpId() > 0) {
				if(otpCodeModel.isOtpActive()) {
					returnObj.setValid(true);
					
					SearchUserRecordOut searchUserRecordOut = new SearchUserRecordOut();
					searchUserRecordOut.setUserTitle(userToCheck.getUserTitleMaster2().getUserTitleName());
					searchUserRecordOut.setFirstName(userToCheck.getUserFirstName());
					searchUserRecordOut.setMiddleName(userToCheck.getUserMiddleName());
					searchUserRecordOut.setLastName(userToCheck.getUserLastName());
					searchUserRecordOut.setEmailID(userToCheck.getUserEmailId());
					searchUserRecordOut.setAddress(userToCheck.getUserAddress());
					searchUserRecordOut.setContactNumber(userToCheck.getUserMobile());
					searchUserRecordOut.setAlternateContactNumber(userToCheck.getUserAlternateNumber());
					searchUserRecordOut.setAadhaarNumber(userToCheck.getUserAadhaarNo());
					searchUserRecordOut.setBeneficiaryReferenceID(userToCheck.getUserBeneficiaryRefId());
					searchUserRecordOut.setAge(String.valueOf(userToCheck.getUserAge()));
					searchUserRecordOut.setDateOfBirth(VaersUtilities.getStringFromDate(userToCheck.getUserDob()));
					searchUserRecordOut.setGender(userToCheck.getUserSex());
					searchUserRecordOut.setIsPregnant(VaersUtilities.getStringFromBoolean(userToCheck.isUserIsPregnant()));
					
					List<UserIllnessOut> userIllnessOuts = new ArrayList<UserIllnessOut>(); 
					if(userToCheck.getUserIllnesses() != null && userToCheck.getUserIllnesses().size() > 0) {
						for(UserIllness userIllness : userToCheck.getUserIllnesses()) {
							UserIllnessOut userIllnessOut = new UserIllnessOut();
							userIllnessOut.setIllnessName(userIllness.getIllnessMaster().getIllnessName());
							userIllnessOuts.add(userIllnessOut);
						}
					}
					searchUserRecordOut.setUserIllnessOuts(userIllnessOuts);
					
					List<UserAllergiesOut> userAllergiesOuts = new ArrayList<UserAllergiesOut>(); 
					if(userToCheck.getUserAllergicConditions() != null && userToCheck.getUserAllergicConditions().size() > 0) {
						for(UserAllergicCondition userAllergicCondition : userToCheck.getUserAllergicConditions()) {
							UserAllergiesOut userAllergiesOut = new UserAllergiesOut();
							userAllergiesOut.setAllergyName(userAllergicCondition.getAllergicConditionsMaster().getAllgcondName());
							userAllergiesOuts.add(userAllergiesOut);
						}
					}
					searchUserRecordOut.setUserAllergiesOut(userAllergiesOuts);
					
					List<UserVaccineReporterAdverseEffectRecordOut> userVaccineReporterAdverseEffectRecordOuts = getUserVaccineReporterAdverseEffectRecordOuts(userToCheck);
					
					searchUserRecordOut.setUserVaccineReporterAdverseEffectRecordOuts(userVaccineReporterAdverseEffectRecordOuts);		
					returnObj.setSearchUserRecordOut(searchUserRecordOut);
				}else {
					//OTP not active
					returnObj.setValid(false);
					returnObj.setSearchUserRecordOut(null);
				}
			}else {
				//OTP mismatch
				returnObj.setValid(false);
				returnObj.setSearchUserRecordOut(null);
			}
		}else {
			//User not found
			returnObj.setValid(false);
			returnObj.setSearchUserRecordOut(null);
		}
		
		return returnObj;
	}
	
	protected List<UserVaccineReporterAdverseEffectRecordOut> getUserVaccineReporterAdverseEffectRecordOuts(User user){
		List<UserVaccineReporterAdverseEffectRecordOut> userVaccineReporterAdverseEffectRecordOuts = new ArrayList<UserVaccineReporterAdverseEffectRecordOut>();
		
		List<UserVaccine> userVaccines =  new ArrayList<UserVaccine>();
		userVaccines = userVaccineRepository.findAllByUser(user);
		
		if(userVaccines != null && userVaccines.size() > 0) {
			for(UserVaccine thisUserVaccine : userVaccines) {
				UserVaccineReporterAdverseEffectRecordOut userVaccineReporterAdverseEffectRecordOut = new UserVaccineReporterAdverseEffectRecordOut();
				
				userVaccineReporterAdverseEffectRecordOut.setVaccineName(thisUserVaccine.getVaccineMaster().getVaccineName());
				userVaccineReporterAdverseEffectRecordOut.setVaccineDoseName(thisUserVaccine.getVaccineDoseMaster().getVaccineDoseName());
				userVaccineReporterAdverseEffectRecordOut.setVaccinationCenter(thisUserVaccine.getUsvacVaccinationCenter());
				userVaccineReporterAdverseEffectRecordOut.setVaccinnationDate(VaersUtilities.getStringFromDate(thisUserVaccine.getUsvacVaccinationDatetime()));
				
				userVaccineReporterAdverseEffectRecordOut.setCaseID(thisUserVaccine.getCaseReporter().getCaseReporterCaseId());
				userVaccineReporterAdverseEffectRecordOut.setCaseReportedBy(thisUserVaccine.getCaseReporter().getCaseInfoAddedBy());
				if(thisUserVaccine.getCaseReporter().getCaseReporterTitleId() != null) {
					userVaccineReporterAdverseEffectRecordOut.setReporterTitleName(thisUserVaccine.getCaseReporter().getCaseReporterTitleId().getUserTitleName());
				}else {
					userVaccineReporterAdverseEffectRecordOut.setReporterTitleName(null);
				}
				userVaccineReporterAdverseEffectRecordOut.setReporterFirstName(thisUserVaccine.getCaseReporter().getCaseReporterFirstName());
				userVaccineReporterAdverseEffectRecordOut.setReporterMiddleName(thisUserVaccine.getCaseReporter().getCaseReporterMiddleName());
				userVaccineReporterAdverseEffectRecordOut.setReporterLastName(thisUserVaccine.getCaseReporter().getCaseReporterLastName());
				userVaccineReporterAdverseEffectRecordOut.setReporterContactNumber(thisUserVaccine.getCaseReporter().getCaseReporterMobileNumber());
				userVaccineReporterAdverseEffectRecordOut.setReporterAlternateNumber(thisUserVaccine.getCaseReporter().getCaseReporterAlternateNumber());
				userVaccineReporterAdverseEffectRecordOut.setReporterEmailID(thisUserVaccine.getCaseReporter().getCaseReporterEmailId());
				
				List<ReportedAdverseEffectRecordOut> reportedAdverseEffectRecordOuts = getReportedAdverseEffectRecordOuts(thisUserVaccine.getAdverseEventReporteds());
				
				userVaccineReporterAdverseEffectRecordOut.setReportedAdverseEffectRecordOuts(reportedAdverseEffectRecordOuts);
				
				userVaccineReporterAdverseEffectRecordOuts.add(userVaccineReporterAdverseEffectRecordOut);
				
			}
		}
			
		return userVaccineReporterAdverseEffectRecordOuts;
	}
	
	protected List<ReportedAdverseEffectRecordOut> getReportedAdverseEffectRecordOuts(List<AdverseEventReported> adverseEventReporteds){
		List<ReportedAdverseEffectRecordOut> reportedAdverseEffectRecordOuts = new ArrayList<ReportedAdverseEffectRecordOut>();
		
		for(AdverseEventReported adverseEventReported : adverseEventReporteds) {
			ReportedAdverseEffectRecordOut reportedAdverseEffectRecordOut = new ReportedAdverseEffectRecordOut();
			reportedAdverseEffectRecordOut.setAdverseEffectName(adverseEventReported.getAdverseEventMaster1().getAdevName());
			reportedAdverseEffectRecordOut.setSeverityName(adverseEventReported.getSeverityMaster().getSevName());
			reportedAdverseEffectRecordOut.setAdditionalNotes(adverseEventReported.getAdevRepAdditionalNotes());
			reportedAdverseEffectRecordOut.setStartDate(VaersUtilities.getStringFromDate(adverseEventReported.getAdevRepStartDateTime()));
			reportedAdverseEffectRecordOut.setIsFatal(VaersUtilities.getStringFromBoolean(adverseEventReported.isAdevRepIsfatal()));
			reportedAdverseEffectRecordOut.setDateOfDeath(VaersUtilities.getStringFromDate(adverseEventReported.getAdevRepDateOfDeath()));
			reportedAdverseEffectRecordOut.setIsMedicNeeded(VaersUtilities.getStringFromBoolean(adverseEventReported.isAdevRepIsmedicNeeded()));
			reportedAdverseEffectRecordOut.setMedicNotes(adverseEventReported.getAdevRepMedicNotes());
			reportedAdverseEffectRecordOut.setIsRecovered(VaersUtilities.getStringFromBoolean(adverseEventReported.isAdevRepIsrecovered()));
			reportedAdverseEffectRecordOut.setRecoveryDate(VaersUtilities.getStringFromDate(adverseEventReported.getAdevRepRecoveryDate()));
			
			reportedAdverseEffectRecordOuts.add(reportedAdverseEffectRecordOut);
		}
		
		return reportedAdverseEffectRecordOuts;
	}

}
