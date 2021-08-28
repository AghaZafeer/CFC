package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class UserVaccineReporterAdverseEffectRecordOut implements Serializable {
	
	public String vaccineName;
	
	public String vaccineDoseName;
	
	public String vaccinnationDate;
	
	public String vaccinationCenter;
	
	public String caseID;
	
	public String caseReportedBy;
	
	public String reporterTitleName;
	
	public String reporterFirstName;
	
	public String reporterMiddleName;
	
	public String reporterLastName;
	
	public String reporterContactNumber;
	
	public String reporterAlternateNumber;
	
	public String reporterEmailID;
	
	public List<ReportedAdverseEffectRecordOut> reportedAdverseEffectRecordOuts;

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccineDoseName() {
		return vaccineDoseName;
	}

	public void setVaccineDoseName(String vaccineDoseName) {
		this.vaccineDoseName = vaccineDoseName;
	}

	public String getVaccinnationDate() {
		return vaccinnationDate;
	}

	public void setVaccinnationDate(String vaccinnationDate) {
		this.vaccinnationDate = vaccinnationDate;
	}

	public String getVaccinationCenter() {
		return vaccinationCenter;
	}

	public void setVaccinationCenter(String vaccinationCenter) {
		this.vaccinationCenter = vaccinationCenter;
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getCaseReportedBy() {
		return caseReportedBy;
	}

	public void setCaseReportedBy(String caseReportedBy) {
		this.caseReportedBy = caseReportedBy;
	}

	public String getReporterTitleName() {
		return reporterTitleName;
	}

	public void setReporterTitleName(String reporterTitleName) {
		this.reporterTitleName = reporterTitleName;
	}

	public String getReporterFirstName() {
		return reporterFirstName;
	}

	public void setReporterFirstName(String reporterFirstName) {
		this.reporterFirstName = reporterFirstName;
	}

	public String getReporterMiddleName() {
		return reporterMiddleName;
	}

	public void setReporterMiddleName(String reporterMiddleName) {
		this.reporterMiddleName = reporterMiddleName;
	}

	public String getReporterLastName() {
		return reporterLastName;
	}

	public void setReporterLastName(String reporterLastName) {
		this.reporterLastName = reporterLastName;
	}

	public String getReporterContactNumber() {
		return reporterContactNumber;
	}

	public void setReporterContactNumber(String reporterContactNumber) {
		this.reporterContactNumber = reporterContactNumber;
	}

	public String getReporterAlternateNumber() {
		return reporterAlternateNumber;
	}

	public void setReporterAlternateNumber(String reporterAlternateNumber) {
		this.reporterAlternateNumber = reporterAlternateNumber;
	}

	public String getReporterEmailID() {
		return reporterEmailID;
	}

	public void setReporterEmailID(String reporterEmailID) {
		this.reporterEmailID = reporterEmailID;
	}

	public List<ReportedAdverseEffectRecordOut> getReportedAdverseEffectRecordOuts() {
		return reportedAdverseEffectRecordOuts;
	}

	public void setReportedAdverseEffectRecordOuts(List<ReportedAdverseEffectRecordOut> reportedAdverseEffectRecordOuts) {
		this.reportedAdverseEffectRecordOuts = reportedAdverseEffectRecordOuts;
	}
}
