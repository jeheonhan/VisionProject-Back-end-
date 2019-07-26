package com.vision.erp.service.businesssupport;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.Local;

public interface BusinessSupportDAO {
	
	public String insertBranch(Branch branch) throws Exception;
	
	public Branch selectBranchDetail(String branchNo) throws Exception;
	
	public void updateBranch(Branch branch) throws Exception;
	
	public void updateBranchUsageStatus(Branch branch) throws Exception;
	
	public List<Branch> selectBranchList(Search saerch) throws Exception;
	
	public List<Local> selectLocalList() throws Exception;
	
	public int selectTotalCount(Search search) throws Exception;

}
