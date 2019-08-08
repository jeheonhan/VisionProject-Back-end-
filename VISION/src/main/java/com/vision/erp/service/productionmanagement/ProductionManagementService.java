package com.vision.erp.service.productionmanagement;

import java.util.List;
import java.util.Map;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;

public interface ProductionManagementService {
	
	//[지점] 주문서등록
	public void addOrderFromBranch(OrderFromBranch orderFromBranch) throws Exception;
	
	//[본사, 지점]주문서상태변경(주문취소요청, 주문취소확정, 주문완료), 주문취소확정시 주문물품상태 출하취소로 변경
	public void modifyOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception;
	
	//[본사, 지점]주문서리스트 가져오기(주문물품 채워서) 지점번호 searchKeyword에 채우기
	public List<OrderFromBranch> getOrderFromBranchList(Search search) throws Exception;
	
	//[본사, 지점]주문서 상세보기
	public OrderFromBranch getOrderFromBranchDetail(Search search) throws Exception;
	
	//[본사] 주문물품 상태변경(출하완료, 출하대기, 출하취소)
	public void modifyOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//물품등록
	public void addProduct(Product product)throws Exception;

	//물품수정
	public void modifyProduct(Product product)throws Exception;

	//물품상태변경 (사용/사용x)
	public void modifyUsageStatus(Product product)throws Exception;

	//물품목록보여줌
	public List<Product> getProductList() throws Exception;

	//물품상세보기
	public Product getDetailProduct(String productNo) throws Exception;

	//발주목록
	public List<OrderToVendor> getOrderToVendorList() throws Exception;

	//발주상태변경(발주대기/발주완료/발주진행/발주취소)
	public void modifyOrderToVenCode(Map<String, Object> map) throws Exception;

	//발주서물품 상태변경(입고대기/입고완료)
	public void modifyOrderToVenItemCode(Map<String, Object> map) throws Exception;
	

	//발주서물품(상세보기)
	/**
	 * 발주서물품상세보기임
	 * @param orderToVendorProduct 거래처물품을 보여주기위한 
	 * @return	리스트	
	 * @throws Exception 예외
	 */
	public List<OrderToVendorProduct> getOrderToVendorDetailList(OrderToVendorProduct orderToVendorProduct) throws Exception;

	//발주요청 
	public String addOrderToVendor(Map<String, Object> map) throws Exception;
	
}
