package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.vo.InformationVO;
import net.sf.json.JSONObject;

public interface IInformationService {
    DataQueryResult<JSONObject> getInfosByPaginationData(Integer typeId, Integer subtypeId, PaginationData paginationData);
    CommandResult getInformation(Integer id);
    CommandResult createInformation(User user, InformationVO informationVO);
}
