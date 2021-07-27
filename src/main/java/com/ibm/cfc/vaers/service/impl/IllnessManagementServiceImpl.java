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
import com.ibm.cfc.vaers.dto.out.IllnessMasterOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.VaccineDoseMasterOut;
import com.ibm.cfc.vaers.model.IllnessMaster;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.IllnessManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@Service
public class IllnessManagementServiceImpl implements IllnessManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(IllnessManagementServiceImpl.class);
	
	@Autowired
	IllnessMasterRepository illnessMasterRepository; 
	
	@Override
	public List<IllnessMasterOut> getListOfIllness(){
		// TODO Auto-generated method stub
		logger.debug("Inside getListOfIllness of IllnessManagementServiceImpl >>>>>>> ");
		
		List <IllnessMasterOut> returnObject = new ArrayList<IllnessMasterOut>(); 
		
			
		List<IllnessMaster> illnessMasters = illnessMasterRepository.findAll();
		
		logger.debug("Inside getListOfIllness of IllnessManagementServiceImpl >>>>>>> illnessMasters.size >>> " + illnessMasters.size());
		
		for(IllnessMaster illnessMaster : illnessMasters) {
			IllnessMasterOut illnessMasterOut = new IllnessMasterOut();
			logger.debug("Inside getListOfIllness of IllnessManagementServiceImpl >>>>>>> illnessMaster.getIllnessId() >>>> " + illnessMaster.getIllnessId());
			
			illnessMasterOut.setIllnessId(illnessMaster.getIllnessId());
			illnessMasterOut.setIllnessName(illnessMaster.getIllnessName());
			illnessMasterOut.setIllnessIsactive(illnessMaster.isIllnessIsactive());
			illnessMasterOut.setIllnessIschronic(illnessMaster.isIllnessIschronic());
			illnessMasterOut.setIllnessIsother(illnessMaster.isIllnessIsother());
			
			returnObject.add(illnessMasterOut);
			illnessMasterOut = null;
			
			logger.debug("Inside getListOfIllness of IllnessManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
		
	}

	
}
