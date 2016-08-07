package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.mydomain.sps.entities.Student;
import com.mydomain.sps.service.BaseDao;

@Name("studentMgr") @Scope(ScopeType.PAGE)
public class StudentMgr extends BaseMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@In BaseDao baseDaoImpl;
	private Student student;	
	private List<Student> students = new ArrayList<Student>();
	private boolean showStudnt;
	
	public void loadStudents() {
		
	}
	
	public void addStudent() {
		
	}
	
	public void saveStudent() {
		
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
	
}
