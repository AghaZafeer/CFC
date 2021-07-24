package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class IllnessMasterOut implements Serializable{

	public long illnessId;
	
	public String illnessName;
	
	public boolean illnessIsactive;
	
	public boolean illnessIschronic;
	
	public boolean illnessIsother;

	public long getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(long illnessId) {
		this.illnessId = illnessId;
	}

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	public boolean isIllnessIsactive() {
		return illnessIsactive;
	}

	public void setIllnessIsactive(boolean illnessIsactive) {
		this.illnessIsactive = illnessIsactive;
	}

	public boolean isIllnessIschronic() {
		return illnessIschronic;
	}

	public void setIllnessIschronic(boolean illnessIschronic) {
		this.illnessIschronic = illnessIschronic;
	}

	public boolean isIllnessIsother() {
		return illnessIsother;
	}

	public void setIllnessIsother(boolean illnessIsother) {
		this.illnessIsother = illnessIsother;
	}
}
