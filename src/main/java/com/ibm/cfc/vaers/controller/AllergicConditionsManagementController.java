package com.ibm.cfc.vaers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.out.AllergicConditionsMasterOut;
import com.ibm.cfc.vaers.service.AllergicConditionsManagementService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class AllergicConditionsManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(AllergicConditionsManagementController.class);

	
	@Autowired AllergicConditionsManagementService allergicConditionsManagementService;
	  
	  @RequestMapping("/getAllergicConditionsList") public List<AllergicConditionsMasterOut> getAllergicConditionsList() { 
		  return allergicConditionsManagementService.getListOfAllergicConditions();
}
}