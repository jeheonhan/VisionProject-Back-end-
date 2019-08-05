package com.vision.erp.service.businesssupport.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.businesssupport.BusinessSupportDAO;
import com.vision.erp.service.businesssupport.BusinessSupportService;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.Local;
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
		
		String area = branch.getAddress().substring(0,2);
		
		switch(area) {
		case "서울" :
			branch.setLocalCodeNo("01");
			break;
			
		case "경기" :
			branch.setLocalCodeNo("02");
			break;
			
		case "인천" :
			branch.setLocalCodeNo("03");
			break;
		
		case "강원" :
			branch.setLocalCodeNo("04");
			break;
		
		case "충북" :
			branch.setLocalCodeNo("05");
			break;
        
        case "충남" :
			branch.setLocalCodeNo("06");
			break;
        
        case "대전" :
			branch.setLocalCodeNo("07");
			break;
        
        case "세종" :
			branch.setLocalCodeNo("08");
			break;
        
        case "광주" :
			branch.setLocalCodeNo("09");
			break;
        
        case "전북" :
			branch.setLocalCodeNo("10");
			break;

        case "전남" :
			branch.setLocalCodeNo("11");
			break;
        
        case "경북" :
			branch.setLocalCodeNo("12");
			break;

        case "경남" :
			branch.setLocalCodeNo("13");
			break;
        
        case "대구" :
			branch.setLocalCodeNo("14");
			break;

        case "부산" :
			branch.setLocalCodeNo("15");
			break;

        case "울산" :
			branch.setLocalCodeNo("16");
			break;

        case "제주" :
			branch.setLocalCodeNo("17");
			break;

        default : 
            System.out.println("businessSupport switch-case문");
        
	}
		String branchTel = branch.getLocalPhoneCode()+'-'+branch.getBranchTel();
		branch.setBranchTel(branchTel);
		String branchNo = businessSupportDAO.insertBranch(branch);
		branch = businessSupportDAO.selectBranchDetail(branchNo);
		
		Statement statement = new Statement();
		statement.setTradeTargetName(branch.getBranchName());
		statement.setStatementCategoryCodeNo("01");
		statement.setStatementDetail(branch.getBranchName()+" 가맹");
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
		
		if(branch.getBranchStatusCodeNo().equals("01")) {
			branch.setBranchStatus("영업중");
		}else if(branch.getBranchStatusCodeNo().equals("02")) {
			branch.setBranchStatus("폐업");
		}
		
		String localPhoneCode = branch.getBranchTel().split("-")[0];
		branch.setLocalPhoneCode(localPhoneCode);
		String updateBranchTel = branch.getBranchTel().split("-")[1]+"-"+branch.getBranchTel().split("-")[2];
		branch.setUpdateBranchTel(updateBranchTel);
		return branch;
		
	}

	@Override
	public List<Branch> getBranchList(Search search) throws Exception {
		
		List<Branch> branchList = businessSupportDAO.selectBranchList(search);
		
		for(int i=0; i<branchList.size(); i++) {
			if(branchList.get(i).getBranchStatusCodeNo().equals("01")) {
				branchList.get(i).setBranchStatus("영업중");
			}else if(branchList.get(i).getBranchStatusCodeNo().equals("02")) {
				branchList.get(i).setBranchStatus("폐업");
			}			
		}
		
		return branchList;
	}

	@Override
	public Branch modifyBranch(Branch branch) throws Exception {
		
		String area = branch.getAddress().substring(0,2);
		
		switch(area) {
		case "서울" :
			branch.setLocalCodeNo("01");
			break;
			
		case "경기" :
			branch.setLocalCodeNo("02");
			break;
			
		case "인천" :
			branch.setLocalCodeNo("03");
			break;
		
		case "강원" :
			branch.setLocalCodeNo("04");
			break;
		
		case "충북" :
			branch.setLocalCodeNo("05");
			break;
        
        case "충남" :
			branch.setLocalCodeNo("06");
			break;
        
        case "대전" :
			branch.setLocalCodeNo("07");
			break;
        
        case "세종" :
			branch.setLocalCodeNo("08");
			break;
        
        case "광주" :
			branch.setLocalCodeNo("09");
			break;
        
        case "전북" :
			branch.setLocalCodeNo("10");
			break;

        case "전남" :
			branch.setLocalCodeNo("11");
			break;
        
        case "경북" :
			branch.setLocalCodeNo("12");
			break;

        case "경남" :
			branch.setLocalCodeNo("13");
			break;
        
        case "대구" :
			branch.setLocalCodeNo("14");
			break;

        case "부산" :
			branch.setLocalCodeNo("15");
			break;

        case "울산" :
			branch.setLocalCodeNo("16");
			break;

        case "제주" :
			branch.setLocalCodeNo("17");
			break;

        default : 
            System.out.println("businessSupport switch-case문");
        
	}
		
		String branchTel = branch.getLocalPhoneCode()+"-"+branch.getUpdateBranchTel();
		
		branch.setBranchTel(branchTel);
		
		businessSupportDAO.updateBranch(branch);
		return businessSupportDAO.selectBranchDetail(branch.getBranchNo());
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

	@Override
	public List<Local> getLocalList() throws Exception {
		return businessSupportDAO.selectLocalList();
	}
	
	
	
}
