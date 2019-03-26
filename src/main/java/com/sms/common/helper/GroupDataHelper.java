package com.sms.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.sms.model.Group;
import com.sms.vo.GroupVO;

import net.sf.json.JSONObject;

public class GroupDataHelper {
	public static List<GroupVO> convertGroupsToGroupVOs(List<Group> groups) {
		List<GroupVO> groupVOs = new ArrayList<GroupVO>();
		groups.stream().forEach(g -> {
			groupVOs.add(convertGroupToGroupVO(g));
		});
		return groupVOs;
	}

	public static GroupVO convertGroupToGroupVO(Group group) {
		GroupVO groupVO = new GroupVO();
		groupVO.setId(group.getId());
		groupVO.setName(group.getName());
		groupVO.setStartDate(group.getStartDate());
		groupVO.setBranchSchoolId(group.getBranchSchoolId());
		return groupVO;
	}

	public static Group convertGroupVOToGroup(GroupVO groupVO) {
		Group group = new Group();

		if (groupVO.getNameIsPresented()) {
			group.setName(groupVO.getName());
		}
		
		if (groupVO.getStartDateIsPresented()) {
			group.setStartDate(groupVO.getStartDate());
		}
		
		if (groupVO.getBranchSchoolIdIsPresented()) {
			group.setBranchSchoolId(groupVO.getBranchSchoolId());
		}
		
		return group;
	}

	public static Group generateUpdatedGroup(Group existingGroup, GroupVO groupVO) {
		Group group = new Group();
		group.setId(existingGroup.getId());
		group.setName(groupVO.getNameIsPresented() ? groupVO.getName() : existingGroup.getName());
		group.setStartDate(groupVO.getStartDateIsPresented() ? groupVO.getStartDate() : existingGroup.getStartDate());
		group.setBranchSchoolId(existingGroup.getBranchSchoolId());
		return group;
	}

	public static List<JSONObject> convertGroupVOsToJSONObjects(List<GroupVO> groupVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		groupVOs.stream().forEach(groupVO -> {
			jsonObjects.add(groupVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
