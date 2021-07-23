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
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	@Column(name="ILLNESS_ISACTIVE", nullable=false)
	private byte illnessIsactive;

	@Column(name="ILLNESS_ISCHRONIC", nullable=false)
	private byte illnessIschronic;

	@Column(name="ILLNESS_ISOTHER", nullable=false)
	private byte illnessIsother;

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

	public byte getIllnessIsactive() {
		return this.illnessIsactive;
	}

	public void setIllnessIsactive(byte illnessIsactive) {
		this.illnessIsactive = illnessIsactive;
	}

	public byte getIllnessIschronic() {
		return this.illnessIschronic;
	}

	public void setIllnessIschronic(byte illnessIschronic) {
		this.illnessIschronic = illnessIschronic;
	}

	public byte getIllnessIsother() {
		return this.illnessIsother;
	}

	public void setIllnessIsother(byte illnessIsother) {
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