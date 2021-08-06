package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;
import com.ibm.cfc.vaers.model.UserVaccine;

@Repository
public interface UserVaccineRepository extends JpaRepository <UserVaccine, Integer>{
	
}
