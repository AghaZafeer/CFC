package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class UserAllergiesOut implements Serializable {
	
	public String allergyName;

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
}
