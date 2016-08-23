package com.mydomain.sps.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="student_exam")
public class StudentExam extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STD_QUN_ID")
	private Integer stdQunId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="STD_CON_ID", unique= true, nullable=true, insertable=true, updatable=true, referencedColumnName="CONCENTRATION_ID")
	private Concentration studentConcentration;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="QUESTION_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Questions question;
	
	@Column(name="STD_ANSWER")
	private String stdAnswer;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	public StudentExam() {
		
	}

	public Integer getStdQunId() {
		return stdQunId;
	}

	public void setStdQunId(Integer stdQunId) {
		this.stdQunId = stdQunId;
	}
	
	public Concentration getStudentConcentration() {
		return studentConcentration;
	}

	public void setStudentConcentration(Concentration studentConcentration) {
		this.studentConcentration = studentConcentration;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public String getStdAnswer() {
		return stdAnswer;
	}

	public void setStdAnswer(String stdAnswer) {
		this.stdAnswer = stdAnswer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
