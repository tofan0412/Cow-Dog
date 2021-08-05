package com.xy.service;

import com.xy.entity.Article;
import com.xy.entity.Member;
import com.xy.repository.ArticleRepository;
import com.xy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
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
    public Long create(Map map) {
        // 1. 사용자 정보 찾기
        System.out.println();
        int temp = (Integer) map.get("member_id");
        String member_id = Integer.toString(temp);

        Member member = memberRepository.getBymemberid(member_id);

        Article article = new Article();
        article.setTitle((String) map.get("title"));
        article.setContent((String) map.get("content"));
        article.setMember_id(member);

        Article result = articleRepository.save(article);
        if ( result != null) {
            // create 성공한 경우 DB에 저장한 객체를 반환한다.
            System.out.println(result.getArticleNo());
            return result.getArticleNo();
        }
        return 0L;
    }

    @Override
    public Article findArticleByArticleNo(Long articleNo) {
        return articleRepository.findArticleByArticleNo(articleNo);
    }


}
