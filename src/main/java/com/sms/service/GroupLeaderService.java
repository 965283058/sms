package com.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.RoleType;
import com.sms.common.helper.SHAHelper;
import com.sms.common.helper.UserDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.BranchSchool;
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupLeaderService extends UserServiceBase implements IGroupLeaderService {
	public DataQueryResult<JSONObject> getGroupLeadersByBranchSchoolIdAndPaginationData(User loggedInUser, Integer branchSchoolId, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

		BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
		if (branchSchool == null) {
			return result;
		}
		
		Integer totalUserCount = 0;
		List<User> users = null;
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
		    users = userMapper.selectGroupLeadersByBranchSchoolIdAndPageAndAsc(branchSchoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    users = userMapper.selectGroupLeadersByBranchSchoolIdAndPageAndDesc(branchSchoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalUserCount = userMapper.getGroupLeaderCountByBranchSchoolId(branchSchoolId);

		result = new DataQueryResult<JSONObject>(totalUserCount);
		result.setDataset(UserDataHelper.convertUsersToJSONObjects(users));

		return result;
	}

	public CommandResult getGroupLeaders(User loggedInUser, Integer branchSchoolId) {
        	// Get branch school
            	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
            	if (branchSchool == null) {
            	    return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
            	}
            	
		List<User> users = userMapper.selectGroupLeadersByBranchSchoolId(branchSchoolId);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), users);
	}

	public synchronized CommandResult createGroupLeader(Integer branchSchoolId, UserVO userVO) {
	    	CommandResult result = validateUserOV(userVO);
	    	if (result != null) {
	    	    return result;
	    	}

	    	// Get branch school
        	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
        	if (branchSchool == null) {
        	    return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
        	}
        	
		// Create user in database
		User user = UserDataHelper.convertUserVOToUser(userVO);
		
		// Hash the password
		String hashedPassword;
		try {
			hashedPassword = SHAHelper.generateHashedString(user.getLogPassword());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
		
		user.setLogPassword(hashedPassword);
		userMapper.insert(user);

		// Create user role in database
		UserRoleAssociation userRoleAssociation = new UserRoleAssociation();
		userRoleAssociation.setUserId(user.getId());
		userRoleAssociation.setRoleId(RoleType.GROUOP_LEADER.getValue());
		userRoleAssociation.setSchoolId(branchSchool.getSchoolId());
		userRoleAssociation.setBranchSchoolId(branchSchoolId);
		userRoleAssociationMapper.insert(userRoleAssociation);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}
}
