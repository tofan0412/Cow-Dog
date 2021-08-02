package com.xy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xy.api.request.NoticeCreatePostReq;
import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Notice;
import com.xy.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cowdog/notice")
@CrossOrigin(origins="http://localhost:8083")
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@PostMapping("") // notice에 post 요청
	@ApiOperation(value = "공지 작성", notes = "공지를 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> create(
			@RequestBody NoticeCreatePostReq noticereq) {
		System.out.println(noticereq);
	
		if(noticeService.createNotice(noticereq).equals("FAIL")) { //NoticeServiceImpl에서 save가 실패하면 FAIL을 return함
			return ResponseEntity.status(200).body(BaseResponseBody.of(404, "FAIL"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
	}
	
}
