package com.mydomain.sps.web;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.mydomain.sps.entities.User;
import com.mydomain.sps.service.BaseDao;

@Name("passwordValidationBean")
public class PasswordValidationBean extends BaseMgr {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//  @Size(min = 5, max = 15, message = "Password length must be between {min} and {max} characters.")
    private String password = "";
    private String confirm = "";
    @In BaseDao baseDaoImpl;
 
 
    public void storeNewPassword() throws IOException {
    	System.out.println("--- ---");
    	if (null ==  password && null == confirm) {
    		addError("Password mismatch, kindly re-enter.");
    	}
    	else if(null !=  password && null != confirm && !password.equalsIgnoreCase(confirm)) {
    		addError("Password mismatch, kindly re-enter.");
    		this.password = "";
    			this.confirm = "";
    	} else {
    		User usr = (User)baseDaoImpl.loadObject(User.class, getLoggedUserId());
    		String pwd = encrypt(confirm, "Bar12345Bar12345");    		
    		usr.setPassword(pwd);
    		usr.setPwdChanged(1);
    		baseDaoImpl.update(usr);
    		if (usr.getRole().equals("Student")) {
	    		FacesContext.getCurrentInstance().getExternalContext().redirect("/sps/studentHome.seam");
	    		addMessage("Successfully changed!");
    		} else {
    			FacesContext.getCurrentInstance().getExternalContext()
	            .redirect("/sps/home.seam");
    		}
    	}
    }
    
    public String logOut() throws Throwable {
        org.jboss.seam.web.Session.instance().invalidate();
        return "/login.xhtml?faces-redirect=true";

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
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
 
    public String getPassword() {
        return password;
    }
 
    public String getConfirm() {
        return confirm;
    }
 
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
