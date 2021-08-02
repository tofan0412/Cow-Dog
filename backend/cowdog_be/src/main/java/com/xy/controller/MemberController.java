package com.xy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xy.api.request.MemberLoginPostReq;
import com.xy.api.response.MemberLoginPostRes;
import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Member;
import com.xy.entity.MemberInfo;
import com.xy.service.MemberService;
import com.xy.auth.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cowdog/mem")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MemberController {

	@Autowired
	MemberService memSer;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;


	@PostMapping("/register")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);

		if (memSer.registerMember(map).equals("FAIL")) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(404, "FAIL"));
		}

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
			@ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
			@ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
			@ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class) })
	public ResponseEntity<? extends BaseResponseBody> login(
			@RequestBody @ApiParam(value = "로그인 정보", required = true) MemberLoginPostReq loginInfo) throws Exception{
		String userId = loginInfo.getId();
		String password = loginInfo.getPassword();
		System.out.println(userId);
		System.out.println(password);
		Member mem = memSer.getMemberByMemberId(userId);


		if (mem == null) {
			System.out.println("회원 읎어");
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "NOT_EXISTS_USER"));
		}
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if (passwordEncoder.matches(password, mem.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.
			System.out.println("로그인 성공");
			long id=mem.getId();
			return ResponseEntity.ok(MemberLoginPostRes.of(200, "SUCESS", jwtUtil.generateToken(userId),id));
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "PASSWORD_INCORRECT"));
	}

	@PostMapping("/confirmId")
	public ResponseEntity<String> confirmUserId(@RequestBody String userId) {

		System.out.println(userId);
		if (memSer.confirmUserId(userId)) {
			System.out.println("아이디 없다");
			return ResponseEntity.status(200).body("NOT_EXISTS_USERID");
		}
		System.out.println("아이디 있다.");
		return ResponseEntity.status(200).body("EXIST_USERID");
	}

	@PostMapping("/confirmNickname")
	public ResponseEntity<String> confirmNickname(@RequestBody String Nickname) {

		System.out.println(Nickname);
		if (memSer.confirmNickname(Nickname)) {
			System.out.println("닉네임 없다");
			return ResponseEntity.status(200).body("NOT_EXISTS_NICKNAME");
		}
		System.out.println("닉네임 있다.");
		return ResponseEntity.status(200).body("EXIST_NICKNAME");
	}
	
	
	
	@GetMapping("/mypage")
	public Member getUserInfo(@RequestParam("userId") int id) {
		System.out.println("현재 로그인한 유저 정보 내놔~~");
		System.out.println(id);
		Member mem=memSer.getMemberById(id);
		System.out.println(mem.toString());
		return mem;
	}
	
	
	
	
	

	
	
	
	
	
	
	

}