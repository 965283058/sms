package com.sms.vo;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SchoolInformationVO {
    private Integer id;
    private Integer schoolId;
    private String schoolName;
    private Integer informationTypeId;
    private String informationTypeName;
    private Integer informationSubtypeId;
    private String informationSubtypeName;
    private String title;
    private String introduction;
    private String photoUrl;
    private String content;
    private Date createdTime;
    private Integer managementStatusId;

    public SchoolInformationVO() {

    }

    public SchoolInformationVO(JSONObject jsonObject) {
        if (jsonObject.has("id")) {
            this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
        }
        if(jsonObject.has("school_id")){
            this.setSchoolId(jsonObject.get("school_id").equals(null) ? null : jsonObject.getInt("school_id"));
        }
        if (jsonObject.has("information_type_id")) {
            this.setInformationTypeId(jsonObject.get("information_type_id").equals(null) ? null : jsonObject.getInt("information_type_id"));
        }
        if (jsonObject.has("information_subtype_id")) {
            this.setInformationSubtypeId(jsonObject.get("information_subtype_id").equals(null) ? null : jsonObject.getInt("information_subtype_id"));
        }
        if (jsonObject.has("title")) {
            this.setTitle(jsonObject.get("title").equals(null) ? null : jsonObject.getString("title"));
        }
        if (jsonObject.has("introduction")){
            this.setIntroduction(jsonObject.get("introduction").equals(null) ? null : jsonObject.getString("introduction"));
        }
        if (jsonObject.has("content")) {
            this.setContent(jsonObject.get("content").equals(null) ? null : jsonObject.getString("content"));
        }
        if (jsonObject.has("photo_url")) {
            this.setPhotoUrl(jsonObject.get("photo_url").equals(null) ? null : jsonObject.getString("photo_url"));
        }
        if (jsonObject.has("management_status_id")) {
            this.setManagementStatusId(jsonObject.get("management_status_id").equals(null) ? null : jsonObject.getInt("management_status_id"));
        }
    }

    public JSONObject serializeToJSONObject() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("school_id", schoolId);
        jsonObject.put("information_type_id", informationTypeId);
        jsonObject.put("information_type_name", informationTypeName);
        jsonObject.put("information_subtype_id", informationSubtypeId);
        jsonObject.put("information_subtype_name", informationSubtypeName);
        jsonObject.put("title", title);
        jsonObject.put("introduction", introduction);
        jsonObject.put("created_time", sdf.format(createdTime));
        jsonObject.put("content", content);
        jsonObject.put("photo_url", photoUrl);
        jsonObject.put("school_name", schoolName);
        jsonObject.put("management_status_id", managementStatusId);
        return jsonObject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getInformationTypeId() {
        return informationTypeId;
    }

    public void setInformationTypeId(Integer informationTypeId) {
        this.informationTypeId = informationTypeId;
    }

    public Integer getInformationSubtypeId() {
        return informationSubtypeId;
    }

    public void setInformationSubtypeId(Integer informationSubtypeId) {
        this.informationSubtypeId = informationSubtypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getManagementStatusId() {
        return managementStatusId;
    }

    public void setManagementStatusId(Integer managementStatusId) {
        this.managementStatusId = managementStatusId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getInformationTypeName() {
        return informationTypeName;
    }

    public void setInformationTypeName(String informationTypeName) {
        this.informationTypeName = informationTypeName;
    }

    public String getInformationSubtypeName() {
        return informationSubtypeName;
    }

    public void setInformationSubtypeName(String informationSubtypeName) {
        this.informationSubtypeName = informationSubtypeName;
    }

}
