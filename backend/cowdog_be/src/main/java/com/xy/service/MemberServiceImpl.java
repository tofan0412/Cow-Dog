package com.xy.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.Member;
import com.xy.entity.MemberInfo;
import com.xy.repository.MemberInfoRepository;
import com.xy.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	MemberInfoRepository memInfoRepo;

	@Override
	public String registerMember(Map member) {
		System.out.println(member);
		MemberInfo newMemberInfo=new MemberInfo();
		
		newMemberInfo.setAddress((String)member.get("address"));
		newMemberInfo.setAge((String)member.get("age"));
		newMemberInfo.setAlcohol(member.get("alcohol").toString());
		System.out.println(member.get("distance"));
		newMemberInfo.setDistance((Integer)member.get("distance"));
		newMemberInfo.setGender(member.get("gender").toString());
		newMemberInfo.setHobby(member.get("hobby").toString());
		newMemberInfo.setInterest(member.get("interest").toString());
		newMemberInfo.setLatitude((double)member.get("latitude"));
		newMemberInfo.setLongitude((double)member.get("longitude"));
		newMemberInfo.setPersonality(member.get("personality").toString());
		newMemberInfo.setReligion(member.get("religion").toString());
		boolean smokingCheck=true;
		if(member.get("smoking").toString().equals("[false]")) {
			smokingCheck=false;
		}
		newMemberInfo.setSmoking(smokingCheck);
		
		
		Member newMember=new Member();
		newMember.setEmail(member.get("email").toString());
		newMember.setIssuspended(false);
		newMember.setMemberid(member.get("id").toString());
		newMember.setMemberinfo(newMemberInfo);
		newMember.setNickname(member.get("nickname").toString());
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		String stringDatetime = new SimpleDateFormat("yyyy.MM.dd").format(date_now);
//		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd-HH"); 
		newMember.setOnlinetime(stringDatetime);
		newMember.setPassword(member.get("password").toString());
		
		
		
		
		if(memInfoRepo.save(newMemberInfo)!=null && memRepo.save(newMember)!=null) {
			return "SUCCESS";
		}
		
		
		
		return "FAIL";
	}

	@Override
	public Member getMemberByMemberId(String id) {
		
		System.out.println(memRepo.existsBymemberid(id));
		if(memRepo.existsBymemberid(id)) {
			return memRepo.getBymemberid(id);
		}
		
		
		return null;
	}

	@Override
	public boolean confirmUserId(String userId) {
		
		
		if(memRepo.existsBymemberid(userId)) {
			return false;
		}
		
		
		return true;
	}

	@Override
	public boolean confirmNickname(String userNickname) {

		if(memRepo.existsBynickname(userNickname)) {
			return false;
		}
		return true;
	}
	
	
	
	

}
