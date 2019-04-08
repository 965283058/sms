package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.FeeDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.*;
import com.sms.vo.FeeVO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FeeService extends ServiceBase implements IFeeService {

    @Override
    public DataQueryResult<JSONObject> getFees(Integer feeTypeId, PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        if (feeTypeId == null) {
            return result;
        }

        Integer totalFeeCount = 0;
        List<Fee> fees = null;
        switch (paginationData.getPageMode()){
            case PRE_PAGE:
                fees = feeMapper.selectByFeeTypeIdAndEndIdAndLimitAndDesc(feeTypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case NEXT_PAGE:
                fees = feeMapper.selectByFeeTypeIdAndStartIdAndLimitAndAsc(feeTypeId, paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }
        totalFeeCount = feeMapper.getCountByFeeTypeId(feeTypeId);

        if (fees.size() > 0){
            fees.stream().forEach(fee -> {
                FeeType feeType = feeTypeMapper.selectByPrimaryKey(fee.getFeeTypeId());
                if(null != feeType){
                    fee.setFeeTypeName(feeType.getName());
                }

                School school = schoolMapper.selectByPrimaryKey(fee.getSchoolId());
                if (null != school){
                    fee.setSchoolName(school.getName());
                }

                BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(fee.getBranchSchoolId());
                if (null != branchSchool){
                    fee.setBranchSchoolName(branchSchool.getName());
                }

                Course course = courseMapper.selectByPrimaryKey(fee.getCourseId());
                if (null != course){
                    fee.setCourseName(course.getName());
                }

                Group group = groupMapper.selectByPrimaryKey(fee.getGroupId());
                if (null != group){
                    fee.setGroupName(group.getName());
                }

                Member member = memberMapper.selectByPrimaryKey(fee.getMemberId());
                if (null != member){
                    fee.setMemberName(member.getName());
                }
            });
            result = new DataQueryResult<>(totalFeeCount);
            List<FeeVO> feeVOS = FeeDataHelper.convertFeesToFeeVOs(fees);
            List<JSONObject> jsonObjects = FeeDataHelper.convertFeeVOsToJSONObjects(feeVOS);
            result.setDataset(jsonObjects);
        }
        return result;
    }

    @Override
    public synchronized CommandResult createFee(FeeVO feeVO) {
        if(null == feeVO || StringUtils.isBlank(feeVO.getName())){
            return new CommandResult(CommandCode.EMPTY_FEE_NAME.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_FEE_NAME));
        }
        Fee fee = FeeDataHelper.convertFeeVOToFee(feeVO);
        feeMapper.insert(fee);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult updateFee(Integer id, FeeVO feeVO) {
        if(null == id){
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }

        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(null == fee){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        fee = FeeDataHelper.convertFeeVOToFee(feeVO);
        fee.setId(id);
        feeMapper.updateByPrimaryKey(fee);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getFee(Integer id) {
        if( null == id){
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        Fee fee = feeMapper.selectByPrimaryKey(id);
        if(null == fee){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        FeeType feeType = feeTypeMapper.selectByPrimaryKey(fee.getFeeTypeId());
        if(null == feeType){
            return new CommandResult(CommandCode.FEE_TYPE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_TYPE_NOT_EXIST));
        }
        fee.setFeeTypeName(feeType.getName());

        School school = schoolMapper.selectByPrimaryKey(fee.getSchoolId());
        if (null == school){
            return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
        }
        fee.setSchoolName(school.getName());
        BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(fee.getBranchSchoolId());
        if (null == branchSchool){
            return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
        }
        fee.setBranchSchoolName(branchSchool.getName());
        Course course = courseMapper.selectByPrimaryKey(fee.getCourseId());
        if (null == course){
            return new CommandResult(CommandCode.COURSE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.COURSE_NOT_EXIST));
        }
        fee.setCourseName(course.getName());
        Group group = groupMapper.selectByPrimaryKey(fee.getGroupId());
        if (null == group){
            return new CommandResult(CommandCode.GROUP_NOT_EXIST.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.GROUP_NOT_EXIST));
        }
        fee.setGroupName(group.getName());
        Member member = memberMapper.selectByPrimaryKey(fee.getMemberId());
        if (null == member){
            return new CommandResult(CommandCode.MEMBER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MEMBER_NOT_EXIST));
        }
        fee.setMemberName(member.getName());
        FeeVO feeVO = FeeDataHelper.convertFeeToFeeVO(fee);
        return new CommandResult(CommandCode.OK.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.OK),feeVO.serializeToJSONObject());
    }

    @Override
    public CommandResult deleteFee(Integer id) {
        feeMapper.deleteByPrimaryKey(id);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }
}
