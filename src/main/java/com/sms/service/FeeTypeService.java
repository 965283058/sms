package com.sms.service;

import com.sms.common.DataQueryResult;
import com.sms.common.helper.BranchSchoolDataHelper;
import com.sms.common.helper.MemberDataHelper;
import com.sms.model.BranchSchool;
import com.sms.model.GenericItem;
import com.sms.vo.BranchSchoolVO;
import com.sms.vo.MemberVO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sms.model.FeeType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class FeeTypeService extends ServiceBase implements IFeeTypeService 
{
	@Override
	public DataQueryResult<FeeType> getFeeTypes() {
		DataQueryResult<FeeType> result = new DataQueryResult<FeeType>(0);

		List<FeeType> feeTypeList = feeTypeMapper.selectAll();
		Integer totalFeeTypeCount = null == feeTypeList ? 0 : feeTypeList.size();

		if(totalFeeTypeCount > 0){
			result = new DataQueryResult<>(totalFeeTypeCount);
			result.setDataset(feeTypeList);
		}
		return result;
	}

	@Override
	public FeeType getFeeTypeById(Integer id) 
	{				
		return feeTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void createFeeType(String name) 
	{
		FeeType feeType = new FeeType();
		feeType.setName(name);		
		feeTypeMapper.insert(feeType);		
	}
}
