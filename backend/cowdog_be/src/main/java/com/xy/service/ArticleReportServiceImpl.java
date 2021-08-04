package com.xy.service;
import java.sql.Timestamp;
import java.util.List;

import com.xy.api.request.ArticleReportPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.ArticleReport;
import com.xy.repository.ArticleReportRepository;

@Service
public class ArticleReportServiceImpl implements ArticleReportService {
	@Autowired
	ArticleReportRepository articleReportRepo;
	
	@Override
	public String createArticleReport(ArticleReportPostReq request) {
		System.out.println(request);
		ArticleReport newArticleReport = new ArticleReport();
		newArticleReport.setTitle(request.getTitle());
		newArticleReport.setContent(request.getContent());
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		newArticleReport.setRegtime(date_now);
		newArticleReport.setReportedArticleNo(request.getReportedArticleNo());
		newArticleReport.setArticleUrl(request.getArticleUrl());
		if(articleReportRepo.save(newArticleReport)!=null) {
			return "SUCCESS";
		}
		return "FAIL";
	}

	@Override
	public List<ArticleReport> getArticleReportList() {
	    return articleReportRepo.findAll();
	}
	
	@Override
	public void deleteReportedArticle(Long reportedArticleNo) {
		articleReportRepo.deleteById(reportedArticleNo);
	}
	
}
