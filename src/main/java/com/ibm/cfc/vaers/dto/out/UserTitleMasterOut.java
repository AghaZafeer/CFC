package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class UserTitleMasterOut implements Serializable{

	public long userTitleId;
	
	public String userTitleName;
	
	public boolean userTitleIsactive;
	
	/**
	* private Date dateModified;	
	* private Date dateCreated;	
	*/
	
		public long getuserTitleId() {
		return userTitleId;
	}

	public void setuserTitleId(long userTitleId) {
		this.userTitleId = userTitleId;
	}

	public String getuserTitleName() {
		return userTitleName;
	}

	public void setuserTitleName(String userTitleName) {
		this.userTitleName = userTitleName;
	}

	public boolean userTitleIsactive() {
		return userTitleIsactive;
	}

	public void setuserTitleIsactive(boolean userTitleIsactive) {
		this.userTitleIsactive = userTitleIsactive;
	}

	
}
