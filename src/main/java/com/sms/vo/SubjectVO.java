package com.sms.vo;

import net.sf.json.JSONObject;

public class SubjectVO {
    private Integer id;
    private Integer schoolId;
    private String schoolName;
    private String name;
    private Integer managementStatusId;

    public SubjectVO(){ }

    public SubjectVO(JSONObject jsonObject){
        if (jsonObject.has("id")) {
            this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
        }
        if (jsonObject.has("name")) {
            this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
        }
        if(jsonObject.has("school_id")){
            this.setSchoolId(jsonObject.get("school_id").equals(null) ? null : jsonObject.getInt("school_id"));
        }
        if(jsonObject.has("school_name")){
            this.setSchoolName(jsonObject.get("school_name").equals(null) ? null : jsonObject.getString("school_name"));
        }
        if(jsonObject.has("management_status_id")){
            this.setManagementStatusId(jsonObject.get("management_status_id").equals(null) ? null : jsonObject.getInt("management_status_id"));
        }
    }
    public JSONObject serializeToJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("school_id", schoolId);
        jsonObject.put("school_name",schoolName);
        jsonObject.put("management_status_id",managementStatusId);
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagementStatusId() {
        return managementStatusId;
    }

    public void setManagementStatusId(Integer managementStatusId) {
        this.managementStatusId = managementStatusId;
    }
}
