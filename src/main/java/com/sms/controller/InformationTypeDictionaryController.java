package com.sms.controller;

import com.sms.common.*;
import com.sms.model.InformationTypeDictionary;
import com.sms.service.IInformationService;
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
public class InformationTypeDictionaryController extends ControllerBase {
    private Logger logger;

    @Autowired
    private IInformationService informationService;

    public InformationTypeDictionaryController(){
        logger = Logger.getLogger(InformationTypeDictionaryController.class);
    }

    @RequestMapping(value = "/InformationTypes", method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<InformationTypeDictionary> getInformationType() {
       return informationService.getInformationTypes();
    }

    @ApiOperation(value = "Get InformationType", notes = "Get InformationType")
    @RequestMapping(value = "/InformationType/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InformationTypeDictionary getInformationType(@PathVariable Integer id){
        return informationService.getInformationType(id);
    }

    @ApiOperation(value = "Create InformationType", notes = "Create InformationType")
    @RequestMapping(value = "/InformationType", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createInformationType(@RequestBody String informationTypeJsonString){
        if (StringUtils.isBlank(informationTypeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject informationTypeVoJsonObject = JSONObject.fromObject(informationTypeJsonString);
            InformationTypeDictionary informationTypeDictionary = (InformationTypeDictionary)JSONObject.toBean(informationTypeVoJsonObject, InformationTypeDictionary.class);
            return informationService.createInformationType(informationTypeDictionary);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
    @ApiOperation(value = "Edit InformationType", notes = "Edit InformationType")
    @RequestMapping(value = "/InformationType/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateInformationType(HttpServletRequest request, @PathVariable Integer id, @RequestBody String informationTypeJsonString) {
        if(StringUtils.isBlank(informationTypeJsonString)){
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }
        try {
            JSONObject informationTypeVoJsonObject = JSONObject.fromObject(informationTypeJsonString);
            InformationTypeDictionary informationTypeDictionary = (InformationTypeDictionary)JSONObject.toBean(informationTypeVoJsonObject, InformationTypeDictionary.class);
            return informationService.updateInformationType(id,informationTypeDictionary.getName());
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete InformationType", notes = "Delete InformationType")
    @RequestMapping(value = "/InformationType/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteInformationType(@PathVariable Integer id){
        try {
            return informationService.deleteInformationType(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
