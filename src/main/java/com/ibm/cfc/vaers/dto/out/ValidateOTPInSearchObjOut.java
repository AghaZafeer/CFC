package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ValidateOTPInSearchObjOut implements Serializable {

	public boolean isValid;
	
	public SearchUserRecordOut searchUserRecordOut;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public SearchUserRecordOut getSearchUserRecordOut() {
		return searchUserRecordOut;
	}

	public void setSearchUserRecordOut(SearchUserRecordOut searchUserRecordOut) {
		this.searchUserRecordOut = searchUserRecordOut;
	}
}
