package com.ibm.cfc.vaers.dto.in;

import java.io.Serializable;

public class ValidateBeneficiaryRefIDObjIn implements Serializable {

	public String beneficiaryRefID;
	
	public String vaccineName;
	
	public String vaccineDose;

	public String getBeneficiaryRefID() {
		return beneficiaryRefID;
	}

	public void setBeneficiaryRefID(String beneficiaryRefID) {
		this.beneficiaryRefID = beneficiaryRefID;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccineDose() {
		return vaccineDose;
	}

	public void setVaccineDose(String vaccineDose) {
		this.vaccineDose = vaccineDose;
	}
}
