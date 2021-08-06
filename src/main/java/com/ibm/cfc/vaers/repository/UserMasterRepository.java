package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.User;



@Repository
public interface UserMasterRepository extends JpaRepository <User, Integer>{
	
	
}
