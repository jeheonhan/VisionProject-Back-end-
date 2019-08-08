//package test.pkj;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.vision.erp.common.Search;
//import com.vision.erp.service.accounting.AccountingDAO;
//import com.vision.erp.service.domain.HumanResourceCard;
//import com.vision.erp.service.domain.InteProduction;
//import com.vision.erp.service.domain.OrderToVendor;
//import com.vision.erp.service.domain.OrderToVendorProduct;
//import com.vision.erp.service.domain.Product;
//import com.vision.erp.service.domain.Statement;
//import com.vision.erp.service.domain.User;
//
//import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;
//import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;
//import com.vision.erp.service.user.UserDAO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"classpath:config/root-context.xml",
//		"classpath:config/aspect-context.xml",
//		"classpath:config/servlet-context.xml",
//		"classpath:config/transaction-context.xml"
//})
//public class OrderToVendorTest{
//
//	@Resource(name = "productionManagementDAOImplrudwn")
//	private ProductionManagementDAOrudwn productionDAO;
//	
//	@Resource(name = "productionManagementServiceImplrudwn")
//	private ProductionManagementServicerudwn productionManagementServicerudwn;
//
//	@Autowired
//	@Qualifier("accountingDAOImpl")
//	private AccountingDAO accountingDAO;
//	
//	
//
//	//@Test
//	public void addOrderToVendor() throws Exception {
//
//		OrderToVendor orderToVendor = new OrderToVendor();
//		Statement statement = new Statement();
//		List<OrderToVendorProduct> productList = new ArrayList<OrderToVendorProduct>();
//		OrderToVendorProduct orderToVendorProduct = new OrderToVendorProduct();
//
//		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
//		String date = format.format (System.currentTimeMillis());
//
//		//		statement.setStatementCategoryCodeNo("02");
//		//		statement.setTradeDate(date);
//		//		statement.setTradeTargetName("양주점");
//		//		statement.setStatementDetail("발주");
//		//		statement.setTradeAmount("40000");
//		//		statement.setAccountNo("1002384718373");
//		//		statement.setStatementUsageStatusCodeNo("01");
//		//		
//		//		System.out.println(statement);
//		//		
//		//		accountingDAO.insertStatement(statement);
//		//		
//		//		String statementNo = statement.getStatementNo();
//		//		
//
//		//statementno 30
//
//
//		//		orderToVendor.setTotalAmount("40000");
//		//		orderToVendor.setOrderToVenStatusCodeNo("01");
//		//		orderToVendor.setStatementNo("1030");
//		//		
//		//		productionDAO.addOrderToVendor(orderToVendor);
//		//		
//		//		String orderToVendorNo = orderToVendor.getOrderToVendorNo();
//		//		
//		//		System.out.println(orderToVendorNo);
//
//		// orderToVendorNo 10005
//		//외래키는 orderToVendorNo와 productNo임
//		orderToVendorProduct.setOrderToVendorNo("10005");
//		orderToVendorProduct.setProductNo("10005");
//		orderToVendorProduct.setPurchasePrice("400");
//		orderToVendorProduct.setQuantity("20");
//		orderToVendorProduct.setAmount("6000");
//		orderToVendorProduct.setOrderToVendorProductStatusCodeNo("01");
//		orderToVendorProduct.setProductName("닭");
//
//		productList.add(orderToVendorProduct);
//
//		orderToVendorProduct.setOrderToVendorNo("10005");
//		orderToVendorProduct.setProductNo("10006");
//		orderToVendorProduct.setPurchasePrice("300");
//		orderToVendorProduct.setQuantity("10");
//		orderToVendorProduct.setAmount("3000");
//		orderToVendorProduct.setOrderToVendorProductStatusCodeNo("01");
//		orderToVendorProduct.setProductName("양념");
//
//		productList.add(orderToVendorProduct);
//
//		System.out.println("productList ::: " + productList);
//		for(int i = 0; i < productList.size();i++) {
//
//			orderToVendorProduct = productList.get(i);
//
////			productionDAO.addorderToVendorProduct(orderToVendorProduct);
//
//		}
//	}
//	//@Test
//	public void selectOrderToVendorList() throws Exception {
//
//		List<OrderToVendor> list 
//		= (List<OrderToVendor>)productionDAO.selectOrderToVendorList();
//
//		for(int i = 0; i<list.size(); i++) {
//			OrderToVendor orderToVendor = list.get(i);
//			System.out.println(orderToVendor);
//		}
//
//	}
//
//	//@Test
//	public void modifyOrderToVenCode() throws Exception {
//
//		OrderToVendor orderToVendor = new OrderToVendor();
//
//		orderToVendor.setOrderToVendorNo("1022");
//		orderToVendor.setOrderToVenStatusCodeNo("01");
//
//
//		System.out.println(orderToVendor);
//
////		productionDAO.modifyOrderToVenCode(orderToVendor);
//
//	}
//
//
//	
//
//	@Test
//	public void addOrderToVendorService() throws Exception {
//		
//		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
//		String date = format.format (System.currentTimeMillis());
//		
//		OrderToVendor orderToVendor = new OrderToVendor();
//		Statement statement = new Statement();
//		List<OrderToVendorProduct> productList = new ArrayList<OrderToVendorProduct>();
//		OrderToVendorProduct orderToVendorProduct1 = new OrderToVendorProduct();
//		OrderToVendorProduct orderToVendorProduct2 = new OrderToVendorProduct();
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		statement.setStatementCategoryCodeNo("02");
//		statement.setTradeDate(date);
//		statement.setTradeTargetName("노원점");
//		statement.setStatementDetail("발주");
//		statement.setTradeAmount("50000");
//		statement.setAccountNo("1002384718373");
//		
//		orderToVendor.setTotalAmount("50000");
//		orderToVendor.setOrderToVenStatusCodeNo("01");
//		
//		orderToVendorProduct1.setProductNo("10023");
//		orderToVendorProduct1.setPurchasePrice("33");
//		orderToVendorProduct1.setQuantity("10");
//		orderToVendorProduct1.setAmount("330");
//		orderToVendorProduct1.setOrderToVendorProductStatusCodeNo("01");
//		orderToVendorProduct1.setProductName("양파");
//		
//		productList.add(orderToVendorProduct1);
//
//		orderToVendorProduct2.setProductNo("10024");
//		orderToVendorProduct2.setPurchasePrice("333");
//		orderToVendorProduct2.setQuantity("10");
//		orderToVendorProduct2.setAmount("3330");
//		orderToVendorProduct2.setOrderToVendorProductStatusCodeNo("01");
//		orderToVendorProduct2.setProductName("생강");
//
//		productList.add(orderToVendorProduct2);
//		
//		map.put("statement", statement);
//		map.put("orderToVendor", orderToVendor);
//		map.put("productList", productList);	
//
//		productionManagementServicerudwn.addOrderToVendor(map);
//			
//		}
//}
