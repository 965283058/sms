package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.GroupVO;

import net.sf.json.JSONObject;

public interface IGroupService {
    public DataQueryResult<JSONObject> getGroupsBySchoolIdAndBranchSchoolIdAndPaginationData(User loggedInUser, Integer branchSchoolId, PaginationData paginationData);
    public CommandResult getGroupsByBranchSchoolId(User loggedInUser, Integer branchSchoolId);
    public CommandResult createGroup(GroupVO groupVO);
    public CommandResult getGroup(Integer id);
    public CommandResult updateGroup(Integer groupId, GroupVO groupVO);
}
