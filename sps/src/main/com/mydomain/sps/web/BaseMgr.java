package com.mydomain.sps.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;

import com.mydomain.sps.entities.User;


/**
 * @author Shiv.Gangadhar base class for all manager classes.
 */
@Name("baseMgr")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class BaseMgr implements Serializable
{

    /**
	 * 
	 */
    

    private static final long serialVersionUID = 1L;
    private int workOrderId;
    private int dispensingNoteId;

    @In
    protected User user;

    /**
     * adds Info messages to FacesContext.
     */
    public void addMessage(String message) {
        StatusMessages.instance().add(Severity.INFO, message);
    }

    /**
     * adds error messages to FacesContext.
     */
    public void addError(String message) {
        StatusMessages.instance().add(Severity.ERROR, message);
    }

    /**
     * adds ws messages to FacesContext.
     */
    public void addWarn(String message) {
        StatusMessages.instance().add(Severity.WARN, message);
    }

    /**
     * @param dateFormat
     *            formatted date like 'ddmmyyyy'.
     * @return formatted date.
     */
    public String getFormattedDate(String dateFormat) {
        SimpleDateFormat simpDate = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return simpDate.format(new Date());
    }

    public String getFormattedDate(String dateFormat, Date dateToFormat) {
        SimpleDateFormat simpDate = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return simpDate.format(dateToFormat);
    }

    /**
     * This is usefull for dropdown menus, radio buttons and chekboxs.
     * 
     * @param List
     *            takes list of Objects[] of type <Integer and String>.
     * @return List with selectedItems.
     */
    protected List<SelectItem> populateData(List<?> list) {
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        selectItemList.add(new SelectItem("", "-- Select --"));
        Object[] object = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            object = (Object[]) list.get(i);
            selectItemList.add(new SelectItem(object[0], (String) object[1]));
        }
        return selectItemList;
    }

    /**
     * This is usefull for dropdown menus, radio buttons and chekboxs .
     * 
     * @param List
     *            takes list of Objects[] of type <String and String>.
     * @return List with selectedItems.
     */
    protected List<SelectItem> populate(List<?> list) {
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        selectItemList.add(new SelectItem("Select", "-- Select --"));
        int size = list.size();
        for (int i = 0; i < size; i++) {
            selectItemList.add(new SelectItem(list.get(i), list.get(i).toString()));
        }
        return selectItemList;
    }

    /**
     * @param name
     *            of the parameter to retrieve from request object.
     * @return requested parameter.
     */
    public String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public int getLoggedUserId() {
        return user.getUserId() == null ? 0 : user.getUserId();
    }

    public String getLoggedUserName() {
        return user.getUserName();
    }

    /**
     * @return request object from FacesContext.
     */
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
    }

    /**
     * Servlet API Convenience method
     * 
     * @return
     */
    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();
    }

    /**
     * @return selected workOrderId
     */
    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    /**
     * @return selected dispensingNoteId
     */
    public int getDispensingNoteId() {
        return dispensingNoteId;
    }

    public void setDispensingNoteId(int dispensingNoteId) {
        this.dispensingNoteId = dispensingNoteId;
    }

    public String getUserRoleId() {
        return user.getRole();
    }

    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    protected static <T> T convertType(Object obj, Class<T> type) {
        T data = null;
        if (null != obj) {
            data = type.cast(obj);
        }
        return data;
    }
}
