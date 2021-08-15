package com.ibm.cfc.vaers.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.in.UserAdverseEffectIn;
import com.ibm.cfc.vaers.dto.in.UserAllergicConditionIn;
import com.ibm.cfc.vaers.dto.in.UserIllnessIn;
import com.ibm.cfc.vaers.dto.out.ReportAdverseEventsForUserOut;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.AdverseEventReported;
import com.ibm.cfc.vaers.model.AllergicConditionsMaster;
import com.ibm.cfc.vaers.model.CaseReporter;
import com.ibm.cfc.vaers.model.IllnessMaster;
import com.ibm.cfc.vaers.model.MailDetail;
import com.ibm.cfc.vaers.model.SeverityMaster;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;
import com.ibm.cfc.vaers.model.UserTitleMaster;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.repository.AdverseEventReportedRepository;
import com.ibm.cfc.vaers.repository.AllergicConditionsMasterRepository;
import com.ibm.cfc.vaers.repository.CaseReporterRepository;
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.MailDetailRepository;
import com.ibm.cfc.vaers.repository.SeverityMasterRepository;
import com.ibm.cfc.vaers.repository.UserAllergicConditionRepository;
import com.ibm.cfc.vaers.repository.UserIllnessRepository;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.repository.UserTitleMasterRepository;
import com.ibm.cfc.vaers.repository.UserVaccineRepository;
import com.ibm.cfc.vaers.repository.VaccineDoseMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.EmailService;
import com.ibm.cfc.vaers.service.ReportAdverseEventService;
import com.ibm.cfc.vaers.utils.MailDetailDTO;
import com.ibm.cfc.vaers.utils.VaersConstants;
import com.ibm.cfc.vaers.utils.VaersUtilities;

@Service
public class ReportAdverseEventServiceImpl implements ReportAdverseEventService {

	private static final Logger logger = LoggerFactory.getLogger(ReportAdverseEventServiceImpl.class);
	
	@Autowired
	UserTitleMasterRepository userTitleMasterRepository;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	AllergicConditionsMasterRepository allergicConditionsMasterRepository;
	
	@Autowired
	UserAllergicConditionRepository userAllergicConditionRepository;
	
	@Autowired
	IllnessMasterRepository illnessMasterRepository;
	
	@Autowired
	UserIllnessRepository userIllnessRepository;
	
	@Autowired
	VaccineMasterRepository vaccineMasterRepository;
	
	@Autowired
	VaccineDoseMasterRepository vaccineDoseMasterRepository;
	
	@Autowired
	UserVaccineRepository userVaccineRepository;
	
	@Autowired 
	AdverseEventMasterRepository adverseEventMasterRepository; 
	
	@Autowired
	SeverityMasterRepository severityMasterRepository;
	
	@Autowired
	AdverseEventReportedRepository adverseEventReportedRepository;
	
	@Autowired
	CaseReporterRepository caseReporterRepository;
	
