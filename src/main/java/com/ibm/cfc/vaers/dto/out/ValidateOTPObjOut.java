package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ValidateOTPObjOut implements Serializable {

	public boolean isValid;
	
	public UserMasterOut userMasterOut;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public UserMasterOut getUserMasterOut() {
		return userMasterOut;
	}

	public void setUserMasterOut(UserMasterOut userMasterOut) {
		this.userMasterOut = userMasterOut;
	}
	
}
