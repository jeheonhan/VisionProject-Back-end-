package com.vision.erp.service.productionmanagement.codms.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.productionmanagement.codms.ProductionManagementDAOcodms;
import com.vision.erp.service.productionmanagement.codms.ProductionManagementServicecodms;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;

@Service("productionManagementServiceImplcodms")
public class ProductionManagementServiceImplcodms implements ProductionManagementServicecodms {

	//field
	@Resource(name="productionManagementDAOImplcodms")
	private ProductionManagementDAOcodms productionManagementDAOcodms;
	
	@Resource(name="productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productionManagementDAOrudwn;
	
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
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/mm/dd");
		String date = format.format (System.currentTimeMillis());
		orderFromBranch.setOrderDate(date);
		
		//전표등록하고 주문서에 전표번호 저장하기
		orderFromBranch.setStatementNo(addStatement(orderFromBranch).getStatementNo());
		
		//주문서 등록
		//지점번호, 전표번호, 총가격이 있어야함
		productionManagementDAOcodms.insertOrderFromBranch(orderFromBranch);
		
		//주문물품 등록
		//주문서번호, 물품번호, 수량, 가격, 금액이 있어야함
		for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
			productionManagementDAOcodms.insertOrderFromBranchProduct(op.setOrderFromBranchNo(orderFromBranch.getOrderFromBranchNo()));
		}
		
	}
	
	//[본사, 지점]주문서상태변경(주문대기01, 주문완료02, 주문진행03, 취소요청04, 취소확정05)
	@Override
	public void modifyOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception {
		//주문서상태 변경하기
		productionManagementDAOcodms.updateOrderFromBranchStatus(orderFromBranch);
		
		//취소요청시 주문물품 출하철회하기
		if(orderFromBranch.getOrderFromBranchStatusCodeNo().equals("04")) {
			for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
				//주문물품상태(출하대기01, 출하완료02, 출하철회03)
				op.setOrderFromBranchProductStatusCodeNo("03");
				productionManagementDAOcodms.updateOrderFromBranchProductStatus(op);
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
		return productionManagementDAOcodms.selectOrderFromBranchList(search);
	}

	//[본사, 지점]주문서 상세보기 주문서번호 searchKeyword에 채우기
	@Override
	public OrderFromBranch getOrderFromBranchDetail(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productionManagementDAOcodms.selectOrderFromBranchDetail(search);
	}

	//[본사] 주문물품 상태변경(출하대기01, 출하완료02, 출하철회03)
	//주문서번호, 물품번호 있어야함
	@Override
	public void modifyOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception {
		//물품상태변경하기
		productionManagementDAOcodms.updateOrderFromBranchProductStatus(orderFromBranchProduct);
		
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
		OrderFromBranch ob = productionManagementDAOcodms.selectOrderFromBranchDetail(search);
		
		//주문서에 해당되는 미배송물품 개수 확인하기
		int notDeliveredYet = productionManagementDAOcodms.selectOrderFromBranchProduct(ob.getOrderFromBranchNo());
		
		//if(모든 물품 출하완료시 주문서상태 주문완료로 변경 || 첫 물품 출하시 주문서 상태 주문진행으로 변경)
		if(notDeliveredYet==0 || (ob.getOrderFromBranchStatusCodeNo().equals("01")&&orderFromBranchProduct.getOrderFromBranchProductStatusCodeNo().equals("02"))) {
			ob.setOrderFromBranchStatusCodeNo(orderFromBranchStatusCodeNo);
			productionManagementDAOcodms.updateOrderFromBranchStatus(ob);
		}
	}

	//주문물품 금액 계산하기
	private OrderFromBranchProduct calculateOrderFromBranchProduct(OrderFromBranchProduct op) throws Exception{
		Product product = productionManagementDAOrudwn.selectDetailProduct(op.getProductNo());
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
}
