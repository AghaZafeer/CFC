package com.ibm.cfc.vaers.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.GenerateOTPObjOut;
import com.ibm.cfc.vaers.dto.out.ValidateOTPObjOut;
import com.ibm.cfc.vaers.model.MailDetail;
import com.ibm.cfc.vaers.model.OtpCode;
import com.ibm.cfc.vaers.repository.MailDetailRepository;
import com.ibm.cfc.vaers.repository.OtpCodeRepository;
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
			}
		}
		
		return returnObj;
	}
		
	//@Scheduled()

	
}
