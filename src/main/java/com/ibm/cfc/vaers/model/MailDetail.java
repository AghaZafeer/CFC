package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mail_detail database table.
 * 
 */
@Entity
@Table(name="mail_detail")
@NamedQuery(name="MailDetail.findAll", query="SELECT m FROM MailDetail m")
public class MailDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MD_ID")
	private String mdId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="MD_BODY")
	private String mdBody;

	@Column(name="MD_CODE")
	private String mdCode;

	@Column(name="MD_FROM_EMAIL")
	private String mdFromEmail;

	@Column(name="MD_FROM_EMAIL_NAME")
	private String mdFromEmailName;

	@Column(name="MD_SUBJECT")
	private String mdSubject;

	public MailDetail() {
	}

	public String getMdId() {
		return this.mdId;
	}

	public void setMdId(String mdId) {
		this.mdId = mdId;
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

	public String getMdBody() {
		return this.mdBody;
	}

	public void setMdBody(String mdBody) {
		this.mdBody = mdBody;
	}

	public String getMdCode() {
		return this.mdCode;
	}

	public void setMdCode(String mdCode) {
		this.mdCode = mdCode;
	}

	public String getMdFromEmail() {
		return this.mdFromEmail;
	}

	public void setMdFromEmail(String mdFromEmail) {
		this.mdFromEmail = mdFromEmail;
	}

	public String getMdFromEmailName() {
		return this.mdFromEmailName;
	}

	public void setMdFromEmailName(String mdFromEmailName) {
		this.mdFromEmailName = mdFromEmailName;
	}

	public String getMdSubject() {
		return this.mdSubject;
	}

	public void setMdSubject(String mdSubject) {
		this.mdSubject = mdSubject;
	}

}