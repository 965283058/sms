package com.sms.common;

public enum CommandCode 
{
    // Common error code
    INTERNAL_ERROR(-1), 
    OK(0), 
    EMPTY_REQUEST_BODY(1),
    USER_NOT_LOGGED_IN(2),
    NO_ACCESS_PERMISSION(3),
    PASSWORD_HASHING_FAILED(4),
    
    MISSING_ID(10),
    SCHOOL_ID_NOT_EXIST(11),
    BRANCH_SCHOOL_ID_NOT_EXIST(12),
    GROUP_ID_NOT_EXIST(13),

    // User error code
    EMPTY_USER_LOGNAME(1000), 
    EMPTY_PASSWORD(1001), 
    USER_NOT_EXIST(1002), 
    INCORRECT_PASSWORD(1003),
    USER_WITH_SAME_LOGNAME_ALREADY_EXISTS(1004),
    USER_WITH_SAME_NAME_ALREADY_EXISTS(1005),
    EMPTY_USER_NAME(1006),
    UNKNOWN_ROLE_TYPE(1007),
    EMPTY_OLD_PASSWORD(1008),
    EMPTY_NEW_PASSWORD(1009),
    NEW_PASSWORD_IDENTICAL_TO_OLD_PASSWORD(1100),
    
    // School error code
    EMPTY_SCHOOL_NAME(2000),
    SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS(2001),
    SCHOOL_NOT_EXIST(2002),
	
    // Branch school error code
    EMPTY_BRANCH_SCHOOL_NAME(3000),
    BRANCH_SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS(3001),
    BRANCH_SCHOOL_NOT_EXIST(3002),
    
    // Group error code
    EMPTY_GROUP_NAME(4000),
    GROUP_WITH_SAME_NAME_ALREADY_EXISTS(4001),
    GROUP_NOT_EXIST(4002),
    
    // Monitor error code
    MONITOR_NOT_EXIST(5000),
    EMPTY_MONITOR_NAME(5001),
    EMPTY_CAMERA_ADDRESS(5002),
    MONITOR_WITH_SAME_NAME_ALREADY_EXISTS(5003),
    MONITOR_PHOTO_NOT_EXIST(5004),

    //Member error code
    EMPTY_MEMBER_NAME(7000),
    MEMBER_WITH_SAME_NAME_ALREADY_EXISTS(7001),
    MEMBER_NOT_EXIST(7002),

    //Fee
    EMPTY_FEE_NAME(8000),
    FEE_NOT_EXIST(8002),
    // File upload error code

    //Fee type
    FEE_TYPE_NOT_EXIST(8102),

    EMPTY_UPLOAD_FILE(6000);

    private int value;

    private CommandCode(int value) 
    {
    	this.value = value;
    }

    public int getCode() 
    {
    	return value;
    }
};
