package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.mydomain.sps.entities.Concentration;
import com.mydomain.sps.entities.Questions;
import com.mydomain.sps.entities.StudentConcentration;
import com.mydomain.sps.entities.StudentExam;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;


/**
 * @author Harshal.Kalavadiya
 */
@Name("studentHome")
@Scope(ScopeType.PAGE)
public class StudentHome extends BaseMgr{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@In BaseDao baseDaoImpl;
	private List<Questions> queList = new ArrayList<Questions>();
	int studentId;
	int constId;
	int stdCnstId;
	String examStatus;
	String userName;

	public void loadQue() {
		if (userName == null )
			userName = getLoggedUserName();
		Object[] object = (Object[]) baseDaoImpl.find(Constants.GET_STD_CON, userName).get(0);
		this.studentId = (int) object[0];
		this.constId = (int) object[1]; 
		this.stdCnstId = (int) object[2];
		this.examStatus = (String) object[3];
		queList = baseDaoImpl.find(Constants.GET_QUESTIONS, constId);
		if("Completed".equals(examStatus)) {
			List<Object[]> ansList = baseDaoImpl.find(Constants.GET_ANSWERS, constId);
			for (Object[] obj : ansList) {
				Integer ques = (Integer) obj[0];
				for (Questions que : queList) {
					if (ques.equals(que.getQuestionId())) {
						que.setTempAns((String) obj[1]); 
					}
				}
			}
		}
	}

	public void save() {			
		Concentration stConObj = (Concentration) baseDaoImpl.getObject(Concentration.class, constId);
		for(Questions obj : queList) {
			StudentExam stExamobj = new StudentExam();
			stExamobj.setStdAnswer(obj.getTempAns());
			Questions que = (Questions) baseDaoImpl.getObject(Questions.class, obj.getQuestionId());
			stExamobj.setQuestion(que);
			stExamobj.setStudentConcentration(stConObj);				
			stExamobj.setActive(true);
			stExamobj.setCreatedBy(getLoggedUserId());
			stExamobj.setCreatedOn(new Date());
			stExamobj.setUpdatedBy(getLoggedUserId());
			stExamobj.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(stExamobj);
		}
		addMessage("Exam completed successfully, Thank you..!");
		StudentConcentration conobj = (StudentConcentration) 
				baseDaoImpl.getObject(StudentConcentration.class, stdCnstId);
		conobj.setExamStatus("Completed");
		baseDaoImpl.update(conobj);
		this.examStatus = "Completed";
	}
	
	public List<Questions> getQueList() {
		return queList;
	}

	public void setQueList(List<Questions> queList) {
		this.queList = queList;
	}

	public BaseDao getBaseDaoImpl() {
		return baseDaoImpl;
	}

	public void setBaseDaoImpl(BaseDao baseDaoImpl) {
		this.baseDaoImpl = baseDaoImpl;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getConstId() {
		return constId;
	}

	public void setConstId(int constId) {
		this.constId = constId;
	}

	public int getStdCnstId() {
		return stdCnstId;
	}

	public void setStdCnstId(int stdCnstId) {
		this.stdCnstId = stdCnstId;
	}

	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
