package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.List;

public class AdverseEventReportObjOut implements Serializable {

	public String adverseEventName;
    public List<UserAndRecoveryDataReportObjOut> userAndRecoveryDataReportObjOuts;
    
	public String getAdverseEventName() {
		return adverseEventName;
	}
	public void setAdverseEventName(String adverseEventName) {
		this.adverseEventName = adverseEventName;
	}
	public List<UserAndRecoveryDataReportObjOut> getUserAndRecoveryDataReportObjOuts() {
		return userAndRecoveryDataReportObjOuts;
	}
	public void setUserAndRecoveryDataReportObjOuts(List<UserAndRecoveryDataReportObjOut> userAndRecoveryDataReportObjOuts) {
		this.userAndRecoveryDataReportObjOuts = userAndRecoveryDataReportObjOuts;
	}
}
