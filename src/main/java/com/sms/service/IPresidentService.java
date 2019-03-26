package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

public interface IPresidentService {
	public DataQueryResult<JSONObject> getPresidentsByPaginationData(User loggedInUser, PaginationData paginationData);
	public CommandResult createPresident(UserVO userVO);
	public CommandResult getPresidents(User loggedInUser);
}
