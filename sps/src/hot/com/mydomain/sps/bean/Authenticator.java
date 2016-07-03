package com.mydomain.sps.bean;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
