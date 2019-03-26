package com.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.helper.SHAHelper;
import com.sms.model.User;
import com.sms.vo.LogonResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class LogonService extends ServiceBase implements ILogonService {
	@Override
	public CommandResult logon(String userName, String password, String sessionId) {
		if (StringUtils.isBlank(userName)) {
			return new CommandResult(CommandCode.EMPTY_USER_LOGNAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_USER_LOGNAME));
		}

		if (StringUtils.isBlank(password)) {
			return new CommandResult(CommandCode.EMPTY_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_PASSWORD));
		}

		// Check if user exists
		User user = userMapper.selectByLogName(userName);
		if (user == null) {
			return new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
		}

		// Hash the password
		String hashedPassword;
		try {
			hashedPassword = SHAHelper.generateHashedString(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
				
		if (!StringUtils.equals(user.getLogPassword(), hashedPassword)) {
			return new CommandResult(CommandCode.INCORRECT_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INCORRECT_PASSWORD));
		}

		// Update session list
		sessionManager.insertSession(sessionId, user.getId());
		
		// NOT to send password
		user.setLogPassword(null);
		Date lastLogonDate = getLastLogonDate(user.getId());
		LogonResult logonResult = new LogonResult(user, lastLogonDate, "/jsp/main.jsp");
		
		// Login success, create session key
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), logonResult);
	}

	@Override
	public CommandResult logout(Integer userId) {
		sessionManager.removeSession(userId);
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), "/");
	}
	
	private Date getLastLogonDate(Integer userId) {
		return null;
	}
}
