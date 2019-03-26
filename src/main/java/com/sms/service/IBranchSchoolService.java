package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.BranchSchoolVO;

import net.sf.json.JSONObject;

public interface IBranchSchoolService {
    public DataQueryResult<JSONObject> getBranchSchoolsBySchoolIdAndPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData);
    public CommandResult getBranchSchoolsBySchoolId(User loggedInUser, Integer schoolId);
    public CommandResult createBranchSchool(BranchSchoolVO branchSchoolVO);
    public CommandResult getBranchSchool(Integer id);
    public CommandResult updateBranchSchool(Integer branchSchoolId, BranchSchoolVO branchSchoolVO);
}
