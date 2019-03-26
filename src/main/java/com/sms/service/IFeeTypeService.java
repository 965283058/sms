package com.sms.service;

import com.sms.common.DataQueryResult;
import com.sms.model.FeeType;

public interface IFeeTypeService 
{
	public DataQueryResult<FeeType> getFeeTypes();
	public FeeType getFeeTypeById(Integer id);	
	public void createFeeType(String name);
}
