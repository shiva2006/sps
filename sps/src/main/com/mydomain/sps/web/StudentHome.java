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
	

	public void loadQue(){
		queList = baseDaoImpl.find(Constants.GET_QUESTIONS);
	}

	public void save() {			
		Concentration stConObj = (Concentration) baseDaoImpl.getObject(Concentration.class,5);
		for(Questions obj : queList) {
			StudentExam stExamobj = new StudentExam();
			stExamobj.setStdAnswer(obj.getTempAns());
			Questions que = (Questions) baseDaoImpl.getObject(Questions.class,obj.getQuestionId());
			stExamobj.setQuestion(que);
			stExamobj.setStudentConcentration(stConObj);				
			stExamobj.setActive(true);
			stExamobj.setCreatedBy(getLoggedUserId());
			stExamobj.setCreatedOn(new Date());
			stExamobj.setUpdatedBy(getLoggedUserId());
			stExamobj.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(stExamobj);
		}			
	}
	
	public List<Questions> getQueList() {
		return queList;
	}

	public void setQueList(List<Questions> queList) {
		this.queList = queList;
	}
}
