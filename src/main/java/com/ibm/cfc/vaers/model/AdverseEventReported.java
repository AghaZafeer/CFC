package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adverse_event_reported database table.
 * 
 */
@Entity
@Table(name="adverse_event_reported")
@NamedQuery(name="AdverseEventReported.findAll", query="SELECT a FROM AdverseEventReported a")
public class AdverseEventReported implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADEV_REP_ID", unique=true, nullable=false)
	private long adevRepId;

	@Lob
	@Column(name="ADEV_REP_ADDITIONAL_NOTES")
	private String adevRepAdditionalNotes;

	@Temporal(TemporalType.DATE)
	@Column(name="ADEV_REP_DATE_OF_DEATH")
	private Date adevRepDateOfDeath;

	@Column(name="ADEV_REP_ISFATAL", nullable=false)
	private boolean adevRepIsfatal;

	@Column(name="ADEV_REP_ISMEDIC_NEEDED", nullable=false)
	private boolean adevRepIsmedicNeeded;

	@Column(name="ADEV_REP_ISRECOVERED")
	private boolean adevRepIsrecovered;

	@Lob
	@Column(name="ADEV_REP_MEDIC_NOTES")
	private String adevRepMedicNotes;

	@Temporal(TemporalType.DATE)
	@Column(name="ADEV_REP_RECOVERY_DATE")
	private Date adevRepRecoveryDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ADEV_REP_START_DATE_TIME", nullable=false)
	private Date adevRepStartDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	//bi-directional many-to-one association to AdverseEventMaster
	@ManyToOne
	@JoinColumn(name="ADEV_REP_PARENT_ADEV_ID", nullable=false)
	private AdverseEventMaster adverseEventMaster1;

	//bi-directional many-to-one association to AdverseEventMaster
	@ManyToOne
	@JoinColumn(name="ADEV_REP_CHILD_ADEV_ID", nullable=false)
	private AdverseEventMaster adverseEventMaster2;

	//bi-directional many-to-one association to UserVaccine
	@ManyToOne
	@JoinColumn(name="ADEV_REP_USVAC_ID", nullable=false)
	private UserVaccine userVaccine;

	public AdverseEventReported() {
	}

	public long getAdevRepId() {
		return this.adevRepId;
	}

	public void setAdevRepId(long adevRepId) {
		this.adevRepId = adevRepId;
	}
	
	public Date getAdevRepDateOfDeath() {
		return this.adevRepDateOfDeath;
	}

	public void setAdevRepDateOfDeath(Date adevRepDateOfDeath) {
		this.adevRepDateOfDeath = adevRepDateOfDeath;
	}

	public Date getAdevRepRecoveryDate() {
		return this.adevRepRecoveryDate;
	}

	public void setAdevRepRecoveryDate(Date adevRepRecoveryDate) {
		this.adevRepRecoveryDate = adevRepRecoveryDate;
	}

	public Date getAdevRepStartDateTime() {
		return this.adevRepStartDateTime;
	}

	public void setAdevRepStartDateTime(Date adevRepStartDateTime) {
		this.adevRepStartDateTime = adevRepStartDateTime;
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

	public AdverseEventMaster getAdverseEventMaster1() {
		return this.adverseEventMaster1;
	}

	public void setAdverseEventMaster1(AdverseEventMaster adverseEventMaster1) {
		this.adverseEventMaster1 = adverseEventMaster1;
	}

	public AdverseEventMaster getAdverseEventMaster2() {
		return this.adverseEventMaster2;
	}

	public void setAdverseEventMaster2(AdverseEventMaster adverseEventMaster2) {
		this.adverseEventMaster2 = adverseEventMaster2;
	}

	public UserVaccine getUserVaccine() {
		return this.userVaccine;
	}

	public void setUserVaccine(UserVaccine userVaccine) {
		this.userVaccine = userVaccine;
	}

	public String getAdevRepAdditionalNotes() {
		return adevRepAdditionalNotes;
	}

	public void setAdevRepAdditionalNotes(String adevRepAdditionalNotes) {
		this.adevRepAdditionalNotes = adevRepAdditionalNotes;
	}

	public boolean isAdevRepIsfatal() {
		return adevRepIsfatal;
	}

	public void setAdevRepIsfatal(boolean adevRepIsfatal) {
		this.adevRepIsfatal = adevRepIsfatal;
	}

	public boolean isAdevRepIsmedicNeeded() {
		return adevRepIsmedicNeeded;
	}

	public void setAdevRepIsmedicNeeded(boolean adevRepIsmedicNeeded) {
		this.adevRepIsmedicNeeded = adevRepIsmedicNeeded;
	}

	public boolean isAdevRepIsrecovered() {
		return adevRepIsrecovered;
	}

	public void setAdevRepIsrecovered(boolean adevRepIsrecovered) {
		this.adevRepIsrecovered = adevRepIsrecovered;
	}

	public String getAdevRepMedicNotes() {
		return adevRepMedicNotes;
	}

	public void setAdevRepMedicNotes(String adevRepMedicNotes) {
		this.adevRepMedicNotes = adevRepMedicNotes;
	}
	
	

}