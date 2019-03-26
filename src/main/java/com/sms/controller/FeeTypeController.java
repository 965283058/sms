package com.sms.controller;

import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sms.model.FeeType;
import com.sms.service.IFeeTypeService;

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
public class FeeTypeController 
{
	@Autowired
	private IFeeTypeService iFeeTypeService;

	@RequestMapping(value = "/FeeTypes", method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<FeeType> getBranchSchoolsInPage() {

		return iFeeTypeService.getFeeTypes();
	}

	@RequestMapping(value = "/FeeType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FeeType getFeeType(@PathVariable Integer id) 
	{
		return iFeeTypeService.getFeeTypeById(id);
	}

	@RequestMapping(value = "/FeeType/{name}", method = RequestMethod.POST)
	@ResponseBody
	public void createFeeType(@PathVariable String name) 
	{
		iFeeTypeService.createFeeType(name);
	}
}
