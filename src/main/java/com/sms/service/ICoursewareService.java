package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.CourseVO;
import com.sms.vo.CoursewareVO;
import net.sf.json.JSONObject;

public interface ICoursewareService {
    DataQueryResult<JSONObject> getCoursewaresByPaginationData(User loggedInUser, Integer courseId, PaginationData paginationData);
    CommandResult createCourseware(CoursewareVO coursewareVO);
    CommandResult getCourseware(Integer id);
    CommandResult updateCourseware(Integer id, CoursewareVO CoursewareVO);
    CommandResult deleteCourseware(Integer id);
}
