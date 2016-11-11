package com.mydomain.sps.web;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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
	private List<Object[]> notCompltdstudents = new ArrayList<Object[]>();
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
	private boolean showExamResult;
	
	private int studentId;
	private Integer advisorId;
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
				.append("<html><body>Dear ")
				.append(object[1].toString() + " "+ object[2])
				.append(",<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ")
				.append("We are glad you have been accepted into the MBA Program - <b> '"
						+ object[9] + " '</b>.")
				.append("<br/>")
				.append("Concentration at the University of Central Missouri and are looking forward to your")
				.append("<br/>")
				.append("participation in the program")
				.append("<br/> <br/>")
				.append("Your next step is to access the web site linked below and answer questions which will")
				.append("<br/>")
				.append("enable your advisor to better serve you.  This information is shared between and MBA ")
				.append("<br/>")
				.append("Program Director and the Program Advisors.  It is not made available to outside parties.")
				.append("<br/>")
				.append("Even if your plans change and you decide not to attend, we ask that you indicate such at the web site.")
				.append("<br/>")
				.append("</p><p>")
				.append("URL : <a href=http://localhost:8080/sps/> http://localhost:8080/sps/</a>")
				.append("<br/>")
				.append("User Name :" + object[1].toString())
				.append("<br/>")
				.append("Password : sps@123")
				.append("<br/> <br/>")
				.append("Questions about the questionnaire or the MBA program should be directed to Dr. Kerry Henson, MBA Program Director at")
				.append("<br/>")
				.append(" <a href=MBA@UCMO.EDU> MBA@UCMO.EDU</a>")
				.append("<br/> <br/>")
				.append("Please do not reply to this message")
				.append("<br/> <br/>")
				.append("Thanks!")
				.append("<br/> <br/>")
				.append("Kerry Henson, PhD")
				.append("<br/>")
				.append("Assistant Dean")
				.append("<br/>")
				.append("MBA Program Director")
				.append("<br/>")
				.append("Harmon College of Business and Professional Studies")
				.append("<br/>")
				.append("University of Central Missouri")
				.append("<br/>")
				.append("Dockery 300C")
				.append("<br/>")
				.append("Warrensburg, Missouri  64093")
				.append("<br/>")
				.append("660-422-2705")
				.append("<br/>")
				.append("mba@ucmo.edu");	

		/*emailMessage = emailMessage
				.append("</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>This is an autogenerated message. Kindly do not reply.</p>")
				.append("</br>Regards,</br> SPS Team </body></html>");*/
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
		if (count == 0) {
			User user = new User();
			user.setRole("Student");
			user.setEmailId(object[5].toString());
			user.setLoginId(object[1].toString());
			String pwd = encrypt("sps@123", "Bar12345Bar12345");
			user.setPassword(pwd);
			user.setPwdChanged(0);
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
		showMailtemp = Boolean.FALSE;
	}
	
	private String encrypt(String plainText, String secretKey) {
		byte[] encrypted = null;
    	try {			
	         String text = plainText;	
	         String key = secretKey; // 128 bit key 
	
	         // Create key and cipher	
	         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");	
	         Cipher cipher = Cipher.getInstance("AES"); 
	
	         // encrypt the text	
	         cipher.init(Cipher.ENCRYPT_MODE, aesKey);	
	         encrypted = cipher.doFinal(text.getBytes());	
	         return new String(encrypted);	
	      } catch(Exception e) {	
	         e.printStackTrace();	
	      }
    	return new String(encrypted);
	}
	
	public void loadStudents() {
		searchKey = null;
		if (user.getRole().equals("Advisor")) {
			students = baseDaoImpl.find(Constants.GET_ADVISOR_STUDENTS, getLoggedUserId());
		} else {
			students = baseDaoImpl.find(Constants.GET_ALL_STUDENTS);
		}
		notCompltdstudents  = baseDaoImpl.find(Constants.INCOMPLETE);
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

	public Integer getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(Integer advisorId) {
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

	public List<Object[]> getNotCompltdstudents() {
		return notCompltdstudents;
	}

	public void setNotCompltdstudents(List<Object[]> notCompltdstudents) {
		this.notCompltdstudents = notCompltdstudents;
	}

	public boolean isShowExamResult() {
		return showExamResult;
	}

	public void setShowExamResult(boolean showExamResult) {
		this.showExamResult = showExamResult;
	}
	
	
}
