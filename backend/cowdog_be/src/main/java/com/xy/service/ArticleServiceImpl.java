package com.xy.service;

import com.xy.entity.Article;
import com.xy.entity.Member;
import com.xy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public String create(Map map) {
        Article article = new Article();

        // 임시 멤버 추가
        Member member = new Member();
        member.setMemberid("tofan");

        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        article.setMember_id(member);

        if (articleRepository.save(article) != null) {
            return "SUCCESS";
        }
        return "FAIL";
    }


}
