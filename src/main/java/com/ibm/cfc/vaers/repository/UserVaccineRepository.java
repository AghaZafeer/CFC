package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;
import com.ibm.cfc.vaers.model.UserVaccine;
import com.ibm.cfc.vaers.model.VaccineDoseMaster;
import com.ibm.cfc.vaers.model.VaccineMaster;

@Repository
public interface UserVaccineRepository extends JpaRepository <UserVaccine, Integer>{
	
	@Query("select userVaccine from UserVaccine userVaccine where userVaccine.user.userIsactive=true and userVaccine.vaccineMaster = ?1")
	List<UserVaccine> findAllByVaccineMaster(VaccineMaster vaccineMaster);
	
	@Query("select userVaccine from UserVaccine userVaccine where userVaccine.user.userIsactive=true and userVaccine.vaccineMaster = ?1 and userVaccine.vaccineDoseMaster = ?2")
	List<UserVaccine> findAllByVaccineMasterAndVaccineDoseMaster(VaccineMaster vaccineMaster, VaccineDoseMaster vaccineDoseMaster);
	
	UserVaccine findByUserAndVaccineMasterAndVaccineDoseMaster(User user, VaccineMaster vaccineMaster, VaccineDoseMaster vaccineDoseMaster);
	
	List<UserVaccine> findAllByUser(User user);
}
