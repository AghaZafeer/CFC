package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.IllnessMaster;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;
import com.ibm.cfc.vaers.model.UserIllness;



@Repository
public interface UserIllnessRepository extends JpaRepository <UserIllness, Integer>{
	
	UserIllness findByUserAndIllnessMaster(User user, IllnessMaster illnessMaster);
}
