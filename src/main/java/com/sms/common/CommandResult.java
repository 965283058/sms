package com.sms.common;

public class CommandResult 
{
	private Integer code;
	private String message;
	private Object data;

	public Integer getCode() 
	{
		return code;
	}

	public String getMessage() 
	{
		return message;
	}

	public Object getData() 
	{
		return data;
	}

	public CommandResult(Integer code, String message) 
	{
		this.code = code;
		this.message = message == null ? null : message.trim();
		data = null;
	}

	public CommandResult(Integer code, String message, Object data) 
	{
		this.code = code;
		this.message = message == null ? null : message.trim();
		this.data = data;
	}
}
