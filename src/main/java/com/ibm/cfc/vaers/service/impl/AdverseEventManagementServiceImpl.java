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
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;
import com.ibm.cfc.vaers.service.IllnessManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@Service
public class AdverseEventManagementServiceImpl implements AdverseEventManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdverseEventManagementServiceImpl.class);
	
	@Autowired
	AdverseEventMasterRepository adverseEventMasterRepository; 
	

		public List<AdverseEventMasterOut> getParentAdverseEvents(){
		// TODO Auto-generated method stub
		logger.debug("Inside getParentAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> ");
		
		List <AdverseEventMasterOut> returnObject = new ArrayList<AdverseEventMasterOut>(); 
		
		List<AdverseEventMaster> adverseEventMasters = adverseEventMasterRepository.findByAdevIsParentElementTrue();
		
		
		logger.debug("Inside getParentAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMasters.size >>> " + adverseEventMasters.size());
		
		for(AdverseEventMaster adverseEventMaster : adverseEventMasters) {
			AdverseEventMasterOut adverseEventMasterOut = new AdverseEventMasterOut();
			logger.debug("Inside getParentAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMaster.getAdevId() >>>> " + adverseEventMaster.getAdevId());
			
			adverseEventMasterOut.setAdevId(adverseEventMaster.getAdevId());
			adverseEventMasterOut.setAdevName(adverseEventMaster.getAdevName());
			adverseEventMasterOut.setAdevIsactive(adverseEventMaster.isAdevIsactive());
			adverseEventMasterOut.setSeverityMasterOut(null);
			
			returnObject.add(adverseEventMasterOut);
			adverseEventMasterOut = null;
			
			logger.debug("Inside getParentAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
		
	}

	
}
