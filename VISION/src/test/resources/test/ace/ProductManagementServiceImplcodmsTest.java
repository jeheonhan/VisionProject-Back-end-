package test.ace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.productionmanagement.ProductionManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml",
		"classpath:config/common-context.xml"
})

public class ProductManagementServiceImplcodmsTest{

	@Resource(name = "productionManagementServiceImplcodms")
	private ProductionManagementService codmsService;
	
	//주문서등록
	//@Test
	public void addOrderFromBranchTest() throws Exception{
		OrderFromBranch ob = new OrderFromBranch();
		ob.setOrderFromBranchTotalAmount("1300");
		ob.setBranchNo("b1004");
		ob.setBranchName("용산점");
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
		String date = format.format (System.currentTimeMillis());
		ob.setOrderDate(date);
		ob.setAccountNo("138294382947");
		List<OrderFromBranchProduct> list = new ArrayList<OrderFromBranchProduct>();
		OrderFromBranchProduct op1 = new OrderFromBranchProduct("10005", "300", null, "3", "900");
		OrderFromBranchProduct op2 = new OrderFromBranchProduct("10006", "400", null, "1", "400");
		list.add(op1);
		list.add(op2);
		ob.setOrderFromBranchProductList(list);
		
		codmsService.addOrderFromBranch(ob);
	}
	
	//주문서 상태 변경
	//@Test
	public void modifyOrderFromBranchStatusTest() throws Exception{
		Search search = new Search();
		search.setSearchKeyword("10041");
		codmsService.getOrderFromBranchDetail(search);
		OrderFromBranch ob = codmsService.getOrderFromBranchDetail(search);
		ob.setOrderFromBranchStatusCodeNo("05");
		codmsService.modifyOrderFromBranchStatus(ob);
	}
	
	//[본사, 지점]주문서리스트 가져오기(주문물품 채워서) 지점번호 searchKeyword에 채우기
	//@Test
	public void getOrderFromBranchListTest() throws Exception{
		Search search = new Search();
		search.setSearchKeyword("b1004");
		List<OrderFromBranch> list = codmsService.getOrderFromBranchList(search);
		for(OrderFromBranch ob : list) {
			System.out.println(ob);
		}
	}
	
	//[본사, 지점]주문서 상세보기 주문서번호 searchKeyword에 채우기
	//@Test
	public void getOrderFromBranchDetailTest() throws Exception{
		Search search = new Search();
		search.setSearchKeyword("10041");
		System.out.println(codmsService.getOrderFromBranchDetail(search));
	}
	
	//[본사] 주문물품 상태변경(출하대기01, 출하완료02, 출하철회03)
	//주문서번호, 물품번호 있어야함
	//@Test
	public void modifyOrderFromBranchProductStatusTest() throws Exception{
		Search search = new Search();
		search.setSearchKeyword("10040");
		codmsService.modifyOrderFromBranchProductStatus(codmsService.getOrderFromBranchDetail(search).getOrderFromBranchProductList().get(0).setOrderFromBranchProductStatusCodeNo("02"));
	}
	
}