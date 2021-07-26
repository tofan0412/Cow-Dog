package com.xy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Member;
import com.xy.service.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/mem")
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
			@RequestBody @ApiParam(value="회원가입 정보", required = true) Member mem) {
		
		System.out.println(mem.toString());
		if(memSer.registerMember(mem).equals("FAIL")) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "FAIL"));
		}
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}
	
}
