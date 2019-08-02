package com.vision.erp.service.businesssupport;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.Local;

public interface BusinessSupportService {
	
	public Branch addBranch(Branch branch) throws Exception;
	
	public Branch getBranchDetail(String branchNo) throws Exception;
	
	public List<Branch> getBranchList(Search search) throws Exception;
	
	public Branch modifyBranch(Branch branch) throws Exception;
	
	public void convertBranchUsageStatus(Branch branch) throws Exception;
	
	public List<Local> getLocalList() throws Exception;

}
