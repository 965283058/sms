package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

public interface IGroupLeaderService {
	public DataQueryResult<JSONObject> getGroupLeadersByBranchSchoolIdAndPaginationData(User loggedInUser, Integer branchSchoolId, PaginationData paginationData);
	public CommandResult createGroupLeader(Integer branchSchoolId, UserVO userVO);
	public CommandResult getGroupLeaders(User loggedInUser, Integer branchSchoolId);
}
