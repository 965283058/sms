package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Courseware;
import com.sms.service.ICoursewareService;
import com.sms.vo.CoursewareVO;
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
public class CoursewareController extends ControllerBase {
    private Logger logger = Logger.getLogger(Courseware.class);
    @Autowired
    private ICoursewareService coursewareService;

    @ApiOperation(value = "Get courswarees in page", notes = "Get coursewares in page")
    @RequestMapping(value = "/Coursewares", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getCoursewaresInPage(HttpServletRequest request, @RequestParam(required = false) Integer courseId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return coursewareService.getCoursewaresByPaginationData(null, courseId, paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get courseware", notes = "Get courseware")
    @RequestMapping(value = "/Courseware/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getCoursewareById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            return coursewareService.getCourseware(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create courseware", notes = "Create courseware")
    @RequestMapping(value = "/Courseware", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createCourseware(HttpServletRequest request, @RequestBody String coursewareJsonString) {
        try {
            if (StringUtils.isBlank(coursewareJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject courseVOJsonObject = JSONObject.fromObject(coursewareJsonString.trim());
            CoursewareVO coursewareVO = new CoursewareVO(courseVOJsonObject);
            logger.debug(String.format("Creating new courseware. name = %s", coursewareVO.getName()));
            return coursewareService.createCourseware(coursewareVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete courseware", notes = "Delete courseware")
    @RequestMapping(value = "/Courseware/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteCourseware(@PathVariable Integer id){
        try {
            return coursewareService.deleteCourseware(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit Courseware", notes = "Edit Courseware")
    @RequestMapping(value = "/Courseware/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateCourseware(@PathVariable Integer id, @RequestBody String coursewareJsonString){
        try {
            logger.debug(String.format("Update courseware id = %s",id));

            if(StringUtils.isBlank(coursewareJsonString)){
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject coursewareJsonObject = JSONObject.fromObject(coursewareJsonString);
            CoursewareVO courseVO = new CoursewareVO(coursewareJsonObject);
            return coursewareService.updateCourseware(id, courseVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
