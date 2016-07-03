package com.mydomain.sps.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Column(name = "CREATED_BY", length = 10)
    protected int createdBy;
    
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn;
    
    @Column(name = "UPDATED_BY", length = 10)
    protected int updatedBy;
    
    @Column(name = "UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedOn;
    
    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }   
    
    public int getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public Date getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }  

}
