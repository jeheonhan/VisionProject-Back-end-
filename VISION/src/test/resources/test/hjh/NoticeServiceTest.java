package test.hjh;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Notice;
import com.vision.erp.service.domain.NoticeHeader;
import com.vision.erp.service.notice.NoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})
public class NoticeServiceTest{

	@Resource(name = "noticeServiceImpl")
	private NoticeService noticeService;
	
	private Notice notice;
	private Search search;
	private NoticeHeader noticeHeader;
	private List<Notice> noticeList;
	private List<NoticeHeader> noticeHeaderList;
	private Map<String, Object> map;
	
	
	//@Test
	public void testAddNotice() throws Exception{
		
		notice = new Notice();
		
		notice.setNoticeTitle("test05");
		notice.setContent("test05");
		notice.setEmployeeNo("1001");
		notice.setReadAuthority("01");
		
		notice = noticeService.addNotice(notice);
				
		System.out.println(notice);
	}
		
	//@Test
	public void testgetToitalCount() throws Exception {
		
		search = new Search();
		search.setSearchCondition("1");
		search.setSearchKeyword("안");
		
		map = (Map<String, Object>)noticeService.getNoticeList(search);
		
		int totalNo = (Integer)map.get("totalCount");
		
		System.out.println("totalNo = "+totalNo);
		
		
	}
	
	@Test
	public void testGetNoticeList() throws Exception {
		
		search = new Search();
		
		search.setSearchCondition("0");
		search.setSearchKeyword("est");
		map = (Map<String, Object>)noticeService.getNoticeList(search);
		
		noticeList = (List<Notice>)map.get("noticeList");
		
		int totalNo = (Integer)map.get("totalCount");
		
		System.out.println("totalNo = "+totalNo);
		
		for(int i = 0; i<noticeList.size(); i++) {
			notice = noticeList.get(i);
			System.out.println(notice);
		}
		
	}
	
	//@Test
	public void testGetNoticeDetail()  throws Exception{
		
		notice = new Notice();
		
		notice = noticeService.getNoticeDetail("1");
		
		System.out.println(notice);
	}
	
	//@Test
	public void testgetNoticeHeaderList() throws Exception {
		
		noticeHeader = new NoticeHeader();
		
		noticeHeaderList = (List<NoticeHeader>)noticeService.getNoticeHeaderList();
		
		for(int i = 0; i<noticeHeaderList.size(); i++) {
			noticeHeader = noticeHeaderList.get(i);
			System.out.println(noticeHeader);
		}
		
	}
	
	//@Test
	public void testModifyNotice() throws Exception {
		
		notice = new Notice();
		
		notice.setNoticeNo("22");
		notice.setEmployeeNo("1001");
		notice.setNoticeTitle("updateTest06");
		notice.setContent("업데이트테스트06");
		notice.setReadAuthority("1");
		
		noticeService.modifyNotice(notice);
		
		notice = noticeService.getNoticeDetail("22");
		
		System.out.println("notice update 내용확인 : "+notice);
		
	}
	
	//@Test
	public void testConvertNoticeStatusCode() throws Exception {
		
		notice = new Notice();
		
		notice.setNoticeNo("22");
		notice.setNoticeStatusCodeNo("01");
		
		noticeService.convertNoticeUsageStatus(notice);
		
		notice = noticeService.getNoticeDetail("22");
		
		System.out.println(notice);
		
	}
	
	//@Test
	public void testViewCount() throws Exception {
		
		notice = new Notice();
		
		notice = noticeService.getNoticeDetail("22");
		
		String viewCount = notice.getViewCount();
		
		System.out.println("조회수 : "+viewCount);
		
	}
	

}
