package com.sms.common.pagination;

public enum PaginationQueryType 
{
    BY_ID(1), 
    BY_CREATE_TIME(2);

    private int value;

    public static PaginationQueryType valueOf(Integer value) 
    {
	switch (value) 
	{
	case 1:
	    return BY_ID;
	case 2:
	    return BY_CREATE_TIME;
	default:
	    return null;
	}
    }

    private PaginationQueryType(Integer value) 
    {
	this.value = value;
    }

    public int value() 
    {
	return value;
    }
}
