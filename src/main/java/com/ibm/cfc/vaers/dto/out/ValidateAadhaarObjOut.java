package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ValidateAadhaarObjOut implements Serializable {

	public boolean isValid;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
