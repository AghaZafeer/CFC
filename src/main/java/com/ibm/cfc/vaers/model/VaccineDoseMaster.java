package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vaccine_dose_master database table.
 * 
 */
@Entity
@Table(name="vaccine_dose_master")
@NamedQuery(name="VaccineDoseMaster.findAll", query="SELECT v FROM VaccineDoseMaster v")
public class VaccineDoseMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VACCINE_DOSE_ID", unique=true, nullable=false)
	private long vaccineDoseId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	@Column(name="VACCINE_DOSE_DETAIL", length=255)
	private String vaccineDoseDetail;

	@Column(name="VACCINE_DOSE_ISACTIVE", nullable=false)
	private byte vaccineDoseIsactive;

	@Column(name="VACCINE_DOSE_NAME", nullable=false, length=100)
	private String vaccineDoseName;

	//bi-directional many-to-one association to UserVaccine
	@OneToMany(mappedBy="vaccineDoseMaster")
	private List<UserVaccine> userVaccines;

	//bi-directional many-to-one association to VaccineMaster
	@ManyToOne
	@JoinColumn(name="VACCINE_ID", nullable=false)
	private VaccineMaster vaccineMaster;

	public VaccineDoseMaster() {
	}

	public long getVaccineDoseId() {
		return this.vaccineDoseId;
	}

	public void setVaccineDoseId(long vaccineDoseId) {
		this.vaccineDoseId = vaccineDoseId;
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

	public String getVaccineDoseDetail() {
		return this.vaccineDoseDetail;
	}

	public void setVaccineDoseDetail(String vaccineDoseDetail) {
		this.vaccineDoseDetail = vaccineDoseDetail;
	}

	public byte getVaccineDoseIsactive() {
		return this.vaccineDoseIsactive;
	}

	public void setVaccineDoseIsactive(byte vaccineDoseIsactive) {
		this.vaccineDoseIsactive = vaccineDoseIsactive;
	}

	public String getVaccineDoseName() {
		return this.vaccineDoseName;
	}

	public void setVaccineDoseName(String vaccineDoseName) {
		this.vaccineDoseName = vaccineDoseName;
	}

	public List<UserVaccine> getUserVaccines() {
		return this.userVaccines;
	}

	public void setUserVaccines(List<UserVaccine> userVaccines) {
		this.userVaccines = userVaccines;
	}

	public UserVaccine addUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().add(userVaccine);
		userVaccine.setVaccineDoseMaster(this);

		return userVaccine;
	}

	public UserVaccine removeUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().remove(userVaccine);
		userVaccine.setVaccineDoseMaster(null);

		return userVaccine;
	}

	public VaccineMaster getVaccineMaster() {
		return this.vaccineMaster;
	}

	public void setVaccineMaster(VaccineMaster vaccineMaster) {
		this.vaccineMaster = vaccineMaster;
	}

}