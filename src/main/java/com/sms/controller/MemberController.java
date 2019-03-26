package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.*;
import com.sms.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IMemberService;

import net.sf.json.JSONObject;

@Controller
@Api
public class MemberController extends ControllerBase {
	@Autowired
	private IMemberService iMemberService;

	public MemberController() {
		logger = Logger.getLogger(MemberController.class);
	}

	@ApiOperation(value = "Get  Members in page", notes = "Get  Members in page")
	@RequestMapping(value = "/Group/{groupId}/Members", params = { "limit", "offset", "paginationData", "_" }, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getMembersInPage(HttpServletRequest request, @PathVariable Integer groupId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iMemberService.getMembersByGroupIdAndPaginationData(loggedInUser, groupId, paginationData);
		};

		return GetPaginationData(request);
	}

	@ApiOperation(value = "Get member's info", notes = "Get member's info")
	@RequestMapping(value = "/Member/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getMemberByMemberId(HttpServletRequest request,@PathVariable Integer id){
		try {
			logger.debug(String.format("Get member id = %s",id));
			return iMemberService.getMember(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@ApiOperation(value = "Create member", notes = "Create member")
	@RequestMapping(value = "/Member", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createMember(HttpServletRequest request, @RequestBody String memberJsonString) {
		try {
			if (StringUtils.isBlank(memberJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject memberVOJsonObject = JSONObject.fromObject(memberJsonString.trim());
			MemberVO memberVO = new MemberVO(memberVOJsonObject);

			logger.debug(String.format("Creating new member. name = %s",memberVO.getName()));
			return iMemberService.createMember(memberVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@ApiOperation(value = "Edit member", notes = "Edit member")
	@RequestMapping(value = "/Member/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateMember(HttpServletRequest request, @PathVariable Integer id, @RequestBody String memberJsonString) {
		try {
			logger.debug(String.format("Update member id = %s",id));

			if(StringUtils.isBlank(memberJsonString)){
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject memberJsonObject = JSONObject.fromObject(memberJsonString.trim());
			MemberVO memberVO = new MemberVO(memberJsonObject);
			return iMemberService.updateBranchSchool(id,memberVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
}
