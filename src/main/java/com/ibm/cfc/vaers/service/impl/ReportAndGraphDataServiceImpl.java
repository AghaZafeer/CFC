package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.ListOfAdverseEventsDataOut;
import com.ibm.cfc.vaers.dto.out.ListOfVaccineDataOut;
import com.ibm.cfc.vaers.dto.out.ListOfVaccineDosesDataOut;
import com.ibm.cfc.vaers.dto.out.ReportAndGraphDataOut;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.AdverseEventReported;
import com.ibm.cfc.vaers.model.SeverityMaster;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.repository.AdverseEventReportedRepository;
import com.ibm.cfc.vaers.repository.SeverityMasterRepository;
import com.ibm.cfc.vaers.repository.UserMasterRepository;
import com.ibm.cfc.vaers.repository.UserVaccineRepository;
import com.ibm.cfc.vaers.repository.VaccineDoseMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.ReportAndGraphDataService;

@Service
public class ReportAndGraphDataServiceImpl implements ReportAndGraphDataService {

	private static final Logger logger = LoggerFactory.getLogger(ReportAndGraphDataServiceImpl.class);
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	VaccineMasterRepository vaccineMasterRepository; 
	
	@Autowired
	UserVaccineRepository userVaccineRepository; 
	
	@Autowired
	VaccineDoseMasterRepository vaccineDoseMasterRepository; 
	
	@Autowired
	AdverseEventMasterRepository adverseEventMasterRepository;
	
	@Autowired
	AdverseEventReportedRepository adverseEventReportedRepository;
	
	@Autowired
	SeverityMasterRepository severityMasterRepository;
	
	@Override
	public ReportAndGraphDataOut getReportAndGraphData() {
		ReportAndGraphDataOut returnObject = new ReportAndGraphDataOut();
		
		List<User> totalNoOfUsers = userMasterRepository.findByUserIsactiveTrue(); 
		
		returnObject.setTotalNoOfUsers(String.valueOf(totalNoOfUsers.size()));
		
		List<ListOfVaccineDataOut> listOfVaccineDataOuts = getListOfVaccineDataOut(); 
		returnObject.setListOfVaccineDataOuts(listOfVaccineDataOuts);
		
		return returnObject;
	}
	
	protected List<ListOfVaccineDataOut> getListOfVaccineDataOut(){
		List<ListOfVaccineDataOut> listOfVaccineDataOuts = new ArrayList<ListOfVaccineDataOut>();
		
		//Get the total vaccines
		List<VaccineMaster> vaccineMasters = vaccineMasterRepository.findByVaccineIsactiveTrue();
		for(VaccineMaster vaccineMaster : vaccineMasters) {
			List<UserVaccine> usersVaccinatedPerVaccine = new ArrayList<UserVaccine>();
			ListOfVaccineDataOut listOfVaccineDataOut = new ListOfVaccineDataOut();
			listOfVaccineDataOut.setVaccineID(String.valueOf(vaccineMaster.getVaccineId()));
			listOfVaccineDataOut.setVaccineName(vaccineMaster.getVaccineName());
			
			usersVaccinatedPerVaccine = userVaccineRepository.findAllByVaccineMaster(vaccineMaster);
			listOfVaccineDataOut.setTotalNoOfUsersPerVaccine(String.valueOf(usersVaccinatedPerVaccine.size()));
			
			List<ListOfVaccineDosesDataOut> listOfVaccineDosesDataOuts = getListOfVaccineDosesDataOut(vaccineMaster); 
			listOfVaccineDataOut.setListOfVaccineDosesDataOuts(listOfVaccineDosesDataOuts);
			
			listOfVaccineDataOuts.add(listOfVaccineDataOut);
		}
		
		return listOfVaccineDataOuts;
	}
	
	protected List<ListOfVaccineDosesDataOut> getListOfVaccineDosesDataOut(VaccineMaster vaccineMaster){
		List<ListOfVaccineDosesDataOut> listOfVaccineDosesDataOuts = new ArrayList<ListOfVaccineDosesDataOut>();
		List<VaccineDoseMaster> vaccineDoseMasters = new ArrayList<VaccineDoseMaster>();
		//Get the doses for the vaccine
		vaccineDoseMasters = vaccineDoseMasterRepository.findByVaccineMaster(vaccineMaster);
		if(vaccineDoseMasters.size() > 0) {
			for(VaccineDoseMaster vaccineDoseMaster : vaccineDoseMasters) {
				
				List<UserVaccine> usersVaccinatedPerVaccineDose = new ArrayList<UserVaccine>();
				List<AdverseEventMaster> adverseEventMasters = new ArrayList<AdverseEventMaster>();
				
				ListOfVaccineDosesDataOut listOfVaccineDosesDataOut = new ListOfVaccineDosesDataOut();
				listOfVaccineDosesDataOut.setVaccineDoseId(String.valueOf(vaccineDoseMaster.getVaccineDoseId()));
				listOfVaccineDosesDataOut.setVaccineDoseName(vaccineDoseMaster.getVaccineDoseName());
				
				usersVaccinatedPerVaccineDose = userVaccineRepository.findAllByVaccineMasterAndVaccineDoseMaster(vaccineMaster, vaccineDoseMaster);
				listOfVaccineDosesDataOut.setTotalNoOfUsersForDose(String.valueOf(usersVaccinatedPerVaccineDose.size()));
				
				List<ListOfAdverseEventsDataOut> listOfAdverseEventsDataOuts = new ArrayList<ListOfAdverseEventsDataOut>();
				adverseEventMasters = adverseEventMasterRepository.findByAdevIsactiveTrue();
				for(AdverseEventMaster adverseEventMaster: adverseEventMasters) {
					ListOfAdverseEventsDataOut listOfAdverseEventsDataOut = getListOfAdverseEventsDataOut(adverseEventMaster, vaccineMaster,vaccineDoseMaster); 
					if(listOfAdverseEventsDataOut.getTotalNoOfUsersForAdverseEvent() > 0) {
						listOfAdverseEventsDataOuts.add(listOfAdverseEventsDataOut);
					}
				}
				
				
				listOfVaccineDosesDataOut.setListOfAdverseEventsDataOuts(listOfAdverseEventsDataOuts);
				
				listOfVaccineDosesDataOuts.add(listOfVaccineDosesDataOut);
			}
		}
		
		return listOfVaccineDosesDataOuts;
	}

