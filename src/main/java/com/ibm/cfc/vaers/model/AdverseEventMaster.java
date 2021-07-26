package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@Column(name="ADEV_ID")
	private String adevId;

	@Column(name="ADEV_IS_PARENT_ELEMENT")
	private byte adevIsParentElement;

	@Column(name="ADEV_ISACTIVE")
	private byte adevIsactive;

	@Column(name="ADEV_NAME")
	private String adevName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	//bi-directional many-to-one association to SeverityMaster
	@ManyToOne
	@JoinColumn(name="ADEV_SEV_ID")
	private SeverityMaster severityMaster;

	public AdverseEventMaster() {
	}

	public String getAdevId() {
		return this.adevId;
	}

	public void setAdevId(String adevId) {
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

	public SeverityMaster getSeverityMaster() {
		return this.severityMaster;
	}

	public void setSeverityMaster(SeverityMaster severityMaster) {
		this.severityMaster = severityMaster;
	}

}