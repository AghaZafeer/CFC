package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.in.GetChildAdverseEventsIn;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.dto.out.SeverityMasterOut;
import com.ibm.cfc.vaers.model.AdverseEventMapping;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMappingRepository;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;


@Service
public class AdverseEventManagementServiceImpl implements AdverseEventManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdverseEventManagementServiceImpl.class);
	
	@Autowired
	AdverseEventMasterRepository adverseEventMasterRepository;
	
	@Autowired
	AdverseEventMappingRepository adverseEventMappingRepository;
	
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
	
	public List<AdverseEventMasterOut> getChildAdverseEvents(GetChildAdverseEventsIn getChildAdverseEventsIn){
		
		logger.debug("Inside getChildAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> ");
		
		List <AdverseEventMasterOut> returnObject = new ArrayList<AdverseEventMasterOut>(); 
		
		AdverseEventMaster parentAdverseEvent = new AdverseEventMaster();
		
		if(getChildAdverseEventsIn != null) {
			parentAdverseEvent.setAdevId(Long.valueOf(getChildAdverseEventsIn.getParentAdevId()));
		}
		
		List<AdverseEventMapping> adverseEventMappings = adverseEventMappingRepository.findByAdverseEventMaster2(parentAdverseEvent);
		
		logger.debug("Inside getChildAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMappings.size >>> " + adverseEventMappings.size());
		
		for(AdverseEventMapping adverseEventMapping : adverseEventMappings) {
			AdverseEventMasterOut adverseEventMasterOut = new AdverseEventMasterOut();
						
			logger.debug("Inside getChildAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> adverseEventMapping.getAdevMappingId() >>>> " + adverseEventMapping.getAdevMappingId());
						
			adverseEventMasterOut.setAdevId(adverseEventMapping.getAdverseEventMaster1().getAdevId());
			adverseEventMasterOut.setAdevName(adverseEventMapping.getAdverseEventMaster1().getAdevName());
			adverseEventMasterOut.setAdevIsactive(adverseEventMapping.getAdverseEventMaster1().isAdevIsactive());
			
			SeverityMasterOut severityMasterOut = new SeverityMasterOut();
			severityMasterOut.setSevId(adverseEventMapping.getAdverseEventMaster1().getSeverityMaster().getSevId());
			severityMasterOut.setSevName(adverseEventMapping.getAdverseEventMaster1().getSeverityMaster().getSevName());
			severityMasterOut.setSevIsactive(adverseEventMapping.getAdverseEventMaster1().getSeverityMaster().isSevIsactive());
			
			adverseEventMasterOut.setSeverityMasterOut(severityMasterOut);
			severityMasterOut = null;
			
			returnObject.add(adverseEventMasterOut);
			adverseEventMasterOut = null;
			
			logger.debug("Inside getChildAdverseEvents of AdverseEventManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		
		return returnObject;
	}

}
