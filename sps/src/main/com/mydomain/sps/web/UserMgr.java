package com.mydomain.sps.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.mydomain.sps.entities.User;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;

/**
 * @author Harshal.Kalavadiya
 */
@Name("userMgr")
@Scope(ScopeType.PAGE)
public class UserMgr extends BaseMgr {
	
	 private static final long serialVersionUID = 1L;
	 private transient static final Logger LOG = Logger.getLogger(UserMgr.class);
	 @In BaseDao baseDaoImpl;	
	 private User user;
	 private List<User> userList = new ArrayList<User>();
	 private String searchKey;
	 private boolean showUserPopup;
	 
	public void loadUsers() {
		searchKey = null;
		userList = baseDaoImpl.find(Constants.GET_ALL_USERS);
	}

	public void searchUsers() {
		userList = baseDaoImpl.find(Constants.GET_SEARCHED_USER, searchKey + "%");
	}

	public void showDialog() {
		user = new User();
		showUserPopup = Boolean.TRUE;
	}
	
	public void addUser() {
		user.setCreatedBy(getLoggedUserId());
		user.setUpdatedBy(getLoggedUserId());
		user.setCreatedOn(new Date());
		user.setUpdatedOn(new Date());
		user.setActive(Boolean.TRUE);
		baseDaoImpl.saveObject(user);
		loadUsers();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public boolean isShowUserPopup() {
		return showUserPopup;
	}
	public void setShowUserPopup(boolean showUserPopup) {
		this.showUserPopup = showUserPopup;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
}
