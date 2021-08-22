package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class VaccineAndAdverseEventReportObjOut implements Serializable {
	public String vaccineDoseId;
    public String vaccineDoseName;
    public List<AdverseEventReportObjOut> adverseEventReportObjOuts;
	
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
	public List<AdverseEventReportObjOut> getAdverseEventReportObjOuts() {
		return adverseEventReportObjOuts;
	}
	public void setAdverseEventReportObjOuts(List<AdverseEventReportObjOut> adverseEventReportObjOuts) {
		this.adverseEventReportObjOuts = adverseEventReportObjOuts;
	}
}
