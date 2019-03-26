package com.sms.common;

import java.util.UUID;

import com.sms.model.User;

public class LogonResultData 
{
	private User user;	
	private UUID sessionKey;
	private String redirectUrl;
	
	public User getUser()
	{
		return user;
	}
	
	public UUID getSessionKey()
	{
		return sessionKey;
	}
	
	public String getRedirectUrl()
	{
		return redirectUrl;
	}
	
	public LogonResultData(User user, UUID sessionKey, String redirectUrl)
	{
		this.user = user;
		this.sessionKey = sessionKey;
		this.redirectUrl = redirectUrl;
	}
}
