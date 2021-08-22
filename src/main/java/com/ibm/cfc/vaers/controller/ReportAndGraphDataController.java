package com.ibm.cfc.vaers.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cfc.vaers.dto.out.RecoveryReportAndGraphDataOut;
import com.ibm.cfc.vaers.dto.out.ReportAndGraphDataOut;
import com.ibm.cfc.vaers.service.ReportAndGraphDataService;

@RestController
@ComponentScan(basePackages = "com.ibm.cfc.vaers")
public class ReportAndGraphDataController {

private static final Logger logger = LoggerFactory.getLogger(ReportAndGraphDataController.class);
	
	@Autowired
	ReportAndGraphDataService reportAndGraphDataService;
	
	@RequestMapping("/getReportAndGraphData")
	public ReportAndGraphDataOut getReportAndGraphData() {
		ReportAndGraphDataOut returnObject = new ReportAndGraphDataOut();
		returnObject = reportAndGraphDataService.getReportAndGraphData();
		return returnObject;
	}
	
	@RequestMapping("/getRecoveryReportAndGraphData")
	public List<RecoveryReportAndGraphDataOut> getRecoveryReportAndGraphData() {
		List<RecoveryReportAndGraphDataOut> returnObject = new ArrayList<RecoveryReportAndGraphDataOut>();
		returnObject = reportAndGraphDataService.getRecoveryReportAndGraphData();
		return returnObject;
	}
}
