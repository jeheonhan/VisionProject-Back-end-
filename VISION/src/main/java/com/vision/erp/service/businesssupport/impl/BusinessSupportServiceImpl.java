package com.vision.erp.service.businesssupport.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.businesssupport.BusinessSupportDAO;
import com.vision.erp.service.businesssupport.BusinessSupportService;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.User;
import com.vision.erp.service.user.UserDAO;

@Service("businessSupportServiceImpl")
public class BusinessSupportServiceImpl implements BusinessSupportService {
	
	@Resource(name = "businessSupportDAOImpl")
	private BusinessSupportDAO businessSupportDAO;
	
	@Resource(name = "accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	@Resource(name = "userDAOImpl")
	private UserDAO userDAO;

	@Override
	public Branch addBranch(Branch branch) throws Exception {
		String branchNo = businessSupportDAO.insertBranch(branch);
		branch = businessSupportDAO.selectBranchDetail(branchNo);
		
		Statement statement = new Statement();
		statement.setTradeTargetName(branch.getBranchName());
		statement.setStatementCategoryCodeNo("01");
		statement.setStatementDetail(branch.getBranchName()+" °¡¸Íºñ");
		statement.setTradeDate(branch.getBranchRegDate());
		statement.setTradeAmount("200000");
		statement.setAccountNo("12393829384910");
		accountingDAO.insertStatement(statement);
		
		User user = new User();
		user.setUserId("U"+branch.getBranchNo());
		user.setPassword("0000");
		user.setBranchNo(branchNo);
		user.setMemberCodeNo("02");
		user.setMemberUsageStatusCodeNo("01");
		
		userDAO.addUser(user);
		
		return branch;		
		
	}

	@Override
	public Branch getBranchDetail(String branchNo) throws Exception {
		
		Branch branch = new Branch();
		branch = businessSupportDAO.selectBranchDetail(branchNo);
		return branch;
		
	}

	@Override
	public Map<String, Object> getBranchList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Branch> branchList = businessSupportDAO.selectBranchList(search);
		map.put("branchList", branchList);
		
		int totalCount = businessSupportDAO.selectTotalCount(search);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public void modifyBranch(Branch branch) throws Exception {
		businessSupportDAO.updateBranch(branch);		
	}

	@Override
	public void convertBranchUsageStatus(Branch branch) throws Exception {		
		
		if(branch.getBranchStatusCodeNo().equals("01")) {
			
			branch.setBranchStatusCodeNo("02");
			businessSupportDAO.updateBranchUsageStatus(branch);
			
		}else if(branch.getBranchStatusCodeNo().equals("02")) {
			
			branch.setBranchStatusCodeNo("01");
			businessSupportDAO.updateBranchUsageStatus(branch);
			
		}
		
		
	}
	
}