	protected ListOfAdverseEventsDataOut getListOfAdverseEventsDataOut(AdverseEventMaster adverseEventMaster, VaccineMaster vaccineMaster, VaccineDoseMaster vaccineDoseMaster){
		ListOfAdverseEventsDataOut listOfAdverseEventsDataOut = new ListOfAdverseEventsDataOut();
		List<AdverseEventReported> adverseEventReporteds = new ArrayList<AdverseEventReported>();
		
		listOfAdverseEventsDataOut.setNameOfAdverseEvent(adverseEventMaster.getAdevName());
		
		adverseEventReporteds = adverseEventReportedRepository.findAllByAdverseEventMaster1AndVaccineMasterAndVaccineDoseMaster(adverseEventMaster, vaccineMaster, vaccineDoseMaster); 
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEvent(adverseEventReporteds.size());
		
		//Perform the other calculations
		int totalNoOfUsersForAdverseEventWithMildSeverity = 0;
	    int totalNoOfUsersForAdverseEventWithModerateSeverity = 0;
	    int totalNoOfUsersForAdverseEventWithHighSeverity = 0;
	    int totalNoOfUsersForAdverseEventOfAgeGroup1 = 0;
	    int totalNoOfUsersForAdverseEventOfAgeGroup2 = 0;
	    int totalNoOfUsersForAdverseEventOfAgeGroup3 = 0; 
		
		if(adverseEventReporteds.size() > 0) {
			SeverityMaster severityMasterMild = severityMasterRepository.findBySevName("Mild");
			SeverityMaster severityMasterModerate = severityMasterRepository.findBySevName("Moderate");
			SeverityMaster severityMasterSevere = severityMasterRepository.findBySevName("Severe");
			for(AdverseEventReported adverseEventReported : adverseEventReporteds) {
				// Calculating the Severity Counts
				if(adverseEventReported.getSeverityMaster().getSevName().equalsIgnoreCase(severityMasterMild.getSevName())) {
					totalNoOfUsersForAdverseEventWithMildSeverity = totalNoOfUsersForAdverseEventWithMildSeverity + 1;
				}else if(adverseEventReported.getSeverityMaster().getSevName().equalsIgnoreCase(severityMasterModerate.getSevName())) {
					totalNoOfUsersForAdverseEventWithModerateSeverity = totalNoOfUsersForAdverseEventWithModerateSeverity + 1;
				}if(adverseEventReported.getSeverityMaster().getSevName().equalsIgnoreCase(severityMasterSevere.getSevName())) {
					totalNoOfUsersForAdverseEventWithHighSeverity = totalNoOfUsersForAdverseEventWithHighSeverity + 1;
				}
				
				//Calcuating the Age Group Counts
				if(adverseEventReported.getUserVaccine().getUser().getUserAge() >= 18 && adverseEventReported.getUserVaccine().getUser().getUserAge() < 40) {
					totalNoOfUsersForAdverseEventOfAgeGroup1 = totalNoOfUsersForAdverseEventOfAgeGroup1 + 1;
				}else if(adverseEventReported.getUserVaccine().getUser().getUserAge() >= 40 && adverseEventReported.getUserVaccine().getUser().getUserAge() < 60) {
					totalNoOfUsersForAdverseEventOfAgeGroup2 = totalNoOfUsersForAdverseEventOfAgeGroup2 + 1;
				}else if(adverseEventReported.getUserVaccine().getUser().getUserAge() >= 60) {
					totalNoOfUsersForAdverseEventOfAgeGroup3 = totalNoOfUsersForAdverseEventOfAgeGroup3 + 1;
				}
			}
		}
		
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventWithMildSeverity(totalNoOfUsersForAdverseEventWithMildSeverity);
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventWithModerateSeverity(totalNoOfUsersForAdverseEventWithModerateSeverity);
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventWithHighSeverity(totalNoOfUsersForAdverseEventWithHighSeverity);
		
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventOfAgeGroup1(totalNoOfUsersForAdverseEventOfAgeGroup1);
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventOfAgeGroup2(totalNoOfUsersForAdverseEventOfAgeGroup2);
		listOfAdverseEventsDataOut.setTotalNoOfUsersForAdverseEventOfAgeGroup3(totalNoOfUsersForAdverseEventOfAgeGroup3);
	    
	    
		return listOfAdverseEventsDataOut;
	}
	
}
