package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.out.VacccineMasterOut;


public interface VaccineManagementService {
	List<VacccineMasterOut> getListOfVaccines();
	
}
