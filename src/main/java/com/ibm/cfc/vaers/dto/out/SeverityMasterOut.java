package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class SeverityMasterOut implements Serializable{

	public long sevId;
	
	public String sevName;
	
	public boolean sevIsactive;

	public long getSevId() {
		return sevId;
	}

	public void setSevId(long sevId) {
		this.sevId = sevId;
	}

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
