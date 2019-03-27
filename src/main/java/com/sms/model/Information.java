package com.sms.model;


import java.util.Date;


public class Information {
    private Integer id;

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

}