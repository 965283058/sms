package com.sms.model;

import java.util.Date;

public class BranchSchool {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.school_id
     *
     * @mbggenerated
     */
    private Integer schoolId;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    private String schoolName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.telephone_number
     *
     * @mbggenerated
     */
    private String telephoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.monitor_open_days_of_week
     *
     * @mbggenerated
     */
    private String monitorOpenDaysOfWeek;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.monitor_start_time
     *
     * @mbggenerated
     */
    private Date monitorStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column branch_school.monitor_end_time
     *
     * @mbggenerated
     */
    private Date monitorEndTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.id
     *
     * @return the value of branch_school.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.id
     *
     * @param id the value for branch_school.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.name
     *
     * @return the value of branch_school.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.name
     *
     * @param name the value for branch_school.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.school_id
     *
     * @return the value of branch_school.school_id
     *
     * @mbggenerated
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.school_id
     *
     * @param schoolId the value for branch_school.school_id
     *
     * @mbggenerated
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.address
     *
     * @return the value of branch_school.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.address
     *
     * @param address the value for branch_school.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.telephone_number
     *
     * @return the value of branch_school.telephone_number
     *
     * @mbggenerated
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.telephone_number
     *
     * @param telephoneNumber the value for branch_school.telephone_number
     *
     * @mbggenerated
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber == null ? null : telephoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.monitor_open_days_of_week
     *
     * @return the value of branch_school.monitor_open_days_of_week
     *
     * @mbggenerated
     */
    public String getMonitorOpenDaysOfWeek() {
        return monitorOpenDaysOfWeek;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.monitor_open_days_of_week
     *
     * @param monitorOpenDaysOfWeek the value for branch_school.monitor_open_days_of_week
     *
     * @mbggenerated
     */
    public void setMonitorOpenDaysOfWeek(String monitorOpenDaysOfWeek) {
        this.monitorOpenDaysOfWeek = monitorOpenDaysOfWeek == null ? null : monitorOpenDaysOfWeek.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.monitor_start_time
     *
     * @return the value of branch_school.monitor_start_time
     *
     * @mbggenerated
     */
    public Date getMonitorStartTime() {
        return monitorStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.monitor_start_time
     *
     * @param monitorStartTime the value for branch_school.monitor_start_time
     *
     * @mbggenerated
     */
    public void setMonitorStartTime(Date monitorStartTime) {
        this.monitorStartTime = monitorStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column branch_school.monitor_end_time
     *
     * @return the value of branch_school.monitor_end_time
     *
     * @mbggenerated
     */
    public Date getMonitorEndTime() {
        return monitorEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column branch_school.monitor_end_time
     *
     * @param monitorEndTime the value for branch_school.monitor_end_time
     *
     * @mbggenerated
     */
    public void setMonitorEndTime(Date monitorEndTime) {
        this.monitorEndTime = monitorEndTime;
    }
}