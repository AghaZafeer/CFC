package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class ReportedAdverseEffectRecordOut implements Serializable {

	public String adverseEffectName;
	
	public String severityName;
	
	public String additionalNotes;
	
	public String startDate;
	
	public String isFatal;
	
	public String dateOfDeath;
	
	public String isMedicNeeded;
	
	public String medicNotes;
	
	public String isRecovered;
	
	public String recoveryDate;

	public String getAdverseEffectName() {
		return adverseEffectName;
	}

	public void setAdverseEffectName(String adverseEffectName) {
		this.adverseEffectName = adverseEffectName;
	}

	public String getSeverityName() {
		return severityName;
	}

	public void setSeverityName(String severityName) {
		this.severityName = severityName;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getIsFatal() {
		return isFatal;
	}

	public void setIsFatal(String isFatal) {
		this.isFatal = isFatal;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getIsMedicNeeded() {
		return isMedicNeeded;
	}

	public void setIsMedicNeeded(String isMedicNeeded) {
		this.isMedicNeeded = isMedicNeeded;
	}

	public String getMedicNotes() {
		return medicNotes;
	}

	public void setMedicNotes(String medicNotes) {
		this.medicNotes = medicNotes;
	}

	public String getIsRecovered() {
		return isRecovered;
	}

	public void setIsRecovered(String isRecovered) {
		this.isRecovered = isRecovered;
	}

	public String getRecoveryDate() {
		return recoveryDate;
	}

	public void setRecoveryDate(String recoveryDate) {
		this.recoveryDate = recoveryDate;
	}
}
