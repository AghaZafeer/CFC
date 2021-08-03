package com.ibm.cfc.vaers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cfc.vaers.dto.out.AllergicConditionsMasterOut;
import com.ibm.cfc.vaers.model.AllergicConditionsMaster;
import com.ibm.cfc.vaers.repository.AllergicConditionsMasterRepository;
import com.ibm.cfc.vaers.service.AllergicConditionsManagementService;

@Service
public class AllericConditionsManagementServiceImpl implements AllergicConditionsManagementService {

private static final Logger logger = LoggerFactory.getLogger(AllericConditionsManagementServiceImpl.class);
	
	@Autowired
	AllergicConditionsMasterRepository allergicConditionsMasterRepository; 
	
	
	public List<AllergicConditionsMasterOut> getListOfAllergicConditions() {
		// TODO Auto-generated method stub
		
	logger.debug("Inside getListOfAllergicConditions of AllergicConditionsManagementServiceImpl >>>>>>> ");
		
		List <AllergicConditionsMasterOut> returnObject = new ArrayList<AllergicConditionsMasterOut>(); 
		
			
		List<AllergicConditionsMaster> allergicConditionsMasters = allergicConditionsMasterRepository.findByAllgcondIsactiveTrue();
		
		logger.debug("Inside getListOfAllergicConditions of AllergicConditionsManagementServiceImpl >>>>>>> allergicConditionsMasters.size >>> " + allergicConditionsMasters.size());
		
		for(AllergicConditionsMaster allergicConditionsMaster : allergicConditionsMasters) {
			AllergicConditionsMasterOut allergicConditionsMasterOut = new AllergicConditionsMasterOut();
			logger.debug("Inside getListOfAllergicConditions of AllergicConditionsManagementServiceImpl >>>>>>> allergicConditionsMasters.getAllgcondId() >>>> " + allergicConditionsMaster.getAllgcondId());
			
			allergicConditionsMasterOut.setAllgcondId(allergicConditionsMaster.getAllgcondId());
			allergicConditionsMasterOut.setAllgcondIsactive(allergicConditionsMaster.isAllgcondIsactive());;
			allergicConditionsMasterOut.setAllgcondName(allergicConditionsMaster.getAllgcondName());;
		
			
			returnObject.add(allergicConditionsMasterOut);
			allergicConditionsMasterOut = null;
			
			logger.debug("Inside getListOfAllergicConditions of AllergicConditionsManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
	}

}
