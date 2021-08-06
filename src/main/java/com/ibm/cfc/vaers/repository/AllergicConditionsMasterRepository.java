package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.cfc.vaers.model.AllergicConditionsMaster;
import com.ibm.cfc.vaers.model.UserTitleMaster;

public interface AllergicConditionsMasterRepository extends JpaRepository<AllergicConditionsMaster, Long> {

	
	List<AllergicConditionsMaster> findByAllgcondIsactiveTrue();
	
	AllergicConditionsMaster findByAllgcondName(String allgcondName);
}
