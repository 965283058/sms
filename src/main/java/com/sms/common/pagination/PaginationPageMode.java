package com.sms.common.pagination;

public enum PaginationPageMode {
    NEXT_PAGE(1), 
    PRE_PAGE(2);

    private Integer value;

    public static PaginationPageMode valueOf(Integer value) 
    {
	switch (value) 
	{
	case 1:
	    return NEXT_PAGE;
	case 2:
	    return PRE_PAGE;
	default:
	    return null;
	}
    }

    private PaginationPageMode(int value) 
    {
	this.value = value;
    }

    public int value() 
    {
	return value;
    }
}
