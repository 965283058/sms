package com.sms.controller;


import com.sms.common.*;
import com.sms.common.helper.FeeDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Fee;
import com.sms.service.IFeeService;
import com.sms.vo.FeeVO;
import com.sms.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
public class FeeController extends ControllerBase {
    private Logger logger;

    @Autowired
    private IFeeService iFeeService;

    public FeeController() {
        logger = Logger.getLogger(FeeController.class);
    }

    @ApiOperation(value = "Get fees in page", notes = "Get fees in page")
    @RequestMapping(value = "/Fees", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getFeesInPage(HttpServletRequest request, @RequestParam Integer feeTypeId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            logger.debug(String.format("get fee list. fee type id = %s", feeTypeId));
            return iFeeService.getFees(feeTypeId,paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Create Fee", notes = "Create Fee")
    @RequestMapping(value = "/Fee", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createFee(HttpServletRequest request, @RequestBody String feeJsonString) {
        if (StringUtils.isBlank(feeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject feeVoJsonObject = JSONObject.fromObject(feeJsonString);
            FeeVO feeVO = new FeeVO(feeVoJsonObject);
            return iFeeService.createFee(feeVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Get fee", notes = "Get fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getFee(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get fee id = %s", id));
            return iFeeService.getFee(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit fee", notes = "Edit fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateFee(HttpServletRequest request, @PathVariable Integer id, @RequestBody String feeJsonString) {
        logger.debug(String.format("Update fee id = %s", id));
        if (StringUtils.isBlank(feeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject feeVoJsonObject = JSONObject.fromObject(feeJsonString);
            FeeVO feeVO = new FeeVO(feeVoJsonObject);
            return iFeeService.updateFee(id, feeVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete fee", notes = "Delete fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteFee(@PathVariable Integer id){
        try {
            return iFeeService.deleteFee(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
