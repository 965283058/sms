package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.vo.PaymentVO;
import net.sf.json.JSONObject;

public interface IPaymentService {
    DataQueryResult<JSONObject> getPaymentsByPaginationData(PaginationData paginationData);
    CommandResult createPayment(PaymentVO paymentVO);
    CommandResult getPayment(Integer id);
}