	@Autowired
	MailDetailRepository mailDetailRepository;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public ReportAdverseEventsForUserOut saveUserReportedAdverseEffects(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn) {
		ReportAdverseEventsForUserOut reportAdverseEventsForUserOut = new ReportAdverseEventsForUserOut();
		
		User userInScope = checkIfAadhaarPresentInRecord(reportAdverseEventsForUserIn);
		
		if(userInScope != null && userInScope.getUserId() > 0) {
			if(!checkIfVaccineAndDosageInfoPresentInRecordForUser(reportAdverseEventsForUserIn, userInScope)) {
				//persist the Case Reporter data
				String caseID = persistCaseReporterInfo(reportAdverseEventsForUserIn, userInScope);
				
				//persist user allergies
				persistUserAllergiesInfo(reportAdverseEventsForUserIn, userInScope);
				
				// persist user illness
				persistUserIllnessInfo(reportAdverseEventsForUserIn, userInScope);
				
				// persist user vaccine
				UserVaccine newUserVaccine = persistUserVaccineInfo(reportAdverseEventsForUserIn, userInScope);
				
				// persist adverse reported table
				persistAdverseEventReportedInfo(reportAdverseEventsForUserIn, newUserVaccine);
				
				reportAdverseEventsForUserOut.setCaseNumber(caseID);
				reportAdverseEventsForUserOut.setStatus("SUCCESS");
				reportAdverseEventsForUserOut.setMessage(VaersConstants.CASE_REPORTED_SUCCESSFULLY);
				
				// Send Email
				MailDetail mailDetail = mailDetailRepository.findByMdCode(VaersConstants.MAIL_CODE_REPORTING);
				MailDetailDTO mailDetailDTO = new MailDetailDTO();
				
				mailDetailDTO.setMailTo(reportAdverseEventsForUserIn.getUserEmail());
				mailDetailDTO.setMailFromEmail(mailDetail.getMdFromEmail());
				mailDetailDTO.setMailFromName(mailDetail.getMdFromEmailName());
				mailDetailDTO.setMailSubject(mailDetail.getMdSubject());
				String tempMailBody = mailDetail.getMdBody().replace(VaersConstants.MAIL_REPORTING_STRING_TO_FIND_1, reportAdverseEventsForUserIn.getUserTitleID());
				mailDetailDTO.setMailBody(tempMailBody.replace(VaersConstants.MAIL_REPORTING_STRING_TO_FIND_2, reportAdverseEventsForUserIn.getUserLastName()));
				
				emailService.sendSimpleMessage(mailDetailDTO);
				
			}else {
				reportAdverseEventsForUserOut.setCaseNumber(null);
				reportAdverseEventsForUserOut.setStatus("ERROR_IN_PERSISTING_DATA");
				reportAdverseEventsForUserOut.setMessage(VaersConstants.MESSAGE_ERROR_IN_DATA);
			}
		}else {
			User newUser = persistUserInfo(reportAdverseEventsForUserIn);
			if(newUser.getUserId() > 0) {
				logger.debug("New User Added with User ID >>>> " + newUser.getUserId());
				//persist the Case Reporter data
				String caseID = persistCaseReporterInfo(reportAdverseEventsForUserIn, newUser);
				
				//persist user allergies
				persistUserAllergiesInfo(reportAdverseEventsForUserIn, newUser);
				
				// persist user illness
				persistUserIllnessInfo(reportAdverseEventsForUserIn, newUser);
				
				// persist user vaccine
				UserVaccine newUserVaccine = persistUserVaccineInfo(reportAdverseEventsForUserIn, newUser);
				
				// persist adverse reported table
				persistAdverseEventReportedInfo(reportAdverseEventsForUserIn, newUserVaccine);
				
				reportAdverseEventsForUserOut.setCaseNumber(caseID);
				reportAdverseEventsForUserOut.setStatus("SUCCESS");
				reportAdverseEventsForUserOut.setMessage(VaersConstants.CASE_REPORTED_SUCCESSFULLY);
				
				// Send Email
				MailDetail mailDetail = mailDetailRepository.findByMdCode(VaersConstants.MAIL_CODE_REPORTING);
				MailDetailDTO mailDetailDTO = new MailDetailDTO();
				
				mailDetailDTO.setMailTo(reportAdverseEventsForUserIn.getUserEmail());
				mailDetailDTO.setMailFromEmail(mailDetail.getMdFromEmail());
				mailDetailDTO.setMailFromName(mailDetail.getMdFromEmailName());
				mailDetailDTO.setMailSubject(mailDetail.getMdSubject());
				String tempMailBody = mailDetail.getMdBody().replace(VaersConstants.MAIL_REPORTING_STRING_TO_FIND_1, reportAdverseEventsForUserIn.getUserTitleID());
				mailDetailDTO.setMailBody(tempMailBody.replace(VaersConstants.MAIL_REPORTING_STRING_TO_FIND_2, reportAdverseEventsForUserIn.getUserLastName()));
				
				emailService.sendSimpleMessage(mailDetailDTO);
			}
		}
		
		return reportAdverseEventsForUserOut;
	}
	
