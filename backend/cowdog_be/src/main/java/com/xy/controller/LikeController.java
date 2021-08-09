package com.xy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xy.entity.Member;
import com.xy.repository.MemberRepository;
import com.xy.service.FollowService;
import com.xy.service.FollowServiceImpl;
import com.xy.service.MemberService;
import com.xy.api.response.DistanceMatchingUserGetRes;
import com.xy.api.response.MemberListGetRes;
import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Follow;

@RestController
@RequestMapping("/cowdog/like")
public class LikeController {
	@Autowired
	MemberService memSer;
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	FollowServiceImpl followSer;
	
	
	@PostMapping("/follow")
	public ResponseEntity<? extends BaseResponseBody> Follow(@RequestBody Map<String, String> map) {
		
		System.out.println(map.toString());
		
		long From=Long.parseLong(map.get("memberid").toString());//팔로우를 하는사람
		Member to=memSer.getMemberByMemberId(map.get("followid"));//팔로우를 당하는사람
		
		Follow follow=new Follow();
		follow.setMember_id(From);
		follow.setFollower_id(to.getId());
		if(followSer.save(follow)!=null) {
			
			
			
			
			
			return ResponseEntity.ok(BaseResponseBody.of(200, "SUCCESS"));
		}
		return ResponseEntity.ok(BaseResponseBody.of(200, "FAIL"));
		
	}
	
	
	@GetMapping("/getFollowUsers")
	public ResponseEntity<? extends BaseResponseBody> getFollowUsers(@RequestParam("id") long id) {
		
		System.out.println("나 팔로우 한 사람 내놔~~~");
		System.out.println(id);//로그인 한사람
		List<Follow> followUsers= new ArrayList<Follow>();
		followUsers=followSer.getMemberId(id);//로그인 한 사람을 팔로우 하는 사람들
		
		System.out.println(followUsers.toString());
		
		List<Member> follower=new ArrayList<Member>();
		
		for(int i=0; i<followUsers.size(); i++) {
			follower.add(memSer.getMemberById(followUsers.get(i).getMember_id()));
		}
		
		
		
		
		
		
		return ResponseEntity.ok(MemberListGetRes.of(200, "SUCCESS",follower));

	}

	
	

}
