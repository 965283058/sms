package com.sms.service;


import com.google.gson.JsonObject;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.InformationDataHelper;
import com.sms.common.helper.MemberDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.Information;
import com.sms.vo.InformationVO;
import com.sms.vo.MemberVO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class InformationService extends ServiceBase implements IInformationService {
    @Override
    public DataQueryResult<JSONObject> getInfosByPaginationData(Integer typeId, Integer subtypeId, PaginationData paginationData) {

        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        Integer totalInformationCount = 0;
        List<Information> informationList = null;
        switch (paginationData.getPageMode()){
            case NEXT_PAGE:
                informationList = informationMapper.selectByTypeAndStartIdAndLimitAndAsc(typeId, subtypeId,paginationData.getQueryId(),paginationData.getCountPerPage());
                break;
            case PRE_PAGE:
                informationList = informationMapper.selectByTypeAndStartIdAndLimitAndDesc(typeId, subtypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }

        totalInformationCount = informationMapper.getCountByType(typeId, subtypeId);
        if(totalInformationCount > 0){
            result = new DataQueryResult<>(totalInformationCount);
//            informationList.forEach(information -> {
//
//            });
            List<InformationVO> informationVOList = InformationDataHelper.convertInformationsToInformationVOs(informationList);
            List<JSONObject> jsonObjects = InformationDataHelper.convertInformationVOsToJSONObjects(informationVOList);
            result.setDataset(jsonObjects);
        }

        return result;
    }
}
