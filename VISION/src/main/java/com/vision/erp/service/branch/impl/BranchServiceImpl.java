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
		
		String branchNo = null;
		String salesDate = null;
		
		branchNo = salesProductList.get(0).getBranchNo();
		salesDate = salesProductList.get(0).getSalesDate();
		
		if(branchDAO.selectDailySalesDetail(branchNo, salesDate).isEmpty() ) {			
			branchDAO.insertDailySales(salesProductList);
			return salesProductList = branchDAO.selectDailySalesDetail(salesProductList.get(0).getBranchNo(), salesProductList.get(0).getSalesDate());
		}else {
			return null;
		}
		
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
	public List<SalesProduct> modifySalesProduct(List<SalesProduct> salesProductList) throws Exception {
		
		String branchNo = salesProductList.get(0).getBranchNo();
		String salesDate = salesProductList.get(0).getSalesDate();
		
		if(!branchDAO.selectDailySalesDetail(branchNo, salesDate).isEmpty() ) {			
			branchDAO.updateSalesProduct(salesProductList);
			salesProductList = branchDAO.selectDailySalesDetail(branchNo, salesDate);
			return salesProductList;
			
		}else {
			return null;
		}
	}

	@Override
	public List<SalesMenu> getSalesMenuList() throws Exception {
		return branchDAO.selectSalesMenuList();
	}

	@Override
	public boolean checkDuplicateSalesDate(BranchDailySales branchDailySales) throws Exception {

		List<BranchDailySales> list = branchDAO.selectDuplicateSalesDateByBranch(branchDailySales);
		
		if(list.size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	
	
	
}
