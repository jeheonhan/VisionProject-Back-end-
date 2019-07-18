package com.vision.erp.service.branch.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.service.branch.BranchDAO;
import com.vision.erp.service.branch.BranchService;
import com.vision.erp.service.domain.BranchDailySales;
import com.vision.erp.service.domain.SalesMenu;
import com.vision.erp.service.domain.SalesProduct;

@Service("branchServiceImpl")
public class BranchServiceImpl implements BranchService{
	
	@Resource(name = "branchDAOImpl")
	private BranchDAO branchDAO;

	@Override
	public List<SalesProduct> addDailySales(List<SalesProduct> salesProductList) throws Exception {		
		
		branchDAO.insertDailySales(salesProductList);
		
		salesProductList = branchDAO.selectDailySalesDetail(salesProductList.get(1).getBranchNo(), salesProductList.get(1).getSalesDate());
		
		return salesProductList;
		
	}

	@Override
	public List<SalesProduct> getBranchDailySalesDetail(String branchNo, String salesDate) throws Exception {
		
		return branchDAO.selectDailySalesDetail(branchNo, salesDate);
	}

	@Override
	public List<BranchDailySales> getBranchDailySalesList(String branchNo) throws Exception {
		return branchDAO.selectDailySalesList(branchNo);
	}

	@Override
	public void modifySalesProduct(List<SalesProduct> salesProductList) throws Exception {
		branchDAO.updateSalesProduct(salesProductList);
	}

	@Override
	public List<SalesMenu> getSalesMenuList() throws Exception {
		return branchDAO.selectSalesMenuList();
	}

	
	
	
}
