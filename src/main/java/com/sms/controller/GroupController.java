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
import com.sms.service.IGroupService;
import com.sms.vo.GroupVO;

import net.sf.json.JSONObject;

@Api
@Controller
public class GroupController extends ControllerBase {
	@Autowired
	IGroupService iGroupService;

	public GroupController() {
		logger = Logger.getLogger(GroupController.class);
	}

	@RequestMapping(value = "/Groups", params = {"BranchSchoolId", "limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getGroupsInPage(HttpServletRequest request, @RequestParam("BranchSchoolId")Integer branchSchoolId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iGroupService.getGroupsBySchoolIdAndBranchSchoolIdAndPaginationData(loggedInUser, branchSchoolId, paginationData);
		};

		return GetPaginationData(request);
	}
	
	@RequestMapping(value = "/Groups", params = {"BranchSchoolId"}, method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getGroupsBySchoolId(HttpServletRequest request, @RequestParam("BranchSchoolId")Integer branchSchoolId) {
		try {
		    User loggedInUser = getLoggedInUser(request);
		    return iGroupService.getGroupsByBranchSchoolId(loggedInUser, branchSchoolId);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/Group", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createGroup(HttpServletRequest request, @RequestBody String groupJsonString) {
		try {
			if (StringUtils.isBlank(groupJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject groupJsonObject = JSONObject.fromObject(groupJsonString.trim());

			GroupVO groupVO = new GroupVO(groupJsonObject);

			logger.debug("Creating new group '" + groupVO.getName() + "' ...");
			return iGroupService.createGroup(groupVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/Group/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getGroup(HttpServletRequest request, @PathVariable Integer id) {
		try {
			logger.debug("Get group '" + id + "' ...");
			return iGroupService.getGroup(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/Group/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateGroup(HttpServletRequest request, @PathVariable Integer id, @RequestBody String groupJsonString) {
		try {
			logger.debug("Update group '" + id + "' ...");

			if (StringUtils.isBlank(groupJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject groupJsonObject = JSONObject.fromObject(groupJsonString.trim());

			GroupVO groupVO = new GroupVO(groupJsonObject);

			return iGroupService.updateGroup(id, groupVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
