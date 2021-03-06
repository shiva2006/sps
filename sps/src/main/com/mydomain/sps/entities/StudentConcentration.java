package com.mydomain.sps.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="student_concentration")
public class StudentConcentration extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STU_CON_ID")
	private Integer stuconId;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="STUDENT_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Student student;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="CONCENTRATION_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Concentration concentration = new Concentration();
	
	@Column(name="ADVISOR")
	private Integer advisor;
	
	@Column(name="EMAIL_TEMPLATE_ID")
	private Date emailTempleteI;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="STATUS_CHANGE_DATE")
	private Date statusChangeOn;
	
	@Column(name="CONDITIONS")
	private String conditions;
	
	@Column(name="EXAM_STATUS")
	private String examStatus;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	public StudentConcentration() {
		
	}

	public Integer getStuconId() {
		return stuconId;
	}

	public void setStuconId(Integer stuconId) {
		this.stuconId = stuconId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Concentration getConcentration() {
		return concentration;
	}

	public void setConcentration(Concentration concentration) {
		this.concentration = concentration;
	}

	public Integer getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Integer advisor) {
		this.advisor = advisor;
	}

	public Date getEmailTempleteI() {
		return emailTempleteI;
	}

	public void setEmailTempleteI(Date emailTempleteI) {
		this.emailTempleteI = emailTempleteI;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusChangeOn() {
		return statusChangeOn;
	}

	public void setStatusChangeOn(Date statusChangeOn) {
		this.statusChangeOn = statusChangeOn;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

}
