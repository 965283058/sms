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
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class PresidentService extends UserServiceBase implements IPresidentService {

	public DataQueryResult<JSONObject> getPresidentsByPaginationData(User loggedInUser, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

		Integer totalUserCount = 0;
		List<User> users = null;
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
		    users = userMapper.selectPresidentsByPageAndAsc(paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    users = userMapper.selectPresidentsByPageAndDesc(paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalUserCount = userMapper.getTotalUserCountByRoleType(RoleType.PRESIDENT.getValue());

		result = new DataQueryResult<JSONObject>(totalUserCount);
		result.setDataset(UserDataHelper.convertUsersToJSONObjects(users));

		return result;
	}

	public CommandResult getPresidents(User loggedInUser) {
		List<User> users = userMapper.selectByRoleId(RoleType.PRESIDENT.getValue());

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), users);
	}

	public synchronized CommandResult createPresident(UserVO userVO) {
		CommandResult result = validateUserOV(userVO);
		if (result != null) {
			return result;
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
		userRoleAssociation.setRoleId(RoleType.PRESIDENT.getValue());
		userRoleAssociationMapper.insert(userRoleAssociation);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}
}
