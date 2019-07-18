package com.vision.erp.service.productionmanagement.rudwn;

import java.util.List;
import java.util.Map;

import com.vision.erp.service.domain.InteProduction;
import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;

public interface ProductionManagementServicerudwn {

	//물품등록
	public void addProduct(Product product)throws Exception;

	//물품수정
	public void updateProduct(Product product)throws Exception;

	//물품상태변경 (사용/사용x)
	public void updateUsageStatus(Product product)throws Exception;

	//물품목록보여줌
	public List<Product> selectProductList() throws Exception;

	//물품상세보기(내가사용하는거아님)
	public Product selectDetailProduct(String productNo) throws Exception;

	//발주목록
	public List<OrderToVendor> selectOrderToVendorList() throws Exception;

	//발주상태변경(발주대기/발주완료/발주진행/발주취소)
	public void modifyOrderToVenCode(Map<String, Object> map) throws Exception;

	//발주서물품 상태변경(입고대기/입고완료)
	public void modifyOrderToVenItemCode(Map<String, Object> map) throws Exception;

	//발주서물품(상세보기)
	public List<OrderToVendorProduct> orderToVendorDetailList(OrderToVendorProduct orderToVendorProduct) throws Exception;

	//발주요청 
	public String addOrderToVendor(Map<String, Object> map) throws Exception;

}
