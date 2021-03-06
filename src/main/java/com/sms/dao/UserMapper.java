package com.sms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.User;

@Repository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);
    
    public User selectByLogName(String logName);
    
    public List<User> selectByPageAndAsc(@Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);
    
    public List<User> selectPresidentsByPageAndAsc(@Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);
    
    public List<User> selectByPageAndDesc(@Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
    
    public List<User> selectPresidentsByPageAndDesc(@Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
    
    public int getTotalUserCount();
    
    public int getTotalUserCountByRoleType(@Param("roleType")int roleType);
    
    public User selectByName(String name);
    
    /*@MapKey("id")
    public HashMap<Integer, HashMap<String, Object>> selectByRoleId(Integer roleId);*/
    
    public List<User> selectByRoleId(@Param("roleId")int roleId);
    
    public List<User> selectByUserIds(@Param("userIds")List<Integer> userIds);
    
    /** Director **/
    public List<User> selectDirectorsBySchoolIdAndPageAndAsc(@Param("schoolId")int schoolId, @Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);
    
    public List<User> selectDirectorsBySchoolIdAndPageAndDesc(@Param("schoolId")int schoolId, @Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
    
    public int getDirectorCountBySchoolId(@Param("schoolId")int schoolId);
    
    public List<User> selectDirectorsBySchoolId(@Param("schoolId")int schoolId);
    
    /** Group leader **/
    public List<User> selectGroupLeadersByBranchSchoolIdAndPageAndAsc(@Param("branchSchoolId")int branchSchoolId, @Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);
    
    public List<User> selectGroupLeadersByBranchSchoolIdAndPageAndDesc(@Param("branchSchoolId")int branchSchoolId, @Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
    
    public int getGroupLeaderCountByBranchSchoolId(@Param("branchSchoolId")int branchSchoolId);
    
    public List<User> selectGroupLeadersByBranchSchoolId(@Param("branchSchoolId")int branchSchoolId);
}