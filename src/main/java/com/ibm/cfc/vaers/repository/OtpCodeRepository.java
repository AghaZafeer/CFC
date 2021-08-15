package com.ibm.cfc.vaers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.cfc.vaers.model.OtpCode;



@Repository
public interface OtpCodeRepository extends JpaRepository <OtpCode, Integer>{
	
	OtpCode findByOtpEmailId(String otpEmailId);
	
	OtpCode findByOtpEmailIdAndOtpValue(String otpEmailId, String otpValue);
	
}
