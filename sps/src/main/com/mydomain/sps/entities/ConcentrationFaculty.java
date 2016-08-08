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

@Entity @Table(name="concentration_faculty")
public class ConcentrationFaculty extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONCENTRATION_SERIAL_NO")
	private Integer conSerialNo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CONCENTRATION_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Concentration concentrationId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FACULTY_ID", referencedColumnName="USER_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private User faculty;
	
	
	public ConcentrationFaculty() {
		
	}

	public Integer getConfacultyId() {
		return conSerialNo;
	}

	public void setConfacultyId(Integer conSerialNo) {
		this.conSerialNo = conSerialNo;
	}

	public Concentration getConcentrationId() {
		return concentrationId;
	}

	public void setConcentrationId(Concentration concentrationId) {
		this.concentrationId = concentrationId;
	}

	public User getFaculty() {
		return faculty;
	}

	public void setFaculty(User faculty) {
		this.faculty = faculty;
	}

	
}
