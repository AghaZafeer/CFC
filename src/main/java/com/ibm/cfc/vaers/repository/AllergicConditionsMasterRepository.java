package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.cfc.vaers.model.AllergicConditionsMaster;

public interface AllergicConditionsMasterRepository extends JpaRepository<AllergicConditionsMaster, Long> {

	
	List<AllergicConditionsMaster> findByAllgcondIsactiveTrue();
}
