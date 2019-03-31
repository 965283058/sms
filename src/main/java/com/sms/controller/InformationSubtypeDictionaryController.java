package com.sms.controller;

import com.sms.common.*;
import com.sms.model.InformationSubtypeDictionary;
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
public class InformationSubtypeDictionaryController extends ControllerBase {
    private Logger logger;

    @Autowired
    private IInformationService informationService;

    public InformationSubtypeDictionaryController(){
        logger = Logger.getLogger(InformationSubtypeDictionaryController.class);
    }

    @RequestMapping(value = "/InformationSubtypes", method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<InformationSubtypeDictionary> getInformationSubtype() {
        return informationService.getInformationSubtypes();
    }

    @ApiOperation(value = "Get InformationSubtype", notes = "Get InformationSubtype")
    @RequestMapping(value = "/InformationSubtype/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InformationSubtypeDictionary getInformationSubtype(@PathVariable Integer id){
        return informationService.getInformationSubtype(id);
    }

    @ApiOperation(value = "Create InformationSubtype", notes = "Create InformationSubtype")
    @RequestMapping(value = "/InformationSubtype", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createInformationSubtype(@RequestBody String informationSubtypeJsonString){
        if (StringUtils.isBlank(informationSubtypeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject informationSubtypeVoJsonObject = JSONObject.fromObject(informationSubtypeJsonString);
            InformationSubtypeDictionary informationSubtypeDictionary = (InformationSubtypeDictionary)JSONObject.toBean(informationSubtypeVoJsonObject, InformationSubtypeDictionary.class);
            return informationService.createInformationSubtype(informationSubtypeDictionary);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
    @ApiOperation(value = "Edit InformationSubtype", notes = "Edit InformationSubtype")
    @RequestMapping(value = "/InformationSubtype/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateInformationSubtype(HttpServletRequest request, @PathVariable Integer id, @RequestBody String informationSubtypeJsonString) {
        if(StringUtils.isBlank(informationSubtypeJsonString)){
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }
        try {
            JSONObject informationSubtypeVoJsonObject = JSONObject.fromObject(informationSubtypeJsonString);
            InformationSubtypeDictionary informationSubtypeDictionary = (InformationSubtypeDictionary)JSONObject.toBean(informationSubtypeVoJsonObject, InformationSubtypeDictionary.class);
            return informationService.updateInformationSubtype(id,informationSubtypeDictionary.getName());
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete InformationSubtype", notes = "Delete InformationSubtype")
    @RequestMapping(value = "/InformationSubtype/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteInformationSubtype(@PathVariable Integer id){
        try {
            return informationService.deleteInformationSubtype(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
