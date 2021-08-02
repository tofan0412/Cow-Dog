package com.xy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xy.entity.ArticleReport;

@Repository
public interface ArticleReportRepository extends JpaRepository<ArticleReport, Long> {
	
}
