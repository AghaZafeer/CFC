package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;



@Repository
public interface VaccineDoseMasterRepository extends JpaRepository <VaccineDoseMaster, Integer>{
	
	VaccineDoseMaster findByVaccineMasterAndVaccineDoseName(VaccineMaster vaccineMaster, String vaccineDoseName);
	
}
