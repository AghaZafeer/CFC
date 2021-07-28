package com.ibm.cfc.vaers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.in.GetChildAdverseEventsIn;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.service.AdverseEventManagementService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class AdverseEventManagamentController {

	private static final Logger logger = LoggerFactory.getLogger(AdverseEventManagamentController.class);
	
	@Autowired
	AdverseEventManagementService adverseEventManagementService;
	
	@RequestMapping("/getParentAdverseEvents")
	public List<AdverseEventMasterOut> getParentAdverseEvents() {
		return adverseEventManagementService.getParentAdverseEvents();
	}
	
	@RequestMapping("/getChildAdverseEvents")
	public List<AdverseEventMasterOut> getChildAdverseEvents(@RequestBody GetChildAdverseEventsIn getChildAdverseEventsIn) {
		return adverseEventManagementService.getChildAdverseEvents(getChildAdverseEventsIn);
	}
	
}
