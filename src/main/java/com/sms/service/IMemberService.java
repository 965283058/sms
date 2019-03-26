package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;

import com.sms.vo.MemberVO;
import net.sf.json.JSONObject;

public interface IMemberService {
	public DataQueryResult<JSONObject> getMembersByGroupIdAndPaginationData(User loggedInUser, Integer groupId, PaginationData paginationData);
	public CommandResult getMember(Integer id);
	public CommandResult createMember(MemberVO memberVO);
	public CommandResult updateBranchSchool(Integer id, MemberVO memberVO);
}
