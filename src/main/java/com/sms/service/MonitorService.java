package com.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.DataQueryResult;
import com.sms.common.helper.MonitorDataHelper;
import com.sms.model.Group;
import com.sms.model.Monitor;
import com.sms.model.User;
import com.sms.vo.MonitorVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class MonitorService extends ServiceBase implements IMonitorService {
    public DataQueryResult<JSONObject> getMonitorsByGroupId(User loggedInUser, Integer groupId) {
	DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

	Group group = groupMapper.selectByPrimaryKey(groupId);
	if (group == null) {
	    return result;
	}

	List<Monitor> monitors = monitorMapper.selectByGroupId(groupId);

	if (monitors.size() > 0) {
	    Integer monitorCount = monitors.size();
	    List<MonitorVO> monitorVOs = MonitorDataHelper.convertMonitorsToMonitorVOs(monitors);
	    result = new DataQueryResult<JSONObject>(monitorCount);
	    result.setDataset(MonitorDataHelper.convertMonitorVOsToJSONObjects(monitorVOs));
	}

	return result;
    }    
}
