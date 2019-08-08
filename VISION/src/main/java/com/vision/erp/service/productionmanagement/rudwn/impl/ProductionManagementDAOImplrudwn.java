//package com.vision.erp.service.productionmanagement.rudwn.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import com.vision.erp.service.domain.InteProduction;
//import com.vision.erp.service.domain.OrderToVendor;
//import com.vision.erp.service.domain.OrderToVendorProduct;
//import com.vision.erp.service.domain.Product;
//import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;
//
//
//@Repository("productionManagementDAOImplrudwn")
//public class ProductionManagementDAOImplrudwn implements ProductionManagementDAOrudwn {
//
//	@Autowired
//	@Qualifier("sqlSession")
//	private SqlSession sqlSession;
//
//	@Override
//	public void insertProduct(Product product) throws Exception {
//		sqlSession.insert("ProductMapper.insertProduct", product);
//
//	}
//
//	@Override
//	public Product selectDetailProduct(String productNo) throws Exception {
//
//		return sqlSession.selectOne("ProductMapper.selectDetailProduct", productNo);
//	}
//
//	@Override
//	public void updateProduct(Product product) throws Exception {
//		sqlSession.update("ProductMapper.updateProduct", product);
//
//	}
//
//	@Override
//	public void updateUsageStatus(Product product) throws Exception {
//		sqlSession.update("ProductMapper.updateUsageStatus", product);
//
//	}
//
//	@Override
//	public List<Product> selectProductList() throws Exception {
//
//		return sqlSession.selectList("ProductMapper.selectproductList");
//	}
//
//	@Override
//	public List<OrderToVendor> selectOrderToVendorList() throws Exception {
//		// TODO Auto-generated method stub
//		return sqlSession.selectList("OrderToVendorMapper.selectOrderToVendorList");
//	}
//
//	@Override
//	public void updateOrderToVenCode(OrderToVendor orderToVendor) throws Exception {
//
//		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode",orderToVendor);
//
//	}
//	
//	@Override
//	public void updateOrderToVenCode1(OrderToVendor orderToVendor) throws Exception {
//
//		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode1",orderToVendor);
//
//	}
//	
//	@Override
//	public void updateOrderToVenCode2(OrderToVendor orderToVendor) throws Exception {
//		sqlSession.update("OrderToVendorMapper.modifyOrderToVenCode2",orderToVendor);
//
//	}
//	
//	
//
//	@Override
//	public void updateOrderToVenItemCode(OrderToVendorProduct orderToVendorProduct) throws Exception {
//		sqlSession.update("OrderToVendorProductMapper.modifyOrderToVenItemCode",orderToVendorProduct);
//
//	}
//	
//	@Override
//	public void updateOrderToVenItemCode2(OrderToVendorProduct orderToVendorProduct) throws Exception {
//		System.out.println("여기로 들어왓냐 @@@@@@@@@@@");
//		System.out.println("orderToVendorProduct 값 :::::::; " + orderToVendorProduct);
//		sqlSession.update("OrderToVendorProductMapper.modifyOrderToVenItemCode2",orderToVendorProduct);
//
//	}
//	
//	
//
//	@Override
//	public void updateProductCount(Product product) throws Exception {
//		sqlSession.update("ProductMapper.updateProductCount", product);
//
//	}
//
//	@Override
//	public List<OrderToVendorProduct> selectOrderToVendorDetailList(OrderToVendorProduct orderToVendorProduct) throws Exception {
//		// TODO Auto-generated method stub
//		return sqlSession.selectList("OrderToVendorProductMapper.orderToVendorDetailList",orderToVendorProduct);
//	}
//
//	//발주요청
//	@Override
//	public String insertOrderToVendor(OrderToVendor orderToVendor) throws Exception {
//		
//		
//		sqlSession.insert("OrderToVendorMapper.insertOrderToVendor", orderToVendor);
//
//		return orderToVendor.getStatementNo();
//	}
//
//	//발주물품등록
//	@Override
//	public void insertOrderToVendorProduct(OrderToVendorProduct orderToVendorProduct) throws Exception {
//		System.out.println("DAO :: "+orderToVendorProduct);
//		// TODO Auto-generated method stub
//		sqlSession.insert("OrderToVendorProductMapper.insertOrderToVenItem",orderToVendorProduct);
//	}
//
//
//}
