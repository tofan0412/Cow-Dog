package com.xy.service;
import java.sql.Timestamp;
import java.util.List;

import com.xy.api.request.ArticleReportPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.ArticleReport;
import com.xy.repository.ArticleReportRepository;
import com.xy.repository.ArticleRepository;

@Service
public class ArticleReportServiceImpl implements ArticleReportService {
	@Autowired
	ArticleReportRepository articleReportRepo;
	ArticleRepository articleRepo;
	
	@Override
	public String createArticleReport(ArticleReportPostReq request) {
		System.out.println(request);
		ArticleReport newArticleReport = new ArticleReport();
		newArticleReport.setTitle(request.getTitle());
		newArticleReport.setContent(request.getContent());
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		newArticleReport.setRegtime(date_now);
		newArticleReport.setReportedArticleNo(request.getReportedArticleNo());
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
	public void deleteArticleReport(Long articleReportNo) {
		articleReportRepo.deleteById(articleReportNo);
	}
	
	// 유저 삭제(유저 삭제 후 유저 관련 신고도 삭제)
	@Override
	public void deleteReportedArticle(String reportedArticleNo, Long reportedArticleLongNo) {
		System.out.println(reportedArticleNo);
		System.out.println(reportedArticleLongNo);
		articleReportRepo.deleteByReportedArticleNo(reportedArticleNo); // userId와 관련된 userReport 모두 삭제
		articleRepo.deleteById(reportedArticleLongNo);
	}
	
}
