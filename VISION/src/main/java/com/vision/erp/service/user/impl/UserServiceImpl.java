package com.vision.erp.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.businesssupport.BusinessSupportDAO;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.User;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.humanresource.HumanResourceDAO;
import com.vision.erp.service.user.UserDAO;
import com.vision.erp.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
	@Autowired
	@Qualifier("humanResourceDAOImpl")
	private HumanResourceDAO humanResourceDAO;
	
	@Autowired
	@Qualifier("accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	@Autowired
	@Qualifier("businessSupportDAOImpl")
	private BusinessSupportDAO businessSupportDAO;
	
	//회원가입
	@Override
	public void addUser(User user) throws Exception {
		
		userDAO.insertUser(user);
		
	}
	@Override
	public User getUser(User user) throws Exception {
		
		return userDAO.selectUser(user);
	}
	//안보여줄거임
	@Override
	public List<User> getUserList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.selectUserList(search);
	}
	//내정보보기(메소드이용)인사카드/근태/급여이력/지점정보(내 메소드 쓰는거아님)
	@Override
	public Map<String,Object> getInfo(String employeeNo) throws Exception {
		
		Search search = new Search();
		Map<String, Object> map = new HashMap<String, Object>();
		search.setSearchKeyword(employeeNo);
		
		HumanResourceCard humanResourceCard = humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo(employeeNo);
		List<Salary> salary = accountingDAO.selectSalaryList(search);
		List<WorkAttitude> workAttitude = humanResourceDAO.selectWorkAttitudeList(search);
		Branch branch = businessSupportDAO.selectBranchDetail(employeeNo);
		
		map.put("humanResourceCard", humanResourceCard);
		map.put("salary", salary);
		map.put("workAttitude", workAttitude);
		map.put("branch", branch);
		
		return map;
	}
	//비밀번호변경
	@Override
	public void modifyPassword(User user) throws Exception {
		
		userDAO.updatePassword(user);
		
	}
	//사원아이디확인
	@Override
	public User getProofMySelfForId1(HumanResourceCard hrcInfo) throws Exception {
		
		return userDAO.selectProofMySelfForId1(hrcInfo);
	}
	//사원비밀번호확인
	@Override
	public User getProofMySelfForPassword1(Map<String, String> map) throws Exception {
		
		return userDAO.selectProofMySelfForPassword1(map);
	}
	//점장아이디왁인
	@Override
	public User getProofMySelfForId2(Branch branch) throws Exception {
		
		return userDAO.selectProofMySelfForId2(branch);
	}
	//점장비밀번호확인
	@Override
	public User getProofMySelfForPassword2(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.selectProofMySelfForPassword2(map);
	}

	
	

}
