package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class UserAndRecoveryDataReportObjOut implements Serializable {

	public String userID;
    public String recoveryDurationInDays;
    
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRecoveryDurationInDays() {
		return recoveryDurationInDays;
	}
	public void setRecoveryDurationInDays(String recoveryDurationInDays) {
		this.recoveryDurationInDays = recoveryDurationInDays;
	}
}
