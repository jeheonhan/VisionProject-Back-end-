package com.vision.erp.service.productionmanagement.rudwn.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;

@Service("productionManagementServiceImplrudwn")
public class ProductionManagementServiceImplrudwn implements ProductionManagementServicerudwn {

	@Autowired
	@Qualifier("productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productionDAO;


	@Autowired
	@Qualifier("accountingDAOImpl")
	private AccountingDAO accountingDAO;



	@Override
	public void addProduct(Product product) throws Exception {
		productionDAO.addProduct(product);

	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productionDAO.updateProduct(product);

	}

	@Override
	public void updateUsageStatus(Product product) throws Exception {
		productionDAO.updateUsageStatus(product);

	}

	@Override
	public List<Product> selectProductList() throws Exception {

		return productionDAO.selectProductList();
	}

	@Override//만드는거아님
	public Product selectDetailProduct(String productNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override///////////////////////////////////////////////////집가서하자 맨붕이다
	public String addOrderToVendor(Map<String, Object> map) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy/MM/dd");
		String date = format.format (System.currentTimeMillis());

		//전표등록
		Statement statement = new Statement();
		//
		OrderToVendorProduct orderToVendorProduct = new OrderToVendorProduct();
		//거래처에대한 물품명찾기위해서
		Product product = new Product();
		
		
		String productNo = (String) map.get("productNo");
		OrderToVendor orderToVendor = (OrderToVendor) map.get("orderToVendor");
		

		//전표테이블에 거래처명을 등록하기 위해 물품의 이름을 가져옴.
		product = productionDAO.selectDetailProduct(productNo);

		//고정
		statement.setStatementCategoryCodeNo("02");
		//변경
		statement.setTradeDate(date);
		//변경
		statement.setTradeTargetName(product.getProductName());
		//고정
		statement.setStatementDetail("발주");
		//변경
		statement.setTradeAmount(orderToVendor.getTotalAmount());
		//고정
		statement.setAccountNo("1002384718373");


		accountingDAO.insertStatement(statement);

		System.out.println(statement.getStatementNo()); //여기까지 전표에 등록함

		String statementNo = statement.getStatementNo();

		orderToVendor.setStatementNo(statementNo);
		orderToVendor.setOrderToVenStatusCodeNo("01");

		productionDAO.addOrderToVendor(orderToVendor);

		String orderToVendorNo =  orderToVendor.getOrderToVendorNo();//여기까지 발주에 등록함
		
		//for(int i = 0; i <= List) {
		
		//}//발주물품등록 어떻게하냐  ㅜㅜ
		
		orderToVendorProduct.setOrderToVendorNo(orderToVendorNo);
	
		return productionDAO.addOrderToVendor(orderToVendor);
	}
	
	
	
	@Override
	public List<OrderToVendor> selectOrderToVendorList() throws Exception {
		// TODO Auto-generated method stub
		return productionDAO.selectOrderToVendorList();
	}

	@Override
	public void modifyOrderToVenCode(OrderToVendor orderToVendor) throws Exception {
		// TODO Auto-generated method stub
		productionDAO.modifyOrderToVenCode(orderToVendor);

	}
////////////////////////////////////////////////연관
	//발주서물품 상태변경(입고대기/입고완료)
	@Override
	public void modifyOrderToVenItemCode(OrderToVendorProduct orderToVendorProduct) throws Exception {
		// TODO Auto-generated method stub
		productionDAO.modifyOrderToVenItemCode(orderToVendorProduct);

	}

	@Override
	public void updateProductCount(Product product) throws Exception {
		// TODO Auto-generated method stub
		productionDAO.updateProduct(product);

	}

	@Override
	public void addorderToVendorProduct(OrderToVendorProduct orderToVendorProduct) throws Exception {
		// TODO Auto-generated method stub
		productionDAO.addorderToVendorProduct(orderToVendorProduct);
	}
////////////////////////////////////////////////
	
	
	@Override
	public List<OrderToVendorProduct> orderToVendorDetailList(OrderToVendorProduct orderToVendorProduct)
			throws Exception {
		// TODO Auto-generated method stub
		return productionDAO.orderToVendorDetailList(orderToVendorProduct);
	}



}
