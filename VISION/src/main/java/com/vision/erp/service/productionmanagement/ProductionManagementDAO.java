package com.vision.erp.service.productionmanagement;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;

public interface ProductionManagementDAO {
	//[지점]주문요청-주문서등록
	public int insertOrderFromBranch(OrderFromBranch orderFromBranch) throws Exception;
	
	//[지점]주문요청-주문물품등록
	public int insertOrderFromBranchProduct(OrderFromBranchProduct orderFromBranchProduct) throws Exception;
		
	//[본사, 지점]주문서상태변경(주문취소요청, 주문취소확정, 주문완료)
	public int updateOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception;
	
	//[본사, 지점]주문서리스트 가져오기(주문물품 채워서) 지점번호 searchKeyword에 채우기
	public List<OrderFromBranch> selectOrderFromBranchList(Search search) throws Exception;
	
	//[본사, 지점]주문서 상세보기
	public OrderFromBranch selectOrderFromBranchDetail(Search search) throws Exception;
	
	//[본사]주문물품 상태변경(출하완료, 출하대기, 출하취소)
	public int updateOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception;
	
	//[본사]주문물품 모두 출하완료인지 확인, 출하대기인 상품개수 return
	public int selectOrderFromBranchProduct(String orderFromBranchNo) throws Exception;
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//물품등록
	public void insertProduct(Product product)throws Exception;

	//물품수정
	public void updateProduct(Product product)throws Exception;

	//물품상태변경 (사용/사용x)
	public void updateUsageStatus(Product product)throws Exception;

	//물품목록보여줌
	public List<Product> selectProductList() throws Exception;

	//물품상세보기
	public Product selectDetailProduct(String productNo) throws Exception;

	//발주목록
	public List<OrderToVendor> selectOrderToVendorList() throws Exception;

	//발주상태변경(발주대기/발주완료/발주진행/발주취소)
	public void updateOrderToVenCode(OrderToVendor orderToVendor) throws Exception;
	
	public void updateOrderToVenCode1(OrderToVendor orderToVendor) throws Exception;
	
	public void updateOrderToVenCode2(OrderToVendor orderToVendor) throws Exception;

	//발주서물품 상태변경(입고대기/입고완료)
	public void updateOrderToVenItemCode(OrderToVendorProduct orderToVendorProduct) throws Exception;
	
	public void updateOrderToVenItemCode2(OrderToVendorProduct orderToVendorProduct) throws Exception;
	
	//발주서물품 상태변경시 물품수량추가됨.서비스에서 처리할거임
	public void updateProductCount(Product product) throws Exception;

	//발주서물품(상세보기)
	public List<OrderToVendorProduct> selectOrderToVendorDetailList(OrderToVendorProduct orderToVendorProduct) throws Exception;
	
	//발주요청 
	public String insertOrderToVendor(OrderToVendor orderToVendor) throws Exception;

	//발주물품등록(발주요청시 insert all통해서 등록)
	public void insertOrderToVendorProduct(OrderToVendorProduct orderToVendorProduct) throws Exception;
	
}
