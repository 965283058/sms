package com.sms.common.helper;

import com.sms.model.School;
import com.sms.model.Subject;
import com.sms.vo.SchoolVO;
import com.sms.vo.SubjectVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataHelper {
	public static List<SubjectVO> convertSubjectsToSubjectVOs(List<Subject> subjects) {
		List<SubjectVO> subjectVOS = new ArrayList<>();
		subjects.stream().forEach(subject -> {
			subjectVOS.add(convertSubjectToSubjectVO(subject));
		});
		return subjectVOS;
	}

	public static SubjectVO convertSubjectToSubjectVO(Subject subject) {
		SubjectVO subjectVO = new SubjectVO();
		BeanUtils.copyProperties(subject, subjectVO);
		return subjectVO;
	}

	public static Subject convertSubjectVOToSubject(SubjectVO subjectVO) {
		Subject subject = new Subject();
		BeanUtils.copyProperties(subjectVO, subject);
		return subject;
	}

	public static School generateUpdatedSchool(School existingSchool, SchoolVO schoolVO) {
		School school = new School();
		school.setId(existingSchool.getId());
		school.setName(schoolVO.getNameIsPresented() ? schoolVO.getName() : existingSchool.getName());
		school.setTelephoneNumber(schoolVO.getTelephoneNumberIsPresented() ? schoolVO.getTelephoneNumber() : existingSchool.getTelephoneNumber());
		school.setSchoolTypeId(schoolVO.getSchoolTypeIsPresented() ? schoolVO.getSchoolType() : existingSchool.getSchoolTypeId());
		return school;
	}

	public static List<JSONObject> convertSubjectsVOsToJSONObjects(List<SubjectVO> subjectVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		subjectVOs.stream().forEach(subjectVO -> {
			jsonObjects.add(subjectVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
