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

	@Column(name="USER_IS_PREGNANT")
	private boolean userIsPregnant;

	@Column(name="USER_ISACTIVE", nullable=false)
	private boolean userIsactive;

	@Column(name="USER_LAST_NAME", length=100)
	private String userLastName;

	@Column(name="USER_MIDDLE_NAME", length=100)
	private String userMiddleName;

	@Column(name="USER_MOBILE", length=15)
	private String userMobile;

	@Column(name="USER_SEX", length=1)
	private String userSex;

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
	
	//bi-directional many-to-one association to CaseReporter
	@OneToMany(mappedBy="user")
	private List<CaseReporter> caseReporters;


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

	public boolean isUserIsPregnant() {
		return this.userIsPregnant;
	}

	public void setUserIsPregnant(boolean userIsPregnant) {
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


	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
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
	
	public List<CaseReporter> getCaseReporters() {
		return this.caseReporters;
	}

	public void setCaseReporters(List<CaseReporter> caseReporters) {
		this.caseReporters = caseReporters;
	}

	public CaseReporter addCaseReporter(CaseReporter caseReporter) {
		getCaseReporters().add(caseReporter);
		caseReporter.setUser(this);

		return caseReporter;
	}

	public CaseReporter removeCaseReporter(CaseReporter caseReporter) {
		getCaseReporters().remove(caseReporter);
		caseReporter.setUser(null);

		return caseReporter;
	}

}