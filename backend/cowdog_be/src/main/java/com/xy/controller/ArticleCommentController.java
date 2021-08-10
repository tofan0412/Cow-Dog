package com.xy.controller;

import com.xy.common.response.BaseResponseBody;
import com.xy.entity.Article_Comments;
import com.xy.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cowdog/appealComment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleCommentController {

    @Autowired
    ArticleCommentService articleCommentService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<? extends BaseResponseBody> create(@RequestBody HashMap<String, Object> map) {
        Long result = articleCommentService.create(map);

        if ( result != 0L) {
            // 성공 시 작성된 게시글 번호를 반환
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, Long.toString(result)));
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }

}
