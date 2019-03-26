package com.sms.common.helper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sms.dao.BranchSchoolMapper;
import com.sms.dao.SchoolMapper;
import com.sms.model.BranchSchool;
import com.sms.model.School;

@Component
public class BranchSchoolHelper {
	@Autowired
	private SchoolMapper autowiredSchoolMapper;
	
	private static SchoolMapper schoolMapper;
	
	@Autowired
	private BranchSchoolMapper autowiredBranchSchoolMapper;
	
	private static BranchSchoolMapper branchSchoolMapper;
	
	@PostConstruct
	private void init() {
		schoolMapper = this.autowiredSchoolMapper;
		branchSchoolMapper = this.autowiredBranchSchoolMapper;
	}

	public static School getSchoolByBranchSchoolId(Integer branchSchoolId) {
		BranchSchool branchSchool = branchSchoolMapper.selectByPrimaryKey(branchSchoolId);
		return branchSchool != null ? schoolMapper.selectByPrimaryKey(branchSchool.getSchoolId()) : null;
	}
}
