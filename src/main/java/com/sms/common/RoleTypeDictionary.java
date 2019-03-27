package com.sms.common;

import java.util.HashMap;
import java.util.Map;

public class RoleTypeDictionary 
{
	private static final Map<RoleType, String> roleTypeMap = new HashMap<>();
	
	static 
	{
		roleTypeMap.put(RoleType.ADMINISTRATOR, "系统管理员");
		roleTypeMap.put(RoleType.PRESIDENT, "校长");
		roleTypeMap.put(RoleType.DIRECTOR, "园长");
		roleTypeMap.put(RoleType.GROUOP_LEADER, "班主任");
	}
	
	public static String getRoleTypeName(RoleType roleType)
	{
		return roleTypeMap.get(roleType);
	}
}
