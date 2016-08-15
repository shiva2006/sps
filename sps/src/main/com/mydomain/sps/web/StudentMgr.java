package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.mydomain.sps.entities.Concentration;
import com.mydomain.sps.entities.Student;
import com.mydomain.sps.entities.StudentConcentration;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;

@Name("studentMgr") @Scope(ScopeType.PAGE)
public class StudentMgr extends BaseMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@In BaseDao baseDaoImpl;
	private Student student = new Student();	
	private List<Student> students = new ArrayList<Student>();
	private List<SelectItem> concentrations;
	private StudentConcentration stdConcentration = new StudentConcentration();
	private String searchKey;
	private boolean showStudnt;
	private String temp;
	
	public void loadStudents() {
		searchKey = null;
		students = baseDaoImpl.find(Constants.GET_ALL_STUDENTS);
	}
	
	public void addStudent() {
		showStudnt=Boolean.TRUE;
		student = new Student();
		concentrations = populateData(baseDaoImpl.find(Constants.GET_CONCENTRATIONS));
	}
	
	public void closeDialog()
	{
		showStudnt = Boolean.FALSE;
	}
	
	public void saveStudent() {
		student.setUpdatedBy(getLoggedUserId());		
		student.setUpdatedOn(new Date());
		student.setActive(Boolean.TRUE);
		student.setCreatedBy(getLoggedUserId());
		student.setCreatedOn(new Date());
		baseDaoImpl.saveObject(student);
		showStudnt = Boolean.FALSE;
		if (stdConcentration.getConcentration() != null 
				&& stdConcentration.getConcentration().getConcentrationId() != null) {
			Concentration con = (Concentration) baseDaoImpl.getObject(Concentration.class, 
					stdConcentration.getConcentration().getConcentrationId());
			Student std = (Student) baseDaoImpl.getObject(Student.class,student.getStudentId());
			stdConcentration.setStudent(std);
			stdConcentration.setConcentration(con);
			stdConcentration.setUpdatedBy(getLoggedUserId());
			stdConcentration.setUpdatedOn(getCurrentDate());
			stdConcentration.setCreatedBy(getLoggedUserId());
			stdConcentration.setCreatedOn(getCurrentDate());
			baseDaoImpl.saveObject(stdConcentration);
		}
		loadStudents();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public boolean isShowStudnt() {
		return showStudnt;
	}

	public void setShowStudnt(boolean showStudnt) {
		this.showStudnt = showStudnt;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public List<SelectItem> getConcentrations() {
		return concentrations;
	}

	public void setConcentrations(List<SelectItem> concentrations) {
		this.concentrations = concentrations;
	}

	public StudentConcentration getStdConcentration() {
		return stdConcentration;
	}

	public void setStdConcentration(StudentConcentration stdConcentration) {
		this.stdConcentration = stdConcentration;
	}
	
	
}
