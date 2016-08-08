package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesContext;
import org.jboss.seam.remoting.RequestContext;

import com.mydomain.sps.bean.ConcentrationBean;
import com.mydomain.sps.entities.Concentration;
import com.mydomain.sps.entities.ConcentrationFaculty;
import com.mydomain.sps.entities.User;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;

/**
 * @author Shanthraj Gowda
 *
 */
@Name("concentrationMgr")
public class ConcentrationMgr extends BaseMgr {
	
	ConcentrationBean concentrationBean = (ConcentrationBean) Component.getInstance("concentrationBean");
	@In
	BaseDao baseDaoImpl;
 
	public void loadConcentration(){
		concentrationBean.setConList(baseDaoImpl.find(Constants.GET_ALL_CONCENTRATION));
	}
	
	public void editConcentration(ConcentrationBean bean){
		concentrationBean.setVisibleFalg(Boolean.TRUE);
		concentrationBean.setEditObject(bean);
		
		System.out.println("-------------------concentartionBean---------------"+concentrationBean.getEditObject());
		System.out.println("--------------"+concentrationBean.getEditObject().getConcentrationName());
		System.out.println("--------------"+concentrationBean.getEditObject().getConcentrationId());
		System.out.println("--------------"+concentrationBean.getEditObject().getFacultyName());
		System.out.println("--------------"+concentrationBean.getEditObject().getDegreeCode());
	}
	
	public void saveEditedConcentration(){
		
	}
	
	public void addConcentration(){
		concentrationBean.setVisibleFalg(Boolean.TRUE);
		List<?> list =  baseDaoImpl.find(Constants.GET_ALL_ADVISORS);
		concentrationBean.setAdvisorUsers(new ArrayList<SelectItem>());
		for(Object obj : list){
			Object[] objj = (Object[]) obj;
			SelectItem selectItem = new SelectItem();
			String label = objj[0].toString()+"--"+objj[1].toString();
			selectItem.setValue(objj[0]);
			selectItem.setLabel(label);
			concentrationBean.getAdvisorUsers().add(selectItem);
		}
		
	}
	
	public void resetValues(){
		concentrationBean.setConcentrationName(null);
		concentrationBean.setDegreeCode(null);
		concentrationBean.setStatus(null);
		concentrationBean.setConfacultyId(null);
		concentrationBean.setVisibleFalg(Boolean.FALSE);
		System.out.println("------------resetValues() is called---------------");
	}
	
	public void saveConcentration(){
		if(!concentrationBean.getConcentrationName().trim().isEmpty() || null!=concentrationBean.getConcentrationName() || !concentrationBean.getDegreeCode().trim().isEmpty()
			|| null!=concentrationBean.getDegreeCode() || !concentrationBean.getStatus().trim().isEmpty() || null!=concentrationBean.getStatus() || null!=concentrationBean.getConfacultyId()){
			Concentration con = new Concentration();
			con.setConcentrationName(concentrationBean.getConcentrationName());
			con.setDegreeCode(concentrationBean.getDegreeCode());
			if("ACTIVE".equalsIgnoreCase(concentrationBean.getStatus())){
				con.setActive(Boolean.TRUE);
			}else{
				con.setActive(Boolean.FALSE);
			}
			con.setCreatedBy(1);
			con.setUpdatedBy(1);
			con.setUpdatedOn(new Date());
			con.setCreatedOn(new Date());
			baseDaoImpl.saveObject(con);
			User user = (User) baseDaoImpl.loadObject(User.class, concentrationBean.getConfacultyId());
			ConcentrationFaculty conFac = new ConcentrationFaculty();
			conFac.setConcentrationId(con);
			conFac.setFaculty(user);
			conFac.setCreatedBy(1);
			conFac.setCreatedOn(new Date());
			conFac.setUpdatedBy(1);
			conFac.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(conFac);
			this.resetValues();
			this.loadConcentration();
		}else{
			addMessage("Can't be saved");
		}
		
	}

}
