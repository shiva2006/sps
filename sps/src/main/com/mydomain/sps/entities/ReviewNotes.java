package com.mydomain.sps.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="review_notes")
public class ReviewNotes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REVIEW_NOTE_ID")
	private Integer studentId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="STUDENT_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Student student;
	
	@Column(name="NOTES")
	private String notes;
	
	@Column(name="REVIEW_DATE")
	private Date reviewDate;
	
	public ReviewNotes() {
		super();
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

}
