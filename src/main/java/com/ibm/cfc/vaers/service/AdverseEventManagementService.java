package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.in.GetChildAdverseEventsIn;
import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;


public interface AdverseEventManagementService {
	List<AdverseEventMasterOut> getParentAdverseEvents();
	
	List<AdverseEventMasterOut> getChildAdverseEvents(GetChildAdverseEventsIn getChildAdverseEventsIn);
}
