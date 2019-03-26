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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IBranchSchoolService;
import com.sms.vo.BranchSchoolVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class BranchSchoolController extends ControllerBase {
	@Autowired
	IBranchSchoolService iBranchSchoolService;

	public BranchSchoolController() {
		logger = Logger.getLogger(BranchSchoolController.class);
	}

	@RequestMapping(value = "/BranchSchools", params = {"SchoolId", "limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getBranchSchoolsInPage(HttpServletRequest request, @RequestParam("SchoolId")Integer schoolId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iBranchSchoolService.getBranchSchoolsBySchoolIdAndPaginationData(loggedInUser, schoolId, paginationData);
		};

		return GetPaginationData(request);
	}
	
	@RequestMapping(value = "/BranchSchools", params = {"SchoolId"}, method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getBranchSchoolsBySchoolId(HttpServletRequest request, @RequestParam("SchoolId")Integer schoolId) {
		try {
		    User loggedInUser = getLoggedInUser(request);
		    return iBranchSchoolService.getBranchSchoolsBySchoolId(loggedInUser, schoolId);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/BranchSchool", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createBranchSchool(HttpServletRequest request, @RequestBody String branchSchoolJsonString) {
		try {
			if (StringUtils.isBlank(branchSchoolJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject schoolJsonObject = JSONObject.fromObject(branchSchoolJsonString.trim());

			BranchSchoolVO branchSchoolVO = new BranchSchoolVO(schoolJsonObject);

			logger.debug("Creating new branch school '" + branchSchoolVO.getName() + "' ...");
			return iBranchSchoolService.createBranchSchool(branchSchoolVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/BranchSchool/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getBranchSchool(HttpServletRequest request, @PathVariable Integer id) {
		try {
			logger.debug("Get branch school '" + id + "' ...");
			return iBranchSchoolService.getBranchSchool(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/BranchSchool/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateBranchSchool(HttpServletRequest request, @PathVariable Integer id, @RequestBody String branchSchoolJsonString) {
		try {
			logger.debug("Update branch school '" + id + "' ...");

			if (StringUtils.isBlank(branchSchoolJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject branchSchoolJsonObject = JSONObject.fromObject(branchSchoolJsonString.trim());

			BranchSchoolVO branchSchoolVO = new BranchSchoolVO(branchSchoolJsonObject);

			return iBranchSchoolService.updateBranchSchool(id, branchSchoolVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
