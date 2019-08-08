//package com.vision.erp.web.productionmanagementrudwn;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.vision.erp.common.Search;
//import com.vision.erp.service.accounting.AccountingService;
//import com.vision.erp.service.domain.Account;
//import com.vision.erp.service.domain.InteProduction;
//import com.vision.erp.service.domain.OrderToVendor;
//import com.vision.erp.service.domain.OrderToVendorProduct;
//import com.vision.erp.service.domain.Product;
//import com.vision.erp.service.domain.Statement;
//import com.vision.erp.service.domain.Vendor;
//import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;
//
//
//@RestController
//public class ProductionManagementControllerrudwn {
//
//	@Autowired
//	@Qualifier("productionManagementServiceImplrudwn")
//	private ProductionManagementServicerudwn productionManagementServicerudwn;
//	
//	@Resource(name = "accountingServiceImpl")
//	private AccountingService accountingService;
//
//	//얘는 발주요청할때 쓰는거임.
//	//ResponseEntity<T> 상태값200이나 400 등 헤더에 보내준다
//	@RequestMapping(value="/pm/addOrderPreparing",method=RequestMethod.GET)
//	public List<Account> insertOrderFromBranchPreparing() throws Exception{
//		
//		List<Account> list = new ArrayList<Account>();
//		Search search = new Search();
//	
//		search.setSearchCondition("01");
//		list =  accountingService.getAccountList(search);
//
//		return list;
//	}
//
//	//물품등록할떄 
//	@RequestMapping(value="/pm/addProductPreparing",method=RequestMethod.GET)
//	public List<Vendor> addProductPreparing() throws Exception{
//		
//		List<Vendor> list = new ArrayList<Vendor>();
//		Search search = new Search();
//		
//		search.setSearchCondition("01");
//	    list =  accountingService.getVendorList(search);
//
//		return list;
//		}
//	
//	@RequestMapping(value = "/pm/addProduct",method=RequestMethod.POST)
//	public void addProduct(@RequestBody Product product)throws Exception{
//		System.out.println("addProduct :: " + product);
//		productionManagementServicerudwn.addProduct(product);
//	}
//
//	@RequestMapping(value = "/pm/modifyProduct",method=RequestMethod.POST)
//	public void modifyProduct(@RequestBody Product product)throws Exception{
//		productionManagementServicerudwn.modifyProduct(product);
//	}
//
//	@RequestMapping(value = "/pm/modifyUsageStatus",method=RequestMethod.POST)
//	public void modifyUsageStatus(@RequestBody Product product)throws Exception{
//		productionManagementServicerudwn.modifyUsageStatus(product);
//	}
//
//	@RequestMapping(value = "/pm/getProductList",method=RequestMethod.GET)
//	public List<Product> getProductList() throws Exception{
//		
//		List<Product> list 
//		= (List<Product>)productionManagementServicerudwn.getProductList();
//
//		return list;
//	}
//
//
//	@RequestMapping(value = "/pm/getOrderToVendorList",method=RequestMethod.GET)
//	public List<OrderToVendor> getOrderToVendorList() throws Exception{
//		
//		List<OrderToVendor> list 
//		= (List<OrderToVendor>)productionManagementServicerudwn.getOrderToVendorList();
//
//		return list;
//	}
//
//	@RequestMapping(value = "/pm/modifyOrderToVenCode/{statementNo}/{orderToVendorNo}",method=RequestMethod.GET)
//	public void modifyOrderToVenCode(@PathVariable(value="statementNo") String statementNo, @PathVariable String orderToVendorNo) throws Exception {
//	
//		Map<String, Object> map = new HashMap<String, Object>();
//		OrderToVendor orderToVendor = new OrderToVendor();
//		Statement statement = new Statement();
//		OrderToVendorProduct orderToVendorProduct = new OrderToVendorProduct();
//		
//		orderToVendorProduct.setOrderToVendorNo(orderToVendorNo);
//		orderToVendor.setOrderToVendorNo(orderToVendorNo);
//		statement.setStatementNo(statementNo);
//		
//		map.put("orderToVendor", orderToVendor);
//		map.put("statement", statement);
//		map.put("orderToVendorProduct", orderToVendorProduct);
//		
//		productionManagementServicerudwn.modifyOrderToVenCode(map);
//	}
//
//
//	@RequestMapping(value = "/pm/getOrderToVendorDetailList",method=RequestMethod.POST)
//	public List<OrderToVendorProduct> getOrderToVendorDetailList(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{
//
//		List<OrderToVendorProduct> list 
//		= (List<OrderToVendorProduct>)productionManagementServicerudwn.getOrderToVendorDetailList(orderToVendorProduct);
//
//		return list;
//	}
//
//	
//	@RequestMapping(value = "/pm/modifyOrderToVenItemCode",method=RequestMethod.POST)
//	public void modifyOrderToVenItemCode(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{
//
//		Product product = new Product();
//
//		product.setProductNo(orderToVendorProduct.getProductNo());
//		product.setQuantity(orderToVendorProduct.getQuantity());
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("product", product);
//		map.put("orderToVendorProduct", orderToVendorProduct);
//		
//		productionManagementServicerudwn.modifyOrderToVenItemCode(map);
//	}
//	
//	@RequestMapping(value = "/pm/addOrderToVendor",method=RequestMethod.POST)
//	public void insertOrderToVendor(@RequestBody InteProduction inteProduction) throws Exception {
//		
//		
//		OrderToVendor orderToVendor = new OrderToVendor();
//		Statement statement = new Statement();
//		List<OrderToVendorProduct> productList = inteProduction.getOrderToVendorProduct();
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		statement.setTradeAmount(inteProduction.getTotalAmount());
//		
//		orderToVendor.setTotalAmount(inteProduction.getTotalAmount());
//
//		map.put("statement", statement);
//		map.put("orderToVendor", orderToVendor);
//		map.put("productList", productList);
//
//		productionManagementServicerudwn.addOrderToVendor(map);
//	}
//	
//	@RequestMapping(value ="/pm/getProduct/{productNo}",method=RequestMethod.GET)
//	public Product getProduct(@PathVariable String productNo) throws Exception {
//		return productionManagementServicerudwn.getDetailProduct(productNo);
//	}
//
//}
