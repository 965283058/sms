package com.sms.vo;

import java.util.Date;

import com.sms.common.helper.DateTimeHelper;

import net.sf.json.JSONObject;

public class GroupVO {
	private Integer id;
	private Boolean idIsPresented = false;

	private String name;
	private Boolean nameIsPresented = false;

	private Date startDate;
	private Boolean startDateIsPresented = false;
	
	private Integer groupLeaderId;
	private Boolean groupLeaderIsPresented = false;

	private String groupLeaderName;
	private Boolean groupLeaderNameIsPresented = false;
	
	private String groupLeaderTelephoneNumber;
	private Boolean groupLeaderTelephoneNumberIsPresented = false;

	private Integer branchSchoolId;
	private Boolean branchSchoolIdIsPresented = false;

	private Integer memberCount;
	private Boolean memberCountIsPresented = false;

	public GroupVO() {
		memberCount = 0;
	}

	public GroupVO(JSONObject jsonObject) {
		if (jsonObject.has("id")) {
			this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
		}

		if (jsonObject.has("name")) {
			this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
		}

		if (jsonObject.has("start_date")) {
			String dateString = jsonObject.getString("start_date");
			this.setStartDate(DateTimeHelper.convertDateStringToDate(dateString));
		}
		
		if (jsonObject.has("group_leader_id")) {
			this.setGrouLeaderId(jsonObject.get("group_leader_id").equals(null) ? null : jsonObject.getInt("group_leader_id"));
		}

		if (jsonObject.has("group_leader_name")) {
			this.setGroupLeaderName(jsonObject.get("group_leader_name").equals(null) ? null : jsonObject.getString("group_leader_name"));
		}

		if (jsonObject.has("group_leader_telephone_number")) {
			this.setGroupLeaderTelephoneNumber(jsonObject.get("group_leader_telephone_number").equals(null) ? null : jsonObject.getString("group_leader_telephone_number"));
		}
		
		if (jsonObject.has("branch_school_id")) {
			this.setBranchSchoolId(jsonObject.get("branch_school_id").equals(null) ? null : jsonObject.getInt("branch_school_id"));
		}

		if (jsonObject.has("member_count")) {
			this.setMemberCount(jsonObject.get("member_count").equals(null) ? null : jsonObject.getInt("member_count"));
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
		
		if (startDateIsPresented) {
			jsonObject.put("start_date", DateTimeHelper.convertDateToDateString(startDate));
		}

		if (groupLeaderIsPresented) {
			jsonObject.put("group_leader_id", groupLeaderId);
		}

		if (groupLeaderNameIsPresented) {
			jsonObject.put("group_leader_name", groupLeaderName);
		}
		
		if (groupLeaderTelephoneNumberIsPresented) {
			jsonObject.put("group_leader_telephone_number", groupLeaderTelephoneNumber);
		}

		if (branchSchoolIdIsPresented) {
			jsonObject.put("branch_school_id", branchSchoolId);
		}

		if (memberCountIsPresented) {
			jsonObject.put("member_count", memberCount);
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.startDateIsPresented = true;
	}

	public Boolean getStartDateIsPresented() {
		return startDateIsPresented;
	}
	
	public Integer getGroupLeaderId() {
		return groupLeaderId;
	}

	public void setGrouLeaderId(Integer groupLeaderId) {
		this.groupLeaderId = groupLeaderId;
		this.groupLeaderIsPresented = true;
	}

	public Boolean getGroupLeaderIdIsPresented() {
		return groupLeaderIsPresented;
	}

	public String getGroupLeaderName() {
		return groupLeaderName;
	}

	public void setGroupLeaderName(String groupLeaderName) {
		this.groupLeaderName = groupLeaderName;
		this.groupLeaderNameIsPresented = true;
	}

	public Boolean getGroupLeaderNameIsPresented() {
		return groupLeaderNameIsPresented;
	}
	
	public String getGroupLeaderTelephoneNumber() {
	    return groupLeaderTelephoneNumber;
	}

	public void setGroupLeaderTelephoneNumber(String groupLeaderTelephoneNumber) {
	    this.groupLeaderTelephoneNumber = groupLeaderTelephoneNumber;
	    this.groupLeaderTelephoneNumberIsPresented = true;
	}

	public Boolean getGroupLeaderTelephoneNumberIsPresented() {
	    return groupLeaderTelephoneNumberIsPresented;
	}

	public Integer getBranchSchoolId() {
		return branchSchoolId;
	}

	public void setBranchSchoolId(Integer branchSchoolId) {
		this.branchSchoolId = branchSchoolId;
		this.branchSchoolIdIsPresented = true;
	}

	public Boolean getBranchSchoolIdIsPresented() {
		return branchSchoolIdIsPresented;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
		this.memberCountIsPresented = true;
	}

	public Boolean getMemberCountIsPresented() {
		return memberCountIsPresented;
	}
}
