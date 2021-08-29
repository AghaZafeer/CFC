package com.ibm.cfc.vaers.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.UserAllergiesOut;
import com.ibm.cfc.vaers.dto.out.UserIllnessOut;
import com.ibm.cfc.vaers.dto.out.UserMasterOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;
import com.ibm.cfc.vaers.model.AllergicConditionsMaster;
import com.ibm.cfc.vaers.model.MailDetail;
import com.ibm.cfc.vaers.model.OtpCode;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;
import com.ibm.cfc.vaers.repository.MailDetailRepository;
import com.ibm.cfc.vaers.repository.OtpCodeRepository;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.service.EmailService;
import com.ibm.cfc.vaers.service.OTPManagementService;
import com.ibm.cfc.vaers.utils.MailDetailDTO;
import com.ibm.cfc.vaers.utils.VaersConstants;
import com.ibm.cfc.vaers.utils.VaersUtilities;


@Service
public class OTPManagementServiceImpl implements OTPManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(OTPManagementServiceImpl.class);
	
	@Autowired
	OtpCodeRepository otpCodeRepository; 
	
	@Autowired
	MailDetailRepository mailDetailRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Override
	public GenerateOTPObjOut getOTP(String emailID){
		GenerateOTPObjOut returnObject = new GenerateOTPObjOut(); 
		
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
		MailDetail mailDetail = mailDetailRepository.findByMdCode(VaersConstants.MAIL_CODE_OTP);
		MailDetailDTO mailDetailDTO = new MailDetailDTO();
		
		mailDetailDTO.setMailTo(emailID);
		mailDetailDTO.setMailFromEmail(mailDetail.getMdFromEmail());
		mailDetailDTO.setMailFromName(mailDetail.getMdFromEmailName());
		mailDetailDTO.setMailSubject(mailDetail.getMdSubject());
		mailDetailDTO.setMailBody(mailDetail.getMdBody().replace(VaersConstants.MAIL_OTP_STRING_TO_FIND, newOTPValue));
		
		emailService.sendSimpleMessage(mailDetailDTO);
		
		returnObject.setOtpGenerationSuccessful(true);
		return returnObject;
	}
	
	@Override
	public ValidateOTPObjOut validateOTP(String otpEmailID, String otpValue){
		ValidateOTPObjOut returnObj = new ValidateOTPObjOut();
		
		returnObj.setValid(false);
		
		OtpCode otpCode = otpCodeRepository.findByOtpEmailIdAndOtpValue(otpEmailID, otpValue);
		
		if(otpCode.getOtpId() > 0) {
			if(otpCode.isOtpActive()) {
				returnObj.setValid(true);
				
				// Set the User
				User thisUser = userMasterRepository.findByUserEmailId(otpEmailID);
				if(thisUser != null && thisUser.getUserId() > 0) {
					UserMasterOut userMasterOut = new UserMasterOut();
					userMasterOut.setUserTitle(thisUser.getUserTitleMaster2().getUserTitleName());
					userMasterOut.setFirstName(thisUser.getUserFirstName());
					userMasterOut.setMiddleName(thisUser.getUserMiddleName());
					userMasterOut.setLastName(thisUser.getUserLastName());
					userMasterOut.setEmailID(thisUser.getUserEmailId());
					userMasterOut.setAddress(thisUser.getUserAddress());
					userMasterOut.setContactNumber(thisUser.getUserMobile());
					userMasterOut.setAlternateContactNumber(thisUser.getUserAlternateNumber());
					userMasterOut.setAadhaarNumber(thisUser.getUserAadhaarNo());
					userMasterOut.setBeneficiaryReferenceID(thisUser.getUserBeneficiaryRefId());
					userMasterOut.setAge(String.valueOf(thisUser.getUserAge()));
					userMasterOut.setDateOfBirth(VaersUtilities.getStringFromDate(thisUser.getUserDob()));
					userMasterOut.setGender(thisUser.getUserSex());
					userMasterOut.setIsPregnant(VaersUtilities.getStringFromBoolean(thisUser.isUserIsPregnant()));
					
					List<UserIllnessOut> userIllnessOuts = new ArrayList<UserIllnessOut>(); 
					if(thisUser.getUserIllnesses() != null && thisUser.getUserIllnesses().size() > 0) {
						for(UserIllness userIllness : thisUser.getUserIllnesses()) {
							UserIllnessOut userIllnessOut = new UserIllnessOut();
							userIllnessOut.setIllnessName(userIllness.getIllnessMaster().getIllnessName());
							userIllnessOuts.add(userIllnessOut);
						}
					}
					userMasterOut.setUserIllnessOuts(userIllnessOuts);
					
					List<UserAllergiesOut> userAllergiesOuts = new ArrayList<UserAllergiesOut>(); 
					if(thisUser.getUserAllergicConditions() != null && thisUser.getUserAllergicConditions().size() > 0) {
						for(UserAllergicCondition userAllergicCondition : thisUser.getUserAllergicConditions()) {
							UserAllergiesOut userAllergiesOut = new UserAllergiesOut();
							userAllergiesOut.setAllergyName(userAllergicCondition.getAllergicConditionsMaster().getAllgcondName());
							userAllergiesOuts.add(userAllergiesOut);
						}
					}
					userMasterOut.setUserAllergiesOut(userAllergiesOuts);
					
					returnObj.setUserMasterOut(userMasterOut);
				}
			}
		}
		
		return returnObj;
	}
		
	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	public void inActivateOTP() {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.SECOND, -120);
		Date expiryDate = cal.getTime();
		
		List<OtpCode> otpCodes = otpCodeRepository.findAllByDateCreatedBeforeAndOtpActiveTrue(expiryDate);
		
		if(otpCodes != null && otpCodes.size() > 0) {
			//System.out.println("otpCodes.size >>>>> " + otpCodes.size());
			for(OtpCode otpCode : otpCodes) {
				otpCode.setOtpActive(false);
				otpCodeRepository.saveAndFlush(otpCode);
			}
		}
	}

	
}
