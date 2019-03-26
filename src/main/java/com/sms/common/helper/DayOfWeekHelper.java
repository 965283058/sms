package com.sms.common.helper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

public class DayOfWeekHelper {
	static public List<DayOfWeek> convertDaysStringToDayOfWeekList(String daysString) {
		if (!StringUtils.isBlank(daysString)) {
			List<DayOfWeek> dowList = new ArrayList<DayOfWeek>();
			JSONArray dowObjectArray = JSONArray.fromObject(daysString);
			dowObjectArray.forEach(d -> dowList.add(DayOfWeek.valueOf(d.toString())));
			return dowList;
		}
		return null;
	}
	
	static public String convertDayOfWeekListToDaysString(List<DayOfWeek> daysOfWeek) {
		if (daysOfWeek == null || daysOfWeek.isEmpty()) {
			return "[]";
		}
		else {
			JSONArray dowArray = JSONArray.fromObject(daysOfWeek);
			return dowArray.toString();
		}
	}
}
