package com.xy.service;

import java.util.List;

import com.xy.api.request.NoticeCreatePostReq;
import com.xy.entity.Notice;

public interface NoticeService {
	public String createNotice(NoticeCreatePostReq noticereq);
	public List<Notice> getNoticeList();
}
