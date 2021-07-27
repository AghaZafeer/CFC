package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class AdverseEventMasterOut implements Serializable{

	public long adevId;
	
	public String adevName;
	
	public boolean adevIsactive;
	
	public SeverityMasterOut severityMasterOut;

	public long getAdevId() {
		return adevId;
	}

	public void setAdevId(long adevId) {
		this.adevId = adevId;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public boolean isAdevIsactive() {
		return adevIsactive;
	}

	public void setAdevIsactive(boolean adevIsactive) {
		this.adevIsactive = adevIsactive;
	}

	public SeverityMasterOut getSeverityMasterOut() {
		return severityMasterOut;
	}

	public void setSeverityMasterOut(SeverityMasterOut severityMasterOut) {
		this.severityMasterOut = severityMasterOut;
	}
}
