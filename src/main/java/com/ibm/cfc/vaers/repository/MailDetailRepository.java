package com.ibm.cfc.vaers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.MailDetail;

@Repository
public interface MailDetailRepository extends JpaRepository <MailDetail, Integer>{
	
	MailDetail findByMdCode(String mdCode);
}
