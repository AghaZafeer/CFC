package com.ibm.cfc.vaers.service;

import com.ibm.cfc.vaers.utils.MailDetailDTO;

public interface EmailService {

	void sendSimpleMessage(MailDetailDTO mailDetailDTO);
}
