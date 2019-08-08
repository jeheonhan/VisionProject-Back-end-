package com.vision.erp.web.productionmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingService;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.InteProduction;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;
import com.vision.erp.service.productionmanagement.ProductionManagementService;

@RestController
public class ProductionManagementController {

	//field
//	@Resource(name = "productionManagementServiceImplcodms")
//	private ProductionManagementService codmsService;
//	
//	@Resource(name = "productionManagementServiceImplrudwn")
//	private ProductionManagementServicerudwn rudwnService;
	
	@Resource(name = "productionManagementServiceImpl")
	private ProductionManagementService productionManagementService;
	
	@Resource(name = "accountingServiceImpl")
	private AccountingService accountingService;


	//constructor
	public ProductionManagementController() {
		super();
		// TODO Auto-generated constructor stub
	}

	//method
	//[주문서목록조회] - 주문서 목록조회(지점, 본사) SearchKeyword에 지점번호
	@RequestMapping(value="/pm/getOrderFromBranchList")
	public List<OrderFromBranch> getOrderFromBranchList(@RequestBody Search search) throws Exception{
		return productionManagementService.getOrderFromBranchList(search);
	}
	
	//[주문서목록조회] - 주문서 상세조회(지점, 본사) SearchKeyword에 주문서번호
	@RequestMapping(value="/pm/getOrderFromBranchDetail")
	public OrderFromBranch getOrderFromBranchDetail(@RequestBody Search search) throws Exception{
		return productionManagementService.getOrderFromBranchDetail(search);
	}
	
	//[주문서목록조회] - 주문취소/취소확정(지점, 본사) 
	//주문서번호, 주문서상태코드 (주문대기01, 주문완료02, 주문진행03, 취소요청04, 취소확정05) 필요
	@RequestMapping(value="/pm/modifyOrderFromBranchStatus")
	public void modifyOrderFromBranchStatus(@RequestBody OrderFromBranch orderFromBranch) throws Exception{
		productionManagementService.modifyOrderFromBranchStatus(orderFromBranch);
	}
	
	//[주문요청] - 준비화면 띄우기 : 계좌번호 가져오기, 물품목록 가져오기
	@RequestMapping(value="/pm/addOrderFromBranchPreparing")
	public Map<String, List> addOrderFromBranchPreparing() throws Exception{
		Map<String, List> map = new HashMap<String, List>();
		//물품목록 가져오기
//		map.put("productList", rudwnService.getProductList());
		
		//계좌번호 가져오기
//		Search search = new Search();
		//계좌 사용처 02주문
//		search.setSearchKeyword("02");
//		map.put("accountList", accountingService.getAccountList(search));
		
		return map;
	}
	
	//[주문요청] - 주문하기
	//branchNo, orderDate, orderFromBranchProductList(productNo, orderFromBranchProductQuantity) 필요
	@RequestMapping(value="/pm/addOrderFromBranch")
	public void addOrderFromBranch(@RequestBody OrderFromBranch orderFromBranch) throws Exception{
		productionManagementService.addOrderFromBranch(orderFromBranch);
	}
	
