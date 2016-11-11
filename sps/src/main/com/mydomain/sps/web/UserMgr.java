package com.mydomain.sps.web;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
	 private User user = new User();
	 private List<User> userList = new ArrayList<User>();
	 private String searchKey;
	 private boolean showUserPopup;
	 private boolean editUser;
	 
	public void loadUsers() {
		searchKey = null;
		userList = baseDaoImpl.find(Constants.GET_ALL_USERS);
	}

	public void searchUsers() {
		userList = baseDaoImpl.find(Constants.GET_SEARCHED_USER, searchKey + "%");
		editUser = Boolean.FALSE;
	}

	public void showDialog() {
		user = new User();
		showUserPopup = Boolean.TRUE;
		editUser = Boolean.FALSE;
	}
	
	public void closeDialog()
	{
		showUserPopup = Boolean.FALSE;
		editUser = Boolean.FALSE;
	}
	
	public void showEditDialog(User userObj)
	{
		user = userObj;
		showUserPopup = Boolean.TRUE;
		editUser = Boolean.TRUE;
	}
	
	public void addUser() {
		user.setUpdatedBy(getLoggedUserId());		
		user.setUpdatedOn(new Date());
		if(!editUser)
		{
			
			String pwd = encrypt("sps@123", "Bar12345Bar12345");
			user.setPassword(pwd);
			user.setActive(Boolean.TRUE);
			user.setCreatedBy(getLoggedUserId());
			user.setCreatedOn(new Date());
			baseDaoImpl.saveObject(user);
		}
		else{
			baseDaoImpl.update(user);
		}		
		showUserPopup = Boolean.FALSE;
		editUser = Boolean.FALSE;
		loadUsers();
		user = new User();
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

	public boolean isEditUser() {
		return editUser;
	}

	public void setEditUser(boolean editUser) {
		this.editUser = editUser;
	}
	 
}
