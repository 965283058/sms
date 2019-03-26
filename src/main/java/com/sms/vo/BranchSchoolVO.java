package com.sms.vo;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import com.sms.common.helper.DateTimeHelper;
import com.sms.common.helper.DayOfWeekHelper;

import net.sf.json.JSONObject;

public class BranchSchoolVO {
	private Integer id;
	private Boolean idIsPresented = false;

	private String name;
	private Boolean nameIsPresented = false;

	private String address;
	private Boolean addressIsPresented = false;
	
	private String telephoneNumber;
	private Boolean telephoneNumberIsPresented = false;

	private Integer directorId;
	private Boolean directorIdIsPresented = false;

	private String directorName;
	private Boolean directorNameIsPresented = false;

	private Integer schoolId;
	private Boolean schoolIdIsPresented = false;

	private Integer groupCount;
	private Boolean groupCountIsPresented = false;
	
	private List<DayOfWeek> monitorOpenDaysOfWeek;
	private Boolean monitorOpenDaysOfWeekIsPresented = false;
	
	private Date monitorStartTime;
	private Boolean monitorStartTimeIsPresented = false;
	
	private Date monitorEndTime;
	private Boolean monitorEndTimeIsPresented = false;

	public BranchSchoolVO() {
		groupCount = 0;
	}

	public BranchSchoolVO(JSONObject jsonObject) {
		if (jsonObject.has("id")) {
			this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
		}

		if (jsonObject.has("name")) {
			this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
		}

		if (jsonObject.has("address")) {
			this.setAddress(jsonObject.get("address").equals(null) ? null : jsonObject.getString("address"));
		}
		
		if (jsonObject.has("telephone_number")) {
			this.setTelephoneNumber(jsonObject.get("telephone_number").equals(null) ? null : jsonObject.getString("telephone_number"));
		}

		if (jsonObject.has("director_id")) {
			this.setDirectorId(jsonObject.get("director_id").equals(null) ? null : jsonObject.getInt("director_id"));
		}

		if (jsonObject.has("director_name")) {
			this.setDirectorName(jsonObject.get("director_name").equals(null) ? null : jsonObject.getString("director_name"));
		}

		if (jsonObject.has("school_id")) {
			this.setSchoolId(jsonObject.get("school_id").equals(null) ? null : jsonObject.getInt("school_id"));
		}

		if (jsonObject.has("group_count")) {
			this.setGroupCount(jsonObject.get("group_count").equals(null) ? null : jsonObject.getInt("group_count"));
		}
		
		if (jsonObject.has("monitor_open_days_of_week")) {
			this.setMonitorOpenDaysOfWeek(jsonObject.getString("monitor_open_days_of_week"));
		}
		
		if (jsonObject.has("monitor_start_time")) {
			String timeString = jsonObject.getString("monitor_start_time");
			this.setMonitorStartTime(DateTimeHelper.convertTimeStringToTime(timeString));
		}
		
		if (jsonObject.has("monitor_end_time")) {
			String timeString = jsonObject.getString("monitor_end_time");
			this.setMonitorEndTime(DateTimeHelper.convertTimeStringToTime(timeString));
		}
	}

	public JSONObject serializeToJSONObject() {
		JSONObject jsonObject = new JSONObject();

		if (idIsPresented) {
			jsonObject.put("id", id);
		}

		if (nameIsPresented) {
			jsonObject.put("name", name);
		}

		if (addressIsPresented) {
		    jsonObject.put("address", address);
		    
		}
		if (telephoneNumberIsPresented) {
			jsonObject.put("telephone_number", telephoneNumber);
		}

		if (directorIdIsPresented) {
			jsonObject.put("director_id", directorId);
		}

		if (directorNameIsPresented) {
			jsonObject.put("director_name", directorName);
		}

		if (schoolIdIsPresented) {
			jsonObject.put("school_id", schoolId);
		}

		if (groupCountIsPresented) {
			jsonObject.put("group_count", groupCount);
		}
		
		if (monitorOpenDaysOfWeekIsPresented) {
			jsonObject.put("monitor_open_days_of_week", DayOfWeekHelper.convertDayOfWeekListToDaysString(monitorOpenDaysOfWeek));
		}
		
		if (monitorStartTimeIsPresented) {
			jsonObject.put("monitor_start_time", DateTimeHelper.convertTimeToTimeString(monitorStartTime));
		}
		
		if (monitorEndTimeIsPresented) {
			jsonObject.put("monitor_end_time", DateTimeHelper.convertTimeToTimeString(monitorEndTime));
		}

		return jsonObject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.idIsPresented = true;
	}

	public Boolean getIdIsPresented() {
		return idIsPresented;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.nameIsPresented = true;
	}

	public Boolean getNameIsPresented() {
		return nameIsPresented;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	    this.addressIsPresented = true;
	}

	public Boolean getAddressIsPresented() {
	    return addressIsPresented;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
		this.telephoneNumberIsPresented = true;
	}

	public Boolean getTelephoneNumberIsPresented() {
		return telephoneNumberIsPresented;
	}

	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
		this.directorIdIsPresented = true;
	}

	public Boolean getDirectorIdIsPresented() {
		return directorIdIsPresented;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
		this.directorNameIsPresented = true;
	}

	public Boolean getDirectorNameIsPresented() {
		return directorNameIsPresented;
	}

	public Integer getSchoolId() {
	    return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
	    this.schoolId = schoolId;
	    this.schoolIdIsPresented = true;
	}

	public Boolean getSchoolIdIsPresented() {
	    return schoolIdIsPresented;
	}

	public Integer getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
		this.groupCountIsPresented = true;
	}

	public Boolean getGroupCountIsPresented() {
		return groupCountIsPresented;
	}

	public List<DayOfWeek> getMonitorOpenDaysOfWeek() {
		return monitorOpenDaysOfWeek;
	}

	public void setMonitorOpenDaysOfWeek(String monitorOpenDaysOfWeekString) {
		this.monitorOpenDaysOfWeek = DayOfWeekHelper.convertDaysStringToDayOfWeekList(monitorOpenDaysOfWeekString);
		this.monitorOpenDaysOfWeekIsPresented = true;
	}

	public Boolean getMonitorOpenDaysOfWeekIsPresented() {
		return monitorOpenDaysOfWeekIsPresented;
	}

	public Date getMonitorStartTime() {
		return monitorStartTime;
	}

	public void setMonitorStartTime(Date monitorStartTime) {
		this.monitorStartTime = monitorStartTime;
		this.monitorStartTimeIsPresented = true;
	}

	public Boolean getMonitorStartTimeIsPresented() {
		return monitorStartTimeIsPresented;
	}

	public Date getMonitorEndTime() {
		return monitorEndTime;
	}

	public void setMonitorEndTime(Date monitorEndTime) {
		this.monitorEndTime = monitorEndTime;
		this.monitorEndTimeIsPresented = true;
	}

	public Boolean getMonitorEndTimeIsPresented() {
		return monitorEndTimeIsPresented;
	}
}
