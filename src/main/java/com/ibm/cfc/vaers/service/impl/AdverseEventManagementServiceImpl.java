package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.dto.out.SeverityMasterOut;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;


@Service
public class AdverseEventManagementServiceImpl implements AdverseEventManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdverseEventManagementServiceImpl.class);
	
	@Autowired
	AdverseEventMasterRepository adverseEventMasterRepository;
	
	public List<AdverseEventMasterOut> getAdverseEvents(){
		// TODO Auto-generated method stub
		logger.debug("Inside getAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> ");
		
		List <AdverseEventMasterOut> returnObject = new ArrayList<AdverseEventMasterOut>(); 
		
		List<AdverseEventMaster> adverseEventMasters = adverseEventMasterRepository.findByAdevIsactiveTrue();
		
		logger.debug("Inside getAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMasters.size >>> " + adverseEventMasters.size());
		
		for(AdverseEventMaster adverseEventMaster : adverseEventMasters) {
			AdverseEventMasterOut adverseEventMasterOut = new AdverseEventMasterOut();
			logger.debug("Inside getAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMaster.getAdevId() >>>> " + adverseEventMaster.getAdevId());
			
			adverseEventMasterOut.setAdevId(adverseEventMaster.getAdevId());
			adverseEventMasterOut.setAdevName(adverseEventMaster.getAdevName());
			adverseEventMasterOut.setAdevIsactive(adverseEventMaster.isAdevIsactive());
			
			returnObject.add(adverseEventMasterOut);
			adverseEventMasterOut = null;
			
			logger.debug("Inside getAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
	}
	

}
