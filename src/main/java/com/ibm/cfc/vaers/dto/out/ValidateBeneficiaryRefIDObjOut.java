package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ValidateBeneficiaryRefIDObjOut implements Serializable {

	public boolean isValid;
	public String message;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
