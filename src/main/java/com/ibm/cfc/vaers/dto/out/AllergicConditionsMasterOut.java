package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class AllergicConditionsMasterOut implements Serializable {
	
	
	private long allgcondId;
	
	private boolean allgcondIsactive;

	private String allgcondName;
	
	public long getAllgcondId() {
		return allgcondId;
	}

	public void setAllgcondId(long allgcondId) {
		this.allgcondId = allgcondId;
	}

	public boolean isAllgcondIsactive() {
		return allgcondIsactive;
	}

	public void setAllgcondIsactive(boolean allgcondIsactive) {
		this.allgcondIsactive = allgcondIsactive;
	}

	public String getAllgcondName() {
		return allgcondName;
	}

	public void setAllgcondName(String allgcondName) {
		this.allgcondName = allgcondName;
	}

	
	
	

}
