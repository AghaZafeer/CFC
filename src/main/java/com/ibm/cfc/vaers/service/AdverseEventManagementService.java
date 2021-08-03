package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;


public interface AdverseEventManagementService {
	List<AdverseEventMasterOut> getAdverseEvents();
	
}
