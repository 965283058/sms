package com.sms.service;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.model.InformationSubtypeDictionary;
import com.sms.model.InformationTypeDictionary;
import com.sms.model.User;
import com.sms.vo.InformationVO;
import net.sf.json.JSONObject;

public interface IInformationService {
    DataQueryResult<JSONObject> getInfosByPaginationData(Integer typeId, Integer subtypeId, PaginationData paginationData);
    CommandResult getInformation(Integer id);
    CommandResult createInformation(User user, InformationVO informationVO);
    CommandResult deleteInformation(Integer id);

    DataQueryResult<InformationTypeDictionary> getInformationTypes();
    InformationTypeDictionary getInformationType(Integer id);
    CommandResult updateInformationType(Integer id, String name);
    CommandResult createInformationType(InformationTypeDictionary informationTypeDictionary);
    CommandResult deleteInformationType(Integer id);

    DataQueryResult<InformationSubtypeDictionary> getInformationSubtypes();
    InformationSubtypeDictionary getInformationSubtype(Integer id);
    CommandResult updateInformationSubtype(Integer id, String name);
    CommandResult createInformationSubtype(InformationSubtypeDictionary informationSubtypeDictionary);
    CommandResult deleteInformationSubtype(Integer id);

}
