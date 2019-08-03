package com.vision.erp.service.notice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Notice;
import com.vision.erp.service.domain.NoticeHeader;
import com.vision.erp.service.notice.NoticeDAO;
import com.vision.erp.service.notice.NoticeService;

@Service("noticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {
	
	@Resource(name = "noticeDAOImpl")
	private NoticeDAO noticeDAO;

	@Override
	public Notice addNotice(Notice notice) throws Exception {
		
		String noticeHeader = "["+notice.getNoticeHeaderCodeName()+"]";
		
		notice.setNoticeHeaderCodeName(noticeHeader);
		
		if(notice.getNoticeHeaderCodeNo().equals("01") || notice.getNoticeHeaderCodeNo().equals("02")) {
			notice.setReadAuthority("01");
		}else {
			notice.setReadAuthority("00");
		}
		
		return noticeDAO.selectNoticeDetail(noticeDAO.insertNotice(notice));		
	}

	@Override
	public Notice getNoticeDetail(String noticeNo) throws Exception {
		
		Notice notice = noticeDAO.selectNoticeDetail(noticeNo);
		
		notice.setViewCount(Integer.toString(Integer.parseInt(notice.getViewCount()) + 1));
		
		noticeDAO.updateNoticeViewCount(notice);
		
		return notice;
	}

	@Override
	public List<Notice> getNoticeList(Search search) throws Exception {
		
		List<Notice> noticeList = noticeDAO.selectNoticeList(search);
		
		return noticeList;
	}

	@Override
	public Notice modifyNotice(Notice notice) throws Exception {		
		noticeDAO.updateNotice(notice);
		
		return noticeDAO.selectNoticeDetail(notice.getNoticeNo());
	}

	@Override
	public void convertNoticeUsageStatus(Notice notice) throws Exception {
		
		if(notice.getNoticeStatusCodeNo().equals("01")) {
			
			notice.setNoticeStatusCodeNo("02");
			noticeDAO.updateNoticeUsageCode(notice);
			
		}else if(notice.getNoticeStatusCodeNo().equals("02")) {
			
			notice.setNoticeStatusCodeNo("01");
			noticeDAO.updateNoticeUsageCode(notice);
			
		}
	}

	@Override
	public List<NoticeHeader> getNoticeHeaderList() throws Exception {
		return noticeDAO.selectHeaderNameList();
	}

}
