package com.vision.erp.web.businesssupport;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.Search;
import com.vision.erp.service.businesssupport.BusinessSupportService;
import com.vision.erp.service.domain.Branch;

@RestController
public class BusinessSupportController {
	
	@Resource(name = "businessSupportServiceImpl")
	private BusinessSupportService businessSupportService;
	
	@RequestMapping(value = "/bs/addBranch", method = RequestMethod.POST)
	public Branch addBranch(@RequestBody Branch branch) throws Exception {
		
		return businessSupportService.addBranch(branch);
	}
	
	@RequestMapping(value = "/bs/getBranch/{branchNo}", method = RequestMethod.GET)
	public Branch getBranch(@PathVariable String branchNo) throws Exception {
		
		return businessSupportService.getBranchDetail(branchNo);
	}
	
	@RequestMapping(value = "/bs/getBranchList", method = RequestMethod.POST)
	public List<Branch> getBranchList(@RequestBody Search search) throws Exception {
		
		return businessSupportService.getBranchList(search);
	}
	
	@RequestMapping(value = "/bs/modifyBranch", method = RequestMethod.POST)
	public Branch modifyBranch(@RequestBody Branch branch) throws Exception {
		
		businessSupportService.modifyBranch(branch);
		
		return businessSupportService.getBranchDetail(branch.getBranchNo());
	}
	
	@RequestMapping(value = "/bs/convertBranchUsageStatus", method = RequestMethod.POST)
	public void convertBranchUsageStatus(@RequestBody Branch branch) throws Exception {
		
		businessSupportService.convertBranchUsageStatus(branch);
		
	}

}
