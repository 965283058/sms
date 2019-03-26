package com.sms.service;

import org.apache.commons.lang.StringUtils;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.model.User;
import com.sms.vo.UserVO;

public class UserServiceBase extends ServiceBase {
    protected CommandResult validateUserOV(UserVO userVO) {
	if (StringUtils.isBlank(userVO.getLogName())) {
	    return new CommandResult(CommandCode.EMPTY_USER_LOGNAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_USER_LOGNAME));
	}

	if (StringUtils.isBlank(userVO.getPassword())) {
	    return new CommandResult(CommandCode.EMPTY_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_PASSWORD));
	}

	if (StringUtils.isBlank(userVO.getName())) {
	    return new CommandResult(CommandCode.EMPTY_USER_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_USER_NAME));
	}

	// Check if user exists
	User user = userMapper.selectByLogName(userVO.getLogName());
	if (user != null) {
	    return new CommandResult(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS));
	}

	// Check if existing user has same name
	user = userMapper.selectByName(userVO.getName());
	if (user != null) {
	    return new CommandResult(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS));
	}
	
	return null;
    }
}
