package com.vision.erp.service.notice;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Notice;
import com.vision.erp.service.domain.NoticeHeader;

public interface NoticeService {
	
	public Notice addNotice(Notice notice) throws Exception;
	
	public Notice getNoticeDetail(String noticeNo) throws Exception;
	
	public List<Notice> getNoticeList(Search search) throws Exception;
	
	public void modifyNotice(Notice notice) throws Exception;
	
	public void convertNoticeUsageStatus(Notice notice) throws Exception;
	
	public List<NoticeHeader> getNoticeHeaderList() throws Exception;
}
