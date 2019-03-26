package com.sms.vo;

import java.util.Date;

import com.sms.common.helper.DateTimeHelper;

import net.sf.json.JSONObject;

public class MemberVO {
	private Integer id;
	private Boolean idIsPresented = false;
	
	private String logName;
	private Boolean logNameIsPresented = false;

	private String logPassword;
	private Boolean logPasswordIsPresented = false;

	private String telephoneNumber;
	private Boolean telephoneNumberIsPresented = false;
	
	private String name;
	private Boolean nameIsPresented = false;
	
	private String nickName;
	private Boolean nickNameIsPresented = false;
	
	private Byte gender;
	private Boolean genderIsPresented = false;
	
	private Date birthday;
	private Boolean birthdayIsPresented = false;

	private String ethnicity;
	private Boolean ethnicityIsPresented = false;
	
	private String photoUrl;
	private Boolean photoUrlIsPresented = false;
	
	private String homeAddress;
	private Boolean homeAddressIsPresented = false;
	
	private String fatherName;
	private Boolean fatherNameIsPresented = false;
	
	private String fatherTelephoneNumber;
	private Boolean fatherTelephoneNumberIsPresented = false;
	
	private String motherName;
	private Boolean motherNameIsPresented = false;
	
	private String motherTelephoneNumber;
	private Boolean motherTelephoneNumberIsPresented = false;
	
	private Integer groupId;
	private Boolean groupIdIsPresented = false;
	private String groupName;

	private Integer branchSchoolId;
	private String branchSchoolName;

	private Integer schoolId;
	private String schoolName;

	private Integer managementStatus;
	private Boolean managementStatusIsPresented = false;
	
	public MemberVO() {
	}
	
	public MemberVO(JSONObject jsonObject) {
		if (jsonObject.has("id")) {
			this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
		}
		if (jsonObject.has("log_name")) {
			this.setLogName(jsonObject.get("log_name").equals(null) ? null : jsonObject.getString("log_name"));
		}
		if (jsonObject.has("log_password")) {
			this.setLogPassword(jsonObject.get("log_password").equals(null) ? null : jsonObject.getString("log_password"));
		}
		if (jsonObject.has("telephone_number")) {
			this.setTelephoneNumber(jsonObject.get("telephone_number").equals(null) ? null : jsonObject.getString("telephone_number"));
		}
		if (jsonObject.has("name")) {
			this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
		}
		if (jsonObject.has("nick_name")) {
			this.setNickName(jsonObject.get("nick_name").equals(null) ? null : jsonObject.getString("nick_name"));
		}
		if (jsonObject.has("gender")) {
			this.setGender(jsonObject.get("gender").equals(null) ? null : (byte)jsonObject.getInt("gender"));
		}
		if (jsonObject.has("birthday")) {
			String dateString = jsonObject.getString("birthday");
			this.setBirthday(DateTimeHelper.convertDateStringToDate(dateString));
		}
		if (jsonObject.has("ethnicity")) {
			this.setEthnicity(jsonObject.get("ethnicity").equals(null) ? null : jsonObject.getString("ethnicity"));
		}
		if (jsonObject.has("photo_url")) {
			this.setPhotoUrl(jsonObject.get("photo_url").equals(null) ? null : jsonObject.getString("photo_url"));
		}
		if (jsonObject.has("home_address")) {
			this.setHomeAddress(jsonObject.get("home_address").equals(null) ? null : jsonObject.getString("home_address"));
		}
		if (jsonObject.has("father_name")) {
			this.setFatherName(jsonObject.get("father_name").equals(null) ? null : jsonObject.getString("father_name"));
		}
		if (jsonObject.has("father_telephone_number")) {
			this.setFatherTelephoneNumber(jsonObject.get("father_telephone_number").equals(null) ? null : jsonObject.getString("father_telephone_number"));
		}
		if (jsonObject.has("mother_name")) {
			this.setMotherName(jsonObject.get("mother_name").equals(null) ? null : jsonObject.getString("mother_name"));
		}
		if (jsonObject.has("mother_telephone_number")) {
			this.setMotherTelephoneNumber(jsonObject.get("mother_telephone_number").equals(null) ? null : jsonObject.getString("mother_telephone_number"));
		}
		if (jsonObject.has("group_id")) {
			this.setGroupId(jsonObject.get("group_id").equals(null) ? null : jsonObject.getInt("group_id"));
		}
		if (jsonObject.has("management_status")) {
			this.setManagementStatus(jsonObject.get("management_status").equals(null) ? null : jsonObject.getInt("management_status"));
		}
	}
	
