package com.xy.service;

import com.xy.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public List<Article> findAll();

    public String create(Map map);
}
