package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.CourseDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Course;
import com.sms.model.Subject;
import com.sms.model.User;
import com.sms.vo.CourseVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseService extends ServiceBase implements ICourseService {
    @Override
    public DataQueryResult<JSONObject> getCoursesByPaginationData(User loggedInUser, Integer subjectId, PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        Integer totalCourseCount = 0;
        List<Course> courses = null;
        switch (paginationData.getPageMode()){
            case PRE_PAGE:
                courses = courseMapper.selectBySubjectIdAndEndIdAndLimitAndDesc(subjectId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case NEXT_PAGE:
                courses = courseMapper.selectBySubjectIdAndStartIdAndLimitAndAsc(subjectId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }
        totalCourseCount = courseMapper.getCountBySubjectId(subjectId);

        if (courses.size() > 0){
            Subject subject = subjectMapper.selectByPrimaryKey(courses.get(0).getSubjectId());
            User user = userMapper.selectByPrimaryKey(courses.get(0).getUserId());
            courses.stream().forEach(course -> {
                if (null != subject){
                    course.setSubjectName(subject.getName());
                }
                if (null != user){
                    course.setUserName(user.getName());
                }
            });
            result = new DataQueryResult<>(totalCourseCount);
            List<CourseVO> courseVOS = CourseDataHelper.convertCoursesToCourseVOs(courses);
            List<JSONObject> jsonObjects = CourseDataHelper.convertCoursesVOsToJSONObjects(courseVOS);
            result.setDataset(jsonObjects);
        }
        return result;
    }

    @Override
    public CommandResult createCourse(CourseVO courseVO) {
        if(null == courseVO || StringUtils.isBlank(courseVO.getName())){
            return new CommandResult(CommandCode.EMPTY_COURSE_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_COURSE_NAME));
        }

        User user = userMapper.selectByPrimaryKey(courseVO.getUserId());
        if(null == user){
            return  new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        }

        Subject subject = subjectMapper.selectByPrimaryKey(courseVO.getSubjectId());
        if (null == subject){
            return new CommandResult(CommandCode.SUBJECT_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SUBJECT_NOT_EXIST));
        }

        Course course = CourseDataHelper.convertCourseVOToCourse(courseVO);
        courseMapper.insert(course);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getCourse(Integer id) {
        if (id == null) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }

        Course course = courseMapper.selectByPrimaryKey(id);
        if(null == course){
            return new CommandResult(CommandCode.COURSE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSE_NOT_EXIST));
        }
        Subject subject = subjectMapper.selectByPrimaryKey(course.getSubjectId());
        if(null == subject){
            return new CommandResult(CommandCode.SUBJECT_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SUBJECT_NOT_EXIST));
        }
        course.setSubjectName(subject.getName());
        CourseVO courseVO = CourseDataHelper.convertCourseToCourseVO(course);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), courseVO.serializeToJSONObject());
    }

    @Override
    public CommandResult updateCourse(Integer id, CourseVO courseVO) {
        User user = userMapper.selectByPrimaryKey(courseVO.getUserId());
        if(null == user){
            return  new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        }

        Subject subject = subjectMapper.selectByPrimaryKey(courseVO.getSubjectId());
        if (null == subject){
            return new CommandResult(CommandCode.SUBJECT_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SUBJECT_NOT_EXIST));
        }

        Course course = CourseDataHelper.convertCourseVOToCourse(courseVO);
        course.setId(id);
        courseMapper.updateByPrimaryKey(course);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteCourse(Integer id) {
        courseMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }
}