	//[주문서상세조회] - 주문물품 상태변경(출하완료02)
	//orderFromBranchNo, productNo 필요
	@RequestMapping(value="/pm/modifyOrderFromBranchProductStatus")
	public void modifyOrderFromBranchProductStatus(@RequestBody OrderFromBranchProduct orderFromBranchProduct) throws Exception{
		productionManagementService.modifyOrderFromBranchProductStatus(orderFromBranchProduct);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//얘는 발주요청할때 쓰는거임.
	//ResponseEntity<T> 상태값200이나 400 등 헤더에 보내준다
	@RequestMapping(value="/pm/addOrderPreparing",method=RequestMethod.GET)
	public List<Account> insertOrderFromBranchPreparing() throws Exception{
		
		List<Account> list = new ArrayList<Account>();
		Search search = new Search();
	
		search.setSearchCondition("01");
		list =  accountingService.getAccountList(search);

		return list;
	}

	//물품등록할떄 
	@RequestMapping(value="/pm/addProductPreparing",method=RequestMethod.GET)
	public List<Vendor> addProductPreparing() throws Exception{
		
		List<Vendor> list = new ArrayList<Vendor>();
		Search search = new Search();
		
		search.setSearchCondition("01");
	    list =  accountingService.getVendorList(search);

		return list;
		}
	
	@RequestMapping(value = "/pm/addProduct",method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product)throws Exception{
		System.out.println("addProduct :: " + product);
		productionManagementService.addProduct(product);
	}

	@RequestMapping(value = "/pm/modifyProduct",method=RequestMethod.POST)
	public void modifyProduct(@RequestBody Product product)throws Exception{
		productionManagementService.modifyProduct(product);
	}

	@RequestMapping(value = "/pm/modifyUsageStatus",method=RequestMethod.POST)
	public void modifyUsageStatus(@RequestBody Product product)throws Exception{
		productionManagementService.modifyUsageStatus(product);
	}

	@RequestMapping(value = "/pm/getProductList",method=RequestMethod.GET)
	public List<Product> getProductList() throws Exception{
		
		List<Product> list 
		= (List<Product>)productionManagementService.getProductList();

		return list;
	}


	@RequestMapping(value = "/pm/getOrderToVendorList",method=RequestMethod.GET)
	public List<OrderToVendor> getOrderToVendorList() throws Exception{
		
		List<OrderToVendor> list 
		= (List<OrderToVendor>)productionManagementService.getOrderToVendorList();

		return list;
	}

	@RequestMapping(value = "/pm/modifyOrderToVenCode/{statementNo}/{orderToVendorNo}",method=RequestMethod.GET)
	public void modifyOrderToVenCode(@PathVariable(value="statementNo") String statementNo, @PathVariable String orderToVendorNo) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		OrderToVendor orderToVendor = new OrderToVendor();
		Statement statement = new Statement();
		OrderToVendorProduct orderToVendorProduct = new OrderToVendorProduct();
		
		orderToVendorProduct.setOrderToVendorNo(orderToVendorNo);
		orderToVendor.setOrderToVendorNo(orderToVendorNo);
		statement.setStatementNo(statementNo);
		
		map.put("orderToVendor", orderToVendor);
		map.put("statement", statement);
		map.put("orderToVendorProduct", orderToVendorProduct);
		
		productionManagementService.modifyOrderToVenCode(map);
	}


	@RequestMapping(value = "/pm/getOrderToVendorDetailList",method=RequestMethod.POST)
	public List<OrderToVendorProduct> getOrderToVendorDetailList(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{

		List<OrderToVendorProduct> list 
		= (List<OrderToVendorProduct>)productionManagementService.getOrderToVendorDetailList(orderToVendorProduct);

		return list;
	}

	
	@RequestMapping(value = "/pm/modifyOrderToVenItemCode",method=RequestMethod.POST)
	public void modifyOrderToVenItemCode(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{

		Product product = new Product();

		product.setProductNo(orderToVendorProduct.getProductNo());
		product.setQuantity(orderToVendorProduct.getQuantity());
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("product", product);
		map.put("orderToVendorProduct", orderToVendorProduct);
		
		productionManagementService.modifyOrderToVenItemCode(map);
	}
	
	@RequestMapping(value = "/pm/addOrderToVendor",method=RequestMethod.POST)
	public void insertOrderToVendor(@RequestBody InteProduction inteProduction) throws Exception {
		
		
		OrderToVendor orderToVendor = new OrderToVendor();
		Statement statement = new Statement();
		List<OrderToVendorProduct> productList = inteProduction.getOrderToVendorProduct();
		Map<String, Object> map = new HashMap<String, Object>();

		statement.setTradeAmount(inteProduction.getTotalAmount());
		
		orderToVendor.setTotalAmount(inteProduction.getTotalAmount());

		map.put("statement", statement);
		map.put("orderToVendor", orderToVendor);
		map.put("productList", productList);

		productionManagementService.addOrderToVendor(map);
	}
	
	@RequestMapping(value ="/pm/getProduct/{productNo}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable String productNo) throws Exception {
		return productionManagementService.getDetailProduct(productNo);
	}
}
