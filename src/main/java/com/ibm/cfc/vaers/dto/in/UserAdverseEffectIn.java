package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class UserAdverseEffectIn implements Serializable {

	public String adverseEffectID;
	public String severityID;
	public String additionalNotes;
	public String adverseEffectReportingDate;
	public boolean adverseEffectIsFatal;
	public String dateOfDeath;
	public boolean adverseEffectIsRecovered;
	public String dateOfRecovery;
	public String getAdverseEffectID() {
		return adverseEffectID;
	}
	public void setAdverseEffectID(String adverseEffectID) {
		this.adverseEffectID = adverseEffectID;
	}
	public String getSeverityID() {
		return severityID;
	}
	public void setSeverityID(String severityID) {
		this.severityID = severityID;
	}
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	public String getAdverseEffectReportingDate() {
		return adverseEffectReportingDate;
	}
	public void setAdverseEffectReportingDate(String adverseEffectReportingDate) {
		this.adverseEffectReportingDate = adverseEffectReportingDate;
	}
	public boolean isAdverseEffectIsFatal() {
		return adverseEffectIsFatal;
	}
	public void setAdverseEffectIsFatal(boolean adverseEffectIsFatal) {
		this.adverseEffectIsFatal = adverseEffectIsFatal;
	}
	public String getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public boolean isAdverseEffectIsRecovered() {
		return adverseEffectIsRecovered;
	}
	public void setAdverseEffectIsRecovered(boolean adverseEffectIsRecovered) {
		this.adverseEffectIsRecovered = adverseEffectIsRecovered;
	}
	public String getDateOfRecovery() {
		return dateOfRecovery;
	}
	public void setDateOfRecovery(String dateOfRecovery) {
		this.dateOfRecovery = dateOfRecovery;
	}
}
