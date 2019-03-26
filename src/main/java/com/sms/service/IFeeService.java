package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.model.Fee;

public interface IFeeService {
    DataQueryResult<Fee> getFees(Integer feeTypeId);
    CommandResult createFee(Fee fee);
    CommandResult updateFee(Integer id, Fee fee);
    CommandResult getFee(Integer id);
}
