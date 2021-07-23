package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adverse_event_master database table.
 * 
 */
@Entity
@Table(name="adverse_event_master")
@NamedQuery(name="AdverseEventMaster.findAll", query="SELECT a FROM AdverseEventMaster a")
public class AdverseEventMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADEV_ID", unique=true, nullable=false)
	private long adevId;

	@Column(name="ADEV_IS_PARENT_ELEMENT", nullable=false)
	private byte adevIsParentElement;

	@Column(name="ADEV_ISACTIVE", nullable=false)
	private byte adevIsactive;

	@Column(name="ADEV_NAME", nullable=false, length=100)
	private String adevName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	//bi-directional many-to-one association to AdverseEventMapping
	@OneToMany(mappedBy="adverseEventMaster1")
	private List<AdverseEventMapping> adverseEventMappings1;

	//bi-directional many-to-one association to AdverseEventMapping
	@OneToMany(mappedBy="adverseEventMaster2")
	private List<AdverseEventMapping> adverseEventMappings2;

	//bi-directional many-to-one association to AdverseEventReported
	@OneToMany(mappedBy="adverseEventMaster1")
	private List<AdverseEventReported> adverseEventReporteds1;

	//bi-directional many-to-one association to AdverseEventReported
	@OneToMany(mappedBy="adverseEventMaster2")
	private List<AdverseEventReported> adverseEventReporteds2;

	public AdverseEventMaster() {
	}

	public long getAdevId() {
		return this.adevId;
	}

	public void setAdevId(long adevId) {
		this.adevId = adevId;
	}

	public byte getAdevIsParentElement() {
		return this.adevIsParentElement;
	}

	public void setAdevIsParentElement(byte adevIsParentElement) {
		this.adevIsParentElement = adevIsParentElement;
	}

	public byte getAdevIsactive() {
		return this.adevIsactive;
	}

	public void setAdevIsactive(byte adevIsactive) {
		this.adevIsactive = adevIsactive;
	}

	public String getAdevName() {
		return this.adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
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

	public List<AdverseEventMapping> getAdverseEventMappings1() {
		return this.adverseEventMappings1;
	}

	public void setAdverseEventMappings1(List<AdverseEventMapping> adverseEventMappings1) {
		this.adverseEventMappings1 = adverseEventMappings1;
	}

	public AdverseEventMapping addAdverseEventMappings1(AdverseEventMapping adverseEventMappings1) {
		getAdverseEventMappings1().add(adverseEventMappings1);
		adverseEventMappings1.setAdverseEventMaster1(this);

		return adverseEventMappings1;
	}

	public AdverseEventMapping removeAdverseEventMappings1(AdverseEventMapping adverseEventMappings1) {
		getAdverseEventMappings1().remove(adverseEventMappings1);
		adverseEventMappings1.setAdverseEventMaster1(null);

		return adverseEventMappings1;
	}

	public List<AdverseEventMapping> getAdverseEventMappings2() {
		return this.adverseEventMappings2;
	}

	public void setAdverseEventMappings2(List<AdverseEventMapping> adverseEventMappings2) {
		this.adverseEventMappings2 = adverseEventMappings2;
	}

	public AdverseEventMapping addAdverseEventMappings2(AdverseEventMapping adverseEventMappings2) {
		getAdverseEventMappings2().add(adverseEventMappings2);
		adverseEventMappings2.setAdverseEventMaster2(this);

		return adverseEventMappings2;
	}

	public AdverseEventMapping removeAdverseEventMappings2(AdverseEventMapping adverseEventMappings2) {
		getAdverseEventMappings2().remove(adverseEventMappings2);
		adverseEventMappings2.setAdverseEventMaster2(null);

		return adverseEventMappings2;
	}

	public List<AdverseEventReported> getAdverseEventReporteds1() {
		return this.adverseEventReporteds1;
	}

	public void setAdverseEventReporteds1(List<AdverseEventReported> adverseEventReporteds1) {
		this.adverseEventReporteds1 = adverseEventReporteds1;
	}

	public AdverseEventReported addAdverseEventReporteds1(AdverseEventReported adverseEventReporteds1) {
		getAdverseEventReporteds1().add(adverseEventReporteds1);
		adverseEventReporteds1.setAdverseEventMaster1(this);

		return adverseEventReporteds1;
	}

	public AdverseEventReported removeAdverseEventReporteds1(AdverseEventReported adverseEventReporteds1) {
		getAdverseEventReporteds1().remove(adverseEventReporteds1);
		adverseEventReporteds1.setAdverseEventMaster1(null);

		return adverseEventReporteds1;
	}

	public List<AdverseEventReported> getAdverseEventReporteds2() {
		return this.adverseEventReporteds2;
	}

	public void setAdverseEventReporteds2(List<AdverseEventReported> adverseEventReporteds2) {
		this.adverseEventReporteds2 = adverseEventReporteds2;
	}

	public AdverseEventReported addAdverseEventReporteds2(AdverseEventReported adverseEventReporteds2) {
		getAdverseEventReporteds2().add(adverseEventReporteds2);
		adverseEventReporteds2.setAdverseEventMaster2(this);

		return adverseEventReporteds2;
	}

	public AdverseEventReported removeAdverseEventReporteds2(AdverseEventReported adverseEventReporteds2) {
		getAdverseEventReporteds2().remove(adverseEventReporteds2);
		adverseEventReporteds2.setAdverseEventMaster2(null);

		return adverseEventReporteds2;
	}

}