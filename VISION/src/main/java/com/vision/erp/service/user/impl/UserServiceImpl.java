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
		
		userDAO.addUser(user);
		
	}
	//안보여줄거임
	@Override
	public List<User> selectUserList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.selectUserList(search);
	}
	//내정보보기(메소드이용)인사카드/근태/급여이력/지점정보
	@Override
	public Map selectUser(String find) throws Exception {
		
		Search search = new Search();
		Map<String, Object> map = new HashMap<String, Object>();
		search.setSearchKeyword(find);
		
		HumanResourceCard humanResourceCard = humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo(find);
		List<Salary> salary = accountingDAO.selectSalaryList(search);
		List<WorkAttitude> workAttitude = humanResourceDAO.selectWorkAttitudeList(search);
		Branch branch = businessSupportDAO.selectBranchDetail(find);
		
		map.put("humanResourceCard", humanResourceCard);
		map.put("salary", salary);
		map.put("workAttitude", workAttitude);
		map.put("branch", branch);
		
		return map;
	}
	//비밀번호변경
	@Override
	public void updatePassword(User user) throws Exception {
		
		userDAO.updatePassword(user);
		
	}
	//사원아이디확인
	@Override
	public String proofMySelfForId1(HumanResourceCard hrcInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//사원비밀번호확인
	@Override
	public String proofMySelfForPassword1(HumanResourceCard hrcInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//점장아이디왁인
	@Override
	public String proofMySelfForId2(Branch branch) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//점장비밀번호확인
	@Override
	public String proofMySelfForPassword2(Branch branch) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
