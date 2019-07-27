package com.vision.erp.web.productionmanagementrudwn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingService;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.InteProduction;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.User;
import com.vision.erp.service.domain.Vendor;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;


@RestController
public class ProductionManagementControllerrudwn {

	@Autowired
	@Qualifier("productionManagementServiceImplrudwn")
	private ProductionManagementServicerudwn productionManagementServicerudwn;
	
	@Resource(name = "accountingServiceImpl")
	private AccountingService accountingService;

	//얘는 발주요청할때 쓰는거임.
	@RequestMapping(value="/pm/addOrderPreparing",method=RequestMethod.GET)
	public List<Account> addOrderFromBranchPreparing() throws Exception{
		
		List<Account> list = new ArrayList<Account>();
		
					 Search search = new Search();
	
					 search.setSearchCondition("01");
					 list =  accountingService.getAccountList(search);

		return list;
	}
	
	//물품등록할떄 
		@RequestMapping(value="/pm/addProductPreparing",method=RequestMethod.GET)
		public List<Vendor> addProductPreparing() throws Exception{
			System.out.println("물품등록할때 쓰는겅ㅁ");
			List<Vendor> list = new ArrayList<Vendor>();
			
						 Search search = new Search();
		
						 search.setSearchCondition("01");
						 list =  accountingService.getVendorList(search);

			return list;
		}
	
	

	@RequestMapping(value = "/pm/addProduct",method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product)throws Exception{
		System.out.println("왔냐.");
		System.out.println("product ::" + product);
		product.setProductUsageStatusCodeNo("01");
		productionManagementServicerudwn.addProduct(product);
	}

	@RequestMapping(value = "/pm/updateProduct",method=RequestMethod.POST)
	public void updateProduct(@RequestBody Product product)throws Exception{
		productionManagementServicerudwn.updateProduct(product);
	}

	@RequestMapping(value = "/pm/updateUsageStatus",method=RequestMethod.POST)
	public void updateUsageStatus(@RequestBody Product product)throws Exception{
		productionManagementServicerudwn.updateUsageStatus(product);
	}

	@RequestMapping(value = "/pm/selectProductList",method=RequestMethod.GET)
	public List<Product> selectProductList() throws Exception{
		System.out.println("들어와라 셀렉트프로덕트리스트");
		List<Product> list 
		= (List<Product>)productionManagementServicerudwn.selectProductList();

		for(int i = 0; i<list.size(); i++) {
			Product product = list.get(i);
			System.out.println(product);
		}

		return list;
	}


	@RequestMapping(value = "/pm/selectOrderToVendorList",method=RequestMethod.GET)
	public List<OrderToVendor> selectOrderToVendorList() throws Exception{
		List<OrderToVendor> list 
		= (List<OrderToVendor>)productionManagementServicerudwn.selectOrderToVendorList();

		for(int i = 0; i<list.size(); i++) {
			OrderToVendor orderToVendor = list.get(i);
			System.out.println(orderToVendor);
		}

		return list;
	}

	@RequestMapping(value = "/pm/modifyOrderToVenCode/{statementNo}/{orderToVendorNo}",method=RequestMethod.GET)
	public void modifyOrderToVenCode(@PathVariable String statementNo, @PathVariable String orderToVendorNo) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		OrderToVendor orderToVendor = new OrderToVendor();
		Statement statement = new Statement();

		orderToVendor.setOrderToVendorNo(orderToVendorNo);
		orderToVendor.setOrderToVenStatusCodeNo("01");
		statement.setStatementNo(statementNo);
		statement.setStatementUsageStatusCodeNo("02");

		map.put("orderToVendor", orderToVendor);
		map.put("statement", statement);

		productionManagementServicerudwn.modifyOrderToVenCode(map);
	}


	@RequestMapping(value = "/pm/orderToVendorDetailList",method=RequestMethod.POST)
	public List<OrderToVendorProduct> orderToVendorDetailList(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{
		System.out.println("ordertovendordetailstart start start");
		List<OrderToVendorProduct> list 
		= (List<OrderToVendorProduct>)productionManagementServicerudwn.orderToVendorDetailList(orderToVendorProduct);

		for(int i = 0; i<list.size(); i++) {
			OrderToVendorProduct orderToVendorProduct1 = list.get(i);
			System.out.println(orderToVendorProduct1);
		}

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

		productionManagementServicerudwn.modifyOrderToVenItemCode(map);
	}


	@RequestMapping(value = "/pm/addOrderToVendor",method=RequestMethod.POST)
	public void addOrderToVendor(@RequestBody InteProduction inteProduction) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
		String date = format.format (System.currentTimeMillis());

		OrderToVendor orderToVendor = new OrderToVendor();
		Statement statement = new Statement();
		List<OrderToVendorProduct> productList = inteProduction.getOrderToVendorProduct();

		Map<String, Object> map = new HashMap<String, Object>();

		//statementNo 가져와야함.
		statement.setStatementCategoryCodeNo("02");
		statement.setTradeDate(date);
		//거래처명
		statement.setTradeTargetName("양주점");
		statement.setStatementDetail("발주");
		statement.setTradeAmount(inteProduction.getTotalAmount());
		statement.setAccountNo("1002384718373");


		orderToVendor.setTotalAmount(inteProduction.getTotalAmount());
		orderToVendor.setOrderToVenStatusCodeNo("01");

		map.put("statement", statement);
		map.put("orderToVendor", orderToVendor);
		map.put("productList", productList);

		System.out.println("map.toString :: " + map.toString());

		productionManagementServicerudwn.addOrderToVendor(map);

	}

}
