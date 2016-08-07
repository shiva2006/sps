package com.mydomain.sps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="students")
public class Student extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STUDENT_ID")
	private Integer studentId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="MAILING_ADDRESS")
	private String mailingAddress;
	
	@Column(name="PHONE_NUMBER")
	private Integer phoneNumber;
	
	@Column(name="GRE_SCORE")
	private Integer greScore;
	
	@Column(name="UCM_EMAIL")
	private String ucmMail;
	
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="OTHER_EMAIL")
	private String otherMail;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	
	public Student() {
		
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getGreScore() {
		return greScore;
	}

	public void setGreScore(Integer greScore) {
		this.greScore = greScore;
	}

	public String getUcmMail() {
		return ucmMail;
	}

	public void setUcmMail(String ucmMail) {
		this.ucmMail = ucmMail;
	}

	public String getOtherMail() {
		return otherMail;
	}

	public void setOtherMail(String otherMail) {
		this.otherMail = otherMail;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

}
