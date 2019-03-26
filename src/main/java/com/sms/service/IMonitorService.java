package com.sms.service;

import com.sms.common.DataQueryResult;
import com.sms.model.User;

import net.sf.json.JSONObject;

public interface IMonitorService {
    public DataQueryResult<JSONObject> getMonitorsByGroupId(User loggedInUser, Integer groupId);
}
