package com.mydomain.sps.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="password_reset_requests")
public class PasswordReset extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REQUEST_ID")
	private Integer requestId;
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private User userId = new User();
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="PROCESSED_ON") @Temporal(TemporalType.TIMESTAMP)
	private Date proceedOn;
	
	@Column(name="PROCESSED_BY")
	private int proceededBy;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	public PasswordReset() {
		
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getProceedOn() {
		return proceedOn;
	}

	public void setProceedOn(Date proceedOn) {
		this.proceedOn = proceedOn;
	}

	public int getProceededBy() {
		return proceededBy;
	}

	public void setProceededBy(int proceededBy) {
		this.proceededBy = proceededBy;
	}
	
}
