package com.sms.controller;

import com.sms.common.*;
import com.sms.common.pagination.PaginationData;
import com.sms.service.ICourseService;
import com.sms.service.IPaymentService;
import com.sms.vo.CourseVO;
import com.sms.vo.PaymentVO;
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
public class PaymentController extends ControllerBase {

    @Autowired
    private IPaymentService paymentService;

    public PaymentController() {
        logger = Logger.getLogger(PaymentController.class);
    }

    @ApiOperation(value = "Get payments in page", notes = "Get payments in page")
    @RequestMapping(value = "/Payments", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getPaymentsInPage(HttpServletRequest request) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            return paymentService.getPaymentsByPaginationData(paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Get payment", notes = "Get payment")
    @RequestMapping(value = "/Payment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getPaymentById(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get payment id = %s", id));
            return paymentService.getPayment(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }


    @ApiOperation(value = "Create payment", notes = "Create payment")
    @RequestMapping(value = "/Payment", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createPayment(HttpServletRequest request, @RequestBody String paymentJsonString) {
        try {
            if (StringUtils.isBlank(paymentJsonString)) {
                return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
            }
            JSONObject paymentVOJsonObject = JSONObject.fromObject(paymentJsonString.trim());
            PaymentVO paymentVO = new PaymentVO(paymentVOJsonObject);
            return paymentService.createPayment(paymentVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
