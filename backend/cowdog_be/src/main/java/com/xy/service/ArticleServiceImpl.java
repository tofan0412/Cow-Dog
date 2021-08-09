package com.xy.service;

import com.xy.entity.Article;
import com.xy.entity.Member;
import com.xy.repository.ArticleRepository;
import com.xy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Long create(Map map) {
        // 1. 사용자 정보 찾기
    	
        System.out.println();
        System.out.println("SERVICEC   "+map.toString());
      
        Article article = new Article();
        article.setMemberId((Long) (Long.parseLong(map.get("member_id").toString())));
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        article.setWriter(map.get("writer").toString());

        // 작성일 입력
        Timestamp now = new Timestamp(System.currentTimeMillis());
        article.setRegtime(now);

        Article result = articleRepository.save(article);
        if ( result != null) {
            // create 성공한 경우 DB에 저장한 객체를 반환한다.
            System.out.println(result.getArticleNo());
            return result.getArticleNo();
        }
        else {
            return 0L;
        }

    }

    @Override
    public Article findArticleByArticleNo(Long articleNo) {
        return articleRepository.findArticleByArticleNo(articleNo);
    }

    @Override
    public String deleteArticle(Long articleNo) {
        articleRepository.deleteById(articleNo);
        return "SUCCESS";
    }

    @Override
    public Long update(Map map) {
        Long articleNo = Long.parseLong(map.get("articleNo").toString());
        System.out.println("게시글 수정합니다! " + articleNo + "번 게시글 수정합니다.");

        Article article = articleRepository.findArticleByArticleNo(articleNo);

        article.setTitle(map.get("title").toString());
        article.setContent(map.get("content").toString());
        article.setRegtime(new Timestamp(System.currentTimeMillis()));

        Article result = articleRepository.save(article);
        if (result != null){
            return result.getArticleNo();
        }
        else {
            return 0L;
        }
    }


}
