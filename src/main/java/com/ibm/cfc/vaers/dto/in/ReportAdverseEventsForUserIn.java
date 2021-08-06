package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;
import java.util.List;

public class ReportAdverseEventsForUserIn implements Serializable {
	
	public String userTitleID;
	public String userFirstName;
	public String userMiddleName;
	public String userLastName;
	public String userAadhaarNumber;
	public String userMobile;
	public String userAlternateMobile;
	public String userEmail;
	public String userAddress;
	public String userAge;
	public String dateOfBirth;
	public String userSex; // 'MALE','FEMALE','UNKNOWN'
	public String userIsPregnant;
	
	public List<UserAllergicConditionIn> userAllergicConditionIns;
	public List<UserIllnessIn> userIllnessIns;
	
	public String vaccineID;
	public String vaccineDoseID;
	public String vaccinationDate;
	public String vaccinationCenter;
	
	public String reportedBy; // 'SELF','HEALTHCARE_PROFESSIONAL','RELATIVE','OTHERS'
	
	public String reporterTitleID;
	public String reporterFirstName;
	public String reporterMiddleName;
	public String reporterLastName;
	public String reporterMobile;
	public String reporterAlternateMobile;
	public String reporterEmail;
	
	public List<UserAdverseEffectIn> userAdverseEffectIns;

	public String getUserTitleID() {
		return userTitleID;
	}

	public void setUserTitleID(String userTitleID) {
		this.userTitleID = userTitleID;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserAadhaarNumber() {
		return userAadhaarNumber;
	}

	public void setUserAadhaarNumber(String userAadhaarNumber) {
		this.userAadhaarNumber = userAadhaarNumber;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAlternateMobile() {
		return userAlternateMobile;
	}

	public void setUserAlternateMobile(String userAlternateMobile) {
		this.userAlternateMobile = userAlternateMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserIsPregnant() {
		return userIsPregnant;
	}

	public void setUserIsPregnant(String userIsPregnant) {
		this.userIsPregnant = userIsPregnant;
	}

	public List<UserAllergicConditionIn> getUserAllergicConditionIns() {
		return userAllergicConditionIns;
	}

	public void setUserAllergicConditionIns(List<UserAllergicConditionIn> userAllergicConditionIns) {
		this.userAllergicConditionIns = userAllergicConditionIns;
	}

	public List<UserIllnessIn> getUserIllnessIns() {
		return userIllnessIns;
	}

	public void setUserIllnessIns(List<UserIllnessIn> userIllnessIns) {
		this.userIllnessIns = userIllnessIns;
	}

	public String getVaccineID() {
		return vaccineID;
	}

	public void setVaccineID(String vaccineID) {
		this.vaccineID = vaccineID;
	}

	public String getVaccineDoseID() {
		return vaccineDoseID;
	}

	public void setVaccineDoseID(String vaccineDoseID) {
		this.vaccineDoseID = vaccineDoseID;
	}

	public String getVaccinationDate() {
		return vaccinationDate;
	}

	public void setVaccinationDate(String vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public String getVaccinationCenter() {
		return vaccinationCenter;
	}

	public void setVaccinationCenter(String vaccinationCenter) {
		this.vaccinationCenter = vaccinationCenter;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getReporterTitleID() {
		return reporterTitleID;
	}

	public void setReporterTitleID(String reporterTitleID) {
		this.reporterTitleID = reporterTitleID;
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

	public String getReporterMobile() {
		return reporterMobile;
	}

	public void setReporterMobile(String reporterMobile) {
		this.reporterMobile = reporterMobile;
	}

	public String getReporterAlternateMobile() {
		return reporterAlternateMobile;
	}

	public void setReporterAlternateMobile(String reporterAlternateMobile) {
		this.reporterAlternateMobile = reporterAlternateMobile;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public List<UserAdverseEffectIn> getUserAdverseEffectIns() {
		return userAdverseEffectIns;
	}

	public void setUserAdverseEffectIns(List<UserAdverseEffectIn> userAdverseEffectIns) {
		this.userAdverseEffectIns = userAdverseEffectIns;
	}
}
