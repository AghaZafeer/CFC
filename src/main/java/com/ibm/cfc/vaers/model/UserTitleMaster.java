package com.ibm.cfc.vaers.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_title_master database table.
 * 
 */
@Entity
@Table(name="user_title_master")
@NamedQuery(name="UserTitleMaster.findAll", query="SELECT u FROM UserTitleMaster u")
public class UserTitleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_TITLE_ID", unique=true, nullable=false)
	private long userTitleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="USER_TITLE_ISACTIVE", nullable=false)
	private boolean userTitleIsactive;

	@Column(name="USER_TITLE_NAME", nullable=false, length=100)
	private String userTitleName;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userTitleMaster1")
	private List<User> users1;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userTitleMaster2")
	private List<User> users2;

	public UserTitleMaster() {
	}

	public long getUserTitleId() {
		return this.userTitleId;
	}

	public void setUserTitleId(long userTitleId) {
		this.userTitleId = userTitleId;
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

	public boolean isUserTitleIsactive() {
		return userTitleIsactive;
	}

	public void setUserTitleIsactive(boolean userTitleIsactive) {
		this.userTitleIsactive = userTitleIsactive;
	}

	public String getUserTitleName() {
		return this.userTitleName;
	}

	public void setUserTitleName(String userTitleName) {
		this.userTitleName = userTitleName;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public User addUsers1(User users1) {
		getUsers1().add(users1);
		users1.setUserTitleMaster1(this);

		return users1;
	}

	public User removeUsers1(User users1) {
		getUsers1().remove(users1);
		users1.setUserTitleMaster1(null);

		return users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public User addUsers2(User users2) {
		getUsers2().add(users2);
		users2.setUserTitleMaster2(this);

		return users2;
	}

	public User removeUsers2(User users2) {
		getUsers2().remove(users2);
		users2.setUserTitleMaster2(null);

		return users2;
	}

}