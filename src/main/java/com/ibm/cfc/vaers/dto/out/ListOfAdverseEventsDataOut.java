package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ListOfAdverseEventsDataOut implements Serializable{
    public String nameOfAdverseEvent;
    public int totalNoOfUsersForAdverseEvent;
    public int totalNoOfUsersForAdverseEventWithMildSeverity;
    public int totalNoOfUsersForAdverseEventWithModerateSeverity;
    public int totalNoOfUsersForAdverseEventWithHighSeverity;
    public int totalNoOfUsersForAdverseEventOfAgeGroup1;
    public int totalNoOfUsersForAdverseEventOfAgeGroup2;
    public int totalNoOfUsersForAdverseEventOfAgeGroup3;
    
	public String getNameOfAdverseEvent() {
		return nameOfAdverseEvent;
	}
	public void setNameOfAdverseEvent(String nameOfAdverseEvent) {
		this.nameOfAdverseEvent = nameOfAdverseEvent;
	}
	
	public int getTotalNoOfUsersForAdverseEvent() {
		return totalNoOfUsersForAdverseEvent;
	}
	public void setTotalNoOfUsersForAdverseEvent(int totalNoOfUsersForAdverseEvent) {
		this.totalNoOfUsersForAdverseEvent = totalNoOfUsersForAdverseEvent;
	}
	
	public int getTotalNoOfUsersForAdverseEventWithMildSeverity() {
		return totalNoOfUsersForAdverseEventWithMildSeverity;
	}
	public void setTotalNoOfUsersForAdverseEventWithMildSeverity(int totalNoOfUsersForAdverseEventWithMildSeverity) {
		this.totalNoOfUsersForAdverseEventWithMildSeverity = totalNoOfUsersForAdverseEventWithMildSeverity;
	}
	
	public int getTotalNoOfUsersForAdverseEventWithModerateSeverity() {
		return totalNoOfUsersForAdverseEventWithModerateSeverity;
	}
	public void setTotalNoOfUsersForAdverseEventWithModerateSeverity(
			int totalNoOfUsersForAdverseEventWithModerateSeverity) {
		this.totalNoOfUsersForAdverseEventWithModerateSeverity = totalNoOfUsersForAdverseEventWithModerateSeverity;
	}
	
	public int getTotalNoOfUsersForAdverseEventWithHighSeverity() {
		return totalNoOfUsersForAdverseEventWithHighSeverity;
	}
	public void setTotalNoOfUsersForAdverseEventWithHighSeverity(int totalNoOfUsersForAdverseEventWithHighSeverity) {
		this.totalNoOfUsersForAdverseEventWithHighSeverity = totalNoOfUsersForAdverseEventWithHighSeverity;
	}
	
	public int getTotalNoOfUsersForAdverseEventOfAgeGroup1() {
		return totalNoOfUsersForAdverseEventOfAgeGroup1;
	}
	public void setTotalNoOfUsersForAdverseEventOfAgeGroup1(int totalNoOfUsersForAdverseEventOfAgeGroup1) {
		this.totalNoOfUsersForAdverseEventOfAgeGroup1 = totalNoOfUsersForAdverseEventOfAgeGroup1;
	}
	
	public int getTotalNoOfUsersForAdverseEventOfAgeGroup2() {
		return totalNoOfUsersForAdverseEventOfAgeGroup2;
	}
	public void setTotalNoOfUsersForAdverseEventOfAgeGroup2(int totalNoOfUsersForAdverseEventOfAgeGroup2) {
		this.totalNoOfUsersForAdverseEventOfAgeGroup2 = totalNoOfUsersForAdverseEventOfAgeGroup2;
	}
	
	public int getTotalNoOfUsersForAdverseEventOfAgeGroup3() {
		return totalNoOfUsersForAdverseEventOfAgeGroup3;
	}
	public void setTotalNoOfUsersForAdverseEventOfAgeGroup3(int totalNoOfUsersForAdverseEventOfAgeGroup3) {
		this.totalNoOfUsersForAdverseEventOfAgeGroup3 = totalNoOfUsersForAdverseEventOfAgeGroup3;
	}
}


