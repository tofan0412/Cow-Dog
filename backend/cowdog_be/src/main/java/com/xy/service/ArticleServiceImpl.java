package com.xy.service;

import com.xy.entity.Article;
import com.xy.entity.Member;
import com.xy.repository.ArticleRepository;
import com.xy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public String create(Map map) {

        // 1. 사용자 정보 찾기
        String member_id = (String) map.get("member_id");
        Member member = memberRepository.getBymemberid(member_id);

        Article article = new Article();
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));


        article.setMember_id(member);

        if (articleRepository.save(article) != null) {
            return "SUCCESS";
        }
        return "FAIL";
    }


}
