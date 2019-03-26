package com.sms.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.common.ControllerBase;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.common.DataQueryResult;
import com.sms.model.User;
import com.sms.service.IMonitorService;

import net.sf.json.JSONObject;

@Api
@Controller
public class MonitorController extends ControllerBase {
    @Autowired
    IMonitorService iMonitorService;
    
    public MonitorController() {
	logger = Logger.getLogger(MonitorController.class);
    }
    
    @RequestMapping(value = "/Monitors", params = {"GroupId"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getMonitorsByGroupId(HttpServletRequest request, @RequestParam("GroupId")Integer groupId) {
	try {
	    User loggedInUser = getLoggedInUser(request);
	    return iMonitorService.getMonitorsByGroupId(loggedInUser, groupId);
	} catch (Exception ex) {
	    logger.error("Exception : " + ex.getMessage());
	    return new DataQueryResult<JSONObject>(0);
	}
    }
}
