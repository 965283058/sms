package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.*;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IPresidentService;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class PresidentController extends ControllerBase {
	@Autowired
	private IPresidentService iPresidentService;
	
	public PresidentController() {
		logger = Logger.getLogger(PresidentController.class);
	}
	
	@RequestMapping(value = "/Presidents", params = { "limit", "offset", "paginationData", "_" }, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getPresidentsInPage(HttpServletRequest request) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iPresidentService.getPresidentsByPaginationData(loggedInUser, paginationData);
		};

		return GetPaginationData(request);
	}

	@RequestMapping(value = "/Presidents", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getPresidents(HttpServletRequest request) {
		try {
			User loggedInUser = getLoggedInUser(request);
			return iPresidentService.getPresidents(loggedInUser);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@RequestMapping(value = "/President", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createPresident(HttpServletRequest request, @RequestBody String userJsonString) {
		try {
			if (StringUtils.isBlank(userJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}

			JSONObject userJsonObject = JSONObject.fromObject(userJsonString.trim());
			UserVO userVO = new UserVO(userJsonObject);
			logger.debug("Creating new president '" + userVO.getLogName() + "' ...");
			return iPresidentService.createPresident(userVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
