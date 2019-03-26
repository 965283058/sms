package com.sms.vo;

import net.sf.json.JSONObject;

public class SchoolVO {
	private Integer id;
	private Boolean idIsPresented = false;

	private String name;
	private Boolean nameIsPresented = false;

	private String telephoneNumber;
	private Boolean telephoneNumberIsPresented = false;

	private Integer presidentId;
	private Boolean presidentIdIsPresented = false;

	private String presidentName;
	private Boolean presidentNameIsPresented = false;

	private Integer schoolType;
	private Boolean schoolTypeIsPresented = false;

	private Integer branchSchoolCount;
	private Boolean branchSchoolCountIsPresented = false;

	public SchoolVO() {
		branchSchoolCount = 0;
	}

	public SchoolVO(JSONObject jsonObject) {
		if (jsonObject.has("id")) {
			this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
		}

		if (jsonObject.has("name")) {
			this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
		}

		if (jsonObject.has("telephone_number")) {
			this.setTelephoneNumber(jsonObject.get("telephone_number").equals(null) ? null : jsonObject.getString("telephone_number"));
		}

		if (jsonObject.has("president_id")) {
			this.setPresidentId(jsonObject.get("president_id").equals(null) ? null : jsonObject.getInt("president_id"));
		}

		if (jsonObject.has("president_name")) {
			this.setPresidentName(jsonObject.get("president_name").equals(null) ? null : jsonObject.getString("president_name"));
		}

		if (jsonObject.has("school_type")) {
			this.setSchoolType(jsonObject.get("school_type").equals(null) ? null : jsonObject.getInt("school_type"));
		}

		if (jsonObject.has("branch_school_count")) {
			this.setBranchSchoolCount(jsonObject.get("branch_school_count").equals(null) ? null : jsonObject.getInt("branch_school_count"));
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

		if (telephoneNumberIsPresented) {
			jsonObject.put("telephone_number", telephoneNumber);
		}

		if (presidentIdIsPresented) {
			jsonObject.put("president_id", presidentId);
		}

		if (presidentNameIsPresented) {
			jsonObject.put("president_name", presidentName);
		}

		if (schoolTypeIsPresented) {
			jsonObject.put("school_type", schoolType);
		}

		if (branchSchoolCountIsPresented) {
			jsonObject.put("branch_school_count", branchSchoolCount);
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

	public Integer getPresidentId() {
		return presidentId;
	}

	public void setPresidentId(Integer presidentId) {
		this.presidentId = presidentId;
		this.presidentIdIsPresented = true;
	}

	public Boolean getPresidentIdIsPresented() {
		return presidentIdIsPresented;
	}

	public String getPresidentName() {
		return presidentName;
	}

	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
		this.presidentNameIsPresented = true;
	}

	public Boolean getPresidentNameIsPresented() {
		return presidentNameIsPresented;
	}

	public Integer getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
		this.schoolTypeIsPresented = true;
	}

	public Boolean getSchoolTypeIsPresented() {
		return schoolTypeIsPresented;
	}

	public Integer getBranchSchoolCount() {
		return branchSchoolCount;
	}

	public void setBranchSchoolCount(Integer branchSchoolCount) {
		this.branchSchoolCount = branchSchoolCount;
		this.branchSchoolCountIsPresented = true;
	}

	public Boolean getBranchSchoolCountIsPresented() {
		return branchSchoolCountIsPresented;
	}
}
