package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Fee;
import com.sms.vo.FeeVO;
import net.sf.json.JSONObject;

public interface IFeeService {
    DataQueryResult<JSONObject> getFees(Integer feeTypeId, PaginationData paginationData);
    CommandResult createFee(FeeVO fee);
    CommandResult updateFee(Integer id, FeeVO fee);
    CommandResult getFee(Integer id);
}
