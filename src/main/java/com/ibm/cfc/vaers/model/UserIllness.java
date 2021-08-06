package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_illness database table.
 * 
 */
@Entity
@Table(name="user_illness")
@NamedQuery(name="UserIllness.findAll", query="SELECT u FROM UserIllness u")
public class UserIllness implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ILLNESS_ID", unique=true, nullable=false)
	private long userIllnessId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	//bi-directional many-to-one association to IllnessMaster
	@ManyToOne
	@JoinColumn(name="ILLNESS_ID", nullable=false)
	private IllnessMaster illnessMaster;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;

	public UserIllness() {
	}

	public long getUserIllnessId() {
		return this.userIllnessId;
	}

	public void setUserIllnessId(long userIllnessId) {
		this.userIllnessId = userIllnessId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public IllnessMaster getIllnessMaster() {
		return this.illnessMaster;
	}

	public void setIllnessMaster(IllnessMaster illnessMaster) {
		this.illnessMaster = illnessMaster;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}