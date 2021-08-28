package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class SearchAadhaarObjIn implements Serializable {

	public String aadhaarNumber;

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
}
