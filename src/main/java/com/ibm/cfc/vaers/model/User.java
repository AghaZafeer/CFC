package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", unique=true, nullable=false)
	private long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="USER_AADHAAR_NO", nullable=false, length=50)
	private String userAadhaarNo;

	@Column(name="USER_ADDRESS", length=255)
	private String userAddress;

	@Column(name="USER_AGE")
	private short userAge;

	@Column(name="USER_ALTERNATE_NUMBER", length=15)
	private String userAlternateNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="USER_DOB")
	private Date userDob;

	@Column(name="USER_EMAIL_ID", length=200)
	private String userEmailId;

	@Column(name="USER_FIRST_NAME", length=100)
	private String userFirstName;

	@Column(name="USER_INFO_ADDED_BY", length=1)
	private String userInfoAddedBy;

	@Column(name="USER_IS_PREGNANT")
	private byte userIsPregnant;

	@Column(name="USER_ISACTIVE", nullable=false)
	private boolean userIsactive;

	@Column(name="USER_LAST_NAME", length=100)
	private String userLastName;

	@Column(name="USER_MIDDLE_NAME", length=100)
	private String userMiddleName;

	@Column(name="USER_MOBILE", length=15)
	private String userMobile;

	@Column(name="USER_REPORTER_ALTERNATE_NUMBER", length=15)
	private String userReporterAlternateNumber;

	@Column(name="USER_REPORTER_EMAIL_ID", length=200)
	private String userReporterEmailId;

	@Column(name="USER_REPORTER_FIRST_NAME", length=100)
	private String userReporterFirstName;

	@Column(name="USER_REPORTER_LAST_NAME", length=100)
	private String userReporterLastName;

	@Column(name="USER_REPORTER_MIDDLE_NAME", length=100)
	private String userReporterMiddleName;

	@Column(name="USER_REPORTER_MOBILE", length=15)
	private String userReporterMobile;

	@Column(name="USER_SEX", length=1)
	private String userSex;

	//bi-directional many-to-one association to UserTitleMaster
	@ManyToOne
	@JoinColumn(name="USER_REPORTER_TITLE_ID")
	private UserTitleMaster userTitleMaster1;

	//bi-directional many-to-one association to UserTitleMaster
	@ManyToOne
	@JoinColumn(name="USER_TITLE_ID")
	private UserTitleMaster userTitleMaster2;

	//bi-directional many-to-one association to UserAllergicCondition
	@OneToMany(mappedBy="user")
	private List<UserAllergicCondition> userAllergicConditions;

	//bi-directional many-to-one association to UserIllness
	@OneToMany(mappedBy="user")
	private List<UserIllness> userIllnesses;

	//bi-directional many-to-one association to UserVaccine
	@OneToMany(mappedBy="user")
	private List<UserVaccine> userVaccines;

	public User() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getUserAadhaarNo() {
		return this.userAadhaarNo;
	}

	public void setUserAadhaarNo(String userAadhaarNo) {
		this.userAadhaarNo = userAadhaarNo;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public short getUserAge() {
		return this.userAge;
	}

	public void setUserAge(short userAge) {
		this.userAge = userAge;
	}

	public String getUserAlternateNumber() {
		return this.userAlternateNumber;
	}

	public void setUserAlternateNumber(String userAlternateNumber) {
		this.userAlternateNumber = userAlternateNumber;
	}

	public Date getUserDob() {
		return this.userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public String getUserEmailId() {
		return this.userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserInfoAddedBy() {
		return this.userInfoAddedBy;
	}

	public void setUserInfoAddedBy(String userInfoAddedBy) {
		this.userInfoAddedBy = userInfoAddedBy;
	}

	public byte getUserIsPregnant() {
		return this.userIsPregnant;
	}

	public void setUserIsPregnant(byte userIsPregnant) {
		this.userIsPregnant = userIsPregnant;
	}

	public boolean isUserIsactive() {
		return userIsactive;
	}

	public void setUserIsactive(boolean userIsactive) {
		this.userIsactive = userIsactive;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserMiddleName() {
		return this.userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserReporterAlternateNumber() {
		return this.userReporterAlternateNumber;
	}

	public void setUserReporterAlternateNumber(String userReporterAlternateNumber) {
		this.userReporterAlternateNumber = userReporterAlternateNumber;
	}

	public String getUserReporterEmailId() {
		return this.userReporterEmailId;
	}

	public void setUserReporterEmailId(String userReporterEmailId) {
		this.userReporterEmailId = userReporterEmailId;
	}

	public String getUserReporterFirstName() {
		return this.userReporterFirstName;
	}

	public void setUserReporterFirstName(String userReporterFirstName) {
		this.userReporterFirstName = userReporterFirstName;
	}

	public String getUserReporterLastName() {
		return this.userReporterLastName;
	}

	public void setUserReporterLastName(String userReporterLastName) {
		this.userReporterLastName = userReporterLastName;
	}

	public String getUserReporterMiddleName() {
		return this.userReporterMiddleName;
	}

	public void setUserReporterMiddleName(String userReporterMiddleName) {
		this.userReporterMiddleName = userReporterMiddleName;
	}

	public String getUserReporterMobile() {
		return this.userReporterMobile;
	}

	public void setUserReporterMobile(String userReporterMobile) {
		this.userReporterMobile = userReporterMobile;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public UserTitleMaster getUserTitleMaster1() {
		return this.userTitleMaster1;
	}

	public void setUserTitleMaster1(UserTitleMaster userTitleMaster1) {
		this.userTitleMaster1 = userTitleMaster1;
	}

	public UserTitleMaster getUserTitleMaster2() {
		return this.userTitleMaster2;
	}

	public void setUserTitleMaster2(UserTitleMaster userTitleMaster2) {
		this.userTitleMaster2 = userTitleMaster2;
	}

	public List<UserAllergicCondition> getUserAllergicConditions() {
		return this.userAllergicConditions;
	}

	public void setUserAllergicConditions(List<UserAllergicCondition> userAllergicConditions) {
		this.userAllergicConditions = userAllergicConditions;
	}

	public UserAllergicCondition addUserAllergicCondition(UserAllergicCondition userAllergicCondition) {
		getUserAllergicConditions().add(userAllergicCondition);
		userAllergicCondition.setUser(this);

		return userAllergicCondition;
	}

	public UserAllergicCondition removeUserAllergicCondition(UserAllergicCondition userAllergicCondition) {
		getUserAllergicConditions().remove(userAllergicCondition);
		userAllergicCondition.setUser(null);

		return userAllergicCondition;
	}

	public List<UserIllness> getUserIllnesses() {
		return this.userIllnesses;
	}

	public void setUserIllnesses(List<UserIllness> userIllnesses) {
		this.userIllnesses = userIllnesses;
	}

	public UserIllness addUserIllness(UserIllness userIllness) {
		getUserIllnesses().add(userIllness);
		userIllness.setUser(this);

		return userIllness;
	}

	public UserIllness removeUserIllness(UserIllness userIllness) {
		getUserIllnesses().remove(userIllness);
		userIllness.setUser(null);

		return userIllness;
	}

	public List<UserVaccine> getUserVaccines() {
		return this.userVaccines;
	}

	public void setUserVaccines(List<UserVaccine> userVaccines) {
		this.userVaccines = userVaccines;
	}

	public UserVaccine addUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().add(userVaccine);
		userVaccine.setUser(this);

		return userVaccine;
	}

	public UserVaccine removeUserVaccine(UserVaccine userVaccine) {
		getUserVaccines().remove(userVaccine);
		userVaccine.setUser(null);

		return userVaccine;
	}

}