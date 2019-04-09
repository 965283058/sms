package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.CourseVO;
import net.sf.json.JSONObject;

import javax.jws.soap.SOAPBinding;

public interface ICourseService {
    DataQueryResult<JSONObject> getCoursesByPaginationData(User loggedInUser, Integer subjectId, PaginationData paginationData);
    CommandResult createCourse(User user, CourseVO CourseVO);
    CommandResult getCourse(Integer id);
    CommandResult updateCourse(Integer id, User user, CourseVO CourseVO);
    CommandResult deleteCourse(Integer id);
}
