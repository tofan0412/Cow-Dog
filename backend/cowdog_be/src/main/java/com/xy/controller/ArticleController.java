package com.xy.controller;

import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Article;
import com.xy.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cowdog/appeal")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 게시글 조회
     * @return 모든 게시글
     */
    @GetMapping("")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @PostMapping("/create")
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
