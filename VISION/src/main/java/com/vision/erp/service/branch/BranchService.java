package com.vision.erp.service.branch;

import java.util.List;

import com.vision.erp.service.domain.BranchDailySales;
import com.vision.erp.service.domain.SalesMenu;
import com.vision.erp.service.domain.SalesProduct;

public interface BranchService {
	
	public void addDailySales(SalesProduct salesProduct) throws Exception;
	
	public List<SalesProduct> getBranchDailySalesDetail(String branchNo, String salesDate) throws Exception;
	
	public List<BranchDailySales> getBranchDailySalesList(String branchNo) throws Exception;
	
	public void modifySalesProduct(SalesProduct salesProduct) throws Exception;
	
	public List<SalesMenu> getSalesMenuList() throws Exception;

}
