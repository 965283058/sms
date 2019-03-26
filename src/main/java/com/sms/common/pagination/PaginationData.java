package com.sms.common.pagination;

import java.util.Date;

public class PaginationData 
{
    private Integer queryId;
    private Date queryTime;
    private Integer countPerPage;
    private PaginationQueryType queryType;
    private PaginationPageMode pageMode;
    
    public PaginationData(PaginationQueryType queryType, PaginationPageMode pageMode, Integer countPerPage)
    {
	this.queryType = queryType;
	this.pageMode = pageMode;
	this.countPerPage = countPerPage;
    }

    public Integer getQueryId() {
	return queryId;
    }
	
    public void setQueryId(Integer queryId) {
	this.queryId = queryId;
    }
	
    public Date getQueryTime() {
	return queryTime;
    }

    public void setQueryTime(Date queryTime) {
	this.queryTime = queryTime;
    }
    
    public PaginationQueryType getQueryType() {
	return queryType;
    }
	
    public PaginationPageMode getPageMode() {
	return pageMode;
    }

    public int getCountPerPage() {
	return countPerPage;
    }
}
