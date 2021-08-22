package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.out.RecoveryReportAndGraphDataOut;
import com.ibm.cfc.vaers.dto.out.ReportAdverseEventsForUserOut;
import com.ibm.cfc.vaers.dto.out.ReportAndGraphDataOut;

public interface ReportAndGraphDataService {

	public ReportAndGraphDataOut getReportAndGraphData();
	
	public List<RecoveryReportAndGraphDataOut> getRecoveryReportAndGraphData();
}
