package com.xy.controller;

import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Article;
import com.xy.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cowdog/appeal")
@CrossOrigin(origins="http://localhost:8081")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("")
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 전체 목록을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Article> findAll() {
        List<Article> articles = articleService.findAll();
        return articles;
    }

    @PostMapping("/create")
    @ApiOperation(value = "게시글 작성", notes = "<strong>제목, 내용</strong>를 통해 게시글 작성한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
    @ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> create(@RequestBody HashMap<String, Object> map) {
        String result = articleService.create(map);

        if ( result.equals("SUCCESS")) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }



}
