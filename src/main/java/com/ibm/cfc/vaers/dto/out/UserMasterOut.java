package com.ibm.cfc.vaers.dto.out;

import java.io.Serializable;

import com.ibm.cfc.vaers.model.User;

public class UserMasterOut implements Serializable {

	public User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
