package com.mydomain.sps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="email_template")
public class EmailTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMAIL_TEMPLATE_ID")
	private Integer emailTemplateId;
	
	@OneToOne
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	
	@Column(name="DOCUMENT")
	private String document;
	
	@Column(name="SEND_TO")
	private String sendTo;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="GENERATED_ON")
	private Date generatedOn;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	public EmailTemplate() {
		
	}

	public Integer getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(Integer emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
