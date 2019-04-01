package com.sms.service;


import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.InformationDataHelper;
import com.sms.common.helper.SchoolInformationDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.*;
import com.sms.vo.InformationVO;
import com.sms.vo.SchoolInformationVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
        switch (paginationData.getPageMode()) {
            case NEXT_PAGE:
                informationList = informationMapper.selectByTypeAndStartIdAndLimitAndAsc(typeId, subtypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case PRE_PAGE:
                informationList = informationMapper.selectByTypeAndStartIdAndLimitAndDesc(typeId, subtypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }

        totalInformationCount = informationMapper.getCountByType(typeId, subtypeId);
        if (totalInformationCount > 0) {
            result = new DataQueryResult<>(totalInformationCount);
            informationList.forEach(information -> {
                BranchSchool branchSchool = branchSchoolMapper.selectByBranchSchoolId(information.getBranchSchoolId());
                if (null != branchSchool) {
                    information.setBranchSchoolName(branchSchool.getName());
                    information.setSchoolName(branchSchool.getSchoolName());
                }
                User user = userMapper.selectByPrimaryKey(information.getUserId());
                if (null != user) {
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
        if (null == id) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        Information information = informationMapper.selectByPrimaryKey(id);
        if (null == information) {
            return new CommandResult(CommandCode.INFORMATION_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_NOT_EXIST));
        }

        BranchSchool branchSchool = branchSchoolMapper.selectByBranchSchoolId(information.getBranchSchoolId());

        if (null != branchSchool) {
            information.setBranchSchoolName(branchSchool.getName());
            information.setSchoolName(branchSchool.getSchoolName());
        }

        User user = userMapper.selectByPrimaryKey(information.getUserId());
        if (null != user) {
            information.setUserName(user.getName());
        }

        InformationTypeDictionary informationTypeDictionary = informationTypeDictionaryMapper.selectByPrimaryKey(information.getInformationTypeId());
        if (null != informationTypeDictionary) {
            information.setInformationTypeName(informationTypeDictionary.getName());
        }

        InformationSubtypeDictionary informationSubtypeDictionary = informationSubtypeDictionaryMapper.selectByPrimaryKey(information.getInformationSubtypeId());
        if (null != informationSubtypeDictionary) {
            information.setInformationSubtypeName(informationSubtypeDictionary.getName());
        }

        InformationVO informationVO = InformationDataHelper.convertInformationToInformationVO(information);

        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), informationVO.serializeToJSONObject());
    }

    @Override
    public synchronized CommandResult createInformation(User user, InformationVO informationVO) {
        if (null == user) {
            user = new User();
            user.setId(1);
//            return new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        }

        Information information = InformationDataHelper.convertInformationVoToInformation(informationVO);
        BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(information.getBranchSchoolId());
        if (null == branchSchool) {
            return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
        }
        School school = schoolMapper.selectByPrimaryKey(information.getSchoolId());
        if (null == school) {
            return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
        }
        InformationTypeDictionary informationTypeDictionary = informationTypeDictionaryMapper.selectByPrimaryKey(information.getInformationTypeId());
        if (null == informationTypeDictionary) {
            return new CommandResult(CommandCode.INFORMATION_TYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_TYPE_NOT_EXIST));
        }
        InformationSubtypeDictionary informationSubtypeDictionary = informationSubtypeDictionaryMapper.selectByPrimaryKey(information.getInformationSubtypeId());
        if (null == informationSubtypeDictionary) {
            return new CommandResult(CommandCode.INFORMATION_SUBTYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_SUBTYPE_NOT_EXIST));
        }
        information.setUserId(user.getId());
        informationMapper.insert(information);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteInformation(Integer id) {
        informationMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public DataQueryResult<JSONObject> getSchoolInfosByPaginationData(Integer typeId, Integer subtypeId, PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        Integer totalSchoolInformationCount = 0;
        List<SchoolInformation> schoolInformationList = null;
        switch (paginationData.getPageMode()) {
            case NEXT_PAGE:
                schoolInformationList = schoolInformationMapper.selectByTypeAndStartIdAndLimitAndAsc(typeId, subtypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case PRE_PAGE:
                schoolInformationList = schoolInformationMapper.selectByTypeAndStartIdAndLimitAndDesc(typeId, subtypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }

        totalSchoolInformationCount = schoolInformationMapper.getCountByType(typeId, subtypeId);
        if (totalSchoolInformationCount > 0) {
            result = new DataQueryResult<>(totalSchoolInformationCount);
            List<SchoolInformationVO> schoolInformationVOList = SchoolInformationDataHelper.convertSchoolInformationsToSchoolInformationVOs(schoolInformationList);
            List<JSONObject> jsonObjects = SchoolInformationDataHelper.convertSchoolInformationVOsToJSONObjects(schoolInformationVOList);
            result.setDataset(jsonObjects);
        }

        return result;
    }

    @Override
    public CommandResult getSchoolInformation(Integer id) {
        if (null == id) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        SchoolInformation schoolInformation = schoolInformationMapper.selectByPrimaryKey(id);
        if (null == schoolInformation) {
            return new CommandResult(CommandCode.INFORMATION_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_NOT_EXIST));
        }

        InformationTypeDictionary informationTypeDictionary = informationTypeDictionaryMapper.selectByPrimaryKey(schoolInformation.getInformationTypeId());
        if (null != informationTypeDictionary) {
            schoolInformation.setInformationTypeName(informationTypeDictionary.getName());
        }

        InformationSubtypeDictionary informationSubtypeDictionary = informationSubtypeDictionaryMapper.selectByPrimaryKey(schoolInformation.getInformationSubtypeId());
        if (null != informationSubtypeDictionary) {
            schoolInformation.setInformationSubtypeName(informationSubtypeDictionary.getName());
        }

        SchoolInformationVO schoolInformationVO = SchoolInformationDataHelper.convertSchoolInformationToSchoolInformationVO(schoolInformation);

        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), schoolInformationVO.serializeToJSONObject());
    }

    @Override
    public CommandResult createSchoolInformation(User user, SchoolInformationVO schoolInformationVO) {
        if (null == user) {
            user = new User();
            user.setId(1);
//            return new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        }

        SchoolInformation schoolInformation = SchoolInformationDataHelper.convertSchoolInformationVoToSchoolInformation(schoolInformationVO);
        School school = schoolMapper.selectByPrimaryKey(schoolInformation.getSchoolId());
        if (null == school){
            return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
        }
        InformationTypeDictionary informationTypeDictionary = informationTypeDictionaryMapper.selectByPrimaryKey(schoolInformation.getInformationTypeId());
        if (null == informationTypeDictionary) {
            return new CommandResult(CommandCode.INFORMATION_TYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_TYPE_NOT_EXIST));
        }
        InformationSubtypeDictionary informationSubtypeDictionary = informationSubtypeDictionaryMapper.selectByPrimaryKey(schoolInformation.getInformationSubtypeId());
        if (null == informationSubtypeDictionary) {
            return new CommandResult(CommandCode.INFORMATION_SUBTYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INFORMATION_SUBTYPE_NOT_EXIST));
        }
        schoolInformationMapper.insert(schoolInformation);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteSchoolInformation(Integer id) {
        schoolInformationMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public DataQueryResult<InformationTypeDictionary> getInformationTypes() {
        DataQueryResult<InformationTypeDictionary> result = new DataQueryResult<InformationTypeDictionary>(0);
        List<InformationTypeDictionary> informationTypeDictionaryList =  informationTypeDictionaryMapper.selectAll();
        if(null != informationTypeDictionaryList){
            result = new DataQueryResult<>(informationTypeDictionaryList.size());
            result.setDataset(informationTypeDictionaryList);
        }
        return result;
    }

    @Override
    public InformationTypeDictionary getInformationType(Integer id) {
        return informationTypeDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommandResult updateInformationType(Integer id, String name) {
        if(StringUtils.isBlank(name)){
            return new CommandResult(CommandCode.EMPTY_INFORMATION_TYPE_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_INFORMATION_TYPE_NAME));
        }
        InformationTypeDictionary informationTypeDictionary = new InformationTypeDictionary();
        informationTypeDictionary.setId(id);
        informationTypeDictionary.setName(name);
        informationTypeDictionaryMapper.updateByPrimaryKey(informationTypeDictionary);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public synchronized CommandResult createInformationType(InformationTypeDictionary informationTypeDictionary) {
        if(null == informationTypeDictionary || StringUtils.isBlank(informationTypeDictionary.getName())){
            return new CommandResult(CommandCode.EMPTY_INFORMATION_TYPE_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_INFORMATION_TYPE_NAME));
        }
        informationTypeDictionaryMapper.insert(informationTypeDictionary);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteInformationType(Integer id) {
        informationTypeDictionaryMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public DataQueryResult<InformationSubtypeDictionary> getInformationSubtypes() {
        DataQueryResult<InformationSubtypeDictionary> result = new DataQueryResult<>(0);
        List<InformationSubtypeDictionary> informationSubtypeDictionaryList = informationSubtypeDictionaryMapper.selectAll();
        if(null != informationSubtypeDictionaryList){
            result = new DataQueryResult<>(informationSubtypeDictionaryList.size());
            result.setDataset(informationSubtypeDictionaryList);
        }
        return result;
    }

    @Override
    public InformationSubtypeDictionary getInformationSubtype(Integer id) {
        return informationSubtypeDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommandResult updateInformationSubtype(Integer id, String name) {
        if(StringUtils.isBlank(name)){
            return new CommandResult(CommandCode.EMPTY_INFORMATION_SUBTYPE_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_INFORMATION_SUBTYPE_NAME));
        }
        InformationSubtypeDictionary informationSubtypeDictionary =  new InformationSubtypeDictionary();
        informationSubtypeDictionary.setId(id);
        informationSubtypeDictionary.setName(name);
        informationSubtypeDictionaryMapper.updateByPrimaryKey(informationSubtypeDictionary);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public synchronized CommandResult createInformationSubtype(InformationSubtypeDictionary informationSubtypeDictionary) {
        if(null == informationSubtypeDictionary || StringUtils.isBlank(informationSubtypeDictionary.getName())){
            return new CommandResult(CommandCode.EMPTY_INFORMATION_SUBTYPE_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_INFORMATION_SUBTYPE_NAME));
        }
        informationSubtypeDictionaryMapper.insert(informationSubtypeDictionary);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteInformationSubtype(Integer id) {
        informationSubtypeDictionaryMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }
}
