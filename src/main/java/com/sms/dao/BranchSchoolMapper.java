package com.sms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.BranchSchool;
import com.sms.model.GenericItem;

@Repository
public interface BranchSchoolMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    int insert(BranchSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    int insertSelective(BranchSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    BranchSchool selectByPrimaryKey(Integer id);

    BranchSchool selectByBranchSchoolId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BranchSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table branch_school
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BranchSchool record);
    
    BranchSchool selectByName(String name);
    
    List<BranchSchool> selectBySchoolIdAndPageAndAsc(@Param("schoolId")Integer schoolId, @Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);
    
    List<BranchSchool> selectBySchoolIdAndPageAndDesc(@Param("schoolId")Integer schoolId, @Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
    
    int getCountBySchoolId(@Param("schoolId")Integer schoolId);
    
    List<BranchSchool> selectByIds(@Param("ids")List<Integer> ids);
    
    List<GenericItem> selectDirectorNamesByBranchSchoolIds(@Param("branchSchoolIds")List<Integer> branchSchoolIds);
    
    List<GenericItem> selectGroupCountByBranchSchoolIds(@Param("branchSchoolIds")List<Integer> branchSchoolIds);
    
    List<BranchSchool> selectBySchoolId(@Param("schoolId")Integer schoolId);
}