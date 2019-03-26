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
import com.sms.service.IGroupLeaderService;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class GroupLeaderController extends ControllerBase {
	@Autowired
	private IGroupLeaderService iGroupLeaderService;
	
	public GroupLeaderController() {
		logger = Logger.getLogger(DirectorController.class);
	}
	
	@RequestMapping(value = "/GroupLeaders", params = { "BranchSchoolId", "limit", "offset", "paginationData", "_" }, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getDirectorsBySchoolIdInPage(HttpServletRequest request, @RequestParam("BranchSchoolId")Integer branchSchoolId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iGroupLeaderService.getGroupLeadersByBranchSchoolIdAndPaginationData(loggedInUser, branchSchoolId, paginationData);
		};

		return GetPaginationData(request);
	}

	@RequestMapping(value = "/GroupLeaders", params = {"BranchSchoolId"}, method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getGroupLeaders(HttpServletRequest request, @RequestParam("BranchSchoolId")Integer branchSchoolId) {
		try {
			User loggedInUser = getLoggedInUser(request);
			return iGroupLeaderService.getGroupLeaders(loggedInUser, branchSchoolId);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@RequestMapping(value = "/GroupLeader", params = { "BranchSchoolId" }, method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createGroupLeader(HttpServletRequest request,  @RequestParam("BranchSchoolId")Integer branchSchoolId, @RequestBody String userJsonString) {
		try {
			if (StringUtils.isBlank(userJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}

			JSONObject userJsonObject = JSONObject.fromObject(userJsonString.trim());
			UserVO userVO = new UserVO(userJsonObject);
			logger.debug("Creating new group leader '" + userVO.getLogName() + "' ...");
			return iGroupLeaderService.createGroupLeader(branchSchoolId, userVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
