package com.sms.common;

import java.util.List;

public class DataQueryResult<T> 
{
	private int totalCount;
	private List<T> dataset;

	public DataQueryResult(int totalCount) 
	{
		this.totalCount = totalCount;
		this.dataset = null;
	}

	public int getTotalCount() 
	{
		return totalCount;
	}

	public List<T> getDataset() 
	{
		return dataset;
	}

	public void setDataset(List<T> dataset) 
	{
		this.dataset = dataset;
	}
}
