package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.SchoolVO;

import net.sf.json.JSONObject;

public interface ISchoolService {
    public DataQueryResult<JSONObject> getSchoolsByPaginationData(User loggedInUser, PaginationData paginationData);
    public CommandResult getSchools(User loggedInUser);
    public CommandResult createSchool(SchoolVO schoolVO);
    public CommandResult getSchool(Integer id);
    public CommandResult updateSchool(Integer id, SchoolVO schoolVO);
}
