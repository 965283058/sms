package com.sms.common;

public enum RoleType 
{
	UNKONW(-1),
	ADMINISTRATOR(0), 
	PRESIDENT(1), 
    DIRECTOR(2), 
    GROUOP_LEADER(3);

    private int value;

    private RoleType(int value) 
    {
    	this.value = value;
    }

    public int getValue() 
    {
    	return value;
    }
    
    public static boolean isValidRoleType(int value) {
    	RoleType[] roleTypes = RoleType.values();
    	for (RoleType type: roleTypes) {
    		if (type.getValue() == value) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static RoleType getRoleType(int value) {
    	RoleType[] roleTypes = RoleType.values();
    	for (RoleType type: roleTypes) {
    		if (type.getValue() == value) {
    			return type;
    		}
    	}
    	return RoleType.UNKONW;
    }
}