	public User checkIfAadhaarPresentInRecord(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn) {
		User userToCheck = new User();
		userToCheck = userMasterRepository.findByUserAadhaarNo(reportAdverseEventsForUserIn.getUserAadhaarNumber().trim());
		return userToCheck;
	}
	
	public boolean checkIfVaccineAndDosageInfoPresentInRecordForUser(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, User user) {
		VaccineMaster vaccineMaster = new VaccineMaster();
		vaccineMaster  = vaccineMasterRepository.findByVaccineName(reportAdverseEventsForUserIn.getVaccineID());
		
		VaccineDoseMaster vaccineDoseMaster = new VaccineDoseMaster(); 		
		vaccineDoseMaster = vaccineDoseMasterRepository.findByVaccineMasterAndVaccineDoseName(vaccineMaster, reportAdverseEventsForUserIn.getVaccineDoseID());
		
		UserVaccine userVaccine = userVaccineRepository.findByUserAndVaccineMasterAndVaccineDoseMaster(user, vaccineMaster, vaccineDoseMaster);
		
		if(userVaccine.getUsvacId() > 0) {
			return true;
		}
		return false;
	}
	
	public User persistUserInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn) {
		
		User user = new User();
		
		UserTitleMaster userTitleMaster = new UserTitleMaster();
		if(reportAdverseEventsForUserIn.getUserTitleID()!= null) {
			userTitleMaster = userTitleMasterRepository.findByUserTitleName(reportAdverseEventsForUserIn.getUserTitleID());
		}
		user.setUserTitleMaster2(userTitleMaster);
		user.setUserFirstName(reportAdverseEventsForUserIn.getUserFirstName());
		user.setUserMiddleName(reportAdverseEventsForUserIn.getUserMiddleName());
		user.setUserLastName(reportAdverseEventsForUserIn.getUserLastName());
		user.setUserAadhaarNo(reportAdverseEventsForUserIn.getUserAadhaarNumber());
		user.setUserMobile(reportAdverseEventsForUserIn.getUserMobile());
		user.setUserAlternateNumber(reportAdverseEventsForUserIn.getUserAlternateMobile());
		user.setUserEmailId(reportAdverseEventsForUserIn.getUserEmail());
		user.setUserAddress(reportAdverseEventsForUserIn.getUserAddress());
		user.setUserAge(Short.parseShort(reportAdverseEventsForUserIn.getUserAge()));
		user.setUserDob(VaersUtilities.getDateFromString(reportAdverseEventsForUserIn.getDateOfBirth()));
		user.setUserSex(reportAdverseEventsForUserIn.getUserSex());
		user.setUserIsPregnant(VaersUtilities.getBooleanFromString(reportAdverseEventsForUserIn.getUserIsPregnant()));
		
		user.setUserIsactive(true);
		user.setDateCreated(VaersUtilities.getCurrentDate());
		user.setDateModified(VaersUtilities.getCurrentDate());
		
		User newUser = userMasterRepository.saveAndFlush(user);
		return newUser;
	}
			
	public void persistUserAllergiesInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, User newUser) {
		// Persisting Data for the User Allergies Table 
		if(reportAdverseEventsForUserIn.getUserAllergicConditionIns() != null && reportAdverseEventsForUserIn.getUserAllergicConditionIns().size() > 0) {
			for(UserAllergicConditionIn userAllergicConditionIn : reportAdverseEventsForUserIn.getUserAllergicConditionIns()) {
				UserAllergicCondition userAllergicCondition = new UserAllergicCondition();
				// Check for matching entries and add if not present
				AllergicConditionsMaster allergicConditionsMaster = allergicConditionsMasterRepository.findByAllgcondName(userAllergicConditionIn.getAllergicConditionID());
				userAllergicCondition = userAllergicConditionRepository.findByUserAndAllergicConditionsMaster(newUser, allergicConditionsMaster);
				if(userAllergicCondition == null) {
					userAllergicCondition.setUser(newUser);
					userAllergicCondition.setAllergicConditionsMaster(allergicConditionsMaster);
					userAllergicCondition.setDateCreated(VaersUtilities.getCurrentDate());
					userAllergicCondition.setDateModified(VaersUtilities.getCurrentDate());
					UserAllergicCondition newUserAllergicCondition = userAllergicConditionRepository.saveAndFlush(userAllergicCondition);
				}
			}
		}
	}	
	
	public void persistUserIllnessInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, User newUser) {
		// Persisting Data for the User Illness Table 
		if(reportAdverseEventsForUserIn.getUserIllnessIns() != null && reportAdverseEventsForUserIn.getUserIllnessIns().size() > 0) {
			for(UserIllnessIn userIllnessIn : reportAdverseEventsForUserIn.getUserIllnessIns()) {
				UserIllness userIllness = new UserIllness();
				//Check for matching entries and add if not present 
				IllnessMaster illnessMaster = illnessMasterRepository.findByIllnessName(userIllnessIn.getIllnessID());
				userIllness = userIllnessRepository.findByUserAndIllnessMaster(newUser, illnessMaster);
				if(userIllness == null) {
					userIllness.setUser(newUser);
					userIllness.setIllnessMaster(illnessMaster);
					userIllness.setDateCreated(VaersUtilities.getCurrentDate());
					userIllness.setDateModified(VaersUtilities.getCurrentDate());
					UserIllness newUserIllness = userIllnessRepository.saveAndFlush(userIllness);
				}
			}
		}
	}
	
	public UserVaccine persistUserVaccineInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, User newUser) {
		UserVaccine newUserVaccine = new UserVaccine();		
		if(reportAdverseEventsForUserIn.getVaccineID() != null) {
			VaccineMaster vaccineMaster = new VaccineMaster();
			vaccineMaster  = vaccineMasterRepository.findByVaccineName(reportAdverseEventsForUserIn.getVaccineID());
			
			VaccineDoseMaster vaccineDoseMaster = new VaccineDoseMaster(); 		
			vaccineDoseMaster = vaccineDoseMasterRepository.findByVaccineMasterAndVaccineDoseName(vaccineMaster, reportAdverseEventsForUserIn.getVaccineDoseID());
			
			UserVaccine userVaccine = new UserVaccine(); 
			userVaccine.setUser(newUser);
			userVaccine.setVaccineMaster(vaccineMaster);
			userVaccine.setVaccineDoseMaster(vaccineDoseMaster);
			userVaccine.setUsvacVaccinationCenter(reportAdverseEventsForUserIn.getVaccinationCenter());
			userVaccine.setUsvacVaccinationDatetime(VaersUtilities.getTimestampFromString(reportAdverseEventsForUserIn.getVaccinationDate()));
			
			userVaccine.setDateCreated(VaersUtilities.getCurrentDate());
			userVaccine.setDateModified(VaersUtilities.getCurrentDate());
			
			newUserVaccine = userVaccineRepository.saveAndFlush(userVaccine);
		}
		
		return newUserVaccine;
	}
	
	public void persistAdverseEventReportedInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, UserVaccine newUserVaccine) {
		if(reportAdverseEventsForUserIn.getUserAdverseEffectIns() != null && reportAdverseEventsForUserIn.getUserAdverseEffectIns().size() > 0) {
			for(UserAdverseEffectIn userAdverseEffectIn : reportAdverseEventsForUserIn.getUserAdverseEffectIns()) {
				
				// Get the Adeverse Event Object from the name
				AdverseEventMaster thisAdverseEventMaster = new AdverseEventMaster();
				thisAdverseEventMaster = adverseEventMasterRepository.findByAdevName(userAdverseEffectIn.getAdverseEffectID());
				
				// Get the Severity Object from the name 
				SeverityMaster thisSeverityMaster = new SeverityMaster();
				thisSeverityMaster = severityMasterRepository.findBySevName(userAdverseEffectIn.getSeverityID());
				
				AdverseEventReported adverseEventReported = new AdverseEventReported();
				
				adverseEventReported.setUserVaccine(newUserVaccine);
				adverseEventReported.setAdverseEventMaster1(thisAdverseEventMaster);
				adverseEventReported.setSeverityMaster(thisSeverityMaster);
				adverseEventReported.setAdevRepAdditionalNotes(userAdverseEffectIn.getAdditionalNotes());
				adverseEventReported.setAdevRepStartDateTime(VaersUtilities.getDateFromString(userAdverseEffectIn.getAdverseEffectReportingDate()));
				adverseEventReported.setAdevRepIsfatal(VaersUtilities.getBooleanFromString(userAdverseEffectIn.getAdverseEffectIsFatal()));
				adverseEventReported.setAdevRepDateOfDeath(VaersUtilities.getDateFromString(userAdverseEffectIn.getDateOfDeath()));
				adverseEventReported.setAdevRepIsrecovered(VaersUtilities.getBooleanFromString(userAdverseEffectIn.getAdverseEffectIsRecovered()));
				adverseEventReported.setAdevRepRecoveryDate(VaersUtilities.getDateFromString(userAdverseEffectIn.getDateOfRecovery()));
				
				adverseEventReported.setDateCreated(VaersUtilities.getCurrentDate());
				adverseEventReported.setDateModified(VaersUtilities.getCurrentDate());
				
				AdverseEventReported newAdverseEventReported = adverseEventReportedRepository.saveAndFlush(adverseEventReported);
			}
		}
		
	}

	public String persistCaseReporterInfo(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn, User newUser) {
		
		String caseID = "";
		caseID = getUniqueCaseID(VaersUtilities.generateCaseID());
		
		CaseReporter caseReporter = new CaseReporter();
		
		caseReporter.setCaseReporterCaseId(caseID);
		caseReporter.setUser(newUser);
		caseReporter.setCaseInfoAddedBy(reportAdverseEventsForUserIn.getReportedBy());
			
		UserTitleMaster reporterTitleMaster = new UserTitleMaster();
		if(reportAdverseEventsForUserIn.getReporterTitleID()!= null) {
			reporterTitleMaster = userTitleMasterRepository.findByUserTitleName(reportAdverseEventsForUserIn.getReporterTitleID());
		}
		caseReporter.setCaseReporterTitleId(reporterTitleMaster);
		caseReporter.setCaseReporterFirstName(reportAdverseEventsForUserIn.getReporterFirstName());
		caseReporter.setCaseReporterMiddleName(reportAdverseEventsForUserIn.getReporterMiddleName());
		caseReporter.setCaseReporterLastName(reportAdverseEventsForUserIn.getReporterLastName());
		caseReporter.setCaseReporterMobileNumber(reportAdverseEventsForUserIn.getReporterMobile());
		caseReporter.setCaseReporterAlternateNumber(reportAdverseEventsForUserIn.getReporterAlternateMobile());
		caseReporter.setCaseReporterEmailId(reportAdverseEventsForUserIn.getReporterEmail());
		caseReporter.setDateCreated(VaersUtilities.getCurrentDate());
		caseReporter.setDateModified(VaersUtilities.getCurrentDate());
		
		CaseReporter newCaseReporter = caseReporterRepository.saveAndFlush(caseReporter);
		
		
		return caseID;
	}
	
	public String getUniqueCaseID(String caseID) {
		String uniqueCaseID = caseID;
		CaseReporter caseReporter = caseReporterRepository.findByCaseReporterCaseId(uniqueCaseID);
		
		while(caseReporter.getCaseReporterId() > 0) {
			uniqueCaseID = getUniqueCaseID(VaersUtilities.generateCaseID());
		}
		
		return uniqueCaseID;
	}
	
}
