package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.out.AdverseEventMasterOut;
import com.ibm.cfc.vaers.dto.out.UserTitleMasterOut;


public interface UserTitleManagementService {
	List<UserTitleMasterOut> getListOfUserTitles();
	
}
