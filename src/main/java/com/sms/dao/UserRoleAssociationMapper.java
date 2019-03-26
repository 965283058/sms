package com.sms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.UserRoleAssociation;

@Repository
public interface UserRoleAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_association
     *
     * @mbggenerated
     */
    int insert(UserRoleAssociation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role_association
     *
     * @mbggenerated
     */
    int insertSelective(UserRoleAssociation record);
    
    List<UserRoleAssociation> selectByUserId(Integer userId);
    
    List<UserRoleAssociation> selectByUserIdAndRoleId(@Param("userId")Integer userId, @Param("roleId")Integer roleId);
    
    int updateSchoolIdByUserIdAndPresidentRole(@Param("schoolId")Integer schoolId, @Param("userId")Integer userId);
    
    UserRoleAssociation selectByPresidentRoleAndSchoolId(Integer schoolId);
    
    int deleteByUserIdAndSchoolIdAndPresidentRole(@Param("userId")Integer userId, @Param("schoolId")Integer schoolId);
    
    UserRoleAssociation selectByDirectorRoleAndBranchSchoolId(Integer branchSchoolId);
    
    int deleteByUserIdAndBranchSchoolIdAndDirectorRole(@Param("userId")Integer userId, @Param("branchSchoolId")Integer branchSchoolId);
    
    UserRoleAssociation selectByGroupLeaderRoleAndGroupId(Integer groupId);
    
    int deleteByUserIdAndGroupIdAndGroupLeaderRole(@Param("userId")Integer userId, @Param("groupId")Integer groupId);
}