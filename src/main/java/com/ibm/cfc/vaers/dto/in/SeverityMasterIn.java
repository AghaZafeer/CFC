package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class SeverityMasterIn implements Serializable{

	public String sevName;
	
	public boolean sevIsactive;

	public String getSevName() {
		return sevName;
	}

	public void setSevName(String sevName) {
		this.sevName = sevName;
	}

	public boolean isSevIsactive() {
		return sevIsactive;
	}

	public void setSevIsactive(boolean sevIsactive) {
		this.sevIsactive = sevIsactive;
	}
}
