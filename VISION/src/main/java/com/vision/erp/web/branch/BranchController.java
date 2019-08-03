package com.vision.erp.web.branch;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.service.branch.BranchService;
import com.vision.erp.service.domain.BranchDailySales;
import com.vision.erp.service.domain.SalesMenu;
import com.vision.erp.service.domain.SalesProduct;

@RestController
public class BranchController {
	
	@Resource(name = "branchServiceImpl")
	private BranchService branchService;
	
	@RequestMapping(value = "/branch/addDailySales", method = RequestMethod.POST)
	public List<SalesProduct> addDailySales(@RequestBody List<SalesProduct> salesProductList) throws Exception {
		
		return branchService.addDailySales(salesProductList);
		
	}
	
	@RequestMapping(value = "/branch/getDailySalesDetail", method = RequestMethod.POST)
	public List<SalesProduct> getDailySalesDetail(@RequestBody BranchDailySales branchDailySales) throws Exception {
		
		List<SalesProduct> list = 
				branchService.getBranchDailySalesDetail(branchDailySales.getBranchNo(), branchDailySales.getSalesDate());		
		
		return list;		
		
	}
	
	@RequestMapping(value = "/branch/getDailySalesList/{branchNo}", method = RequestMethod.GET)
	public List<BranchDailySales> getDailySalesList(@PathVariable String branchNo) throws Exception {
		
		List<BranchDailySales> list = branchService.getBranchDailySalesList(branchNo);
		
		return list;
	}
	
	@RequestMapping(value = "/branch/getSalesMenuList", method = RequestMethod.GET)
	public List<SalesMenu> getSalesMenuList() throws Exception {
		
		List<SalesMenu> list = branchService.getSalesMenuList();
		
		return list;
	}
	
	@RequestMapping(value = "/branch/modifySalesProduct", method = RequestMethod.POST)
	public List<SalesProduct> modifySalesProduct(@RequestBody List<SalesProduct> salesProductList) throws Exception {
			
			return branchService.modifySalesProduct(salesProductList);
		
	}

}
