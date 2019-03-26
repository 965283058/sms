package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.MemberDataHelper;
import com.sms.model.Fee;
import com.sms.model.FeeType;
import com.sms.model.Member;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FeeService extends ServiceBase implements IFeeService {

    @Override
    public DataQueryResult<Fee> getFees(Integer feeTypeId) {
        DataQueryResult<Fee> result = new DataQueryResult<Fee>(0);

        if (feeTypeId == null) {
            return result;
        }

        // Check if fee type exists
        FeeType feeType = feeTypeMapper.selectByPrimaryKey(feeTypeId);
        if (null == feeType) {
            return result;
        }
        List<Fee> feeList = feeMapper.selectAll(feeTypeId);
        if (null != feeList) {
            feeList.forEach(fee -> {
                fee.setFeeTypeName(feeType.getName());
            });
        }
        Integer totalFeeCount = null == feeList ? 0 : feeList.size();

        if (totalFeeCount > 0) {
            result = new DataQueryResult<>(totalFeeCount);
            result.setDataset(feeList);
        }
        return result;
    }

    @Override
    public synchronized CommandResult createFee(Fee fee) {
        if(null == fee || StringUtils.isBlank(fee.getName())){
            return new CommandResult(CommandCode.EMPTY_FEE_NAME.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_FEE_NAME));
        }
        feeMapper.insert(fee);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult updateFee(Integer id, Fee feeVO) {
        if(null == id){
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }

        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(null == fee){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        if(null != feeVO){
            feeVO.setId(id);
        }
        feeMapper.updateByPrimaryKey(feeVO);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getFee(Integer id) {
        if( null == id){
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(null == fee){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        FeeType feeType = feeTypeMapper.selectByPrimaryKey(fee.getFeeTypeId());
        if(null == feeType){
            return new CommandResult(CommandCode.FEE_TYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_TYPE_NOT_EXIST));
        }
        fee.setFeeTypeName(feeType.getName());

        return new CommandResult(CommandCode.OK.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.OK),JSONObject.fromObject(fee));
    }
}
