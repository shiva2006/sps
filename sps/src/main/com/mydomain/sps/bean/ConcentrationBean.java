package com.mydomain.sps.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author Shanthraj Gowda
 *
 */
@Name("concentrationBean")
@Scope(ScopeType.PAGE)
public class ConcentrationBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer concentrationId;
	private String concentrationName;
	private String degreeCode;
	private boolean active;
	private String status;
	private Integer confacultyId;
	private String facultyName;
	private List<ConcentrationBean> conList = new ArrayList<ConcentrationBean>();
	private ConcentrationBean editObject;
	private boolean visibleFalg;
	private List<SelectItem> advisorUsers;
	private boolean addFlag;
	private boolean editFlag;
	private String searchKey;
	private String  selectedConName;
	
	public ConcentrationBean(){
		
	}
	
	public ConcentrationBean(Integer concentrationId,String concentrationName,String degreeCode,boolean active,String facultyName){
		this.concentrationId = concentrationId;
		this.concentrationName = concentrationName;
		this.degreeCode = degreeCode;
		this.active = active;
		if(Boolean.TRUE.equals(this.active)){
			this.status = "ACTIVE";
		}else{
			this.status = "INACTIVE";
		}
		this.facultyName = facultyName;
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
	public Integer getConfacultyId() {
		return confacultyId;
	}
	public void setConfacultyId(Integer confacultyId) {
		this.confacultyId = confacultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public List<ConcentrationBean> getConList() {
		return conList;
	}
	public void setConList(List<ConcentrationBean> conList) {
		this.conList = conList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ConcentrationBean getEditObject() {
		return editObject;
	}

	public void setEditObject(ConcentrationBean editObject) {
		this.editObject = editObject;
	}

	public boolean isVisibleFalg() {
		return visibleFalg;
	}

	public void setVisibleFalg(boolean visibleFalg) {
		this.visibleFalg = visibleFalg;
	}

	public List<SelectItem> getAdvisorUsers() {
		return advisorUsers;
	}

	public void setAdvisorUsers(List<SelectItem> advisorUsers) {
		this.advisorUsers = advisorUsers;
	}

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSelectedConName() {
		return selectedConName;
	}

	public void setSelectedConName(String selectedConName) {
		this.selectedConName = selectedConName;
	}
	
	
	
}
