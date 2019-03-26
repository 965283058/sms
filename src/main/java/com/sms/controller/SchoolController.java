package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.*;
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

import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.ISchoolService;
import com.sms.vo.SchoolVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class SchoolController extends ControllerBase {
	@Autowired
	ISchoolService iSchoolService;

	public SchoolController() {
		logger = Logger.getLogger(SchoolController.class);
	}

	@RequestMapping(value = "/Schools", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getSchoolsInPage(HttpServletRequest request) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iSchoolService.getSchoolsByPaginationData(loggedInUser, paginationData);
		};

		return GetPaginationData(request);
	}
	
	@RequestMapping(value = "/Schools", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getSchools(HttpServletRequest request) {
		try {
		    User loggedInUser = getLoggedInUser(request);
		    return iSchoolService.getSchools(loggedInUser);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/School", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createSchool(HttpServletRequest request, @RequestBody String schoolJsonString) {
		try {
			if (StringUtils.isBlank(schoolJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject schoolJsonObject = JSONObject.fromObject(schoolJsonString.trim());

			SchoolVO schoolVO = new SchoolVO(schoolJsonObject);

			logger.debug("Creating new school '" + schoolVO.getName() + "' ...");
			return iSchoolService.createSchool(schoolVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/School/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getSchool(HttpServletRequest request, @PathVariable Integer id) {
		try {
			logger.debug("Get school '" + id + "' ...");
			return iSchoolService.getSchool(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/School/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateSchool(HttpServletRequest request, @PathVariable Integer id, @RequestBody String schoolJsonString) {
		try {
			logger.debug("Update school '" + id + "' ...");

			if (StringUtils.isBlank(schoolJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject schoolJsonObject = JSONObject.fromObject(schoolJsonString.trim());

			SchoolVO schoolVO = new SchoolVO(schoolJsonObject);

			return iSchoolService.updateSchool(id, schoolVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
