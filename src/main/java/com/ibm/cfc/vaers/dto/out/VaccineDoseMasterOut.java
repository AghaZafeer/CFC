package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

public class VaccineDoseMasterOut implements Serializable {

	public long vaccineDoseId;
	
	public String vaccineDoseName;
	
	public String vaccineDoseDetail;

	public long getVaccineDoseId() {
		return vaccineDoseId;
	}

	public void setVaccineDoseId(long vaccineDoseId) {
		this.vaccineDoseId = vaccineDoseId;
	}

	public String getVaccineDoseName() {
		return vaccineDoseName;
	}

	public void setVaccineDoseName(String vaccineDoseName) {
		this.vaccineDoseName = vaccineDoseName;
	}

	public String getVaccineDoseDetail() {
		return vaccineDoseDetail;
	}

	public void setVaccineDoseDetail(String vaccineDoseDetail) {
		this.vaccineDoseDetail = vaccineDoseDetail;
	}
	
}
