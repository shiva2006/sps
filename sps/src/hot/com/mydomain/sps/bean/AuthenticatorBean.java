package com.mydomain.sps.bean;

import java.util.List;

import javax.ejb.Stateless;

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
        	List<User> result = baseDaoImpl.find(Constants.GET_USERQ, credentials.getUsername(), credentials.getPassword());
        	if (null == result || result.isEmpty()) 
        	{
        		StatusMessages.instance().add(Severity.ERROR,"Invalid credentials, Please try again");
        		authFlag=Boolean.FALSE;
        	}
        	else
        	{
        		user = result.get(0);
        		authFlag=Boolean.TRUE;
        	}
        }
        return authFlag;
    }

}
