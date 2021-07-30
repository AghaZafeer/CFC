package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the severity_master database table.
 * 
 */
@Entity
@Table(name="severity_master")
@NamedQuery(name="SeverityMaster.findAll", query="SELECT s FROM SeverityMaster s")
public class SeverityMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SEV_ID")
	private long sevId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="SEV_ISACTIVE")
	private boolean sevIsactive;

	@Column(name="SEV_NAME")
	private String sevName;

	//bi-directional many-to-one association to AdverseEventMaster
	@OneToMany(mappedBy="severityMaster")
	private List<AdverseEventMaster> adverseEventMasters;

	public SeverityMaster() {
	}

	public long getSevId() {
		return this.sevId;
	}

	public void setSevId(long sevId) {
		this.sevId = sevId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateMofified() {
		return this.dateModified;
	}

	public void setDateMofified(Date dateMofified) {
		this.dateModified = dateMofified;
	}

	public boolean isSevIsactive() {
		return sevIsactive;
	}

	public void setSevIsactive(boolean sevIsactive) {
		this.sevIsactive = sevIsactive;
	}

	public String getSevName() {
		return this.sevName;
	}

	public void setSevName(String sevName) {
		this.sevName = sevName;
	}

	public List<AdverseEventMaster> getAdverseEventMasters() {
		return this.adverseEventMasters;
	}

	public void setAdverseEventMasters(List<AdverseEventMaster> adverseEventMasters) {
		this.adverseEventMasters = adverseEventMasters;
	}

	public AdverseEventMaster addAdverseEventMaster(AdverseEventMaster adverseEventMaster) {
		getAdverseEventMasters().add(adverseEventMaster);
		adverseEventMaster.setSeverityMaster(this);

		return adverseEventMaster;
	}

	public AdverseEventMaster removeAdverseEventMaster(AdverseEventMaster adverseEventMaster) {
		getAdverseEventMasters().remove(adverseEventMaster);
		adverseEventMaster.setSeverityMaster(null);

		return adverseEventMaster;
	}

}