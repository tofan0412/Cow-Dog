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
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional // 트랜잭션 설정
    public ResponseEntity<? extends BaseResponseBody> create(@RequestBody HashMap<String, Object> map) {
        // 작성된 게시글 번호 또는 0L을 반환한다.
        Long result = articleService.create(map);

        if ( result != 0L) {
            // 성공 시 작성된 게시글 번호를 반환
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, Long.toString(result)));
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }

    @GetMapping("/detail")
    public Article findArticleByArticleNo(@RequestParam("articleNo") Long articleNo){
        System.out.println("게시글 번호는~~!~!~!" + articleNo);
        // 프론트로부터 게시글 PK를 받아, 해당 게시글 객체를 반환한다.
        Article result = articleService.findArticleByArticleNo(articleNo);
        return result;
    }





}
