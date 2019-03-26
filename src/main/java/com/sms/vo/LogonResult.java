package com.sms.vo;

import java.util.Date;

import com.sms.model.User;

public class LogonResult {
	private User user;
	private Date lastLogonDate;
	private String redirectUrl;
	
	public User getUser()
	{
		return user;
	}
	
	public Date getLastLogonDate() {
		return lastLogonDate;
	}
	
	public String getRedirectUrl()
	{
		return redirectUrl;
	}
	
	public LogonResult(User user, Date lastLogonDate, String redirectUrl)
	{
		this.user = user;
		this.lastLogonDate = lastLogonDate;
		this.redirectUrl = redirectUrl;
	}
}
