package com.sms.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.sms.model.School;
import com.sms.vo.SchoolVO;

import net.sf.json.JSONObject;

public class SchoolDataHelper {
	public static List<SchoolVO> convertSchoolsToSchoolVOs(List<School> schools) {
		List<SchoolVO> schoolVOs = new ArrayList<SchoolVO>();
		schools.stream().forEach(school -> {
			schoolVOs.add(convertSchoolToSchoolVO(school));
		});
		return schoolVOs;
	}

	public static SchoolVO convertSchoolToSchoolVO(School school) {
		SchoolVO schoolVO = new SchoolVO();
		schoolVO.setId(school.getId());
		schoolVO.setName(school.getName());
		schoolVO.setTelephoneNumber(school.getTelephoneNumber());
		schoolVO.setSchoolType(school.getSchoolTypeId());
		return schoolVO;
	}

	public static School convertSchoolVOToSchool(SchoolVO schoolVO) {
		School school = new School();

		if (schoolVO.getNameIsPresented()) {
			school.setName(schoolVO.getName());
		}

		if (schoolVO.getTelephoneNumberIsPresented()) {
			school.setTelephoneNumber(schoolVO.getTelephoneNumber());
		}

		if (schoolVO.getSchoolTypeIsPresented()) {
			school.setSchoolTypeId(schoolVO.getSchoolType());
		}

		return school;
	}

	public static School generateUpdatedSchool(School existingSchool, SchoolVO schoolVO) {
		School school = new School();
		school.setId(existingSchool.getId());
		school.setName(schoolVO.getNameIsPresented() ? schoolVO.getName() : existingSchool.getName());
		school.setTelephoneNumber(schoolVO.getTelephoneNumberIsPresented() ? schoolVO.getTelephoneNumber() : existingSchool.getTelephoneNumber());
		school.setSchoolTypeId(schoolVO.getSchoolTypeIsPresented() ? schoolVO.getSchoolType() : existingSchool.getSchoolTypeId());
		return school;
	}

	public static List<JSONObject> convertSchoolVOsToJSONObjects(List<SchoolVO> schoolVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		schoolVOs.stream().forEach(schoolVO -> {
			jsonObjects.add(schoolVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
