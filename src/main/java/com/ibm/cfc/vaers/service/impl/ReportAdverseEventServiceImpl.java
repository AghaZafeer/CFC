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
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.SeverityMasterRepository;
import com.ibm.cfc.vaers.repository.UserAllergicConditionRepository;
import com.ibm.cfc.vaers.repository.UserIllnessRepository;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.repository.UserTitleMasterRepository;
import com.ibm.cfc.vaers.repository.UserVaccineRepository;
import com.ibm.cfc.vaers.repository.VaccineDoseMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.ReportAdverseEventService;
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
	
	@Override
	@Transactional
	public ReportAdverseEventsForUserOut saveUserReportedAdverseEffects(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn) {
		ReportAdverseEventsForUserOut reportAdverseEventsForUserOut = new ReportAdverseEventsForUserOut();
		
		try {
			//Persisting the data for User table
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
			
			user.setUserInfoAddedBy(reportAdverseEventsForUserIn.getReportedBy());
			
			UserTitleMaster reporterTitleMaster = new UserTitleMaster();
			if(reportAdverseEventsForUserIn.getReporterTitleID()!= null) {
				reporterTitleMaster = userTitleMasterRepository.findByUserTitleName(reportAdverseEventsForUserIn.getReporterTitleID());
			}
			
			user.setUserTitleMaster1(reporterTitleMaster);
			user.setUserReporterFirstName(reportAdverseEventsForUserIn.getReporterFirstName());
			user.setUserReporterMiddleName(reportAdverseEventsForUserIn.getReporterMiddleName());
			user.setUserReporterLastName(reportAdverseEventsForUserIn.getReporterLastName());
			user.setUserReporterMobile(reportAdverseEventsForUserIn.getReporterMobile());
			user.setUserReporterAlternateNumber(reportAdverseEventsForUserIn.getReporterAlternateMobile());
			user.setUserReporterEmailId(reportAdverseEventsForUserIn.getReporterEmail());
			
			user.setUserIsactive(true);
			user.setDateCreated(VaersUtilities.getCurrentDate());
			user.setDateModified(VaersUtilities.getCurrentDate());
			
			User newUser = userMasterRepository.saveAndFlush(user);
			if(newUser.getUserId() > 0) {
				logger.debug("New User Added with User ID >>>> " + newUser.getUserId());
			}
			
			// Persisting Data for the User Allergies Table 
			if(reportAdverseEventsForUserIn.getUserAllergicConditionIns() != null && reportAdverseEventsForUserIn.getUserAllergicConditionIns().size() > 0) {
				for(UserAllergicConditionIn userAllergicConditionIn : reportAdverseEventsForUserIn.getUserAllergicConditionIns()) {
					UserAllergicCondition userAllergicCondition = new UserAllergicCondition();
					
					userAllergicCondition.setUser(newUser);
					userAllergicCondition.setAllergicConditionsMaster(allergicConditionsMasterRepository.findByAllgcondName(userAllergicConditionIn.getAllergicConditionID()));
					
					userAllergicCondition.setDateCreated(VaersUtilities.getCurrentDate());
					userAllergicCondition.setDateModified(VaersUtilities.getCurrentDate());
					
					UserAllergicCondition newUserAllergicCondition = userAllergicConditionRepository.saveAndFlush(userAllergicCondition);
				}
			}
			
			// Persisting Data for the User Illness Table 
			if(reportAdverseEventsForUserIn.getUserIllnessIns() != null && reportAdverseEventsForUserIn.getUserIllnessIns().size() > 0) {
				for(UserIllnessIn userIllnessIn : reportAdverseEventsForUserIn.getUserIllnessIns()) {
					UserIllness userIllness = new UserIllness();
					
					userIllness.setUser(newUser);
					userIllness.setIllnessMaster(illnessMasterRepository.findByIllnessName(userIllnessIn.getIllnessID()));
					
					userIllness.setDateCreated(VaersUtilities.getCurrentDate());
					userIllness.setDateModified(VaersUtilities.getCurrentDate());
					
					UserIllness newUserIllness = userIllnessRepository.saveAndFlush(userIllness);
				}
			}
			
			// Persisting the Data for the User Vaccine table 
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
			
			//Persisting the Data for the Adverse Event Reported table
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
			
			reportAdverseEventsForUserOut.setMessage("Successfully persisted the Adverse Event Reporting data");
			reportAdverseEventsForUserOut.setStatus("SUCCESS");
			
		}catch(Exception e) {
			reportAdverseEventsForUserOut.setMessage("Error while persisting the Adverse Event Reporting data" + e.getMessage());
			reportAdverseEventsForUserOut.setStatus("ERROR");
		}
		
		return reportAdverseEventsForUserOut;
	}

}
