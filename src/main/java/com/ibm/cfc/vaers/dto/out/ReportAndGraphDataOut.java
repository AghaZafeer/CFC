package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class ReportAndGraphDataOut implements Serializable {

	public String totalNoOfUsers;
    public List<ListOfVaccineDataOut> listOfVaccineDataOuts;
    
	public String getTotalNoOfUsers() {
		return totalNoOfUsers;
	}
	public void setTotalNoOfUsers(String totalNoOfUsers) {
		this.totalNoOfUsers = totalNoOfUsers;
	}
	
	public List<ListOfVaccineDataOut> getListOfVaccineDataOuts() {
		return listOfVaccineDataOuts;
	}
	public void setListOfVaccineDataOuts(List<ListOfVaccineDataOut> listOfVaccineDataOuts) {
		this.listOfVaccineDataOuts = listOfVaccineDataOuts;
	}
}