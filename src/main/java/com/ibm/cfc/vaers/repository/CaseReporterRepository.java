package com.ibm.cfc.vaers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.CaseReporter;



@Repository
public interface CaseReporterRepository extends JpaRepository <CaseReporter, Integer>{
	
	CaseReporter findByCaseReporterCaseId(String caseReporterCaseId);
}
