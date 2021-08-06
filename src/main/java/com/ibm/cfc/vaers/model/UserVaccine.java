package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user_vaccine database table.
 * 
 */
@Entity
@Table(name="user_vaccine")
@NamedQuery(name="UserVaccine.findAll", query="SELECT u FROM UserVaccine u")
public class UserVaccine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USVAC_ID", unique=true, nullable=false)
	private long usvacId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="USVAC_VACCINATION_CENTER", length=255)
	private String usvacVaccinationCenter;

	@Column(name="USVAC_VACCINATION_DATETIME", nullable=false)
	private Timestamp usvacVaccinationDatetime;

	//bi-directional many-to-one association to AdverseEventReported
	@OneToMany(mappedBy="userVaccine")
	private List<AdverseEventReported> adverseEventReporteds;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USVAC_USER_ID", nullable=false)
	private User user;

	//bi-directional many-to-one association to VaccineDoseMaster
	@ManyToOne
	@JoinColumn(name="USVAC_VACCINE_DOSE_ID", nullable=false)
	private VaccineDoseMaster vaccineDoseMaster;

	//bi-directional many-to-one association to VaccineMaster
	@ManyToOne
	@JoinColumn(name="USVAC_VACCINE_ID", nullable=false)
	private VaccineMaster vaccineMaster;

	public UserVaccine() {
	}

	public long getUsvacId() {
		return this.usvacId;
	}

	public void setUsvacId(long usvacId) {
		this.usvacId = usvacId;
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

	public String getUsvacVaccinationCenter() {
		return this.usvacVaccinationCenter;
	}

	public void setUsvacVaccinationCenter(String usvacVaccinationCenter) {
		this.usvacVaccinationCenter = usvacVaccinationCenter;
	}

	public Timestamp getUsvacVaccinationDatetime() {
		return this.usvacVaccinationDatetime;
	}

	public void setUsvacVaccinationDatetime(Timestamp usvacVaccinationDatetime) {
		this.usvacVaccinationDatetime = usvacVaccinationDatetime;
	}

	public List<AdverseEventReported> getAdverseEventReporteds() {
		return this.adverseEventReporteds;
	}

	public void setAdverseEventReporteds(List<AdverseEventReported> adverseEventReporteds) {
		this.adverseEventReporteds = adverseEventReporteds;
	}

	public AdverseEventReported addAdverseEventReported(AdverseEventReported adverseEventReported) {
		getAdverseEventReporteds().add(adverseEventReported);
		adverseEventReported.setUserVaccine(this);

		return adverseEventReported;
	}

	public AdverseEventReported removeAdverseEventReported(AdverseEventReported adverseEventReported) {
		getAdverseEventReporteds().remove(adverseEventReported);
		adverseEventReported.setUserVaccine(null);

		return adverseEventReported;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VaccineDoseMaster getVaccineDoseMaster() {
		return this.vaccineDoseMaster;
	}

	public void setVaccineDoseMaster(VaccineDoseMaster vaccineDoseMaster) {
		this.vaccineDoseMaster = vaccineDoseMaster;
	}

	public VaccineMaster getVaccineMaster() {
		return this.vaccineMaster;
	}

	public void setVaccineMaster(VaccineMaster vaccineMaster) {
		this.vaccineMaster = vaccineMaster;
	}

}