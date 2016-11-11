package com.mydomain.sps.bean;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.mydomain.sps.entities.User;
import com.mydomain.sps.service.BaseDao;
import com.mydomain.sps.service.Constants;

@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    
    @In BaseDao baseDaoImpl;
    
    @In @Out User user;

    public boolean authenticate()
    {
        boolean authFlag = Boolean.FALSE;
    	if (credentials.getUsername().isEmpty()
				|| credentials.getPassword().isEmpty()) 
    	{
			StatusMessages.instance().add(Severity.ERROR,"Invalid credentials, Please try again");
			authFlag = Boolean.FALSE;
		}
    	else
    	{
    		String pwd = decrypt(credentials.getPassword(), "Bar12345Bar12345");
        	List<User> result = baseDaoImpl.find(Constants.GET_USERQ, credentials.getUsername(), pwd);
        	if (null == result || result.isEmpty()) 
        	{
        		StatusMessages.instance().add(Severity.ERROR,"Invalid credentials, Please try again");
        		authFlag=Boolean.FALSE;
        	}
        	else
        	{
        		user = result.get(0);
        		authFlag=Boolean.TRUE;
        		if(user.getRole().equals("Student") && user.getPwdChanged() == 0) {
        			
                    try {
                        FacesContext.getCurrentInstance().getExternalContext()
                                .redirect("/sps/changepwd.seam");
                    }
                    catch (IOException e) {
                       
                    }
        		}
        		
        	}
        }
        return authFlag;
    }

    private String decrypt(String decryptedText, String secretKey) {
    	byte[] encrypted = null;
    	try {			
	         String text = decryptedText;	
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
}
