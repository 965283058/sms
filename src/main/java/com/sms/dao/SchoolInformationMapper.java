package com.sms.dao;

import com.sms.model.SchoolInformation;

public interface SchoolInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    int insert(SchoolInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    int insertSelective(SchoolInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    SchoolInformation selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolInformation record);
}