package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

import com.ibm.cfc.vaers.model.User;

public class SearchUserRecordOut implements Serializable {

	public String userTitle;
	
	public String firstName;
	
	public String middleName;
	
	public String lastName;
	
	public String emailID;
	
	public String address;
	
	public String contactNumber;
	
	public String alternateContactNumber;
	
	public String aadhaarNumber;
	
	public String beneficiaryReferenceID;
	
	public String age;
	
	public String dateOfBirth;
	
	public List<UserIllnessOut> userIllnessOuts;
	
	public List<UserAllergiesOut> userAllergiesOut;
	
	public String gender;
	
	public String isPregnant;
	
	public List<UserVaccineReporterAdverseEffectRecordOut> userVaccineReporterAdverseEffectRecordOuts; 

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getBeneficiaryReferenceID() {
		return beneficiaryReferenceID;
	}

	public void setBeneficiaryReferenceID(String beneficiaryReferenceID) {
		this.beneficiaryReferenceID = beneficiaryReferenceID;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<UserIllnessOut> getUserIllnessOuts() {
		return userIllnessOuts;
	}

	public void setUserIllnessOuts(List<UserIllnessOut> userIllnessOuts) {
		this.userIllnessOuts = userIllnessOuts;
	}

	public List<UserAllergiesOut> getUserAllergiesOut() {
		return userAllergiesOut;
	}

	public void setUserAllergiesOut(List<UserAllergiesOut> userAllergiesOut) {
		this.userAllergiesOut = userAllergiesOut;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsPregnant() {
		return isPregnant;
	}

	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}

	public List<UserVaccineReporterAdverseEffectRecordOut> getUserVaccineReporterAdverseEffectRecordOuts() {
		return userVaccineReporterAdverseEffectRecordOuts;
	}

	public void setUserVaccineReporterAdverseEffectRecordOuts(
			List<UserVaccineReporterAdverseEffectRecordOut> userVaccineReporterAdverseEffectRecordOuts) {
		this.userVaccineReporterAdverseEffectRecordOuts = userVaccineReporterAdverseEffectRecordOuts;
	}
}
