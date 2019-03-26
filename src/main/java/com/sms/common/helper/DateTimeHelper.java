package com.sms.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateTimeHelper {
    static public Date convertTimeStringToTime(String timeString) {
	if (StringUtils.isBlank(timeString)) {
	    return null;
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    Date time;
	    try {
		time = sdf.parse(timeString);
	    } catch (ParseException e) {
		e.printStackTrace();
		return null;
	    }
	    return time;
	}
    }

    static public String convertTimeToTimeString(Date time) {
	if (time != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    return sdf.format(time);
	}
	return null;
    }

    static public Date convertDateStringToDate(String dateString) {
	if (StringUtils.isBlank(dateString)) {
	    return null;
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date time;
	    try {
		time = sdf.parse(dateString);
	    } catch (ParseException e) {
		e.printStackTrace();
		return null;
	    }
	    return time;
	}
    }

    static public String convertDateToDateString(Date date) {
	if (date != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(date);
	}
	return null;
    }

    static public Date convertDateTimeStringToDate(String datetimeString) {
	if (StringUtils.isBlank(datetimeString)) {
	    return null;
	} else {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date time;
	    try {
		time = sdf.parse(datetimeString);
	    } catch (ParseException e) {
		e.printStackTrace();
		return null;
	    }
	    return time;
	}
    }
    
    static public String convertDateToDateTimeString(Date date) {
	if (date != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    return sdf.format(date);
	}
	return null;
    }
}
