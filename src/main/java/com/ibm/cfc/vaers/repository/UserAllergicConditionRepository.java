package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.AllergicConditionsMaster;
import com.ibm.cfc.vaers.model.User;
import com.ibm.cfc.vaers.model.UserAllergicCondition;



@Repository
public interface UserAllergicConditionRepository extends JpaRepository <UserAllergicCondition, Integer>{
	
	UserAllergicCondition findByUserAndAllergicConditionsMaster(User user, AllergicConditionsMaster allergicConditionsMaster);
}
