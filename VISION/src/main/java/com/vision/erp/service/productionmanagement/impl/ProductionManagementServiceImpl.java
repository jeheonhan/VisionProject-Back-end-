package com.vision.erp.service.productionmanagement.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.productionmanagement.ProductionManagementDAO;
import com.vision.erp.service.productionmanagement.ProductionManagementService;

@Service("productionManagementServiceImpl")
public class ProductionManagementServiceImpl implements ProductionManagementService {

//	//field
//	@Resource(name="productionManagementDAOImplcodms")
//	private ProductionManagementDAO productionManagementDAOcodms;
//	
//	@Resource(name="productionManagementDAOImplrudwn")
//	private ProductionManagementDAOrudwn productionManagementDAOrudwn;
	
	@Resource(name = "productionManagementDAOImpl")
	private ProductionManagementDAO productionManagementDAO;
	
	@Resource(name="accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	//method
	//[지점] 주문서등록
	@Override
	public void addOrderFromBranch(OrderFromBranch orderFromBranch) throws Exception {
		
		//물건 가격 계산하고 주문서에 저장하기
		List<OrderFromBranchProduct> finalList = new ArrayList<OrderFromBranchProduct>(); 
		int orderFromBranchTotalAmount = 0;
		for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
			OrderFromBranchProduct resultOp = calculateOrderFromBranchProduct(op);
			finalList.add(resultOp);
			orderFromBranchTotalAmount += Integer.parseInt(resultOp.getOrderFromBranchProductAmount());
		}
		orderFromBranch.setOrderFromBranchProductList(finalList);
		orderFromBranch.setOrderFromBranchTotalAmount(""+orderFromBranchTotalAmount);
		
		//오늘 날짜로 주문일자 입력하기
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
		String date = format.format (System.currentTimeMillis());
		orderFromBranch.setOrderDate(date);
		
		//전표등록하고 주문서에 전표번호 저장하기
		orderFromBranch.setStatementNo(addStatement(orderFromBranch).getStatementNo());
		
		//주문서 등록
		//지점번호, 전표번호, 총가격이 있어야함
		productionManagementDAO.insertOrderFromBranch(orderFromBranch);
		
		//주문물품 등록
		//주문서번호, 물품번호, 수량, 가격, 금액이 있어야함
		for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
			productionManagementDAO.insertOrderFromBranchProduct(op.setOrderFromBranchNo(orderFromBranch.getOrderFromBranchNo()));
		}
		
	}
	
	//[본사, 지점]주문서상태변경(주문대기01, 주문완료02, 주문진행03, 취소요청04, 취소확정05)
	@Override
	public void modifyOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception {
		//주문서상태 변경하기
		productionManagementDAO.updateOrderFromBranchStatus(orderFromBranch);
		
		//취소요청시 주문물품 출하철회하기
		if(orderFromBranch.getOrderFromBranchStatusCodeNo().equals("04")) {
			for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
				//주문물품상태(출하대기01, 출하완료02, 출하철회03)
				op.setOrderFromBranchProductStatusCodeNo("03");
				productionManagementDAO.updateOrderFromBranchProductStatus(op);
			}
		}
		
		//취소확정시 전표 취소하기
		if(orderFromBranch.getOrderFromBranchStatusCodeNo().equals("05")) {
			Statement stmt = accountingDAO.selectStatementDetail(orderFromBranch.getStatementNo());
			stmt.setStatementUsageStatusCodeNo("02");
			accountingDAO.updateStatementUsageStatus(stmt);
		}
	}

	//[본사, 지점]주문서리스트 가져오기(주문물품 채워서) 지점번호 searchKeyword에 채우기
	@Override
	public List<OrderFromBranch> getOrderFromBranchList(Search search) throws Exception {
		return productionManagementDAO.selectOrderFromBranchList(search);
	}

	//[본사, 지점]주문서 상세보기 주문서번호 searchKeyword에 채우기
	@Override
	public OrderFromBranch getOrderFromBranchDetail(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productionManagementDAO.selectOrderFromBranchDetail(search);
	}

	//[본사] 주문물품 상태변경(출하대기01, 출하완료02, 출하철회03)
	//주문서번호, 물품번호 있어야함
	@Override
	public void modifyOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception {
		//물품상태변경하기
		productionManagementDAO.updateOrderFromBranchProductStatus(orderFromBranchProduct);
		
		if(orderFromBranchProduct.getOrderFromBranchProductStatusCodeNo().equals("02")) {
			//주문서의 물품이 첫 출하라면 주문서 상태를 주문진행으로 변경
			changeOrderFromBranchStatus(orderFromBranchProduct, "03");
			//모든 물품 출하완료시 주문서상태 주문완료로 변경
			changeOrderFromBranchStatus(orderFromBranchProduct, "02");
		}
		
	}
	
	//출하시 주문서상태 변경
	private void changeOrderFromBranchStatus(OrderFromBranchProduct orderFromBranchProduct, String orderFromBranchStatusCodeNo) throws Exception{
		//주문물품에 해당되는 주문서 가져오기
		Search search = new Search();
		search.setSearchKeyword(orderFromBranchProduct.getOrderFromBranchNo());
		OrderFromBranch ob = productionManagementDAO.selectOrderFromBranchDetail(search);
		
		//주문서에 해당되는 미배송물품 개수 확인하기
		int notDeliveredYet = productionManagementDAO.selectOrderFromBranchProduct(ob.getOrderFromBranchNo());
		
		//if(모든 물품 출하완료시 주문서상태 주문완료로 변경 || 첫 물품 출하시 주문서 상태 주문진행으로 변경)
		if(notDeliveredYet==0 || (ob.getOrderFromBranchStatusCodeNo().equals("01")&&orderFromBranchProduct.getOrderFromBranchProductStatusCodeNo().equals("02"))) {
			ob.setOrderFromBranchStatusCodeNo(orderFromBranchStatusCodeNo);
			productionManagementDAO.updateOrderFromBranchStatus(ob);
		}
	}

	//주문물품 금액 계산하기
	private OrderFromBranchProduct calculateOrderFromBranchProduct(OrderFromBranchProduct op) throws Exception{
		Product product = productionManagementDAO.selectDetailProduct(op.getProductNo());
		op.setPrice(product.getSalesPrice());
		op.setOrderFromBranchProductAmount(""+(Integer.parseInt(product.getSalesPrice())*Integer.parseInt(op.getOrderFromBranchProductQuantity())));
		System.out.println("CalculateOrderFromBranchProduct() : "+op);
		return op;
	}
	
	//전표 등록하기
	private Statement addStatement(OrderFromBranch orderFromBranch) throws Exception {
		Statement statement = new Statement();
		statement.setTradeDate(orderFromBranch.getOrderDate());
		statement.setTradeTargetName(orderFromBranch.getBranchName());
		statement.setStatementCategoryCodeNo("01");
		statement.setStatementDetail("주문");
		statement.setAccountNo(orderFromBranch.getAccountNo());
		statement.setTradeAmount(orderFromBranch.getOrderFromBranchTotalAmount());
		accountingDAO.insertStatement(statement);
		
		return statement;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	@Override
	public void addProduct(Product product) throws Exception {
		
		String replacePurchasePrice = product.getPurchasePrice();
		String resultPurchasePrice = replacePurchasePrice.replaceAll("," , "");
		String replaceSalesPrice = product.getSalesPrice();
		String resultSalesPrice = replaceSalesPrice.replaceAll("," , "");
		String replaceQuantity = product.getQuantity();
		String resultQuantity = replaceQuantity.replaceAll("," , "");
		
		product.setProductUsageStatusCodeNo("01");
		product.setPurchasePrice(resultPurchasePrice);
		product.setSalesPrice(resultSalesPrice);
		product.setQuantity(resultQuantity);

		productionManagementDAO.insertProduct(product);

	}

	@Override
	public void modifyProduct(Product product) throws Exception {
		productionManagementDAO.updateProduct(product);

	}

	@Override
	public void modifyUsageStatus(Product product) throws Exception {
		productionManagementDAO.updateUsageStatus(product);

	}

	@Override
	public List<Product> getProductList() throws Exception {

		return productionManagementDAO.selectProductList();
	}

	@Override
	public Product getDetailProduct(String productNo) throws Exception {
		
		return productionManagementDAO.selectDetailProduct(productNo);
	}

	@Override
	public List<OrderToVendor> getOrderToVendorList() throws Exception {
		// TODO Auto-generated method stub
		return productionManagementDAO.selectOrderToVendorList();
	}

	//발주대기에서 발주취소하는 메소드+전표상태코드변경
	@Override
	public void modifyOrderToVenCode(Map<String, Object> map) throws Exception {

		OrderToVendor orderToVendor = (OrderToVendor) map.get("orderToVendor");
		Statement statement = (Statement) map.get("statement");
		OrderToVendorProduct orderToVendorProduct = (OrderToVendorProduct) map.get("orderToVendorProduct");

		orderToVendor.setOrderToVenStatusCodeNo("01");
		statement.setStatementUsageStatusCodeNo("02");

		productionManagementDAO.updateOrderToVenCode(orderToVendor);
		productionManagementDAO.updateOrderToVenItemCode2(orderToVendorProduct);
		accountingDAO.updateStatementUsageStatus(statement);

	}

	//발주서물품 상태변경(입고대기/입고완료)
	@Override
	public void modifyOrderToVenItemCode(Map<String, Object> map) throws Exception {

		OrderToVendorProduct orderToVendorProduct = (OrderToVendorProduct) map.get("orderToVendorProduct");
		Product product = (Product) map.get("product");

		OrderToVendor orderToVendor =new OrderToVendor();
		orderToVendor.setOrderToVendorNo(orderToVendorProduct.getOrderToVendorNo());
		orderToVendor.setOrderToVenStatusCodeNo("01");


		//해당상태코드를 바꾸기위한거
		System.out.println("orderToVendorProduct :: " + orderToVendorProduct);
		//해당물품재고를 올리기 위해서
		System.out.println("product :: " + product);
		//발주물품이 모두 입고가 되었는지 확인하기위한 변수
		//System.out.println("sizeCount :: " + sizeCount);

		//입고대기를 입고완료로
		productionManagementDAO.updateOrderToVenItemCode(orderToVendorProduct);
		//물품재고올리는
		productionManagementDAO.updateProductCount(product);
		//발주대기를 발주진행으로
		productionManagementDAO.updateOrderToVenCode2(orderToVendor);


		int count = 0;
		//입고완료가 다 되었을때 발주진행을 발주완료로 바꾸기 위해서 값가져오는거
		List<OrderToVendorProduct> listOrderSize = productionManagementDAO.selectOrderToVendorDetailList(orderToVendorProduct);
		int sizeCount = listOrderSize.size();

		for(int i=0; i<listOrderSize.size(); i++) {

			if(listOrderSize.get(i).getOrderToVendorProductStatusCodeNo().equals("02")) {
				count += 1;				
				if(count == sizeCount) {
					//발주진행을 발주완료로
					productionManagementDAO.updateOrderToVenCode1(orderToVendor);
				} 
			}
		}
	}

	@Override
	public List<OrderToVendorProduct> getOrderToVendorDetailList(OrderToVendorProduct orderToVendorProduct)
			throws Exception {
		// TODO Auto-generated method stub
		return productionManagementDAO.selectOrderToVendorDetailList(orderToVendorProduct);
	}

	@Override
	public String addOrderToVendor(Map<String, Object> map) throws Exception {

		Statement statement = (Statement) map.get("statement");
		OrderToVendor orderToVendor = (OrderToVendor) map.get("orderToVendor");
		List<OrderToVendorProduct> orderToVendorProducts = (List<OrderToVendorProduct>) map.get("productList");

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");

		//거래처명중복안되게
		String addVendorName = "";
		for(int i = 0; i < orderToVendorProducts.size(); i++) {
			String vendorName = orderToVendorProducts.get(i).getVendorName();
			if(-1 == addVendorName.indexOf(vendorName)) {
				addVendorName += vendorName + ", ";
			}
		}	
		addVendorName=addVendorName.substring(0, addVendorName.length()-2);
		
		System.out.println("addVendorName :: " + addVendorName);
		
		String date = format.format (System.currentTimeMillis());
		statement.setTradeDate(date);
		statement.setTradeTargetName(addVendorName);
		statement.setStatementCategoryCodeNo("02");
		statement.setStatementDetail("발주");
		statement.setAccountNo("1002384718373");
		System.out.println("statement :: " + statement);
		//전표에 등록
		accountingDAO.insertStatement(statement);

		String statementNo = statement.getStatementNo();

		orderToVendor.setStatementNo(statementNo);

		//발주서 등록
		orderToVendor.setOrderToVenStatusCodeNo("01");
		productionManagementDAO.insertOrderToVendor(orderToVendor);

		String orderToVendorNo =  orderToVendor.getOrderToVendorNo();

		for(int i = 0; i < orderToVendorProducts.size();i++) {
			OrderToVendorProduct orderToVendorProduct = orderToVendorProducts.get(i);
			orderToVendorProduct.setOrderToVendorNo(orderToVendorNo);
			orderToVendorProduct.setOrderToVendorProductStatusCodeNo("01");
			productionManagementDAO.insertOrderToVendorProduct(orderToVendorProduct);
		}

		return null;
	}

}
