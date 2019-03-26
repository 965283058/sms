package com.sms.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.sms.model.User;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

public class UserDataHelper {
	public static UserVO convertUserToUserVO(User user) {
		UserVO userVO = new UserVO();

		userVO.setId(user.getId());
		userVO.setLogName(user.getLogName());
		userVO.setName(user.getName());
		userVO.setTelephoneNumber(user.getTelephoneNumber());
		// userVO.setRoleId(user.getRoleId());
		userVO.setStatus(user.getStatus());

		return userVO;
	}

	public static List<UserVO> convertUsersToUserVOs(List<User> users) {
		List<UserVO> userVOs = new ArrayList<UserVO>();

		users.stream().forEach(user -> {
			userVOs.add(convertUserToUserVO(user));
		});

		return userVOs;
	}

	public static User convertUserVOToUser(UserVO userVO) {
		User user = new User();

		if (userVO.getLogNameIsPresented()) {
			user.setLogName(userVO.getLogName());
		}

		if (userVO.getPasswordIsPresented()) {
			user.setLogPassword(userVO.getPassword());
		}

		if (userVO.getNameIsPresented()) {
			user.setName(userVO.getName());
		}

		if (userVO.getTelephoneNumberIsPresented()) {
			user.setTelephoneNumber(userVO.getTelephoneNumber());
		}

		/*
		 * if (userViewObject.getRoleIdIsPresented()) {
		 * user.setRoleId(userViewObject.getRoleId()); }
		 */

		if (userVO.getStatusIsPresented()) {
			user.setStatus(userVO.getStatus());
		}

		return user;
	}

	public static User generateUpdatedUser(User existingUser, UserVO userVO) {
		User user = new User();

		user.setId(existingUser.getId());
		user.setLogName(userVO.getLogNameIsPresented() ? userVO.getLogName() : existingUser.getLogName());
		user.setLogPassword(userVO.getPasswordIsPresented() ? userVO.getPassword() : existingUser.getLogPassword());
		user.setName(userVO.getNameIsPresented() ? userVO.getName() : existingUser.getName());
		user.setTelephoneNumber(userVO.getTelephoneNumberIsPresented() ? userVO.getTelephoneNumber() : existingUser.getTelephoneNumber());
		// user.setRoleId(userViewObject.getRoleIdIsPresented() ?
		// userViewObject.getRoleId() : existingUser.getRoleId());
		user.setStatus(userVO.getStatusIsPresented() ? userVO.getStatus() : existingUser.getStatus());

		return user;
	}

	public static List<JSONObject> convertUserVOsToJSONObject(List<UserVO> userVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

		userVOs.stream().forEach(userVO -> {
			jsonObjects.add(userVO.serializeToJSONObject());
		});

		return jsonObjects;
	}

	public static List<JSONObject> convertUsersToJSONObjects(List<User> users) {
		List<JSONObject> jsonObjects = null;

		if (users != null && users.size() > 0) {
			List<UserVO> userVOs = UserDataHelper.convertUsersToUserVOs(users);
			jsonObjects = convertUserVOsToJSONObject(userVOs);
		}

		return jsonObjects;
	}
}
