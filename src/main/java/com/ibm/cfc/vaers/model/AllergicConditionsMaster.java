package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the allergic_conditions_master database table.
 * 
 */
@Entity
@Table(name="allergic_conditions_master")
@NamedQuery(name="AllergicConditionsMaster.findAll", query="SELECT a FROM AllergicConditionsMaster a")
public class AllergicConditionsMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ALLGCOND_ID", unique=true, nullable=false)
	private long allgcondId;

	@Column(name="ALLGCOND_ISACTIVE", nullable=false)
	private boolean allgcondIsactive;

	@Column(name="ALLGCOND_NAME", nullable=false, length=100)
	private String allgcondName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	//bi-directional many-to-one association to UserAllergicCondition
	@OneToMany(mappedBy="allergicConditionsMaster")
	private List<UserAllergicCondition> userAllergicConditions;

	public AllergicConditionsMaster() {
	}

	public long getAllgcondId() {
		return this.allgcondId;
	}

	public void setAllgcondId(long allgcondId) {
		this.allgcondId = allgcondId;
	}

	public boolean isAllgcondIsactive() {
		return allgcondIsactive;
	}

	public void setAllgcondIsactive(boolean allgcondIsactive) {
		this.allgcondIsactive = allgcondIsactive;
	}

	public String getAllgcondName() {
		return this.allgcondName;
	}

	public void setAllgcondName(String allgcondName) {
		this.allgcondName = allgcondName;
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

	public List<UserAllergicCondition> getUserAllergicConditions() {
		return this.userAllergicConditions;
	}

	public void setUserAllergicConditions(List<UserAllergicCondition> userAllergicConditions) {
		this.userAllergicConditions = userAllergicConditions;
	}

	public UserAllergicCondition addUserAllergicCondition(UserAllergicCondition userAllergicCondition) {
		getUserAllergicConditions().add(userAllergicCondition);
		userAllergicCondition.setAllergicConditionsMaster(this);

		return userAllergicCondition;
	}

	public UserAllergicCondition removeUserAllergicCondition(UserAllergicCondition userAllergicCondition) {
		getUserAllergicConditions().remove(userAllergicCondition);
		userAllergicCondition.setAllergicConditionsMaster(null);

		return userAllergicCondition;
	}

}