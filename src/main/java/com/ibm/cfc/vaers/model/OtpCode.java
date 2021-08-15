package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the otp_codes database table.
 * 
 */
@Entity
@Table(name="otp_codes")
@NamedQuery(name="OtpCode.findAll", query="SELECT o FROM OtpCode o")
public class OtpCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OTP_ID", unique=true, nullable=false)
	private long otpId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="OTP_ACTIVE")
	private boolean otpActive;

	@Column(name="OTP_EMAIL_ID")
	private String otpEmailId;

	@Column(name="OTP_VALUE")
	private String otpValue;

	public OtpCode() {
	}

	public long getOtpId() {
		return this.otpId;
	}

	public void setOtpId(long otpId) {
		this.otpId = otpId;
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

	public boolean isOtpActive() {
		return this.otpActive;
	}

	public void setOtpActive(boolean otpActive) {
		this.otpActive = otpActive;
	}

	public String getOtpEmailId() {
		return this.otpEmailId;
	}

	public void setOtpEmailId(String otpEmailId) {
		this.otpEmailId = otpEmailId;
	}

	public String getOtpValue() {
		return this.otpValue;
	}

	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}

}