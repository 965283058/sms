package com.sms.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.sms.model.Member;
import com.sms.vo.MemberVO;

import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

public class MemberDataHelper {
	public static List<MemberVO> convertMembersToMemberVOs(List<Member> members) {
		List<MemberVO> memberVOs = new ArrayList<MemberVO>();
		members.stream().forEach(member -> {
			memberVOs.add(convertMemberToMemberVO(member));
		});
		return memberVOs;
	}
	
	public static MemberVO convertMemberToMemberVO(Member member) {
		MemberVO memberVO = new MemberVO();
		memberVO.setId(member.getId());
		memberVO.setLogName(member.getLogName());
		memberVO.setTelephoneNumber(member.getTelephoneNumber());
		memberVO.setName(member.getName());
		memberVO.setNickName(member.getNickName());
		memberVO.setGender(member.getGender());
		memberVO.setBirthday(member.getBirthday());
		memberVO.setEthnicity(member.getEthnicity());
		memberVO.setPhotoUrl(member.getPhotoUrl());
		memberVO.setHomeAddress(member.getHomeAddress());
		memberVO.setFatherName(member.getFatherName());
		memberVO.setFatherTelephoneNumber(member.getFatherTelephoneNumber());
		memberVO.setMotherName(member.getMotherName());
		memberVO.setMotherTelephoneNumber(member.getMotherTelephoneNumber());
		memberVO.setGroupId(member.getGroupId());
		memberVO.setManagementStatus(member.getManagementStatusId());
		return memberVO;
	}

	public static Member convertMemberVOToMember(MemberVO memberVO) {
		Member member = new Member();
		BeanUtils.copyProperties(memberVO,member);
		return member;
	}

	public static Member generateUpdatedMember(Member existingMember, MemberVO memberVO) {
		Integer id = existingMember.getId();
		BeanUtils.copyProperties(memberVO,existingMember);
		existingMember.setId(id);
		return existingMember;
	}

	public static List<JSONObject> convertMemberVOsToJSONObjects(List<MemberVO> memberVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		memberVOs.stream().forEach(memberVO -> {
			jsonObjects.add(memberVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
