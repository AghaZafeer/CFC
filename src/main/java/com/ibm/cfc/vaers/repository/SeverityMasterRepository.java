package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.SeverityMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;



@Repository
public interface SeverityMasterRepository extends JpaRepository <SeverityMaster, Integer>{
	
	List<SeverityMaster> findBySevIsactiveTrue();
	
	SeverityMaster findBySevName(String sevName);
}
