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
import javax.persistence.Transient;

@Entity @Table(name="questions")
public class Questions extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUESTION_ID")
	private Integer questionId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CONCENTRATION_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Concentration concentration;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="OPTION_1")
	private String option1;
	
	@Column(name="OPTION_2")
	private String option2;
	
	@Column(name="OPTION_3")
	private String option3;
	
	@Column(name="OPTION_4")
	private String option4;
	
	
	@Column(name="QUESTION_TYPE")
	private String questionType;
		
	
	@Column(name="ANSWER")
	private String answer;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@Transient
	private String tempAns="";
	
	public Questions() {
		
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Concentration getConcentration() {
		return concentration;
	}

	public void setConcentration(Concentration concentration) {
		this.concentration = concentration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTempAns() {
		return tempAns;
	}

	public void setTempAns(String tempAns) {
		this.tempAns = tempAns;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

}
