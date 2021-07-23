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
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.dto.out.VaccineDoseMasterOut;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;
import com.ibm.cfc.vaers.repository.VaccineMasterRepository;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@Service
public class VaccineManagementServiceImpl implements VaccineManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(VaccineManagementServiceImpl.class);
	
	@Autowired
	VaccineMasterRepository vaccineMasterRepository; 
	
	@Override
	public List<VacccineMasterOut> getListOfVaccines(){
		// TODO Auto-generated method stub
		logger.debug("Inside getListOfVaccines of VaccineManagementServiceImpl >>>>>>> ");
		
		List <VacccineMasterOut> returnObject = new ArrayList<VacccineMasterOut>(); 
		
			
		List<VaccineMaster> vaccineMasters = vaccineMasterRepository.findAll();
		
		System.out.println("Inside getListOfVaccines of VaccineManagementServiceImpl >>>>>>> vaccineMasters.size >>> " + vaccineMasters.size());
		
		for(VaccineMaster vaccineMaster : vaccineMasters) {
			VacccineMasterOut vacccineMasterOut = new VacccineMasterOut();
			System.out.println("Inside getListOfVaccines of VaccineManagementServiceImpl >>>>>>> vaccineMaster.getVaccineId() >>>> " + vaccineMaster.getVaccineId());
			
			vacccineMasterOut.setVaccineID(vaccineMaster.getVaccineId());
			vacccineMasterOut.setVaccineName(vaccineMaster.getVaccineName());
			vacccineMasterOut.setVaccineManufacturer(vaccineMaster.getVaccineManufacturer());
			
			List <VaccineDoseMasterOut> vaccineDoseMasterOuts = new ArrayList<VaccineDoseMasterOut>();
			
			if(vaccineMaster.getVaccineDoseMasters().size() > 0) {
				for(VaccineDoseMaster vaccineDoseMaster : vaccineMaster.getVaccineDoseMasters()) {
					VaccineDoseMasterOut vaccineDoseMasterOut = new VaccineDoseMasterOut();
					vaccineDoseMasterOut.setVaccineDoseId(vaccineDoseMaster.getVaccineDoseId());
					vaccineDoseMasterOut.setVaccineDoseName(vaccineDoseMaster.getVaccineDoseName());
					vaccineDoseMasterOut.setVaccineDoseDetail(vaccineDoseMaster.getVaccineDoseDetail());
					
					vaccineDoseMasterOuts.add(vaccineDoseMasterOut);
					
					vaccineDoseMasterOut = null;
								
				}
				
				vacccineMasterOut.setVaccineDoses(vaccineDoseMasterOuts);
				
			}
			returnObject.add(vacccineMasterOut);
			vacccineMasterOut = null;
			
			System.out.println("Inside getListOfVaccines of VaccineManagementServiceImpl >>>>>>> returnObject.size >>> " + returnObject.size());
		}
		return returnObject;
		
	}

	
}
