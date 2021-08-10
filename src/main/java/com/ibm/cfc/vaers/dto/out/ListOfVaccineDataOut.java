package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class ListOfVaccineDataOut implements Serializable{
    public String vaccineID;
    public String vaccineName;
    public String totalNoOfUsersPerVaccine;
    public List<ListOfVaccineDosesDataOut> listOfVaccineDosesDataOuts;
	
    public String getVaccineID() {
		return vaccineID;
	}
	public void setVaccineID(String vaccineID) {
		this.vaccineID = vaccineID;
	}
	
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	
	public String getTotalNoOfUsersPerVaccine() {
		return totalNoOfUsersPerVaccine;
	}
	public void setTotalNoOfUsersPerVaccine(String totalNoOfUsersPerVaccine) {
		this.totalNoOfUsersPerVaccine = totalNoOfUsersPerVaccine;
	}
	
	public List<ListOfVaccineDosesDataOut> getListOfVaccineDosesDataOuts() {
		return listOfVaccineDosesDataOuts;
	}
	public void setListOfVaccineDosesDataOuts(List<ListOfVaccineDosesDataOut> listOfVaccineDosesDataOuts) {
		this.listOfVaccineDosesDataOuts = listOfVaccineDosesDataOuts;
	}
}
