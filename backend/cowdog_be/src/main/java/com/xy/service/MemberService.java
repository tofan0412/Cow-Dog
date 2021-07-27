package com.xy.service;

import java.util.Map;

import com.xy.entity.Member;
import com.xy.entity.MemberInfo;

public interface MemberService {
	public String registerMember(Map memberMap, Map memberInfoMap);
	public Member getMemberByMemberId(String id);
}
