package com.sms.service;

import com.sms.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.sms.authentication.SessionManager;

public abstract class ServiceBase {
	@Autowired
	protected UserMapper userMapper;
	
	@Autowired
	protected UserRoleAssociationMapper userRoleAssociationMapper;
	
	@Autowired
	protected GroupMapper groupMapper;
	
	@Autowired
	protected MemberMapper memberMapper;
	
	@Autowired
	protected BranchSchoolMapper branchSchoolMapper;
	
	@Autowired
	protected SchoolMapper schoolMapper;
	
	@Autowired
	protected FeeTypeMapper feeTypeMapper;

	@Autowired
	protected FeeMapper feeMapper;

	@Autowired
	protected SessionManager sessionManager;

	@Autowired
	protected MonitorMapper monitorMapper;
}
