package com.ibm.cfc.vaers.service;

import java.util.List;

import com.ibm.cfc.vaers.dto.out.IllnessMasterOut;


public interface IllnessManagementService {
	List<IllnessMasterOut> getListOfIllness();
	
}
