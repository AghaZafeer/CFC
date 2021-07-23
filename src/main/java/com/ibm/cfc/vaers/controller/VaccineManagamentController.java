package com.ibm.cfc.vaers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class VaccineManagamentController {

	private static final Logger logger = LoggerFactory.getLogger(VaccineManagamentController.class);
	
	@Autowired
	VaccineManagementService vaccineManagementService;
	
	@RequestMapping("/getVaccineList")
	public List<VacccineMasterOut> getVaccineList() {
		//System.out.println("Inside getVaccineList of VaccineManagamentController >>>>>>> ");   
		return vaccineManagementService.getListOfVaccines();
	}
	
}
