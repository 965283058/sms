package com.sms.service;


import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.InformationDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.*;
import com.sms.vo.InformationVO;
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
            informationList.forEach(information -> {
                BranchSchool branchSchool = branchSchoolMapper.selectByBranchSchoolId(information.getBranchSchoolId());
                if(null != branchSchool){
                    information.setBranchSchoolName(branchSchool.getName());
                    information.setSchoolName(branchSchool.getSchoolName());
                }
                User user = userMapper.selectByPrimaryKey(information.getUserId());
                if(null != user){
                    information.setUserName(user.getName());
                }
            });
            List<InformationVO> informationVOList = InformationDataHelper.convertInformationsToInformationVOs(informationList);
            List<JSONObject> jsonObjects = InformationDataHelper.convertInformationVOsToJSONObjects(informationVOList);
            result.setDataset(jsonObjects);
        }

        return result;
    }

    @Override
    public CommandResult getInformation(Integer id) {
        if( null == id){
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        Information information = informationMapper.selectByPrimaryKey(id);
        if(null == information){
            return new CommandResult(CommandCode.INFORMATION_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_NOT_EXIST));
        }

        BranchSchool branchSchool = branchSchoolMapper.selectByBranchSchoolId(information.getBranchSchoolId());

        if(null != branchSchool){
            information.setBranchSchoolName(branchSchool.getName());
            information.setSchoolName(branchSchool.getSchoolName());
        }

        User user = userMapper.selectByPrimaryKey(information.getUserId());
        if(null != user){
            information.setUserName(user.getName());
        }

        InformationTypeDictionary informationTypeDictionary = informationTypeDictionaryMapper.selectByPrimaryKey(information.getInformationTypeId());
        if(null != informationTypeDictionary){
            information.setInformationTypeName(informationTypeDictionary.getName());
        }

        InformationSubtypeDictionary informationSubtypeDictionary = informationSubtypeDictionaryMapper.selectByPrimaryKey(information.getInformationSubtypeId());
        if(null != informationSubtypeDictionary){
            information.setInformationSubtypeName(informationSubtypeDictionary.getName());
        }

        InformationVO  informationVO = InformationDataHelper.convertInformationToInformationVO(information);

        return new CommandResult(CommandCode.OK.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.OK),informationVO.serializeToJSONObject());
    }
}
