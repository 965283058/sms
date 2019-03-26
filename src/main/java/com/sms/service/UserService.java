package com.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.helper.SHAHelper;
import com.sms.common.helper.UserDataHelper;
import com.sms.model.User;
import com.sms.vo.UserVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends ServiceBase implements IUserService {
	
	public CommandResult getUser(Integer id) {
        	if (id == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	User user = userMapper.selectByPrimaryKey(id);
        	if (user == null) {
        		return new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        	}
        
        	UserVO userVO = UserDataHelper.convertUserToUserVO(user);
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), userVO.serializeToJSONObject());
	}
	
	public CommandResult updateUser(Integer id, UserVO userVO) {
        	if (id == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	User user = userMapper.selectByPrimaryKey(id);
        	if (user == null) {
        		return new CommandResult(CommandCode.USER_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_NOT_EXIST));
        	}
        
        	User updatedUser = UserDataHelper.generateUpdatedUser(user, userVO);
        	userMapper.updateByPrimaryKey(updatedUser);
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
        }
	
	public CommandResult updatePassword(Integer id, String oldPassword, String newPassword) {
		if (StringUtils.isBlank(oldPassword)) {
			return new CommandResult(CommandCode.EMPTY_OLD_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_OLD_PASSWORD));
		}
		
		if (StringUtils.isBlank(newPassword)) {
			return new CommandResult(CommandCode.EMPTY_NEW_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_NEW_PASSWORD));
		}
		
		if (newPassword.compareTo(oldPassword) == 0) {
			return new CommandResult(CommandCode.NEW_PASSWORD_IDENTICAL_TO_OLD_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.NEW_PASSWORD_IDENTICAL_TO_OLD_PASSWORD));
		}
		
		User user = userMapper.selectByPrimaryKey(id);
		
		// Hash the old password
		String hashedPassword;
		try {
			hashedPassword = SHAHelper.generateHashedString(oldPassword);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
		
		// Check old password
		if (!StringUtils.equals(user.getLogPassword(), hashedPassword)) {
			return new CommandResult(CommandCode.INCORRECT_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.INCORRECT_PASSWORD));
		}
		
		// Hash the new password
		try {
			hashedPassword = SHAHelper.generateHashedString(newPassword);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
		
		user.setLogPassword(hashedPassword);
		userMapper.updateByPrimaryKey(user);
		
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}
	
	/*public DataQueryResult<JSONObject> getUsersByPaginationData(PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

		List<User> users = null;
		switch (paginationData.getQueryType()) {
		case NEXT_PAGE:
			users = userMapper.selectByPageAndAsc(paginationData.getQueryId(), paginationData.getCountPerPage());
			break;
		case PRE_PAGE:
			users = userMapper.selectByPageAndDesc(paginationData.getQueryId(), paginationData.getCountPerPage());
			break;
		default:
			return result;
		}

		result = new DataQueryResult<JSONObject>(userMapper.getTotalUserCount());
		result.setDataset(ConvertUsersToJSONObjects(users));
		
		return result;
	}*/
	
	/*public CommandResult getUserNamesByRoleType(Integer roleType) {
		List<JSONObject> userNameList = new ArrayList<JSONObject>();
		HashMap<Integer, HashMap<String, Object>> userNameMap = userMapper.selectByRoleId(roleType);
		userNameMap.forEach((k, v) -> {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", v.get("id"));
			jsonObject.put("name", v.get("name"));
			userNameList.add(jsonObject);
		});
		
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), userNameList);
	}*/

	/*public CommandResult createUser(UserViewObject userViewObject) {
		if (StringUtils.isBlank(userViewObject.getLogName())) {
			return new CommandResult(CommandCode.EMPTY_USER_LOGNAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_USER_LOGNAME));
		}

		if (StringUtils.isBlank(userViewObject.getPassword())) {
			return new CommandResult(CommandCode.EMPTY_PASSWORD.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_PASSWORD));
		}

		if (StringUtils.isBlank(userViewObject.getName())) {
			return new CommandResult(CommandCode.EMPTY_USER_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_USER_NAME));
		}

		// Check if user exists
		User user = userMapper.selectByLogName(userViewObject.getLogName());
		if (user != null) {
			return new CommandResult(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS));
		}

		// Check if existing user has same name
		user = userMapper.selectByName(userViewObject.getName());
		if (user != null) {
			return new CommandResult(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS));
		}

		// Create user in database by using mapper
		user = UserDataHelper.ConvertUserViewObjectToUser(userViewObject);
		userMapper.insert(user);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}*/	
}
