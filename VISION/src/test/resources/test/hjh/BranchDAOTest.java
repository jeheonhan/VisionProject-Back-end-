package test.hjh;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.service.branch.BranchDAO;
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
public class BranchDAOTest{

	@Resource(name = "branchDAOImpl")
	private BranchDAO branchDAO;
	
	private SalesProduct salesProduct;
	private BranchDailySales branchDailySales;
	private SalesMenu salesMenu;
	private List<SalesProduct> salesProductList;
	private List<BranchDailySales> branchDailySalesList;
	private List<SalesMenu> salesMenuList;
	
	@Test
	public void testIsertDailySales() throws Exception {
		
		salesProduct = new SalesProduct();
		SalesProduct salesProduct2 = new SalesProduct();
		salesProductList = new ArrayList<SalesProduct>();
		
		salesProduct.setBranchNo("b1024");
		salesProduct.setSalesDate("2019/07/17");
		salesProduct.setMenuNo("1");
		salesProduct.setSalesAmount("150000");
		salesProduct.setSalesQuantity("10");
		
		salesProductList.add(salesProduct);
		System.out.println(salesProductList);
		
		salesProduct2.setBranchNo("b1024");
		salesProduct2.setSalesDate("2019/07/17");
		salesProduct2.setMenuNo("2");
		salesProduct2.setSalesAmount("160000");
		salesProduct2.setSalesQuantity("10");
		
		salesProductList.add(salesProduct2);
		
		System.out.println(salesProductList);
		
		branchDAO.insertDailySales(salesProductList);

	}
	
	//@Test
	public void testSelectDailySalesDetail() throws Exception {
		
		String branchNo = "b1003";
		String salesDate = "2019/06/08";
		List<SalesProduct> list
			= (List<SalesProduct>)branchDAO.selectDailySalesDetail(branchNo, salesDate);
		
		for(int i = 0; i<list.size(); i++) {
			salesProduct = list.get(i);
			System.out.println(salesProduct);
		}
		
	}
	
	//@Test
	public void testSelectBranchDailySalesList() throws Exception {
		
		String branchNo = "b1003";
		
		branchDailySalesList = 
				(List<BranchDailySales>)branchDAO.selectDailySalesList(branchNo);
		
		for(int i = 0; i<branchDailySalesList.size(); i++) {
			branchDailySales = branchDailySalesList.get(i);
			System.out.println(branchDailySales);
		}				
		
	}
	
	//@Test
	public void testUpdateSalesProduct() throws Exception {
		
		salesProduct = new SalesProduct();
		
		salesProduct.setSalesNumbering("4");
		salesProduct.setSalesQuantity("20");
		salesProduct.setSalesAmount("30000");
		salesProduct.setSalesDate("19/07/13");
		
		//branchDAO.updateSalesProduct(salesProduct);
		
		salesProduct.setSalesNumbering("5");
		salesProduct.setSalesQuantity("30");
		salesProduct.setSalesAmount("48000");
		salesProduct.setSalesDate("19/07/13");
		
		//branchDAO.updateSalesProduct(salesProduct);
		
		
		salesProductList
		= (List<SalesProduct>)branchDAO.selectDailySalesDetail("b1003", "19/07/13");
	
		for(int i = 0; i<salesProductList.size(); i++) {
			salesProduct = salesProductList.get(i);
			System.out.println(salesProduct);
		}
		
	}
	
	//@Test
	public void testSelectSalesMenuList() throws Exception {
		
		salesMenuList = 
				(List<SalesMenu>)branchDAO.selectSalesMenuList();
		
		
		for(int i = 0; i<salesMenuList.size(); i++) {
			salesMenu = salesMenuList.get(i);
			System.out.println(salesMenu);
		}
	}
	

}
