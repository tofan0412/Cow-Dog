package com.xy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xy.api.request.MemberLoginPostReq;
import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Member;
import com.xy.entity.MemberInfo;
import com.xy.service.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cowdog/mem")
public class MemberController {

	@Autowired
	MemberService memSer;
	
	
	
	
	@PostMapping("/register")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody HashMap<String, Object> map) {
		Map memberInfoMap=(Map)map.get("memberinfo");
		Map memberMap=(Map)map.get("member");
		
		
		
		
		if(memSer.registerMember(memberMap, memberInfoMap).equals("FAIL")) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(404, "FAIL"));
		}
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}
	
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<? extends BaseResponseBody> login(@RequestBody @ApiParam(value="로그인 정보", required = true) MemberLoginPostReq loginInfo) {
		String userId = loginInfo.getId();
		String password = loginInfo.getPassword();
		System.out.println(userId);
		System.out.println(password);
		Member mem = memSer.getMemberByMemberId(userId);
		if(mem==null) {
			System.out.println("회원 읎어");
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "FAIL"));
		}
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(200).body(BaseResponseBody.of(200,"SUCCESS"));
	}
	

	@PostMapping("/confirmId")
	public ResponseEntity<String> confirmUserId(@RequestBody String userId) {
		
		System.out.println(userId);
		if(memSer.confirmUserId(userId)) {
			System.out.println("아이디 없다");
			return ResponseEntity.status(200).body("SUCCESS");
		}
		System.out.println("아이디 있다.");
		return ResponseEntity.status(200).body("FAIL");
	}
	
	
	
	
	
	
}
