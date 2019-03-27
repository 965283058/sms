package com.sms.common;

import java.util.HashMap;
import java.util.Map;

public class CommandCodeDictionary 
{
	private static final Map<CommandCode, String> commandCodeMap = new HashMap<>();
	
	static 
	{
		commandCodeMap.put(CommandCode.INTERNAL_ERROR, "系统错误。"); // "Unknown error"
		commandCodeMap.put(CommandCode.OK, "操作成功。"); //"OK"
		commandCodeMap.put(CommandCode.EMPTY_REQUEST_BODY, "请求命令数据为空。"); // "Empty http request body."
		commandCodeMap.put(CommandCode.USER_NOT_LOGGED_IN, "用户还没有登录。"); // "User has not logged in."
		commandCodeMap.put(CommandCode.NO_ACCESS_PERMISSION, "您没有改操作权限。"); // "User has no access permission."
		commandCodeMap.put(CommandCode.PASSWORD_HASHING_FAILED, "密码哈希失败。"); //"Failed to hash password"
		
		commandCodeMap.put(CommandCode.MISSING_ID, "ID为空"); // "Missing ID."
		commandCodeMap.put(CommandCode.SCHOOL_ID_NOT_EXIST, "幼儿园号不存在。"); // "Missing school ID."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_ID_NOT_EXIST, "分园号不存在。"); // "Missing branch school ID."
		commandCodeMap.put(CommandCode.GROUP_ID_NOT_EXIST, "班级号不存在。"); // "Missing group school ID."
		
		commandCodeMap.put(CommandCode.EMPTY_USER_LOGNAME, "用户登录名不能为空。"); // "User log name is empty."
		commandCodeMap.put(CommandCode.EMPTY_PASSWORD, "密码不能为空。"); // "Password is empty."
		commandCodeMap.put(CommandCode.USER_NOT_EXIST, "用户不存在。"); // "User does not exist."
		commandCodeMap.put(CommandCode.INCORRECT_PASSWORD, "密码错误。"); // "Incorrect password."
		commandCodeMap.put(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS, "用户已存在。"); // "User with same log name already exists."
		commandCodeMap.put(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS, "用户已存在。"); // "User with same name already exists."
		commandCodeMap.put(CommandCode.EMPTY_USER_NAME, "用户姓名不能为空。"); // "User name is empty."
		commandCodeMap.put(CommandCode.UNKNOWN_ROLE_TYPE, "无效的职位类型。"); // "Unknown role type."
		commandCodeMap.put(CommandCode.EMPTY_OLD_PASSWORD, "旧密码不能为空。"); // "Old password is empty."
		commandCodeMap.put(CommandCode.EMPTY_NEW_PASSWORD, "新密码不能为空。"); // "New password is empty."
		commandCodeMap.put(CommandCode.NEW_PASSWORD_IDENTICAL_TO_OLD_PASSWORD, "旧密码和新密码必须不同。"); // "New password is identical to old password."
		
		commandCodeMap.put(CommandCode.EMPTY_SCHOOL_NAME, "幼儿园名称不能为空。"); // "School name is empty."
		commandCodeMap.put(CommandCode.SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS, "该幼儿园已存在。"); // "School with same name already exists."
		commandCodeMap.put(CommandCode.SCHOOL_NOT_EXIST, "该幼儿园不存在。"); //"School does not exist."
		
		commandCodeMap.put(CommandCode.EMPTY_BRANCH_SCHOOL_NAME, "分园名称不能为空。"); // "Branch school name is empty."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS, "该分园已存在。"); // "Branch school with same name already exists."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_NOT_EXIST, "该分园不存在。"); //"Branch school does not exist."
		
		commandCodeMap.put(CommandCode.EMPTY_GROUP_NAME, "班级名称不能为空。"); // "Group name is empty."
		commandCodeMap.put(CommandCode.GROUP_WITH_SAME_NAME_ALREADY_EXISTS, "该班级已存在。"); // "Group with same name already exists."
		commandCodeMap.put(CommandCode.GROUP_NOT_EXIST, "该班级不存在。"); //"Group does not exist."
		
		commandCodeMap.put(CommandCode.MONITOR_NOT_EXIST, "该监控不存在。"); //"Monitor does not exist."
		commandCodeMap.put(CommandCode.EMPTY_MONITOR_NAME, "监控名称不能为空。"); // "Monitor name is empty."
		commandCodeMap.put(CommandCode.EMPTY_CAMERA_ADDRESS, "视频地址不能为空。"); // "Camera address is empty."
		commandCodeMap.put(CommandCode.MONITOR_WITH_SAME_NAME_ALREADY_EXISTS, "该监控已存在。"); // "Monitor with same name already exists."
		commandCodeMap.put(CommandCode.MONITOR_PHOTO_NOT_EXIST, "该监控上传的图片不存在。"); // "The uploaded monitor photo does not exist."

		commandCodeMap.put(CommandCode.EMPTY_MEMBER_NAME, "会员名称不能为空。");
		commandCodeMap.put(CommandCode.MEMBER_WITH_SAME_NAME_ALREADY_EXISTS,"该会员已存在。");
		commandCodeMap.put(CommandCode.MEMBER_NOT_EXIST, "该会员不存在。");

		commandCodeMap.put(CommandCode.EMPTY_FEE_NAME,"资费名称不能为空。");
		commandCodeMap.put(CommandCode.FEE_NOT_EXIST,"该资费不存在。");
		commandCodeMap.put(CommandCode.FEE_TYPE_NOT_EXIST,"该资费类型不存在。");

		commandCodeMap.put(CommandCode.INFORMATION_NOT_EXIST,"该通知不存在。");
		commandCodeMap.put(CommandCode.EMPTY_UPLOAD_FILE, "上传文件为空。"); // "Upload file is empty."
	}
	
	public static String getCodeMessage(CommandCode code)
	{
		return commandCodeMap.get(code);
	}
}
