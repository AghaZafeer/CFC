package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class VacccineMasterOut implements Serializable{

	public long vaccineID;
	
	public String vaccineName;
	
	public String vaccineManufacturer;
	
	public boolean vaccineIsActive;
	
	public List<VaccineDoseMasterOut> vaccineDoses;

	public long getVaccineID() {
		return vaccineID;
	}

	public void setVaccineID(long vaccineID) {
		this.vaccineID = vaccineID;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccineManufacturer() {
		return vaccineManufacturer;
	}

	public void setVaccineManufacturer(String vaccineManufacturer) {
		this.vaccineManufacturer = vaccineManufacturer;
	}

	public List<VaccineDoseMasterOut> getVaccineDoses() {
		return vaccineDoses;
	}

	public void setVaccineDoses(List<VaccineDoseMasterOut> vaccineDoses) {
		this.vaccineDoses = vaccineDoses;
	}

	public boolean isVaccineIsActive() {
		return vaccineIsActive;
	}

	public void setVaccineIsActive(boolean vaccineIsActive) {
		this.vaccineIsActive = vaccineIsActive;
	}
	
}
