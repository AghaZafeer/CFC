package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vaccine_master database table.
 * 
 */
@Entity
@Table(name="vaccine_master")
@NamedQuery(name="VaccineMaster.findAll", query="SELECT v FROM VaccineMaster v")
public class VaccineMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VACCINE_ID", unique=true, nullable=false)
	private long vaccineId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	@Column(name="VACCINE_ISACTIVE", nullable=false)
	private byte vaccineIsactive;

	@Column(name="VACCINE_MANUFACTURER", length=100)
	private String vaccineManufacturer;

	@Column(name="VACCINE_NAME", nullable=false, length=100)
	private String vaccineName;

	//bi-directional many-to-one association to UserVaccine
	@OneToMany(mappedBy="vaccineMaster")
	private List<UserVaccine> userVaccines;

	//bi-directional many-to-one association to VaccineDoseMaster
	@OneToMany(mappedBy="vaccineMaster")
	private List<VaccineDoseMaster> vaccineDoseMasters;

	public VaccineMaster() {
	}

	public long getVaccineId() {
		return this.vaccineId;
	}

	public void setVaccineId(long vaccineId) {
		this.vaccineId = vaccineId;
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

	public byte getVaccineIsactive() {
		return this.vaccineIsactive;
	}

	public void setVaccineIsactive(byte vaccineIsactive) {
		this.vaccineIsactive = vaccineIsactive;
	}

	public String getVaccineManufacturer() {
		return this.vaccineManufacturer;
	}

	public void setVaccineManufacturer(String vaccineManufacturer) {
		this.vaccineManufacturer = vaccineManufacturer;
	}

	public String getVaccineName() {
		return this.vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public List<UserVaccine> getUserVaccines() {
		return this.userVaccines;
	}

	public void setUserVaccines(List<UserVaccine> userVaccines) {
		this.userVaccines = userVaccines;
	}

	public UserVaccine addUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().add(userVaccine);
		userVaccine.setVaccineMaster(this);

		return userVaccine;
	}

	public UserVaccine removeUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().remove(userVaccine);
		userVaccine.setVaccineMaster(null);

		return userVaccine;
	}

	public List<VaccineDoseMaster> getVaccineDoseMasters() {
		return this.vaccineDoseMasters;
	}

	public void setVaccineDoseMasters(List<VaccineDoseMaster> vaccineDoseMasters) {
		this.vaccineDoseMasters = vaccineDoseMasters;
	}

	public VaccineDoseMaster addVaccineDoseMaster(VaccineDoseMaster vaccineDoseMaster) {
		getVaccineDoseMasters().add(vaccineDoseMaster);
		vaccineDoseMaster.setVaccineMaster(this);

		return vaccineDoseMaster;
	}

	public VaccineDoseMaster removeVaccineDoseMaster(VaccineDoseMaster vaccineDoseMaster) {
		getVaccineDoseMasters().remove(vaccineDoseMaster);
		vaccineDoseMaster.setVaccineMaster(null);

		return vaccineDoseMaster;
	}

}