package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class GetChildAdverseEventsIn implements Serializable {
	
	public String parentAdevId;

	public String getParentAdevId() {
		return parentAdevId;
	}

	public void setParentAdevId(String parentAdevId) {
		this.parentAdevId = parentAdevId;
	}
}
