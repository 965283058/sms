package com.sms.service;

import com.sms.common.CommandResult;

public interface ILogonService 
{
	CommandResult logon(String userName, String password, String sessionId);
	CommandResult logout(Integer userId);
}
