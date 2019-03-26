package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

public interface IDirectorService {
	public DataQueryResult<JSONObject> getDirectorsBySchoolIdAndPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData);
	public CommandResult createDirector(Integer schoolId, UserVO userVO);
	public CommandResult getDirectors(User loggedInUser, Integer schoolId);
}
