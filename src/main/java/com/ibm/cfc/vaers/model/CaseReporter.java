package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the case_reporter database table.
 * 
 */
@Entity
@Table(name="case_reporter")
@NamedQuery(name="CaseReporter.findAll", query="SELECT c FROM CaseReporter c")
public class CaseReporter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CASE_REPORTER_ID", unique=true, nullable=false)
	private long caseReporterId;

	@Column(name="CASE_INFO_ADDED_BY")
	private String caseInfoAddedBy;

	@Column(name="`CASE_REPORTER_ CASE_ID`")
	private String caseReporterCaseId;

	@Column(name="CASE_REPORTER_ALTERNATE_NUMBER")
	private String caseReporterAlternateNumber;

	@Column(name="CASE_REPORTER_EMAIL_ID")
	private String caseReporterEmailId;

	@Column(name="CASE_REPORTER_FIRST_NAME")
	private String caseReporterFirstName;

	@Column(name="CASE_REPORTER_LAST_NAME")
	private String caseReporterLastName;

	@Column(name="CASE_REPORTER_MIDDLE_NAME")
	private String caseReporterMiddleName;

	@Column(name="CASE_REPORTER_MOBILE_NUMBER")
	private String caseReporterMobileNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	//bi-directional many-to-one association to UserTitleMaster
	@ManyToOne
	@JoinColumn(name="CASE_REPORTER_TITLE_ID")
	private UserTitleMaster caseReporterTitleId;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="CASE_REPORTER_USER_ID")
	private User user;

	public CaseReporter() {
	}

	public long getCaseReporterId() {
		return this.caseReporterId;
	}

	public void setCaseReporterId(long caseReporterId) {
		this.caseReporterId = caseReporterId;
	}

	public String getCaseInfoAddedBy() {
		return this.caseInfoAddedBy;
	}

	public void setCaseInfoAddedBy(String caseInfoAddedBy) {
		this.caseInfoAddedBy = caseInfoAddedBy;
	}

	public String getCaseReporterCaseId() {
		return this.caseReporterCaseId;
	}

	public void setCaseReporterCaseId(String caseReporterCaseId) {
		this.caseReporterCaseId = caseReporterCaseId;
	}

	public String getCaseReporterAlternateNumber() {
		return this.caseReporterAlternateNumber;
	}

	public void setCaseReporterAlternateNumber(String caseReporterAlternateNumber) {
		this.caseReporterAlternateNumber = caseReporterAlternateNumber;
	}

	public String getCaseReporterEmailId() {
		return this.caseReporterEmailId;
	}

	public void setCaseReporterEmailId(String caseReporterEmailId) {
		this.caseReporterEmailId = caseReporterEmailId;
	}

	public String getCaseReporterFirstName() {
		return this.caseReporterFirstName;
	}

	public void setCaseReporterFirstName(String caseReporterFirstName) {
		this.caseReporterFirstName = caseReporterFirstName;
	}

	public String getCaseReporterLastName() {
		return this.caseReporterLastName;
	}

	public void setCaseReporterLastName(String caseReporterLastName) {
		this.caseReporterLastName = caseReporterLastName;
	}

	public String getCaseReporterMiddleName() {
		return this.caseReporterMiddleName;
	}

	public void setCaseReporterMiddleName(String caseReporterMiddleName) {
		this.caseReporterMiddleName = caseReporterMiddleName;
	}

	public String getCaseReporterMobileNumber() {
		return this.caseReporterMobileNumber;
	}

	public void setCaseReporterMobileNumber(String caseReporterMobileNumber) {
		this.caseReporterMobileNumber = caseReporterMobileNumber;
	}

	public UserTitleMaster getCaseReporterTitleId() {
		return this.caseReporterTitleId;
	}

	public void setCaseReporterTitleId(UserTitleMaster caseReporterTitleId) {
		this.caseReporterTitleId = caseReporterTitleId;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}