package test.hjh;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.service.branch.BranchService;
import com.vision.erp.service.domain.BranchDailySales;
import com.vision.erp.service.domain.SalesMenu;
import com.vision.erp.service.domain.SalesProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})
public class BranchServiceTest{

	@Resource(name = "branchServiceImpl")
	private BranchService branchService;
	
	private SalesProduct salesProduct;
	private BranchDailySales branchDailySales;
	private SalesMenu salesMenu;
	private List<SalesProduct> salesProductList;
	private List<BranchDailySales> branchDailySalesList;
	private List<SalesMenu> salesMenuList;
	
	//@Test
	public void testAddDailySales() throws Exception {
		
		salesProduct = new SalesProduct();
		
		salesProduct.setBranchNo("b1020");
		salesProduct.setSalesDate("2019/07/13");
		salesProduct.setMenuNo("1");
		salesProduct.setSalesAmount("125000");
		salesProduct.setSalesQuantity("9");
		
		branchService.addDailySales(salesProduct);
		
		salesProduct.setBranchNo("b1020");
		salesProduct.setSalesDate("2019/07/13");
		salesProduct.setMenuNo("2");
		salesProduct.setSalesAmount("160000");
		salesProduct.setSalesQuantity("10");
		
		branchService.addDailySales(salesProduct);
		
		salesProductList 
			= (List<SalesProduct>)branchService.getBranchDailySalesDetail("b1020", "2019/07/13");
		
		for(int i = 0; i<salesProductList.size(); i++) {
			SalesProduct salesProduct = salesProductList.get(i);
			System.out.println(salesProduct);
		}
		
	}
	
	//@Test
	public void testSelectDailySalesDetail() throws Exception {		
			
		salesProductList 
		= (List<SalesProduct>)branchService.getBranchDailySalesDetail("b1020", "2019/07/13");
	
		for(int i = 0; i<salesProductList.size(); i++) {
			SalesProduct salesProduct = salesProductList.get(i);
			System.out.println(salesProduct);
		}
		
	}

	//@Test
	public void testgetBranchDailySalesList() throws Exception {
		
		String branchNo = "b1020";
		
		branchDailySalesList = 
				(List<BranchDailySales>)branchService.getBranchDailySalesList(branchNo);
		
		for(int i = 0; i<branchDailySalesList.size(); i++) {
			branchDailySales = branchDailySalesList.get(i);
			System.out.println(branchDailySales);
		}				
		
	}
	
	//@Test
	public void testModifySalesProduct() throws Exception {
		
		salesProduct = new SalesProduct();
		
		salesProduct.setSalesNumbering("4");
		salesProduct.setSalesQuantity("20");
		salesProduct.setSalesAmount("30000");
		salesProduct.setSalesDate("19/07/13");
		
		branchService.modifySalesProduct(salesProduct);
		
		salesProduct.setSalesNumbering("5");
		salesProduct.setSalesQuantity("30");
		salesProduct.setSalesAmount("48000");
		salesProduct.setSalesDate("19/07/13");
		
		branchService.modifySalesProduct(salesProduct);
		
		
		salesProductList
		= (List<SalesProduct>)branchService.getBranchDailySalesDetail("b1003", "19/07/13");
	
		for(int i = 0; i<salesProductList.size(); i++) {
			salesProduct = salesProductList.get(i);
			System.out.println(salesProduct);
		}
		
	}


	@Test
	public void testgetSalesMenuList() throws Exception {
		
		salesMenuList = 
				(List<SalesMenu>)branchService.getSalesMenuList();
		
		for(int i = 0; i<salesMenuList.size(); i++) {
			salesMenu = salesMenuList.get(i);
			System.out.println(salesMenu);
		}
	}
	

}
