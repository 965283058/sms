package com.sms.common.helper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.BranchSchool;
import com.sms.vo.BranchSchoolVO;

import net.sf.json.JSONObject;

public class BranchSchoolDataHelper {
	public static List<BranchSchoolVO> convertBranchSchoolsToBranchSchoolVOs(List<BranchSchool> branchSchools) {
		List<BranchSchoolVO> schoolVOs = new ArrayList<BranchSchoolVO>();
		branchSchools.stream().forEach(branchSchool -> {
			schoolVOs.add(convertBranchSchoolToBranchSchoolVO(branchSchool));
		});
		return schoolVOs;
	}

	public static BranchSchoolVO convertBranchSchoolToBranchSchoolVO(BranchSchool branchSchool) {
		BranchSchoolVO schoolVO = new BranchSchoolVO();
		schoolVO.setId(branchSchool.getId());
		schoolVO.setName(branchSchool.getName());
		schoolVO.setAddress(branchSchool.getAddress());
		schoolVO.setTelephoneNumber(branchSchool.getTelephoneNumber());
		schoolVO.setSchoolId(branchSchool.getSchoolId());
		schoolVO.setMonitorOpenDaysOfWeek(branchSchool.getMonitorOpenDaysOfWeek());
		schoolVO.setMonitorStartTime(branchSchool.getMonitorStartTime());
		schoolVO.setMonitorEndTime(branchSchool.getMonitorEndTime());
		return schoolVO;
	}

	public static BranchSchool convertBranchSchoolVOToBranchSchool(BranchSchoolVO branchSchoolVO) {
		BranchSchool branchSchool = new BranchSchool();

		if (branchSchoolVO.getNameIsPresented()) {
			branchSchool.setName(branchSchoolVO.getName());
		}

		if (branchSchoolVO.getAddressIsPresented()) {
			branchSchool.setAddress(branchSchoolVO.getAddress());
		}
		
		if (branchSchoolVO.getTelephoneNumberIsPresented()) {
			branchSchool.setTelephoneNumber(branchSchoolVO.getTelephoneNumber());
		}
		
		if (branchSchoolVO.getSchoolIdIsPresented()) {
			branchSchool.setSchoolId(branchSchoolVO.getSchoolId());
		}
		
		if (branchSchoolVO.getMonitorOpenDaysOfWeekIsPresented()) {
			List<DayOfWeek> daysOfWeek = branchSchoolVO.getMonitorOpenDaysOfWeek();
			branchSchool.setMonitorOpenDaysOfWeek(DayOfWeekHelper.convertDayOfWeekListToDaysString(daysOfWeek));
		}
		
		if (branchSchoolVO.getMonitorStartTimeIsPresented()) {
			branchSchool.setMonitorStartTime(branchSchoolVO.getMonitorStartTime());
		}
		
		if (branchSchoolVO.getMonitorEndTimeIsPresented()) {
			branchSchool.setMonitorEndTime(branchSchoolVO.getMonitorEndTime());
		}
		
		return branchSchool;
	}

	public static BranchSchool generateUpdatedBranchSchool(BranchSchool existingSchool, BranchSchoolVO branchSchoolVO) {
		BranchSchool branchSchool = new BranchSchool();
		branchSchool.setId(existingSchool.getId());
		branchSchool.setName(branchSchoolVO.getNameIsPresented() ? branchSchoolVO.getName() : existingSchool.getName());
		branchSchool.setAddress(branchSchoolVO.getAddressIsPresented() ? branchSchoolVO.getAddress() : existingSchool.getAddress());
		branchSchool.setTelephoneNumber(branchSchoolVO.getTelephoneNumberIsPresented() ? branchSchoolVO.getTelephoneNumber() : existingSchool.getTelephoneNumber());
		branchSchool.setSchoolId(existingSchool.getSchoolId());
		
		if (branchSchoolVO.getMonitorOpenDaysOfWeekIsPresented()) {
			List<DayOfWeek> daysOfWeek = branchSchoolVO.getMonitorOpenDaysOfWeek();
			branchSchool.setMonitorOpenDaysOfWeek(DayOfWeekHelper.convertDayOfWeekListToDaysString(daysOfWeek));
		}
		else {
			branchSchool.setMonitorOpenDaysOfWeek(existingSchool.getMonitorOpenDaysOfWeek());
		}
		
		branchSchool.setMonitorStartTime(branchSchoolVO.getMonitorStartTimeIsPresented() ? branchSchoolVO.getMonitorStartTime() : existingSchool.getMonitorStartTime());
		branchSchool.setMonitorEndTime(branchSchoolVO.getMonitorEndTimeIsPresented() ? branchSchoolVO.getMonitorEndTime() : existingSchool.getMonitorEndTime());
		
		return branchSchool;
	}

	public static List<JSONObject> convertBranchSchoolVOsToJSONObjects(List<BranchSchoolVO> branchSchoolVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		branchSchoolVOs.stream().forEach(branchSchoolVO -> {
			jsonObjects.add(branchSchoolVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
