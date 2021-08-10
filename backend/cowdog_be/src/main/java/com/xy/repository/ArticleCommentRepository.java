package com.xy.repository;

import com.xy.entity.Article_Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<Article_Comments, Long> {

}
