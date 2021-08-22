package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.AdverseEventMaster;
import com.ibm.cfc.vaers.model.AdverseEventReported;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;



@Repository
public interface AdverseEventReportedRepository extends JpaRepository <AdverseEventReported, Integer>{
	
	@Query("select adverseEventReported from AdverseEventReported adverseEventReported "
			+ "where adverseEventReported.adverseEventMaster1 = ?1 and "
			+ "adverseEventReported.userVaccine.user.userIsactive=true and "
			+ "adverseEventReported.userVaccine.vaccineMaster = ?2 and "
			+ "adverseEventReported.userVaccine.vaccineDoseMaster = ?3")
	List<AdverseEventReported> findAllByAdverseEventMaster1AndVaccineMasterAndVaccineDoseMaster(AdverseEventMaster adverseEventMaster, VaccineMaster vaccineMaster, VaccineDoseMaster vaccineDoseMaster);
	
	@Query("select adverseEventReported from AdverseEventReported adverseEventReported "
			+ "where adverseEventReported.adverseEventMaster1 = ?1 and "
			+ "adverseEventReported.adevRepIsrecovered = true and "
			+ "adverseEventReported.userVaccine.user.userIsactive=true and "
			+ "adverseEventReported.userVaccine.vaccineMaster = ?2 and "
			+ "adverseEventReported.userVaccine.vaccineDoseMaster = ?3")
	List<AdverseEventReported> findAllByAdverseEventMaster1AndVaccineMasterAndVaccineDoseMasterAndAdevRepIsrecoveredTrue(AdverseEventMaster adverseEventMaster, VaccineMaster vaccineMaster, VaccineDoseMaster vaccineDoseMaster);
	
}
