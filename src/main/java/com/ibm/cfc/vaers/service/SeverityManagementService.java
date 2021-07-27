package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.dto.out.SeverityMasterOut;


public interface SeverityManagementService {
	List<SeverityMasterOut> getListOfSeverity();
	
}
