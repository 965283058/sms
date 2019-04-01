package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IInformationService;
import com.sms.vo.InformationVO;
import com.sms.vo.SchoolInformationVO;
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
public class SchoolInformationController extends ControllerBase {

    @Autowired
    private IInformationService informationService;

    public SchoolInformationController() {
        logger = Logger.getLogger(SchoolInformationController.class);
    }

    @ApiOperation(value = "Get school infos in page", notes = "Get school infos in page")
    @RequestMapping(value = "/SchoolInformations", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getSchoolInformationsInPage(HttpServletRequest request, @RequestParam(required = false) Integer informationTypeId, @RequestParam(required = false) Integer informationSubtypeId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return informationService.getSchoolInfosByPaginationData(informationTypeId, informationSubtypeId, paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get school information", notes = "Get school information")
    @RequestMapping(value = "/SchoolInformation/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getSchoolInformationById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get school information id = %s", id));
            return informationService.getSchoolInformation(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create school information", notes = "Create school information")
    @RequestMapping(value = "/SchoolInformation", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createSchoolInformation(HttpServletRequest request, @RequestBody String informationJsonString) {
        try {
            if (StringUtils.isBlank(informationJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject informationVOJsonObject = JSONObject.fromObject(informationJsonString.trim());
            SchoolInformationVO informationVO = new SchoolInformationVO(informationVOJsonObject);
            logger.debug(String.format("Creating new school information. name = %s", informationVO.getTitle()));
            User loggedInUser = getLoggedInUser(request);
            return informationService.createSchoolInformation(loggedInUser, informationVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete school information", notes = "Delete school information")
    @RequestMapping(value = "/SchoolInformation/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteSchoolInformation(@PathVariable Integer id){
        try {
            return informationService.deleteSchoolInformation(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
