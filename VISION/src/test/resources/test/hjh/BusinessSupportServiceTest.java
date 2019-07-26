package test.hjh;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.businesssupport.BusinessSupportService;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.Local;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})

public class BusinessSupportServiceTest{

	@Resource(name = "businessSupportServiceImpl")
	private BusinessSupportService businessSupportService;
	
	private Branch branch;
	private Search search;
	private List<Branch> branchList;
	private Map<String, Object> map;
	
	
	//@Test
	public void testAddBranch() throws Exception {
		
		branch = new Branch();
		
		branch.setBranchName("Test05");
		branch.setZipCode("12345");
		branch.setAddress("Test05");
		branch.setDetailAddress("1111");
		branch.setBusinessLicenseNo("123-12-12355");	//UNIQUE
		branch.setBranchTel("111-111-1155");			//UNIQUE
		branch.setBranchManagerPhone("010-1111-1155");		//UNIQUE
		branch.setBranchManagerName("test05");
		branch.setLocalCodeNo("07");
		branch.setBranchStatusCodeNo("01");
		
		branch = businessSupportService.addBranch(branch);

		System.out.println("insertÇÑ selectBranchDetail : " +branch);
		
	}
	
	//@Test
	public void testGetBranchList() throws Exception {
		
		search = new Search();
		
		search.setSearchCondition("1");
		search.setSearchKeyword("´ë");
		
		map = (Map<String, Object>)businessSupportService.getBranchList(search);
		
		branchList = (List<Branch>)map.get("branchList");

		int totalCount = (Integer)map.get("totalCount");
		
		System.out.println("List ¼ö : "+totalCount);
		for(int i = 0; i<branchList.size(); i++) {
			branch = branchList.get(i);
			System.out.println(branch);
		}
		
	}

	//@Test
	public void testGetBranchDetail() throws Exception {
		
		branch = new Branch();
		
		String branchNo = "b1003";
		
		branch = businessSupportService.getBranchDetail(branchNo);		
		System.out.println("BranchDtail : "+branch);
		
	}
	
	//@Test
	public void testModifyBranch() throws Exception {
		
		branch = new Branch();
		
		branch.setBranchNo("b1024");
		branch.setBranchName("Test06");
		branch.setZipCode("12346");
		branch.setAddress("Test06");
		branch.setDetailAddress("66666");
		branch.setBusinessLicenseNo("123-12-12666");	//UNIQUE
		branch.setBranchTel("111-111-1116");			//UNIQUE
		branch.setBranchManagerPhone("010-1111-1116");		//UNIQUE
		branch.setBranchManagerName("test06");
		branch.setLocalCodeNo("08");
		branch.setBranchStatusCodeNo("01");
		
		businessSupportService.modifyBranch(branch);
		
		branch = businessSupportService.getBranchDetail("b1024");
		
		System.out.println(branch);	
		
		
	}
	
	//@Test
	public void testModifyBranchUsageCode() throws Exception {
		
		branch = new Branch();
		
		branch.setBranchNo("b1024");
		branch.setBranchStatusCodeNo("01");
		
		businessSupportService.convertBranchUsageStatus(branch);
		
		branch = businessSupportService.getBranchDetail("b1024");
		
		System.out.println(branch);
		
	}
	
	//@Test
	public void testGetTotalCount() throws Exception {
		
		search = new Search();
		
		search.setSearchCondition("1");
		search.setSearchKeyword("est");
		
		System.out.println(search);
		map = (Map<String, Object>)businessSupportService.getBranchList(search);
		
		branchList = (List<Branch>)map.get("branchList");
		
		int totalCount = (Integer)map.get("totalCount");
		
		System.out.println("totalCount : "+totalCount);
		
	}
	
	@Test
	public void testLocalList() throws Exception {
		
		List<Local> localList = businessSupportService.getLocalList();
		
		System.out.println("localList :::: "+localList);
	}

}
