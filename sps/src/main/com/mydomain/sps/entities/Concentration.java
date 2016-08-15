package com.mydomain.sps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="concentrations")
public class Concentration extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONCENTRATION_ID")
	private Integer concentrationId;
	
	@Column(name="CONCENTRATION_NAME")
	private String concentrationName;
	
	@Column(name="DEGREE_CODE")
	private String degreeCode;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	public Concentration() {
		
	}
	
	public Concentration(Integer concentrationId, String concentrationName) {
		super();
		this.concentrationId = concentrationId;
		this.concentrationName = concentrationName;
	}


	public Integer getConcentrationId() {
		return concentrationId;
	}

	public void setConcentrationId(Integer concentrationId) {
		this.concentrationId = concentrationId;
	}

	public String getConcentrationName() {
		return concentrationName;
	}

	public void setConcentrationName(String concentrationName) {
		this.concentrationName = concentrationName;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
