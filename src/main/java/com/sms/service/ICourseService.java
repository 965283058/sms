package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.CourseVO;
import net.sf.json.JSONObject;

public interface ICourseService {
    DataQueryResult<JSONObject> getCoursesByPaginationData(User loggedInUser, Integer subjectId, PaginationData paginationData);
    CommandResult createCourse(CourseVO CourseVO);
    CommandResult getCourse(Integer id);
    CommandResult updateCourse(Integer id, CourseVO CourseVO);
    CommandResult deleteCourse(Integer id);
}
