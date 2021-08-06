package com.ibm.cfc.vaers.service;

import com.ibm.cfc.vaers.dto.in.ReportAdverseEventsForUserIn;
import com.ibm.cfc.vaers.dto.out.ReportAdverseEventsForUserOut;

public interface ReportAdverseEventService {

	public ReportAdverseEventsForUserOut saveUserReportedAdverseEffects(ReportAdverseEventsForUserIn reportAdverseEventsForUserIn);
}
