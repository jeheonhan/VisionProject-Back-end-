package com.vision.erp.service.productionmanagement.rudwn.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.vision.erp.service.domain.InteProduction;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;


@Repository("productionManagementDAOImplrudwn")
public class ProductionManagementDAOImplrudwn implements ProductionManagementDAOrudwn {

	@Autowired
	@Qualifier("sqlSession")
	private SqlSession sqlSession;

	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.insertProduct", product);

	}

	@Override
	public Product selectDetailProduct(String productNo) throws Exception {

		return sqlSession.selectOne("ProductMapper.selectDetailProduct", productNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);

	}

	@Override
	public void updateUsageStatus(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateUsageStatus", product);

	}

	@Override
	public List<Product> selectProductList() throws Exception {

		return sqlSession.selectList("ProductMapper.selectproductList");
	}

	@Override
	public List<OrderToVendor> selectOrderToVendorList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("OrderToVendorMapper.selectOrderToVendorList");
	}

	@Override
	public void modifyOrderToVenCode(OrderToVendor orderToVendor) throws Exception {

		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode",orderToVendor);

	}
	
	@Override
	public void modifyOrderToVenCode1(OrderToVendor orderToVendor) throws Exception {

		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode1",orderToVendor);

	}
	
	@Override
	public void modifyOrderToVenCode2(OrderToVendor orderToVendor) throws Exception {
		System.out.println("ORDER_TO_VENDOR_NO//ORDER_TO_VEN_STATUS_CODE_NO");
		System.out.println("orderToVendor :: " + orderToVendor);
		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode2",orderToVendor);

	}
	
	

	@Override
	public void modifyOrderToVenItemCode(OrderToVendorProduct orderToVendorProduct) throws Exception {
	System.out.println("상태코드변하기위한 orderTOVendItemProduct");
	System.out.println("orderToVendorProduct :: " + orderToVendorProduct);
		sqlSession.update("OrderToVendorProductMapper.modifyOrderToVenItemCode",orderToVendorProduct);

	}
	
	

	@Override
	public void updateProductCount(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProductCount", product);

	}

	@Override
	public List<OrderToVendorProduct> orderToVendorDetailList(OrderToVendorProduct orderToVendorProduct) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("OrderToVendorProductMapper.orderToVendorDetailList",orderToVendorProduct);
	}

	//발주요청
	@Override
	public String addOrderToVendor(OrderToVendor orderToVendor) throws Exception {
		
		
		sqlSession.insert("OrderToVendorMapper.insertOrderToVendor", orderToVendor);

		return orderToVendor.getStatementNo();
	}

	//발주물품등록
	@Override
	public void addorderToVendorProduct(OrderToVendorProduct orderToVendorProduct) throws Exception {
		System.out.println("DAO :: "+orderToVendorProduct);
		// TODO Auto-generated method stub
		sqlSession.insert("OrderToVendorProductMapper.insertOrderToVenItem",orderToVendorProduct);
	}


}
