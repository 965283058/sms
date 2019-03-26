package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.ControllerBase;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import com.sms.common.CommandCode;
import com.sms.common.CommandResult;
import com.sms.model.User;
import com.sms.service.ILogonService;

@Api
@Controller
public class LogonController extends ControllerBase {
	private static Logger logger = Logger.getLogger(LogonController.class);

	@Autowired
	private ILogonService iLogonService;

	@RequestMapping(value = "/Logon", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult userLogon(HttpServletRequest request) {
		try {
			String userName = request.getParameter("user_name");
			String password = request.getParameter("password");
			logger.debug("User '" + userName + "' is trying to logon.");

			return iLogonService.logon(userName, password, request.getSession().getId());
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult userLogout(HttpServletRequest request) {
		try {
			User loggedInUser = getLoggedInUser(request);
			if (loggedInUser != null) {
				logger.debug("User '" + loggedInUser.getLogName() + "' is logging out.");
			}
			
			// Invalidate the session
			request.getSession().invalidate();

			return iLogonService.logout(loggedInUser.getId());
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
