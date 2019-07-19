package com.vision.erp.web.notice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Notice;
import com.vision.erp.service.domain.NoticeHeader;
import com.vision.erp.service.notice.NoticeService;

@RestController
public class NoticeController {
	
	@Resource(name = "noticeServiceImpl")
	private NoticeService noticeService;
	
	@RequestMapping(value = "/notice/addNotice", method = RequestMethod.POST)
	public Notice addNotice(@RequestBody Notice notice) throws Exception {
		
		return noticeService.addNotice(notice);
	}
	
	@RequestMapping(value = "/notice/getNoticeDetail/{noticeNo}", method = RequestMethod.GET)
	public Notice getNoticeDetail(@PathVariable String noticeNo) throws Exception {
		
		return noticeService.getNoticeDetail(noticeNo);
	}
	
	@RequestMapping(value = "/notice/getNoticeList", method = RequestMethod.POST)
	public List<Notice> getNoticeList(@RequestBody Search search) throws Exception {
		
		return noticeService.getNoticeList(search);
	}
	
	@RequestMapping(value = "/notice/modifyNotice", method = RequestMethod.POST)
	public Notice modifyNotice(@RequestBody Notice notice) throws Exception {
		
		noticeService.modifyNotice(notice);
		
		return noticeService.getNoticeDetail(notice.getNoticeNo());
	}
	
	@RequestMapping(value = "/notice/convertNoticeUsageStatus", method = RequestMethod.POST)
	public void convertNoticeUsageStatus(@RequestBody Notice notice) throws Exception {
		
		noticeService.convertNoticeUsageStatus(notice);
	}
	
	@RequestMapping(value = "/notice/getNoticeHeaderList", method = RequestMethod.GET)
	public List<NoticeHeader> getNoticeHeaderList() throws Exception {
		
		
		return noticeService.getNoticeHeaderList();
	}

}
