package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.SubjectDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.*;
import com.sms.vo.SubjectVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectService  extends ServiceBase implements ISubjectService{
    @Override
    public DataQueryResult<JSONObject> getSubjectsByPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        if (null == schoolId){
            return result;
        }

        School school = schoolMapper.selectByPrimaryKey(schoolId);
        if(null == school){
            return result;
        }
        Integer totalSubjectCount = 0 ;
        List<Subject> subjects = null;
        switch (paginationData.getPageMode()){
            case PRE_PAGE:
                subjects = subjectMapper.selectBySchoolIdAndEndIdAndLimitAndDesc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case NEXT_PAGE:
                subjects = subjectMapper.selectBySchoolIdAndStartIdAndLimitAndAsc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }
        totalSubjectCount = subjectMapper.getCountBySchoolId(schoolId);
        if(subjects.size() > 0) {
            result = new DataQueryResult<>(totalSubjectCount);
            List<SubjectVO> subjectVOS = SubjectDataHelper.convertSubjectsToSubjectVOs(subjects);
            List<JSONObject> jsonObjects = SubjectDataHelper.convertSubjectsVOsToJSONObjects(subjectVOS);
            result.setDataset(jsonObjects);
        }
        return result;
    }

    @Override
    public synchronized CommandResult createSubject(SubjectVO subjectVO) {
        if (null == subjectVO || StringUtils.isBlank(subjectVO.getName())) {
            return new CommandResult(CommandCode.EMPTY_SUBJECT_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_SUBJECT_NAME));
        }
        Subject subject = SubjectDataHelper.convertSubjectVOToSubject(subjectVO);
        subjectMapper.insert(subject);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getSubject(Integer id) {
        if (id == null) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }

        Subject subject = subjectMapper.selectByPrimaryKey(id);
        if (null == subject){
            return new CommandResult(CommandCode.SUBJECT_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SUBJECT_NOT_EXIST));
        }

        School school = schoolMapper.selectByPrimaryKey(subject.getSchoolId());
        if(null == school){
            return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
        }
        subject.setSchoolName(school.getName());
        SubjectVO subjectVO = SubjectDataHelper.convertSubjectToSubjectVO(subject);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), subjectVO.serializeToJSONObject());
    }

    @Override
    public CommandResult updateSubject(Integer id, SubjectVO subjectVO) {
        Subject subject = SubjectDataHelper.convertSubjectVOToSubject(subjectVO);
        subject.setId(id);
        subjectMapper.updateByPrimaryKey(subject);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult deleteSubject(Integer id) {
        subjectMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }
}
