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
import com.ibm.cfc.vaers.dto.out.UserTitleMasterOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.VaccineDoseMasterOut;
import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.IllnessMaster;
import com.ibm.cfc.vaers.model.UserTitleMaster;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.AdverseEventMasterRepository;
import com.ibm.cfc.vaers.repository.IllnessMasterRepository;
import com.ibm.cfc.vaers.repository.UserTitleMasterRepository;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;
import com.ibm.cfc.vaers.service.IllnessManagementService;
import com.ibm.cfc.vaers.service.UserTitleManagementService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@Service
public class UserTitleManagementServiceImpl implements UserTitleManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTitleManagementServiceImpl.class);
	
	@Autowired
	UserTitleMasterRepository userTitleMasterRepository; 
	

		public List<UserTitleMasterOut> getListOfUserTitles(){
		// TODO Auto-generated method stub
		logger.debug("Inside getListOfUserTitles of UserTitleManagementServiceImpl >>>>>>> ");
		
		List <UserTitleMasterOut> returnObject = new ArrayList<UserTitleMasterOut>(); 
		
		List<UserTitleMaster> userTitleMasters = userTitleMasterRepository.findAll();
		
		
		logger.debug("Inside getListOfUserTitles of UserTitleManagementServiceImpl >>>>>>> userTitleMasters.size >>> " + userTitleMasters.size());
		
		for(UserTitleMaster userTitleMaster : userTitleMasters) {
			UserTitleMasterOut userTitleMasterOut = new UserTitleMasterOut();
			logger.debug("Inside getListOfUserTitles of UserTitleManagementServiceImpl >>>>>>> userTitleMasters.getuserTitleId() >>>> " + userTitleMaster.getUserTitleId());;
			
			userTitleMasterOut.setuserTitleId(userTitleMaster.getUserTitleId());
			userTitleMasterOut.setuserTitleName(userTitleMaster.getUserTitleName());
			userTitleMasterOut.setuserTitleIsactive(userTitleMaster.isUserTitleIsactive());
			
			
			returnObject.add(userTitleMasterOut);
			userTitleMasterOut = null;
			
			logger.debug("Inside getListOfUserTitles of UserTitleManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
		
	}

	
}
