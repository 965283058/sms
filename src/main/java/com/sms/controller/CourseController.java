package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.ICourseService;
import com.sms.service.ISubjectService;
import com.sms.vo.CourseVO;
import com.sms.vo.SubjectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
public class CourseController extends ControllerBase {

    @Autowired
    private ICourseService courseService;

    public CourseController() {
        logger = Logger.getLogger(CourseController.class);
    }

    @ApiOperation(value = "Get courses in page", notes = "Get courses in page")
    @RequestMapping(value = "/Courses", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getCoursesInPage(HttpServletRequest request, @RequestParam(required = false) Integer subjectId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return courseService.getCoursesByPaginationData(null, subjectId, paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get course", notes = "Get course")
    @RequestMapping(value = "/Course/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getCourseById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get course id = %s", id));
            return courseService.getCourse(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create course", notes = "Create course")
    @RequestMapping(value = "/Course", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createCourse(HttpServletRequest request, @RequestBody String courseJsonString) {
        try {
            if (StringUtils.isBlank(courseJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject courseVOJsonObject = JSONObject.fromObject(courseJsonString.trim());
            CourseVO courseVO = new CourseVO(courseVOJsonObject);
            logger.debug(String.format("Creating new course. name = %s", courseVO.getName()));
            User loggedInUser = getLoggedInUser(request);
            return courseService.createCourse(loggedInUser, courseVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete course", notes = "Delete course")
    @RequestMapping(value = "/Course/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteCourse(@PathVariable Integer id){
        try {
            return courseService.deleteCourse(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit Course", notes = "Edit Course")
    @RequestMapping(value = "/Course/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateCourse(HttpServletRequest request, @PathVariable Integer id, @RequestBody String courseJsonString){
        try {
            logger.debug(String.format("Update course id = %s",id));

            if(StringUtils.isBlank(courseJsonString)){
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject courseJsonObject = JSONObject.fromObject(courseJsonString);
            CourseVO courseVO = new CourseVO(courseJsonObject);
            User loggedInUser = getLoggedInUser(request);
            return courseService.updateCourse(id, loggedInUser, courseVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
