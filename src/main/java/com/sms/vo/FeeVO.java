package com.sms.vo;

import net.sf.json.JSONObject;

import java.math.BigDecimal;

public class FeeVO {

    private Integer id;
    private String name;
    private Integer feeTypeId;
    private String feeTypeName;
    private Integer courseId;
    private String courseName;
    private Integer schoolId;
    private String schoolName;
    private Integer branchSchoolId;
    private String branchSchoolName;
    private Integer groupId;
    private String groupName;
    private Integer memberId;
    private String memberName;
    private BigDecimal price;
    private Integer managementStatusId;
    public FeeVO(){}
    public FeeVO(JSONObject jsonObject){
        if (jsonObject.has("id")) {
            this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
        }
        if (jsonObject.has("name")) {
            this.setName(jsonObject.get("name").equals(null) ? null : jsonObject.getString("name"));
        }
        if (jsonObject.has("fee_type_id")){
            this.setFeeTypeId(jsonObject.get("fee_type_id").equals(null) ? null : jsonObject.getInt("fee_type_id"));
        }
        if (jsonObject.has("fee_type_name")){
            this.setFeeTypeName(jsonObject.get("fee_type_name").equals(null) ? null : jsonObject.getString("fee_type_name"));
        }
        if (jsonObject.has("course_id")){
            this.setCourseId(jsonObject.get("course_id").equals(null) ? null : jsonObject.getInt("course_id"));
        }
        if (jsonObject.has("course_name")){
            this.setCourseName(jsonObject.get("course_name").equals(null) ? null : jsonObject.getString("course_name"));
        }
        if (jsonObject.has("school_id")){
            this.setSchoolId(jsonObject.get("school_id").equals(null) ? null : jsonObject.getInt("school_id"));
        }
        if (jsonObject.has("school_name")){
            this.setSchoolName(jsonObject.get("school_name").equals(null) ? null : jsonObject.getString("school_name"));
        }
        if (jsonObject.has("branch_school_id")){
            this.setBranchSchoolId(jsonObject.get("branch_school_id").equals(null) ? null : jsonObject.getInt("branch_school_id"));
        }
        if (jsonObject.has("branch_school_name")){
            this.setBranchSchoolName(jsonObject.get("branch_school_name").equals(null) ? null : jsonObject.getString("branch_school_name"));
        }
        if (jsonObject.has("group_id")){
            this.setGroupId(jsonObject.get("group_id").equals(null) ? null : jsonObject.getInt("group_id"));
        }
        if (jsonObject.has("group_name")){
            this.setGroupName(jsonObject.get("group_name").equals(null) ? null : jsonObject.getString("group_name"));
        }
        if (jsonObject.has("member_id")){
            this.setMemberId(jsonObject.get("member_id").equals(null) ? null : jsonObject.getInt("member_id"));
        }
        if (jsonObject.has("member_name")){
            this.setMemberName(jsonObject.get("member_name").equals(null) ? null : jsonObject.getString("member_name"));
        }
        if (jsonObject.has("management_status_id")){
            this.setManagementStatusId(jsonObject.get("management_status_id").equals(null) ? null : jsonObject.getInt("management_status_id"));
        }
        if (jsonObject.has("price")){
            this.setPrice(jsonObject.get("price").equals(null) ? null : new BigDecimal(jsonObject.getString("price")));
        }
    }
    public JSONObject serializeToJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("fee_type_id",feeTypeId);
        jsonObject.put("fee_type_name",feeTypeName);
        jsonObject.put("course_id",courseId);
        jsonObject.put("course_name",courseName);
        jsonObject.put("school_id",schoolId);
        jsonObject.put("school_name",schoolName);
        jsonObject.put("branch_school_id",branchSchoolId);
        jsonObject.put("branch_school_name",branchSchoolName);
        jsonObject.put("group_id",groupName);
        jsonObject.put("member_id",memberId);
        jsonObject.put("member_name",memberName);
        jsonObject.put("price", price);
        jsonObject.put("management_status_id",managementStatusId);
        return jsonObject;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBranchSchoolName() {
        return branchSchoolName;
    }

    public void setBranchSchoolName(String branchSchoolName) {
        this.branchSchoolName = branchSchoolName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.id
     *
     * @return the value of fee.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.id
     *
     * @param id the value for fee.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.name
     *
     * @return the value of fee.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.name
     *
     * @param name the value for fee.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.fee_type_id
     *
     * @return the value of fee.fee_type_id
     *
     * @mbggenerated
     */
    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.fee_type_id
     *
     * @param feeTypeId the value for fee.fee_type_id
     *
     * @mbggenerated
     */
    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.course_id
     *
     * @return the value of fee.course_id
     *
     * @mbggenerated
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.course_id
     *
     * @param courseId the value for fee.course_id
     *
     * @mbggenerated
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.school_id
     *
     * @return the value of fee.school_id
     *
     * @mbggenerated
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.school_id
     *
     * @param schoolId the value for fee.school_id
     *
     * @mbggenerated
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.branch_school_id
     *
     * @return the value of fee.branch_school_id
     *
     * @mbggenerated
     */
    public Integer getBranchSchoolId() {
        return branchSchoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.branch_school_id
     *
     * @param branchSchoolId the value for fee.branch_school_id
     *
     * @mbggenerated
     */
    public void setBranchSchoolId(Integer branchSchoolId) {
        this.branchSchoolId = branchSchoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.group_id
     *
     * @return the value of fee.group_id
     *
     * @mbggenerated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.group_id
     *
     * @param groupId the value for fee.group_id
     *
     * @mbggenerated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.member_id
     *
     * @return the value of fee.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.member_id
     *
     * @param memberId the value for fee.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.price
     *
     * @return the value of fee.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.price
     *
     * @param price the value for fee.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fee.management_status_id
     *
     * @return the value of fee.management_status_id
     *
     * @mbggenerated
     */
    public Integer getManagementStatusId() {
        return managementStatusId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fee.management_status_id
     *
     * @param managementStatusId the value for fee.management_status_id
     *
     * @mbggenerated
     */
    public void setManagementStatusId(Integer managementStatusId) {
        this.managementStatusId = managementStatusId;
    }


    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }
}