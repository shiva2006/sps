package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.model.SelectItem;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.mydomain.sps.entities.Concentration;
import com.mydomain.sps.entities.ReviewNotes;
import com.mydomain.sps.entities.Student;
import com.mydomain.sps.entities.StudentConcentration;
import com.mydomain.sps.entities.User;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;

@Name("studentMgr") @Scope(ScopeType.PAGE)
public class StudentMgr extends BaseMgr {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@In BaseDao baseDaoImpl;
	@In	Session mailSession;
	
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
	private boolean showMailtemp;
	
	private int studentId;
	private int advisorId;
	private int stdCntId;
	private int selctdStd;
	
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
	
	public void createMailTemplate(int index) {
		this.selctdStd = index;
		Object[] object = students.get(index);
		mailTemplate = new String();
		sendTo = object[5].toString();
		StringBuilder emailMessage = new StringBuilder();
		emailMessage = emailMessage
				.append("<html><body>Hello ")
				.append(object[1].toString())
				.append(",<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ")
				.append("You have been choosen for course <b> '"
						+ object[9] + " '</b>.")
				.append("<br/>")
				.append("Kindly Login to following URL to complete exam. http://localhost:8080/sps/")
				.append("</p><p>")
				.append("<br/>")
				.append("User Name :" + object[1].toString())
				.append("<br/>")
				.append("Password : sps@123");	

		emailMessage = emailMessage
				.append("</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>This is an autogenerated message. Kindly do not reply.</p>")
				.append("</br>Regards,</br> SPS Team </body></html>");
		mailTemplate = emailMessage.toString();
		showMailtemp = Boolean.TRUE;
	}
	
	public void sendMail() throws MessagingException {
		Object[] object = students.get(selctdStd);
		MimeMessage message = new MimeMessage(mailSession);
		Address[] to = new InternetAddress[] { new InternetAddress(sendTo) };
		message.setRecipients(Message.RecipientType.TO, to);
		message.setSubject("Message From SPS");
		message.setContent(mailTemplate, "text/html");
		Transport.send(message);
		
		int count = baseDaoImpl.countforObjects(Constants.CHECK_USER, object[1].toString());
		if (count != 0) {
			User user = new User();
			user.setRole("STUDENT");
			user.setEmailId(object[5].toString());
			user.setLoginId(object[1].toString());
			user.setPassword("sps@123");
			user.setUserName(object[1].toString());
			user.setActive(Boolean.TRUE);
			user.setCreatedBy(getLoggedUserId());
			user.setCreatedOn(new Date());
			user.setUpdatedBy(getLoggedUserId());		
			user.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(user);
			showMailtemp = Boolean.FALSE;
			mailTemplate = "";
		}
		addMessage("E-mail send successfully.");
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

	public boolean isShowMailtemp() {
		return showMailtemp;
	}

	public void setShowMailtemp(boolean showMailtemp) {
		this.showMailtemp = showMailtemp;
	}

	public int getSelctdStd() {
		return selctdStd;
	}

	public void setSelctdStd(int selctdStd) {
		this.selctdStd = selctdStd;
	}
	
	
}
