package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.CoursewareDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Course;
import com.sms.model.Courseware;
import com.sms.model.User;
import com.sms.vo.CoursewareVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CoursewareService extends ServiceBase implements ICoursewareService {
    @Override
    public DataQueryResult<JSONObject> getCoursewaresByPaginationData(User loggedInUser, Integer courseId, PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        Integer totalCoursewareCount = 0;
        List<Courseware> coursewares = null;
        switch (paginationData.getPageMode()){
            case PRE_PAGE:
                coursewares = coursewareMapper.selectByCourseIdAndEndIdAndLimitAndDesc(courseId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case NEXT_PAGE:
                coursewares = coursewareMapper.selectByCourseIdAndStartIdAndLimitAndAsc(courseId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }
        totalCoursewareCount = coursewareMapper.getCountByCourseId(courseId);

        if (coursewares.size() > 0){
            Course course = courseMapper.selectByPrimaryKey(coursewares.get(0).getCourseId());
            coursewares.stream().forEach(courseware -> {
                if (null != course){
                    courseware.setCourseName(course.getName());
                }
            });

            result = new DataQueryResult<>(totalCoursewareCount);
            List<CoursewareVO> coursewareVOS = CoursewareDataHelper.convertCoursewaresToCoursewareVOs(coursewares);
            List<JSONObject> jsonObjects = CoursewareDataHelper.convertCoursewaresVOsToJSONObjects(coursewareVOS);
            result.setDataset(jsonObjects);
        }
        return result;
    }

    @Override
    public CommandResult createCourseware(CoursewareVO coursewareVO) {
        if (null == coursewareVO || StringUtils.isBlank(coursewareVO.getName())){
            return new CommandResult(CommandCode.EMPTY_COURSEWARE_NAME.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_COURSEWARE_NAME));
        }

        Course course = courseMapper.selectByPrimaryKey(coursewareVO.getCourseId());
        if (null == course){
            return new CommandResult(CommandCode.COURSE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSE_NOT_EXIST));
        }

        Courseware courseware = CoursewareDataHelper.convertCoursewareVOToCourseware(coursewareVO);
        coursewareMapper.insert(courseware);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getCourseware(Integer id) {
        if (id == null) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }

        Courseware courseware = coursewareMapper.selectByPrimaryKey(id);
        if (null == courseware){
            return new CommandResult(CommandCode.COURSEWARE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSEWARE_NOT_EXIST));
        }
        Course course = courseMapper.selectByPrimaryKey(courseware.getCourseId());
        if (null == course){
            return new CommandResult(CommandCode.COURSE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSE_NOT_EXIST));
        }
        courseware.setCourseName(course.getName());
        CoursewareVO coursewareVO = CoursewareDataHelper.convertCoursewareToCoursewareVO(courseware);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), coursewareVO.serializeToJSONObject());
    }

    @Override
    public CommandResult updateCourseware(Integer id, CoursewareVO coursewareVO) {
        Course course = courseMapper.selectByPrimaryKey(coursewareVO.getCourseId());
        if (null == course){
            return new CommandResult(CommandCode.COURSE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSE_NOT_EXIST));
        }

        Courseware courseware = CoursewareDataHelper.convertCoursewareVOToCourseware(coursewareVO);
        courseware.setId(id);
        coursewareMapper.updateByPrimaryKey(courseware);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteCourseware(Integer id) {
        coursewareMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }
}
