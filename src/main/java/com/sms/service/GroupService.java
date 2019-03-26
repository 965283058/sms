package com.sms.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.RoleType;
import com.sms.common.helper.GroupDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.BranchSchool;
import com.sms.model.GenericItem;
import com.sms.model.Group;
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.GroupVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupService extends ServiceBase implements IGroupService {
	public DataQueryResult<JSONObject> getGroupsBySchoolIdAndBranchSchoolIdAndPaginationData(User loggedInUser, Integer branchSchoolId, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);
		
		BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
		if (branchSchool == null) {
			return result;
		}
		
		Integer totalGroupCount = 0;
		List<Group> groups = null;
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
		    groups = groupMapper.selectByBranchSchoolIdAndPageAndAsc(branchSchoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    groups = groupMapper.selectByBranchSchoolIdAndPageAndDesc(branchSchoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalGroupCount = groupMapper.getCountByBranchSchoolId(branchSchoolId);

		if (groups.size() > 0) {
			result = new DataQueryResult<JSONObject>(totalGroupCount);

			// Get group ids
			List<Integer> groupIds = groups.stream().map(bs -> bs.getId()).collect(Collectors.toList());

			// Get group leader name of each group 
			List<GenericItem> groupLeaderNames = groupMapper.selectGroupLeaderNamesByGroupIds(groupIds);
			Map<Integer, String> groupLeaderNameMap = groupLeaderNames.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getStringData()));
			
			// Get group leader name of each group 
			List<GenericItem> groupLeaderTelephoneNumbers = groupMapper.selectGroupLeaderTelephoneNumbersByGroupIds(groupIds);
			Map<Integer, String> groupLeaderTelephoneNumberMap = groupLeaderTelephoneNumbers.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getStringData()));

			// Get member count of each school
			List<GenericItem> memberCountList = groupMapper.selectMemberCountByGroupIds(groupIds);
			Map<Integer, Integer> memberCountMap = memberCountList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getIntegerData()));

			List<GroupVO> groupVOs = GroupDataHelper.convertGroupsToGroupVOs(groups);

			groupVOs.stream().forEach(groupVo -> {
			    groupVo.setGroupLeaderName(groupLeaderNameMap.containsKey(groupVo.getId()) ? groupLeaderNameMap.get(groupVo.getId()) : null);
			    groupVo.setGroupLeaderTelephoneNumber(groupLeaderTelephoneNumberMap.containsKey(groupVo.getId()) ? groupLeaderTelephoneNumberMap.get(groupVo.getId()) : null);
			    groupVo.setMemberCount(memberCountMap.containsKey(groupVo.getId()) ? memberCountMap.get(groupVo.getId()) : 0);
			});

			List<JSONObject> jsonObjects = GroupDataHelper.convertGroupVOsToJSONObjects(groupVOs);
			result.setDataset(jsonObjects);
		}

		return result;
	}

	public CommandResult getGroupsByBranchSchoolId(User loggedInUser, Integer branchSchoolId) {
	    	if (branchSchoolId == null) {
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}
	    	
	    	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
		if (branchSchool == null) {
			return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
		}
		
		List<Group> groups = groupMapper.selectByBranchSchoolId(branchSchoolId);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), groups);
	}

	public synchronized CommandResult createGroup(GroupVO groupVO) {
		if (StringUtils.isBlank(groupVO.getName())) {
			return new CommandResult(CommandCode.EMPTY_GROUP_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_GROUP_NAME));
		}

		// Check if branch group id exists
		if (!groupVO.getBranchSchoolIdIsPresented() || groupVO.getBranchSchoolId() == null) {
			return new CommandResult(CommandCode.BRANCH_SCHOOL_ID_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_ID_NOT_EXIST));
		}
		
		// Check if branch school exists
		Integer branchSchoolId = groupVO.getBranchSchoolId();
		BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
		if (branchSchool == null) {
			return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
		}
		
		// Check if group exists
		Group group = groupMapper.selectByName(groupVO.getName());
		if (group != null) {
			return new CommandResult(CommandCode.GROUP_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.GROUP_WITH_SAME_NAME_ALREADY_EXISTS));
		}
		
		// Create new group
		group = GroupDataHelper.convertGroupVOToGroup(groupVO);
		groupMapper.insert(group);

		// Add user_role_association if there is group leader id
		if (groupVO.getGroupLeaderIdIsPresented() && groupVO.getGroupLeaderId() != null) {
		    addGroupLeaderRoleAssociation(groupVO.getGroupLeaderId(), branchSchool.getSchoolId(), branchSchool.getId(), group.getId());
		}

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	public CommandResult getGroup(Integer id) {
        	if (id == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	Group group = groupMapper.selectByPrimaryKey(id);
        	if (group == null) {
        		return new CommandResult(CommandCode.GROUP_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.GROUP_NOT_EXIST));
        	}
        
        	GroupVO groupVO = GroupDataHelper.convertGroupToGroupVO(group);
        
        	// Get user id the group
        	UserRoleAssociation association = userRoleAssociationMapper.selectByGroupLeaderRoleAndGroupId(id);
        	if (association != null) {
        	    	User groupLeader = userMapper.selectByPrimaryKey(association.getUserId());
        		groupVO.setGrouLeaderId(groupLeader.getId());
        		groupVO.setGroupLeaderName(groupLeader.getName());
        		groupVO.setGroupLeaderTelephoneNumber(groupLeader.getTelephoneNumber());
        	}
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), groupVO.serializeToJSONObject());
	}

	public CommandResult updateGroup(Integer groupId, GroupVO groupVO) {
        	if (groupId == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	Group existingGroup = groupMapper.selectByPrimaryKey(groupId);
        	if (existingGroup == null) {
        		return new CommandResult(CommandCode.GROUP_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.GROUP_NOT_EXIST));
        	}
        
        	// Update group
        	Group updatedGroup = GroupDataHelper.generateUpdatedGroup(existingGroup, groupVO);
        	groupMapper.updateByPrimaryKey(updatedGroup);
        
        	// Get branch school
        	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(existingGroup.getBranchSchoolId());
        	
        	// Update user role association
        	updateUserRoleAssociation(branchSchool.getSchoolId(), updatedGroup.getBranchSchoolId(), updatedGroup.getId(), groupVO);
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	private void addGroupLeaderRoleAssociation(Integer userId, Integer schoolId, Integer branchSchoolId, Integer groupId) {
		UserRoleAssociation association = new UserRoleAssociation();
		association.setUserId(userId);
		association.setRoleId(RoleType.GROUOP_LEADER.getValue());
		association.setSchoolId(schoolId);
		association.setBranchSchoolId(branchSchoolId);
		association.setGroupId(groupId);
		userRoleAssociationMapper.insert(association);
	}
	
	private void updateUserRoleAssociation(Integer schoolId, Integer branchSchoolId, Integer groupId, GroupVO groupVO) {
		// Association record can only either be one or none
		UserRoleAssociation association = userRoleAssociationMapper.selectByGroupLeaderRoleAndGroupId(groupId);

		// If no association before
		if (association == null) {
			if (groupVO.getGroupLeaderIdIsPresented() && groupVO.getGroupLeaderId() != null) {
			    // Add association
			    addGroupLeaderRoleAssociation(groupVO.getGroupLeaderId(), schoolId, branchSchoolId, groupId);
			}
		} else {
			if (groupVO.getGroupLeaderIdIsPresented()) {
				if (association.getUserId() != groupVO.getGroupLeaderId()) {
					// Delete existing association
					userRoleAssociationMapper.deleteByUserIdAndGroupIdAndGroupLeaderRole(association.getUserId(), association.getGroupId());
					
					// Add new association
					if (groupVO.getGroupLeaderId() != null) {
					    addGroupLeaderRoleAssociation(groupVO.getGroupLeaderId(), schoolId, branchSchoolId, groupId);
					}
				}
			}
		}
	}
}
