package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class UserAllergicConditionIn implements Serializable {

	public String allergicConditionID;

	public String getAllergicConditionID() {
		return allergicConditionID;
	}

	public void setAllergicConditionID(String allergicConditionID) {
		this.allergicConditionID = allergicConditionID;
	}
}
