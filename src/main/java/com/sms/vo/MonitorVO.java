package com.sms.vo;

import java.util.Date;

import com.sms.common.helper.DateTimeHelper;

import net.sf.json.JSONObject;

public class MonitorVO {
    private Integer id;
    private Boolean idIsPresented = false;

    private String name;
    private Boolean nameIsPresented = false;
    
    private Date installDate;
    private Boolean installDateIsPresented = false;
    
    private String cameraAddress;
    private Boolean cameraAddressIsPresented = false;
    
    private String photoUrl;
    private Boolean photoUrlIsPresented = false;

    private String description;
    private Boolean descriptionIsPresented = false;
    
    private Integer groupId;
    private Boolean groupIdIsPresented = false;
    
    private String groupName;
    private Boolean groupNameIsPresented = false;
    
    public MonitorVO() {}
    
    public MonitorVO(JSONObject jsonObject) {
	if (jsonObject.has("id")) {
		this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
	}

	if (jsonObject.has("name")) {
		this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
	}

	if (jsonObject.has("install_date")) {
		String dateString = jsonObject.getString("install_date");
		this.setInstallDate(DateTimeHelper.convertDateStringToDate(dateString));
	}
	
	if (jsonObject.has("camera_address")) {
		this.setCameraAddress(jsonObject.get("camera_address").equals(null) ? null : jsonObject.getString("camera_address"));
	}
	
	if (jsonObject.has("photo_url")) {
		this.setPhotoUrl(jsonObject.get("photo_url").equals(null) ? null : jsonObject.getString("photo_url"));
	}
	
	if (jsonObject.has("description")) {
		this.setDescription(jsonObject.get("description").equals(null) ? null : jsonObject.getString("description"));
	}
	
	if (jsonObject.has("group_id")) {
		this.setGroupId(jsonObject.get("group_id").equals(null) ? null : jsonObject.getInt("group_id"));
	}

	if (jsonObject.has("group_name")) {
		this.setGroupName(jsonObject.get("group_name").equals(null) ? null : jsonObject.getString("group_name"));
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
	if (installDateIsPresented) {
	    jsonObject.put("install_date", DateTimeHelper.convertDateToDateString(installDate));
	}
	if (cameraAddressIsPresented) {
	    jsonObject.put("camera_address", cameraAddress);
	}
	if (photoUrlIsPresented) {
	    jsonObject.put("photo_url", photoUrl);
	}
	if (descriptionIsPresented) {
	    jsonObject.put("description", description);
	}
	if (groupIdIsPresented) {
	    jsonObject.put("group_id", groupId);
	}
	if (groupNameIsPresented) {
	    jsonObject.put("group_name", groupName);
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

    public Date getInstallDate() {
	return installDate;
    }

    public void setInstallDate(Date installDate) {
	this.installDate = installDate;
	installDateIsPresented = true;
    }

    public Boolean getInstallDateIsPresented() {
	return installDateIsPresented;
    }

    public String getCameraAddress() {
	return cameraAddress;
    }

    public void setCameraAddress(String cameraAddress) {
	this.cameraAddress = cameraAddress;
	this.cameraAddressIsPresented = true;
    }

    public Boolean getCameraAddressIsPresented() {
	return cameraAddressIsPresented;
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

    public String getGroupName() {
	return groupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
	this.groupNameIsPresented = true;
    }

    public Boolean getGroupNameIsPresented() {
	return groupNameIsPresented;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
	this.descriptionIsPresented = true;
    }

    public Boolean getDescriptionIsPresented() {
	return descriptionIsPresented;
    }
}
