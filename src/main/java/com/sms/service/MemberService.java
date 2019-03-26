package com.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.helper.BranchSchoolDataHelper;
import com.sms.common.helper.SHAHelper;
import com.sms.common.helper.SchoolDataHelper;
import com.sms.model.*;
import com.sms.vo.BranchSchoolVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.DataQueryResult;
import com.sms.common.helper.MemberDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.vo.MemberVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService extends ServiceBase implements IMemberService {
	@Override
	public DataQueryResult<JSONObject> getMembersByGroupIdAndPaginationData(User loggedInUser, Integer groupId, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

		if (groupId == null) {
			return result;
		}

		// Check if school exists
		Group group = groupMapper.selectByPrimaryKey(groupId);
		if (group == null) {
			return result;
		}

		Integer totalMemberCount = 0;
		List<Member> members = null;
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
		    members = memberMapper.selectByGroupIdAndStartIdAndLimitAndAsc(groupId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    members = memberMapper.selectByGroupIdAndEndIdAndLimitAndDesc(groupId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalMemberCount = memberMapper.getCountByGroupId(groupId);

		if (members.size() > 0) {
			result = new DataQueryResult<JSONObject>(totalMemberCount);
			List<MemberVO> memberVOs = MemberDataHelper.convertMembersToMemberVOs(members);
			List<JSONObject> jsonObjects = MemberDataHelper.convertMemberVOsToJSONObjects(memberVOs);
			result.setDataset(jsonObjects);
		}

		return result;
	}

	@Override
	public CommandResult getMember(Integer id) {
		if( null == id){
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}

		Member member = memberMapper.selectByPrimaryKey(id);
		if(null == member){
			return new CommandResult(CommandCode.MEMBER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MEMBER_NOT_EXIST));
		}

		Group group = groupMapper.selectByPrimaryKey(member.getGroupId());
		if(null == group){
			return new CommandResult(CommandCode.GROUP_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.GROUP_NOT_EXIST));
		}
		BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(group.getBranchSchoolId());
		if(null == branchSchool){
			return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
		}
		School school = schoolMapper.selectByPrimaryKey(branchSchool.getSchoolId());
		if(null == school){
			return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
		}

		MemberVO memberVO = MemberDataHelper.convertMemberToMemberVO(member);
		memberVO.setGroupName(group.getName());
		memberVO.setBranchSchoolId(branchSchool.getId());
		memberVO.setBranchSchoolName(branchSchool.getName());
		memberVO.setSchoolId(school.getId());
		memberVO.setSchoolName(school.getName());

		return new CommandResult(CommandCode.OK.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.OK),memberVO.serializeToJSONObject());
	}

	@Override
	public synchronized CommandResult createMember(MemberVO memberVO) {
		if(null == memberVO || StringUtils.isBlank(memberVO.getName())){
			return new CommandResult(CommandCode.EMPTY_MEMBER_NAME.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_MEMBER_NAME));
		}

		// Check if member exists
		Member member = memberMapper.selectByMemberName(memberVO.getName());
		if(null != member){
			return new CommandResult(CommandCode.MEMBER_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MEMBER_WITH_SAME_NAME_ALREADY_EXISTS));
		}

//		// Create new member in database by using mapper
		member = MemberDataHelper.convertMemberVOToMember(memberVO);

		// Hash the password
		String hashedPassword;
		try {
			hashedPassword = SHAHelper.generateHashedString(memberVO.getLogPassword());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
		member.setLogPassword(hashedPassword);

		memberMapper.insert(member);
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	@Override
	public CommandResult updateBranchSchool(Integer id, MemberVO memberVO) {
		if(null == id){
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}

		Member member = memberMapper.selectByPrimaryKey(id);
		if(null == member){
			return new CommandResult(CommandCode.MEMBER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MEMBER_NOT_EXIST));
		}

		// Update member
		Member updatedMember = MemberDataHelper.generateUpdatedMember(member,memberVO);
		memberMapper.updateByPrimaryKey(updatedMember);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}
}
