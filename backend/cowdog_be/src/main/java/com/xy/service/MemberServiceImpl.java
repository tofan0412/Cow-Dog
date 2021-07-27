package com.xy.service;

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
	public String registerMember(Map memberMap, Map memberInfoMap) {
		
		MemberInfo newMemberInfo=new MemberInfo();
		
		newMemberInfo.setAddress((String)memberInfoMap.get("address"));
		newMemberInfo.setAge((String)memberInfoMap.get("age"));
		newMemberInfo.setAlcohol((String)memberInfoMap.get("alcohol"));
		newMemberInfo.setDistance((double)memberInfoMap.get("distance"));
		newMemberInfo.setGender((String)memberInfoMap.get("gender"));
		newMemberInfo.setHobby((String)memberInfoMap.get("hobby"));
		newMemberInfo.setInterest((String)memberInfoMap.get("interest"));
		newMemberInfo.setLatitude((double)memberInfoMap.get("latitude"));
		newMemberInfo.setLongitude((double)memberInfoMap.get("longitude"));
		newMemberInfo.setPersonality((String)memberInfoMap.get("personality"));
		newMemberInfo.setReligion((String)memberInfoMap.get("religion"));
		newMemberInfo.setSmoking((boolean)memberInfoMap.get("smoking"));
		
		
		Member newMember=new Member();
		newMember.setEmail((String)memberMap.get("email"));
		newMember.setIssuspended(false);
		newMember.setMemberid((String)memberMap.get("memberid"));
		newMember.setMemberinfo(newMemberInfo);
		newMember.setNickname((String)memberMap.get("nickname"));
//		newMember.setOnlinetime("sdf");
		newMember.setPassword((String)memberMap.get("password"));
		
		
		
		
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
	
	
	
	

}
