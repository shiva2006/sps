package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConcentrationBean concentrationBean = (ConcentrationBean) Component
			.getInstance("concentrationBean");
	@In
	BaseDao baseDaoImpl;

	@SuppressWarnings("unchecked")
	public void loadConcentration() {
		List<Object[]> result = baseDaoImpl
				.find(Constants.GET_ALL_CONCENTRATION);
		List<ConcentrationBean> conList = new ArrayList<ConcentrationBean>();
		for (Object[] obj : result) {
			conList.add(new ConcentrationBean((Integer) obj[0],
					(String) obj[1], (String) obj[2], (int) obj[3],
					(String) obj[4]));
		}
		concentrationBean.setConList(conList);
		concentrationBean.setSearchKey(null);
	}

	public void editConcentration(ConcentrationBean bean) {
		this.resetValues();
		concentrationBean.setVisibleFalg(Boolean.TRUE);
		concentrationBean.setEditFlag(Boolean.TRUE);
		concentrationBean.setEditObject(bean);
		concentrationBean.setConcentrationName(concentrationBean
				.getEditObject().getConcentrationName());
		concentrationBean.setDegreeCode(concentrationBean.getEditObject()
				.getDegreeCode());
		if ("ACTIVE".equalsIgnoreCase(concentrationBean.getEditObject()
				.getStatus())) {
			concentrationBean.setStatus("ACTIVE");
		} else {
			concentrationBean.setStatus("INACTIVE");
		}
		this.getAdvisorsList();
		concentrationBean.setSelectedConName(concentrationBean.getEditObject()
				.getConcentrationName());

	}

	public void saveEditedConcentration() {
		Integer concentrationID = 0;
		List<?> conID = baseDaoImpl.find(Constants.GET_CONCENTRATION_ID,
				concentrationBean.getSelectedConName());
		for (Object obj : conID) {
			concentrationID = (Integer) obj;
		}
		Concentration con = (Concentration) baseDaoImpl.loadObject(
				Concentration.class, concentrationID);
		con.setConcentrationName(concentrationBean.getConcentrationName());
		con.setDegreeCode(concentrationBean.getDegreeCode());
		if ("ACTIVE".equalsIgnoreCase(concentrationBean.getStatus())) {
			con.setActive(Boolean.TRUE);
		} else {
			con.setActive(Boolean.FALSE);
		}
		con.setUpdatedBy(getLoggedUserId());
		con.setUpdatedOn(new Date());
		baseDaoImpl.update(con);

		Integer serial = 0;
		List<?> serialNo = baseDaoImpl.find(
				Constants.GET_CONCENTRATION_SERIALNO, con.getConcentrationId());
		for (Object obj : serialNo) {
			serial = (Integer) obj;
		}
		if (serial == 0) {
			ConcentrationFaculty conFac = new ConcentrationFaculty();
			conFac.setConcentrationId(con);
			User user = (User) baseDaoImpl.loadObject(User.class,
					concentrationBean.getConfacultyId());
			conFac.setFaculty(user);
			conFac.setCreatedBy(getLoggedUserId());
			conFac.setCreatedOn(new Date());
			conFac.setUpdatedBy(getLoggedUserId());
			conFac.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(conFac);
		} else {
			ConcentrationFaculty conFac = (ConcentrationFaculty) baseDaoImpl
					.loadObject(ConcentrationFaculty.class, serial);
			User user = (User) baseDaoImpl.loadObject(User.class,
					concentrationBean.getConfacultyId());
			conFac.setFaculty(user);
			conFac.setUpdatedBy(getLoggedUserId());
			conFac.setUpdatedOn(new Date());
			baseDaoImpl.update(conFac);
		}

		this.loadConcentration();
	}

	public void addConcentration() {
		this.resetValues();
		concentrationBean.setVisibleFalg(Boolean.TRUE);
		concentrationBean.setAddFlag(Boolean.TRUE);
		this.getAdvisorsList();

	}

	private void getAdvisorsList() {
		List<?> list = baseDaoImpl.find(Constants.GET_ALL_ADVISORS);
		concentrationBean.setAdvisorUsers(new ArrayList<SelectItem>());
		for (Object obj : list) {
			Object[] objj = (Object[]) obj;
			SelectItem selectItem = new SelectItem();
			String label = objj[0].toString() + "--" + objj[1].toString();
			selectItem.setValue(objj[0]);
			selectItem.setLabel(label);
			concentrationBean.getAdvisorUsers().add(selectItem);
		}

	}

	public void resetValues() {
		concentrationBean.setConcentrationName(null);
		concentrationBean.setDegreeCode(null);
		concentrationBean.setStatus(null);
		concentrationBean.setConfacultyId(null);
		concentrationBean.setVisibleFalg(Boolean.FALSE);
		concentrationBean.setAddFlag(Boolean.FALSE);
		concentrationBean.setEditFlag(Boolean.FALSE);
	}

	public void saveConcentration() {
		if (this.vaildate()) {
			Concentration con = new Concentration();
			con.setConcentrationName(concentrationBean.getConcentrationName());
			con.setDegreeCode(concentrationBean.getDegreeCode());
			if ("ACTIVE".equalsIgnoreCase(concentrationBean.getStatus())) {
				con.setActive(Boolean.TRUE);
			} else {
				con.setActive(Boolean.FALSE);
			}
			con.setCreatedBy(getLoggedUserId());
			con.setUpdatedBy(getLoggedUserId());
			con.setUpdatedOn(new Date());
			con.setCreatedOn(new Date());
			baseDaoImpl.saveObject(con);
			User user = (User) baseDaoImpl.loadObject(User.class,
					concentrationBean.getConfacultyId());
			ConcentrationFaculty conFac = new ConcentrationFaculty();
			conFac.setConcentrationId(con);
			conFac.setFaculty(user);
			conFac.setCreatedBy(getLoggedUserId());
			conFac.setCreatedOn(new Date());
			conFac.setUpdatedBy(getLoggedUserId());
			conFac.setUpdatedOn(new Date());
			baseDaoImpl.saveObject(conFac);
			this.resetValues();
			this.loadConcentration();
		}

	}

	private boolean vaildate() {
		boolean flag = Boolean.FALSE;
		if (!concentrationBean.getConcentrationName().trim().isEmpty()
				|| null != concentrationBean.getConcentrationName()
				|| !concentrationBean.getDegreeCode().trim().isEmpty()
				|| null != concentrationBean.getDegreeCode()
				|| !concentrationBean.getStatus().trim().isEmpty()
				|| null != concentrationBean.getStatus()
				|| null != concentrationBean.getConfacultyId()) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public void search() {
		String searchKey = concentrationBean.getSearchKey();
		if (!searchKey.trim().isEmpty() || null != searchKey) {
			concentrationBean.setConList(baseDaoImpl.find(
					Constants.GET_SEARCH_CONCENTRATION, searchKey + "%"));
		}

	}

}
