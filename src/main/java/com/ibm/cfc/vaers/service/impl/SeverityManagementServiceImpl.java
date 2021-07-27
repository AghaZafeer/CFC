package com.ibm.cfc.vaers.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.controller.VaccineManagamentController;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.dto.out.IllnessMasterOut;
import com.ibm.cfc.vaers.dto.out.SeverityMasterOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.VaccineDoseMasterOut;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.IllnessMaster;
import com.ibm.cfc.vaers.model.SeverityMaster;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.SeverityMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;
import com.ibm.cfc.vaers.service.IllnessManagementService;
import com.ibm.cfc.vaers.service.SeverityManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@Service
public class SeverityManagementServiceImpl implements SeverityManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(SeverityManagementServiceImpl.class);
	
	@Autowired
	SeverityMasterRepository severityMasterRepository; 
	

		public List<SeverityMasterOut> getListOfSeverity(){
		// TODO Auto-generated method stub
		logger.debug("Inside getListOfSeverity of SeverityManagementServiceImpl >>>>>>> ");
		
		List <SeverityMasterOut> returnObject = new ArrayList<SeverityMasterOut>(); 
		
		List<SeverityMaster> severityMasters = severityMasterRepository.findAll();
		
		
		logger.debug("Inside getListOfSeverity of SeverityManagementServiceImpl >>>>>>> severityMasters.size >>> " + severityMasters.size());
		
		for(SeverityMaster severityMaster : severityMasters) {
			SeverityMasterOut severityMasterOut = new SeverityMasterOut();
			logger.debug("Inside getListOfSeverity of SeverityManagementServiceImpl >>>>>>> severityMaster.getAdevId() >>>> " + severityMaster.getSevId());
			
			severityMasterOut.setSevId(severityMaster.getSevId());
			severityMasterOut.setSevName(severityMaster.getSevName());
			severityMasterOut.setSevIsactive(severityMaster.isSevIsactive());
						
			returnObject.add(severityMasterOut);
			severityMasterOut = null;
			
			logger.debug("Inside getListOfSeverity of SeverityManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
		
	}

	
}
