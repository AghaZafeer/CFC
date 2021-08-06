package com.ibm.cfc.vaers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.out.ReportAdverseEventsForUserOut;
import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;
import com.ibm.cfc.vaers.service.ReportAdverseEventService;
import com.ibm.cfc.vaers.service.VaccineManagementService;


@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class ReportAdverseEventController {

	private static final Logger logger = LoggerFactory.getLogger(ReportAdverseEventController.class);
	
	@Autowired
	ReportAdverseEventService reportAdverseEventService;
	
	@PostMapping("/reportAdverseEffects")
	public ReportAdverseEventsForUserOut reportAdverseEffects(@RequestBody ReportAdverseEventsForUserIn reportAdverseEventsForUserIn) {
		ReportAdverseEventsForUserOut reportAdverseEventsForUserOut = new ReportAdverseEventsForUserOut();
		
		reportAdverseEventsForUserOut = reportAdverseEventService.saveUserReportedAdverseEffects(reportAdverseEventsForUserIn);
		
		return reportAdverseEventsForUserOut;
	}
	
}
