package com.vision.erp.service.productionmanagement.rudwn.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

		product.setProductUsageStatusCodeNo("01");

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

	@Override
	public Product selectDetailProduct(String productNo) throws Exception {
		
		return productionDAO.selectDetailProduct(productNo);
	}

	@Override
	public List<OrderToVendor> selectOrderToVendorList() throws Exception {
		// TODO Auto-generated method stub
		return productionDAO.selectOrderToVendorList();
	}

	//발주대기에서 발주취소하는 메소드+전표상태코드변경
	@Override
	public void modifyOrderToVenCode(Map<String, Object> map) throws Exception {

		OrderToVendor orderToVendor = (OrderToVendor) map.get("orderToVendor");
		Statement statement = (Statement) map.get("statement");
		OrderToVendorProduct orderToVendorProduct = (OrderToVendorProduct) map.get("orderToVendorProduct");

		orderToVendor.setOrderToVenStatusCodeNo("01");
		statement.setStatementUsageStatusCodeNo("02");

		productionDAO.modifyOrderToVenCode(orderToVendor);
		productionDAO.modifyOrderToVenItemCode2(orderToVendorProduct);
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
		productionDAO.modifyOrderToVenItemCode(orderToVendorProduct);
		//물품재고올리는
		productionDAO.updateProductCount(product);
		//발주대기를 발주진행으로
		productionDAO.modifyOrderToVenCode2(orderToVendor);


		int count = 0;
		//입고완료가 다 되었을때 발주진행을 발주완료로 바꾸기 위해서 값가져오는거
		List<OrderToVendorProduct> listOrderSize = productionDAO.orderToVendorDetailList(orderToVendorProduct);
		int sizeCount = listOrderSize.size();

		for(int i=0; i<listOrderSize.size(); i++) {

			if(listOrderSize.get(i).getOrderToVendorProductStatusCodeNo().equals("02")) {
				count += 1;				
				if(count == sizeCount) {
					//발주진행을 발주완료로
					productionDAO.modifyOrderToVenCode1(orderToVendor);
				} 
			}
		}
	}

	@Override
	public List<OrderToVendorProduct> orderToVendorDetailList(OrderToVendorProduct orderToVendorProduct)
			throws Exception {
		// TODO Auto-generated method stub
		return productionDAO.orderToVendorDetailList(orderToVendorProduct);
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
		productionDAO.addOrderToVendor(orderToVendor);

		String orderToVendorNo =  orderToVendor.getOrderToVendorNo();

		for(int i = 0; i < orderToVendorProducts.size();i++) {
			OrderToVendorProduct orderToVendorProduct = orderToVendorProducts.get(i);
			orderToVendorProduct.setOrderToVendorNo(orderToVendorNo);
			orderToVendorProduct.setOrderToVendorProductStatusCodeNo("01");
			productionDAO.addorderToVendorProduct(orderToVendorProduct);
		}

		return null;
	}

}
