package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the illness_master database table.
 * 
 */
@Entity
@Table(name="illness_master")
@NamedQuery(name="IllnessMaster.findAll", query="SELECT i FROM IllnessMaster i")
public class IllnessMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ILLNESS_ID", unique=true, nullable=false)
	private long illnessId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="ILLNESS_ISACTIVE", nullable=false)
	private boolean illnessIsactive;

	@Column(name="ILLNESS_ISCHRONIC", nullable=false)
	private boolean illnessIschronic;

	@Column(name="ILLNESS_ISOTHER", nullable=false)
	private boolean illnessIsother;

	@Column(name="ILLNESS_NAME", nullable=false, length=100)
	private String illnessName;

	//bi-directional many-to-one association to UserIllness
	@OneToMany(mappedBy="illnessMaster")
	private List<UserIllness> userIllnesses;

	public IllnessMaster() {
	}

	public long getIllnessId() {
		return this.illnessId;
	}

	public void setIllnessId(long illnessId) {
		this.illnessId = illnessId;
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

	public String getIllnessName() {
		return this.illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	public List<UserIllness> getUserIllnesses() {
		return this.userIllnesses;
	}

	public void setUserIllnesses(List<UserIllness> userIllnesses) {
		this.userIllnesses = userIllnesses;
	}

	public UserIllness addUserIllness(UserIllness userIllness) {
		getUserIllnesses().add(userIllness);
		userIllness.setIllnessMaster(this);

		return userIllness;
	}

	public UserIllness removeUserIllness(UserIllness userIllness) {
		getUserIllnesses().remove(userIllness);
		userIllness.setIllnessMaster(null);

		return userIllness;
	}

}