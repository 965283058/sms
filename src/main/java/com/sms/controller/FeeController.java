package com.sms.controller;


import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.model.Fee;
import com.sms.service.IFeeService;
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
public class FeeController {
    private Logger logger;

    @Autowired
    private IFeeService iFeeService;

    public FeeController() {
        logger = Logger.getLogger(FeeController.class);
    }

    @RequestMapping(value = "/Fees", method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<Fee> getBranchSchoolsInPage(@RequestParam("feeTypeId") Integer feeTypeId) {
        logger.debug(String.format("get fee list. fee type id = %s", feeTypeId));
        return iFeeService.getFees(feeTypeId);
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
            Fee fee = (Fee) JSONObject.toBean(feeVoJsonObject, Fee.class);
            return iFeeService.createFee(fee);
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
            Fee fee = (Fee) JSONObject.toBean(feeVoJsonObject, Fee.class);
            return iFeeService.updateFee(id, fee);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
