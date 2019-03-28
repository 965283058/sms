package com.sms.vo;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InformationVO {
    private Integer id;
    private String title;
    private Integer informationTypeId;
    private String informationTypeName;
    private Integer informationSubtypeId;
    private String informationSubtypeName;
    private Integer userId;
    private String userName;
    private Date createdTime;
    private String contentFilePath;
    private String photoUrl;
    private Integer schoolId;
    private String schoolName;
    private Integer branchSchoolId;
    private String branchSchoolName;
    private Integer managementStatusId;

    public InformationVO() {

    }

    public InformationVO(JSONObject jsonObject) {
        if (jsonObject.has("id")) {
            this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
        }
        if (jsonObject.has("title")) {
            this.setTitle(jsonObject.get("title").equals(null) ? null : jsonObject.getString("title"));
        }
        if (jsonObject.has("information_type_id")) {
            this.setInformationTypeId(jsonObject.get("information_type_id").equals(null) ? null : jsonObject.getInt("information_type_id"));
        }
        if (jsonObject.has("information_subtype_id")) {
            this.setInformationSubtypeId(jsonObject.get("information_subtype_id").equals(null) ? null : jsonObject.getInt("information_subtype_id"));
        }
        if (jsonObject.has("content_file_path")) {
            this.setContentFilePath(jsonObject.get("content_file_path").equals(null) ? null : jsonObject.getString("content_file_path"));
        }
        if (jsonObject.has("photo_url")) {
            this.setPhotoUrl(jsonObject.get("photo_url").equals(null) ? null : jsonObject.getString("photo_url"));
        }
        if (jsonObject.has("school_id")) {
            this.setSchoolId(jsonObject.get("school_id").equals(null) ? null : jsonObject.getInt("school_id"));
        }
        if (jsonObject.has("branch_school_id")) {
            this.setBranchSchoolId(jsonObject.get("branch_school_id").equals(null) ? null : jsonObject.getInt("branch_school_id"));
        }
        if (jsonObject.has("management_status_id")) {
            this.setManagementStatusId(jsonObject.get("management_status_id").equals(null) ? null : jsonObject.getInt("management_status_id"));
        }
    }

    public JSONObject serializeToJSONObject() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("title", title);
        jsonObject.put("information_type_id", informationTypeId);
        jsonObject.put("information_type_name", informationTypeName);
        jsonObject.put("information_subtype_id", informationSubtypeId);
        jsonObject.put("information_subtype_name", informationSubtypeName);
        jsonObject.put("user_id", userId);
        jsonObject.put("user_name", userName);
        jsonObject.put("created_time", sdf.format(createdTime));
        jsonObject.put("content_file_path", contentFilePath);
        jsonObject.put("photo_url", photoUrl);
        jsonObject.put("school_id", schoolId);
        jsonObject.put("school_name", schoolName);
        jsonObject.put("branch_school_id", branchSchoolId);
        jsonObject.put("branch_school_name", branchSchoolName);
        jsonObject.put("management_status_id", managementStatusId);
        return jsonObject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInformationTypeId() {
        return informationTypeId;
    }

    public void setInformationTypeId(Integer informationTypeId) {
        this.informationTypeId = informationTypeId;
    }

    public String getInformationTypeName() {
        return informationTypeName;
    }

    public void setInformationTypeName(String informationTypeName) {
        this.informationTypeName = informationTypeName;
    }

    public Integer getInformationSubtypeId() {
        return informationSubtypeId;
    }

    public void setInformationSubtypeId(Integer informationSubtypeId) {
        this.informationSubtypeId = informationSubtypeId;
    }

    public String getInformationSubtypeName() {
        return informationSubtypeName;
    }

    public void setInformationSubtypeName(String informationSubtypeName) {
        this.informationSubtypeName = informationSubtypeName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContentFilePath() {
        return contentFilePath;
    }

    public void setContentFilePath(String contentFilePath) {
        this.contentFilePath = contentFilePath;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public Integer getManagementStatusId() {
        return managementStatusId;
    }

    public void setManagementStatusId(Integer managementStatusId) {
        this.managementStatusId = managementStatusId;
    }

}
