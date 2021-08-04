package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class UserIllnessIn implements Serializable {

	public String illnessID;

	public String getIllnessID() {
		return illnessID;
	}

	public void setIllnessID(String illnessID) {
		this.illnessID = illnessID;
	}
}
