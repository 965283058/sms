package com.sms.vo;

import net.sf.json.JSONObject;

public class UserVO {
	private Integer id;
	private Boolean idIsPresented = false;

	private String logName;
	private Boolean logNameIsPresented = false;

	private String password;
	private Boolean passwordIsPresented = false;

	private String name;
	private Boolean nameIsPresented = false;

	private String telephoneNumber;
	private Boolean telephoneNumberIsPresented = false;

	// private Integer roleId;
	// private Boolean roleIdIsPresented = false;

	private Boolean status;
	private Boolean statusIsPresented = false;

	// private Integer schoolId;
	// private Boolean schoolIdIsPresented = false;

	// private Integer branchSchoolId;
	// private Boolean branchSchoolIdIsPresented = false;

	// private Integer groupId;
	// private Boolean groupIdIsPresented = false;

	public UserVO() {
	}

	public UserVO(JSONObject userJsonObject) {
		if (userJsonObject.has("id")) {
			this.setId(userJsonObject.get("id").equals(null) ? null : userJsonObject.getInt("id"));
		}

		if (userJsonObject.has("log_name")) {
			this.setLogName(userJsonObject.get("log_name").equals(null) ? null : userJsonObject.getString("log_name"));
		}

		if (userJsonObject.has("password")) {
			this.setPassword(userJsonObject.get("password").equals(null) ? null : userJsonObject.getString("password"));
		}

		if (userJsonObject.has("name")) {
			this.setName(userJsonObject.get("name").equals(null) ? null : userJsonObject.getString("name"));
		}

		if (userJsonObject.has("telephone_number")) {
			this.setTelephoneNumber(userJsonObject.get("telephone_number").equals(null) ? null
					: userJsonObject.getString("telephone_number"));
		}

		/*
		 * if (userJsonObject.has("role_id")) {
		 * this.setRoleId(userJsonObject.get("role_id").equals(null) ? null :
		 * userJsonObject.getInt("role_id")); }
		 */

		if (userJsonObject.has("status")) {
			this.setStatus(userJsonObject.get("status").equals(null) ? null : userJsonObject.getBoolean("status"));
		}

		/*
		 * if (userJsonObject.has("school_id")) {
		 * this.setSchoolId(userJsonObject.get("school_id").equals(null) ? null
		 * : userJsonObject.getInt("school_id")); }
		 */

		/*
		 * if (userJsonObject.has("branch_school_id")) {
		 * this.setBranchSchoolId(userJsonObject.get("branch_school_id").equals(
		 * null) ? null : userJsonObject.getInt("branch_school_id")); }
		 */

		/*
		 * if (userJsonObject.has("group_id")) {
		 * this.setGroupId(userJsonObject.get("group_id").equals(null) ? null :
		 * userJsonObject.getInt("group_id")); }
		 */
	}

	public JSONObject serializeToJSONObject() {
		JSONObject jsonObject = new JSONObject();

		if (idIsPresented) {
			jsonObject.put("id", id);
		}

		if (logNameIsPresented) {
			jsonObject.put("log_name", logName);
		}

		if (passwordIsPresented) {
			jsonObject.put("password", password);
		}

		if (nameIsPresented) {
			jsonObject.put("name", name);
		}

		if (telephoneNumberIsPresented) {
			jsonObject.put("telephone_number", telephoneNumber);
		}

		/*
		 * if (roleIdIsPresented) { jsonObject.put("role_id", roleId); }
		 */

		if (statusIsPresented) {
			jsonObject.put("status", status);
		}

		/*
		 * if (schoolIdIsPresented) { jsonObject.put("school_id", schoolId); }
		 */

		/*
		 * if (branchSchoolIdIsPresented) { jsonObject.put("branch_school_iId",
		 * branchSchoolId); }
		 */

		/*
		 * if (groupIdIsPresented) { jsonObject.put("group_iId", groupId); }
		 */

		return jsonObject;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getIdIsPresented() {
		return idIsPresented;
	}

	public void setId(Integer id) {
		this.id = id;
		this.idIsPresented = true;
	}

	public String getLogName() {
		return logName;
	}

	public Boolean getLogNameIsPresented() {
		return logNameIsPresented;
	}

	public void setLogName(String logName) {
		this.logName = logName;
		this.logNameIsPresented = true;
	}

	public Boolean getPasswordIsPresented() {
		return passwordIsPresented;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordIsPresented = true;
	}

	public String getName() {
		return name;
	}

	public Boolean getNameIsPresented() {
		return nameIsPresented;
	}

	public void setName(String name) {
		this.name = name;
		this.nameIsPresented = true;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public Boolean getTelephoneNumberIsPresented() {
		return telephoneNumberIsPresented;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
		this.telephoneNumberIsPresented = true;
	}

	/*
	 * public Integer getRoleId() { return roleId; }
	 */

	/*
	 * public Boolean getRoleIdIsPresented() { return roleIdIsPresented; }
	 */

	/*
	 * public void setRoleId(Integer roleId) { this.roleId = roleId;
	 * this.roleIdIsPresented = true; }
	 */

	public Boolean getStatus() {
		return status;
	}

	public Boolean getStatusIsPresented() {
		return statusIsPresented;
	}

	public void setStatus(Boolean status) {
		this.status = status;
		this.statusIsPresented = true;
	}

	/*
	 * public Integer getSchoolId() { return schoolId; }
	 */

	/*
	 * public Boolean getSchoolIdIsPresented() { return schoolIdIsPresented; }
	 */

	/*
	 * public void setSchoolId(Integer schoolId) { this.schoolId = schoolId;
	 * this.schoolIdIsPresented = true; }
	 */

	/*
	 * public Integer getBranchSchoolId() { return branchSchoolId; }
	 */

	/*
	 * public Boolean getBranchSchoolIdIsPresented() { return
	 * branchSchoolIdIsPresented; }
	 */

	/*
	 * public void setBranchSchoolId(Integer branchSchoolId) {
	 * this.branchSchoolId = branchSchoolId; this.branchSchoolIdIsPresented =
	 * true; }
	 */

	/*
	 * public Integer getGroupId() { return groupId; }
	 */

	/*
	 * public Boolean getGroupIdIsPresented() { return groupIdIsPresented; }
	 */

	/*
	 * public void setGroupId(Integer groupId) { this.groupId = groupId;
	 * this.groupIdIsPresented = true; }
	 */
}
