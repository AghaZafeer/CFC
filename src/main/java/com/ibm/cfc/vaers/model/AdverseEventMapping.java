package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;


/**
 * The persistent class for the adverse_event_mapping database table.
 * 
 */
@Entity
@Table(name="adverse_event_mapping")
@NamedQuery(name="AdverseEventMapping.findAll", query="SELECT a FROM AdverseEventMapping a")
public class AdverseEventMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADEV_MAPPING_ID", unique=true, nullable=false)
	private long adevMappingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED", nullable=false)
	private Date dateModified;

	//bi-directional many-to-one association to AdverseEventMaster
	@ManyToOne
	@JoinColumn(name="ADEV_MAPPING_ADEV_ID", nullable=false)
	private AdverseEventMaster adverseEventMaster1;

	//bi-directional many-to-one association to AdverseEventMaster
	@ManyToOne
	@JoinColumn(name="ADEV_MAPPING_PARENT_ADEV_ID")
	private AdverseEventMaster adverseEventMaster2;

	public AdverseEventMapping() {
	}

	public long getAdevMappingId() {
		return this.adevMappingId;
	}

	public void setAdevMappingId(long adevMappingId) {
		this.adevMappingId = adevMappingId;
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

}