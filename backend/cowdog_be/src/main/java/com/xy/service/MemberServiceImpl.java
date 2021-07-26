package com.xy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.Member;
import com.xy.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberRepository memRepo;

	@Override
	public String registerMember(Member mem) {
		
		
		if(memRepo.save(mem)!=null) {
			return "SUCCESS";
		}
		
		
		
		return "FAIL";
	}
	
	
	
	

}
