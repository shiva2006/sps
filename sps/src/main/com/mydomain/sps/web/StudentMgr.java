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
import com.mydomain.sps.entities.ReviewNotes;
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
	private StudentConcentration stdConcentration = new StudentConcentration();
	
	private List<Object[]> students = new ArrayList<Object[]>();
	private List<String> reviewNotes;
	private List<SelectItem> concentrations;
	private List<SelectItem> advisorsList;
	
	private String searchKey;
	private String temp;
	private String sendTo;
	private String mailTemplate;
	private String reviewTxt;
	private String status;
	private String conName;
	
	private boolean showStudnt;
	private boolean reivewTxt;
	private boolean showAssignDlg;
	private boolean showUpdatebtn;
	
	private int studentId;
	private int advisorId;
	private int stdCntId;
	
	public void editStudent() {
		student = new Student();
		student = (Student) 
				baseDaoImpl.getObject(Student.class, studentId);
		stdConcentration = (StudentConcentration) 
				baseDaoImpl.getObject(StudentConcentration.class, stdCntId);
		if (null == stdConcentration) {
			stdConcentration = new StudentConcentration();
		}
		concentrations = populateData(baseDaoImpl.find(Constants.GET_CONCENTRATIONS));
		showUpdatebtn = Boolean.TRUE;
		showStudnt = Boolean.TRUE;
	}
	
	public void updateStudent() {
		
		if (stdConcentration.getConcentration() != null 
				&& stdConcentration.getConcentration().getConcentrationId() != null) {
			Concentration con = (Concentration) baseDaoImpl.getObject(Concentration.class, 
					stdConcentration.getConcentration().getConcentrationId());
			stdConcentration.setStudent(student);
			stdConcentration.setConcentration(con);
			stdConcentration.setUpdatedBy(getLoggedUserId());
			stdConcentration.setUpdatedOn(getCurrentDate());
			stdConcentration.setCreatedBy(getLoggedUserId());
			stdConcentration.setCreatedOn(getCurrentDate());
		}
		baseDaoImpl.update(student);
		baseDaoImpl.update(stdConcentration);
		showUpdatebtn = Boolean.FALSE;
		showStudnt = Boolean.FALSE;
		loadStudents();
	}
	
	public void populateAdvisorsList() {
		advisorsList = populateData(baseDaoImpl.find(Constants.GET_ADVISORS, conName));
		showAssignDlg = Boolean.TRUE;
	}
	
	public void updateAdvisor() {
		StudentConcentration std = (StudentConcentration) 
				baseDaoImpl.getObject(StudentConcentration.class, stdCntId);
		std.setAdvisor(advisorId);
		baseDaoImpl.update(std);
		showAssignDlg = Boolean.FALSE;
		loadStudents();
	}
	
	public void changeStatus() {
		StudentConcentration std = (StudentConcentration) 
				baseDaoImpl.getObject(StudentConcentration.class, stdCntId);
		std.setStatus(status);
		std.setStatusChangeOn(getCurrentDate());
		baseDaoImpl.update(std);
		showAssignDlg = Boolean.FALSE;
		loadStudents();
		setStatus("");
	}
	
	public void loadReviewDetails() {		
		reviewNotes = baseDaoImpl.find(Constants.GET_NOTES, studentId);
		reivewTxt = Boolean.TRUE;
		reviewTxt = "";
	}
	
	public void addReviewTxt() {
		Student std = (Student) baseDaoImpl.getObject(Student.class, studentId);
		ReviewNotes notes = new ReviewNotes();
		notes.setStudent(std);
		notes.setNotes(reviewTxt);
		notes.setReviewDate(getCurrentDate());
		baseDaoImpl.saveObject(notes);
		reivewTxt = Boolean.FALSE;
	}
	
	public void sendMail() {
		
	}
	
	public void loadStudents() {
		searchKey = null;
		students = baseDaoImpl.find(Constants.GET_ALL_STUDENTS);
	}
	
	public void addStudent() {
		showStudnt = Boolean.TRUE;
		student = new Student();
		showUpdatebtn = Boolean.FALSE;
		concentrations = populateData(baseDaoImpl.find(Constants.GET_CONCENTRATIONS));
	}
	
	public void closeDialog() {
		showStudnt = Boolean.FALSE;
		showUpdatebtn = Boolean.FALSE;
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

	public List<Object[]> getStudents() {
		return students;
	}

	public void setStudents(List<Object[]> students) {
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

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getMailTemplate() {
		return mailTemplate;
	}

	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	public List<String> getReviewNotes() {
		return reviewNotes;
	}

	public void setReviewNotes(List<String> reviewNotes) {
		this.reviewNotes = reviewNotes;
	}

	public boolean isReivewTxt() {
		return reivewTxt;
	}

	public void setReivewTxt(boolean reivewTxt) {
		this.reivewTxt = reivewTxt;
	}

	public String getReviewTxt() {
		return reviewTxt;
	}

	public void setReviewTxt(String reviewTxt) {
		this.reviewTxt = reviewTxt;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<SelectItem> getAdvisorsList() {
		return advisorsList;
	}

	public void setAdvisorsList(List<SelectItem> advisorsList) {
		this.advisorsList = advisorsList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public boolean isShowAssignDlg() {
		return showAssignDlg;
	}

	public void setShowAssignDlg(boolean showAssignDlg) {
		this.showAssignDlg = showAssignDlg;
	}

	public int getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}

	public int getStdCntId() {
		return stdCntId;
	}

	public void setStdCntId(int stdCntId) {
		this.stdCntId = stdCntId;
	}

	public boolean isShowUpdatebtn() {
		return showUpdatebtn;
	}

	public void setShowUpdatebtn(boolean showUpdatebtn) {
		this.showUpdatebtn = showUpdatebtn;
	}
	
	
}
