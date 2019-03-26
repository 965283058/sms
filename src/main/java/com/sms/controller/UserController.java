package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.ControllerBase;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.model.User;
import com.sms.service.IUserService;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class UserController extends ControllerBase {
	@Autowired
	private IUserService iUserService;

	public UserController() {
		logger = Logger.getLogger(UserController.class);
	}
	
	@RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getUser(HttpServletRequest request, @PathVariable Integer id) {
		try {
			logger.debug("Get user '" + id + "' ...");
			return iUserService.getUser(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/User/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateUser(HttpServletRequest request, @PathVariable Integer id, @RequestBody String userJsonString) {
		try {
			logger.debug("Update user '" + id + "' ...");

			if (StringUtils.isBlank(userJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject userJsonObject = JSONObject.fromObject(userJsonString.trim());

			UserVO userVO = new UserVO(userJsonObject);

			return iUserService.updateUser(id, userVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/User/{id}/Password", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updatePassword(HttpServletRequest request, @PathVariable Integer id, @RequestBody String userJsonString) {
		try {
			logger.debug("Update password of user '" + id + "' ...");

			if (StringUtils.isBlank(userJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject userJsonObject = JSONObject.fromObject(userJsonString.trim());
			
			// Check if user's id is same as logged in user's id 
			User loggedInUser = getLoggedInUser(request);
			if (id != loggedInUser.getId()) {
				return new CommandResult(CommandCode.NO_ACCESS_PERMISSION.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.NO_ACCESS_PERMISSION));
			}
			
			// Check passwords
			String oldPassword = userJsonObject.getString("old_password");
			String newPassword = userJsonObject.getString("new_password");
			
			return iUserService.updatePassword(id, oldPassword, newPassword);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
