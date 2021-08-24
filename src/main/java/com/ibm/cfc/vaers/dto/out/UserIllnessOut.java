package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class UserIllnessOut implements Serializable {

	public String illnessName;

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}
}
