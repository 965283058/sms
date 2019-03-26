package com.sms.service;

import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import net.sf.json.JSONObject;

public interface IInformationService {
    DataQueryResult<JSONObject> getInfosByPaginationData(Integer typeId, Integer subtypeId, PaginationData paginationData);
}