	public JSONObject serializeToJSONObject() {
		JSONObject jsonObject = new JSONObject();
		if (idIsPresented) {
			jsonObject.put("id", id);
		}
		if (logNameIsPresented) {
			jsonObject.put("log_name", logName);
		}
		if (logPasswordIsPresented) {
			jsonObject.put("log_password", logPassword);
		}
		if (telephoneNumberIsPresented) {
			jsonObject.put("telephone_number", telephoneNumber);
		}
		if (nameIsPresented) {
			jsonObject.put("name", name);
		}
		if (nickNameIsPresented) {
			jsonObject.put("nick_name", nickName);
		}
		if (genderIsPresented) {
			jsonObject.put("gender", gender);
		}
		if (birthdayIsPresented) {
			jsonObject.put("birthday", DateTimeHelper.convertDateToDateString(birthday));
		}
		if (ethnicityIsPresented) {
			jsonObject.put("ethnicity", ethnicity);
		}
		if (photoUrlIsPresented) {
			jsonObject.put("photo_url", photoUrl);
		}
		if (homeAddressIsPresented) {
			jsonObject.put("home_address", homeAddress);
		}
		if (fatherNameIsPresented) {
			jsonObject.put("father_name", fatherName);
		}
		if (fatherTelephoneNumberIsPresented) {
			jsonObject.put("father_telephone_number", fatherTelephoneNumber);
		}
		if (motherNameIsPresented) {
			jsonObject.put("mother_name", motherName);
		}
		if (motherTelephoneNumberIsPresented) {
			jsonObject.put("mother_telephone_number", motherTelephoneNumber);
		}
		if (groupIdIsPresented) {
			jsonObject.put("group_id", groupId);
			jsonObject.put("group_name",groupName);
			jsonObject.put("branch_school_id",branchSchoolId);
			jsonObject.put("branch_school_name",branchSchoolName);
			jsonObject.put("school_id",schoolId);
			jsonObject.put("school_name",schoolName);
		}
		if (managementStatusIsPresented) {
			jsonObject.put("management_status", managementStatus);
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

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
		this.logNameIsPresented = true;
	}

	public Boolean getLogNameIsPresented() {
		return logNameIsPresented;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
		this.logPasswordIsPresented = true;
	}

	public Boolean getLogPasswordIsPresented() {
		return logPasswordIsPresented;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
		this.nickNameIsPresented = true;
	}

	public Boolean getNickNameIsPresented() {
		return nickNameIsPresented;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
		this.genderIsPresented = true;
	}

	public Boolean getGenderIsPresented() {
		return genderIsPresented;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		this.birthdayIsPresented = true;
	}

	public Boolean getBirthdayIsPresented() {
		return birthdayIsPresented;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
		this.ethnicityIsPresented = true;
	}

	public Boolean getEthnicityIsPresented() {
		return ethnicityIsPresented;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		this.photoUrlIsPresented = true;
	}

	public Boolean getPhotoUrlIsPresented() {
		return photoUrlIsPresented;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
		this.homeAddressIsPresented = true;
	}

	public Boolean getHomeAddressIsPresented() {
		return homeAddressIsPresented;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
		this.fatherNameIsPresented = true;
	}

	public Boolean getFatherNameIsPresented() {
		return fatherNameIsPresented;
	}

	public String getFatherTelephoneNumber() {
		return fatherTelephoneNumber;
	}

	public void setFatherTelephoneNumber(String fatherTelephoneNumber) {
		this.fatherTelephoneNumber = fatherTelephoneNumber;
		this.fatherTelephoneNumberIsPresented = true;
	}

	public Boolean getFatherTelephoneNumberIsPresented() {
		return fatherTelephoneNumberIsPresented;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
		this.motherNameIsPresented = true;
	}

	public Boolean getMotherNameIsPresented() {
		return motherNameIsPresented;
	}

	public String getMotherTelephoneNumber() {
		return motherTelephoneNumber;
	}

	public void setMotherTelephoneNumber(String motherTelephoneNumber) {
		this.motherTelephoneNumber = motherTelephoneNumber;
		this.motherTelephoneNumberIsPresented = true;
	}

	public Boolean getMotherTelephoneNumberIsPresented() {
		return motherTelephoneNumberIsPresented;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
		this.groupIdIsPresented = true;
	}

	public Boolean getGroupIdIsPresented() {
		return groupIdIsPresented;
	}

	public Integer getManagementStatus() {
		return managementStatus;
	}

	public void setManagementStatus(Integer managementStatus) {
		this.managementStatus = managementStatus;
		this.managementStatusIsPresented = true;
	}

	public Boolean getManagementStatusIsPresented() {
		return managementStatusIsPresented;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getBranchSchoolId() {
		return branchSchoolId;
	}

	public void setBranchSchoolId(Integer branchSchoolId) {
		this.branchSchoolId = branchSchoolId;
	}

	public String getBranchSchoolName() {
		return branchSchoolName;
	}

	public void setBranchSchoolName(String branchSchoolName) {
		this.branchSchoolName = branchSchoolName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
