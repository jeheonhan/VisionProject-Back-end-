package com.vision.erp.service.businesssupport;

import java.util.Map;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Branch;

public interface BusinessSupportService {
	
	public Branch addBranch(Branch branch) throws Exception;
	
	public Branch getBranchDetail(String branchNo) throws Exception;
	
	public Map<String, Object> getBranchList(Search search) throws Exception;
	
	public void modifyBranch(Branch branch) throws Exception;
	
	public void convertBranchUsageStatus(Branch branch) throws Exception;

}
