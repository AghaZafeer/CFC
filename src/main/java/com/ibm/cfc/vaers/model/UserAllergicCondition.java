package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_allergic_conditions database table.
 * 
 */
@Entity
@Table(name="user_allergic_conditions")
@NamedQuery(name="UserAllergicCondition.findAll", query="SELECT u FROM UserAllergicCondition u")
public class UserAllergicCondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ALLGCOND_ID", unique=true, nullable=false)
	private long userAllgcondId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	//bi-directional many-to-one association to AllergicConditionsMaster
	@ManyToOne
	@JoinColumn(name="ALLGCOND_ID", nullable=false)
	private AllergicConditionsMaster allergicConditionsMaster;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;

	public UserAllergicCondition() {
	}

	public long getUserAllgcondId() {
		return this.userAllgcondId;
	}

	public void setUserAllgcondId(long userAllgcondId) {
		this.userAllgcondId = userAllgcondId;
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

	public AllergicConditionsMaster getAllergicConditionsMaster() {
		return this.allergicConditionsMaster;
	}

	public void setAllergicConditionsMaster(AllergicConditionsMaster allergicConditionsMaster) {
		this.allergicConditionsMaster = allergicConditionsMaster;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}