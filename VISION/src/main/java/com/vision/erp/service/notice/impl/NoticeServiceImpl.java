package com.vision.erp.service.notice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> getNoticeList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = noticeDAO.selectTotalCount(search);
		map.put("totalCount", totalCount);
		
		List<Notice> noticeList = noticeDAO.selectNoticeList(search);
		map.put("noticeList", noticeList);
		
		return map;
	}

	@Override
	public void modifyNotice(Notice notice) throws Exception {		
		noticeDAO.updateNotice(notice);
	}

	@Override
	public void convertNoticeUsageStatus(Notice notice) throws Exception {
		noticeDAO.updateNoticeUsageCode(notice);
	}

	@Override
	public List<NoticeHeader> getNoticeHeaderList() throws Exception {
		return noticeDAO.selectHeaderNameList();
	}
	
	

}
