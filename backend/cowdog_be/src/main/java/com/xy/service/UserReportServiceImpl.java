package com.xy.service;
import java.sql.Timestamp;
import java.util.List;

import com.xy.api.request.UserReportPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.UserReport;
import com.xy.repository.UserReportRepository;

@Service
public class UserReportServiceImpl implements UserReportService{ // NoticeService를 상속받아 내용을 적을 것
	
	@Autowired
	UserReportRepository userReportRepo; //NoticeRepository를 가져와서 noticeRepo로 이름지음
	
	
	@Override
	public String createUserReport(UserReportPostReq request) {
		System.out.println(request);
		
		UserReport newUserReport = new UserReport();
		// title
		newUserReport.setTitle(request.getTitle());
		// content
		newUserReport.setContent(request.getContent());
		// regtime
		Timestamp date_now = new Timestamp(System.currentTimeMillis());
		newUserReport.setRegtime(date_now);
		// member_id
		newUserReport.setReportedId(request.getReportedId());
		// image 추가
		if(userReportRepo.save(newUserReport)!=null) {
			return "SUCCESS";
		}
		return "FAIL";
	}

	@Override
	public List<UserReport> getUserReportList() {
	    return userReportRepo.findAll();
	}
	
}
