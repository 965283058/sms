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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IDirectorService;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class DirectorController extends ControllerBase {
	@Autowired
	private IDirectorService iDirectorService;
	
	public DirectorController() {
		logger = Logger.getLogger(DirectorController.class);
	}
	
	@RequestMapping(value = "/Directors", params = { "SchoolId", "limit", "offset", "paginationData", "_" }, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getDirectorsBySchoolIdInPage(HttpServletRequest request, @RequestParam("SchoolId")Integer schoolId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iDirectorService.getDirectorsBySchoolIdAndPaginationData(loggedInUser, schoolId, paginationData);
		};

		return GetPaginationData(request);
	}

	@RequestMapping(value = "/Directors", params = {"SchoolId"}, method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getDirectors(HttpServletRequest request, @RequestParam("SchoolId")Integer schoolId) {
		try {
			User loggedInUser = getLoggedInUser(request);
			return iDirectorService.getDirectors(loggedInUser, schoolId);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@RequestMapping(value = "/Director", params = { "SchoolId" }, method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createDirector(HttpServletRequest request,  @RequestParam("SchoolId")Integer schoolId, @RequestBody String userJsonString) {
		try {
			if (StringUtils.isBlank(userJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}

			JSONObject userJsonObject = JSONObject.fromObject(userJsonString.trim());
			UserVO userVO = new UserVO(userJsonObject);
			logger.debug("Creating new director '" + userVO.getLogName() + "' ...");
			return iDirectorService.createDirector(schoolId, userVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
