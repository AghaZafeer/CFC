package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.SeverityMasterOut;
import com.ibm.cfc.vaers.model.SeverityMaster;
import com.ibm.cfc.vaers.repository.SeverityMasterRepository;
import com.ibm.cfc.vaers.service.SeverityManagementService;


@Service
public class SeverityManagementServiceImpl implements SeverityManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(SeverityManagementServiceImpl.class);
	
	@Autowired
	SeverityMasterRepository severityMasterRepository; 
	

		public List<SeverityMasterOut> getListOfSeverity(){
		// TODO Auto-generated method stub
		logger.debug("Inside getListOfSeverity of SeverityManagementServiceImpl >>>>>>> ");
		
		List <SeverityMasterOut> returnObject = new ArrayList<SeverityMasterOut>(); 
		
		List<SeverityMaster> severityMasters = severityMasterRepository.findBySevIsactiveTrue();
		
		
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
