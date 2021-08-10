package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class ListOfVaccineDosesDataOut implements Serializable{
	public String vaccineDoseId;
    public String vaccineDoseName;
    public Object totalNoOfUsersForDose;
    public List<ListOfAdverseEventsDataOut> listOfAdverseEventsDataOuts;
    
	public String getVaccineDoseId() {
		return vaccineDoseId;
	}
	public void setVaccineDoseId(String vaccineDoseId) {
		this.vaccineDoseId = vaccineDoseId;
	}
	
	public String getVaccineDoseName() {
		return vaccineDoseName;
	}
	public void setVaccineDoseName(String vaccineDoseName) {
		this.vaccineDoseName = vaccineDoseName;
	}
	
	public Object getTotalNoOfUsersForDose() {
		return totalNoOfUsersForDose;
	}
	public void setTotalNoOfUsersForDose(Object totalNoOfUsersForDose) {
		this.totalNoOfUsersForDose = totalNoOfUsersForDose;
	}
	
	public List<ListOfAdverseEventsDataOut> getListOfAdverseEventsDataOuts() {
		return listOfAdverseEventsDataOuts;
	}
	public void setListOfAdverseEventsDataOuts(List<ListOfAdverseEventsDataOut> listOfAdverseEventsDataOuts) {
		this.listOfAdverseEventsDataOuts = listOfAdverseEventsDataOuts;
	}
}
