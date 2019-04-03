package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Subject;
import com.sms.model.User;
import com.sms.service.IInformationService;
import com.sms.service.ISubjectService;
import com.sms.vo.InformationVO;
import com.sms.vo.MemberVO;
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
public class SubjectController extends ControllerBase {

    @Autowired
    private ISubjectService subjectService;

    public SubjectController() {
        logger = Logger.getLogger(SubjectController.class);
    }

    @ApiOperation(value = "Get subjects in page", notes = "Get subjects in page")
    @RequestMapping(value = "/Subjects", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getSubjectsInPage(HttpServletRequest request, @RequestParam Integer schoolId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return subjectService.getSubjectsByPaginationData(null, schoolId, paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get subject", notes = "Get subject")
    @RequestMapping(value = "/Subject/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getSubjectById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get subject id = %s", id));
            return subjectService.getSubject(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create subject", notes = "Create subject")
    @RequestMapping(value = "/Subject", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createSubject(HttpServletRequest request, @RequestBody String subjectJsonString) {
        try {
            if (StringUtils.isBlank(subjectJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject subjectVOJsonObject = JSONObject.fromObject(subjectJsonString.trim());
            SubjectVO subjectVO = new SubjectVO(subjectVOJsonObject);
            logger.debug(String.format("Creating new subject. name = %s", subjectVO.getSchoolName()));
            return subjectService.createSubject(subjectVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete Subject", notes = "Delete Subject")
    @RequestMapping(value = "/Subject/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteSubject(@PathVariable Integer id){
        try {
            return subjectService.deleteSubject(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit Subject", notes = "Edit Subject")
    @RequestMapping(value = "/Subject/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateSubject(@PathVariable Integer id, @RequestBody String subjectJsonString){
        try {
            logger.debug(String.format("Update subject id = %s",id));

            if(StringUtils.isBlank(subjectJsonString)){
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject subjectJsonObject = JSONObject.fromObject(subjectJsonString);
            SubjectVO subjectVO = new SubjectVO(subjectJsonObject);
            return subjectService.updateSubject(id, subjectVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
