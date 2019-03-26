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
import com.sms.common.helper.BranchSchoolDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.BranchSchool;
import com.sms.model.GenericItem;
import com.sms.model.School;
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.BranchSchoolVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class BranchSchoolService extends ServiceBase implements IBranchSchoolService {
	public DataQueryResult<JSONObject> getBranchSchoolsBySchoolIdAndPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);
		
		Integer totalBranchSchoolCount = 0;
		List<BranchSchool> branchSchools = null;
		
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
		    branchSchools = branchSchoolMapper.selectBySchoolIdAndPageAndAsc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    branchSchools = branchSchoolMapper.selectBySchoolIdAndPageAndDesc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalBranchSchoolCount = branchSchoolMapper.getCountBySchoolId(schoolId);

		if (branchSchools.size() > 0) {
			result = new DataQueryResult<JSONObject>(totalBranchSchoolCount);

			// Get branch school ids
			List<Integer> branchSchoolIds = branchSchools.stream().map(bs -> bs.getId()).collect(Collectors.toList());

			// Get director name of each branch school
			List<GenericItem> directorNames = branchSchoolMapper.selectDirectorNamesByBranchSchoolIds(branchSchoolIds);
			Map<Integer, String> directorNameMap = directorNames.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getStringData()));

			// Get group count of each school
			List<GenericItem> groupCountList = branchSchoolMapper.selectGroupCountByBranchSchoolIds(branchSchoolIds);
			Map<Integer, Integer> groupCountMap = groupCountList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getIntegerData()));

			List<BranchSchoolVO> branchSchoolVOs = BranchSchoolDataHelper.convertBranchSchoolsToBranchSchoolVOs(branchSchools);

			branchSchoolVOs.stream().forEach(branchSchoolVo -> {
			    branchSchoolVo.setDirectorName(directorNameMap.containsKey(branchSchoolVo.getId()) ? directorNameMap.get(branchSchoolVo.getId()) : null);
			    branchSchoolVo.setGroupCount(groupCountMap.containsKey(branchSchoolVo.getId()) ? groupCountMap.get(branchSchoolVo.getId()) : 0);
			});

			List<JSONObject> jsonObjects = BranchSchoolDataHelper.convertBranchSchoolVOsToJSONObjects(branchSchoolVOs);
			result.setDataset(jsonObjects);
		}

		return result;
	}

	public CommandResult getBranchSchoolsBySchoolId(User loggedInUser, Integer schoolId) {
	    	if (schoolId == null) {
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}
	    	
	    	School school = schoolMapper.selectByPrimaryKey(schoolId);
		if (school == null) {
			return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
		}
		
		List<BranchSchool> branchSchools = branchSchoolMapper.selectBySchoolId(schoolId);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), branchSchools);
	}

	public synchronized CommandResult createBranchSchool(BranchSchoolVO branchSchoolVO) {
		if (StringUtils.isBlank(branchSchoolVO.getName())) {
			return new CommandResult(CommandCode.EMPTY_BRANCH_SCHOOL_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_BRANCH_SCHOOL_NAME));
		}

		// Check if school id exists
		if (!branchSchoolVO.getSchoolIdIsPresented() || branchSchoolVO.getSchoolId() == null) {
			return new CommandResult(CommandCode.SCHOOL_ID_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_ID_NOT_EXIST));
		}
		
		// Check if school exists
		Integer schoolId = branchSchoolVO.getSchoolId();
		School school = schoolMapper.selectByPrimaryKey(schoolId);
		if (school == null) {
			return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
		}
		
		// Check if branch school exists
		BranchSchool branchSchool = branchSchoolMapper.selectByName(branchSchoolVO.getName());
		if (branchSchool != null) {
			return new CommandResult(CommandCode.BRANCH_SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS));
		}
		
		// Create new branch school 
		branchSchool = BranchSchoolDataHelper.convertBranchSchoolVOToBranchSchool(branchSchoolVO);
		branchSchoolMapper.insert(branchSchool);

		// Add user_role_association if there is director id
		if (branchSchoolVO.getDirectorIdIsPresented() && branchSchoolVO.getDirectorId() != null) {
			addDirectorRoleAssociation(branchSchoolVO.getDirectorId(), schoolId, branchSchool.getId());
		}

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	public CommandResult getBranchSchool(Integer id) {
        	if (id == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(id);
        	if (branchSchool == null) {
        		return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
        	}
        
        	BranchSchoolVO branchSchoolVO = BranchSchoolDataHelper.convertBranchSchoolToBranchSchoolVO(branchSchool);
        
        	// Get user id of the branch school
        	UserRoleAssociation association = userRoleAssociationMapper.selectByDirectorRoleAndBranchSchoolId(id);
        	if (association != null) {
        		branchSchoolVO.setDirectorId(association.getUserId());
        	}
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), branchSchoolVO.serializeToJSONObject());
	}

	public CommandResult updateBranchSchool(Integer branchSchoolId, BranchSchoolVO branchSchoolVO) {
        	if (branchSchoolId == null) {
        		return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        	}
        
        	BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
        	if (branchSchool == null) {
        		return new CommandResult(CommandCode.BRANCH_SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.BRANCH_SCHOOL_NOT_EXIST));
        	}
        
        	// Update branch school
        	BranchSchool updatedBranchSchool = BranchSchoolDataHelper.generateUpdatedBranchSchool(branchSchool, branchSchoolVO);
        	branchSchoolMapper.updateByPrimaryKey(updatedBranchSchool);
        
        	// Update user role association
        	updateUserRoleAssociation(updatedBranchSchool.getSchoolId(), updatedBranchSchool.getId(), branchSchoolVO);
        
        	return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	private void addDirectorRoleAssociation(Integer userId, Integer schoolId, Integer branchSchoolId) {
		UserRoleAssociation association = new UserRoleAssociation();
		association.setUserId(userId);
		association.setRoleId(RoleType.DIRECTOR.getValue());
		association.setSchoolId(schoolId);
		association.setBranchSchoolId(branchSchoolId);
		userRoleAssociationMapper.insert(association);
	}
	
	private void updateUserRoleAssociation(Integer schoolId, Integer branchSchoolId, BranchSchoolVO branchSchoolVO) {
		// Association record can only either be one or none
		UserRoleAssociation association = userRoleAssociationMapper.selectByDirectorRoleAndBranchSchoolId(branchSchoolId);

		// If no association before
		if (association == null) {
			if (branchSchoolVO.getDirectorIdIsPresented() && branchSchoolVO.getDirectorId() != null) {
				// Add association
			    addDirectorRoleAssociation(branchSchoolVO.getDirectorId(), schoolId, branchSchoolId);
			}
		} else {
			if (branchSchoolVO.getDirectorIdIsPresented()) {
				if (association.getUserId() != branchSchoolVO.getDirectorId()) {
					// Delete existing association
					userRoleAssociationMapper.deleteByUserIdAndBranchSchoolIdAndDirectorRole(association.getUserId(),
							association.getBranchSchoolId());
					// Add new association
					if (branchSchoolVO.getDirectorId() != null) {
					    addDirectorRoleAssociation(branchSchoolVO.getDirectorId(), schoolId, branchSchoolId);
					}
				}
			}
		}
	}
}
