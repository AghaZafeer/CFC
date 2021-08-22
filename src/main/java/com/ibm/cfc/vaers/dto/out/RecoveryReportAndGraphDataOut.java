package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class RecoveryReportAndGraphDataOut implements Serializable {

	public String vaccineID;
    public String vaccineName;
    public List<VaccineAndAdverseEventReportObjOut> vaccineAndAdverseEventReportObjOuts;
	
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
	public List<VaccineAndAdverseEventReportObjOut> getVaccineAndAdverseEventReportObjOuts() {
		return vaccineAndAdverseEventReportObjOuts;
	}
	public void setVaccineAndAdverseEventReportObjOuts(
			List<VaccineAndAdverseEventReportObjOut> vaccineAndAdverseEventReportObjOuts) {
		this.vaccineAndAdverseEventReportObjOuts = vaccineAndAdverseEventReportObjOuts;
	}
}
