package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.service.IInformationService;
import com.sms.vo.InformationVO;
import com.sms.vo.MemberVO;
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
public class InformationController extends ControllerBase {

    @Autowired
    private IInformationService informationService;

    public InformationController() {
        logger = Logger.getLogger(InformationController.class);
    }

    @ApiOperation(value = "Get Infos in page", notes = "Get  Infos in page")
    @RequestMapping(value = "/Informations", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getInformationsInPage(HttpServletRequest request, @RequestParam(required = false) Integer informationTypeId, @RequestParam(required = false) Integer informationSubtypeId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return informationService.getInfosByPaginationData(informationTypeId, informationSubtypeId, paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get information", notes = "Get information")
    @RequestMapping(value = "/Information/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getInformationById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get information id = %s", id));
            return informationService.getInformation(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create information", notes = "Create information")
    @RequestMapping(value = "/Information", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createInformation(HttpServletRequest request, @RequestBody String informationJsonString) {
        try {
            if (StringUtils.isBlank(informationJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject informationVOJsonObject = JSONObject.fromObject(informationJsonString.trim());
            InformationVO informationVO = new InformationVO(informationVOJsonObject);
            logger.debug(String.format("Creating new information. name = %s", informationVO.getTitle()));
            User loggedInUser = getLoggedInUser(request);
            return informationService.createInformation(loggedInUser, informationVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete Information", notes = "Delete Information")
    @RequestMapping(value = "/Information/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteInformation(@PathVariable Integer id){
        try {
            return informationService.deleteInformation(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
