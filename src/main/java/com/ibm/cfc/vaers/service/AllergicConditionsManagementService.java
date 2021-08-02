package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.out.AllergicConditionsMasterOut;

public interface AllergicConditionsManagementService {
	
	List<AllergicConditionsMasterOut> getListOfAllergicConditions();

}
