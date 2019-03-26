package com.sms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sms.authentication.SessionManager;
import com.sms.common.helper.DateTimeHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.common.pagination.PaginationPageMode;
import com.sms.common.pagination.PaginationQueryType;
import com.sms.dao.UserMapper;
import com.sms.model.User;

import net.sf.json.JSONObject;

public class ControllerBase {
    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private UserMapper userMapper;

    protected Logger logger = null;
    protected FIGetItemsByPaginationData fiGetItemsByPaginationData;

    protected DataQueryResult<JSONObject> GetPaginationData(HttpServletRequest request) {
	try {
	    String limit = request.getParameter("limit");
	    int countPerPage = Integer.parseInt(limit);
	    if (StringUtils.isBlank(limit) || countPerPage == 0) {
		return new DataQueryResult<JSONObject>(0);
	    }

	    String paginationDataJsonString = request.getParameter("paginationData");
	    if (StringUtils.isBlank(paginationDataJsonString)) {
		return new DataQueryResult<JSONObject>(0);
	    }

	    paginationDataJsonString = paginationDataJsonString.trim();
	    JSONObject paginationDataJsonObject = JSONObject.fromObject(paginationDataJsonString);
	    if (paginationDataJsonObject == null) {
		return new DataQueryResult<JSONObject>(0);
	    }
	    
	    String queryType = paginationDataJsonObject.get("query_type").toString();
	    String pageMode = paginationDataJsonObject.get("page_mode").toString();
	    if (StringUtils.isBlank(pageMode) || StringUtils.isBlank(queryType)) {
		return new DataQueryResult<JSONObject>(0);
	    }
	    
	    PaginationQueryType paginationQueryType = PaginationQueryType.valueOf(Integer.parseInt(queryType));
	    PaginationPageMode paginationPageMode = PaginationPageMode.valueOf(Integer.parseInt(pageMode));
	    if (paginationQueryType == null || paginationPageMode == null) {
		return new DataQueryResult<JSONObject>(0);
	    }
	    
	    PaginationData paginationData = new PaginationData(paginationQueryType, paginationPageMode, countPerPage);
	    
	    if (paginationQueryType == PaginationQueryType.BY_ID) {
		String queryId = paginationDataJsonObject.get("query_id").toString();
		paginationData.setQueryId(Integer.parseInt(queryId));
	    }
	    else if (paginationQueryType == PaginationQueryType.BY_CREATE_TIME) {
		String queryTime = paginationDataJsonObject.get("query_create_time").toString();
		paginationData.setQueryTime(DateTimeHelper.convertDateTimeStringToDate(queryTime));
	    }
	    
	    return fiGetItemsByPaginationData.getItemsByPaginationData(paginationData);
	} catch (Exception ex) {
	    logger.error("Exception : " + ex.getMessage());
	    return new DataQueryResult<JSONObject>(0);
	}
    }

    protected User getLoggedInUser(HttpServletRequest request) {
	HttpSession session = request.getSession();
	String sessionId = session.getId();
	Integer userId = sessionManager.getUserIdBySessionId(sessionId);
	return userMapper.selectByPrimaryKey(userId);
    }
}
