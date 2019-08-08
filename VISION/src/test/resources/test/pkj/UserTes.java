//package test.pkj;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.vision.erp.common.Search;
//import com.vision.erp.service.accounting.AccountingDAO;
//import com.vision.erp.service.businesssupport.BusinessSupportDAO;
//import com.vision.erp.service.domain.Branch;
//import com.vision.erp.service.domain.HumanResourceCard;
//import com.vision.erp.service.domain.Product;
//import com.vision.erp.service.domain.Salary;
//import com.vision.erp.service.domain.User;
//import com.vision.erp.service.domain.WorkAttitude;
//import com.vision.erp.service.humanresource.HumanResourceDAO;
//import com.vision.erp.service.humanresource.HumanResourceService;
//import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;
//import com.vision.erp.service.user.UserDAO;
//import com.vision.erp.service.user.UserService;
//import com.vision.erp.service.user.impl.UserServiceImpl;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"classpath:config/root-context.xml",
//		"classpath:config/aspect-context.xml",
//		"classpath:config/servlet-context.xml",
//		"classpath:config/transaction-context.xml"
//})
//public class UserTes{
//
//	@Resource(name = "userDAOImpl")
//	private UserDAO userDAO;
//
////	@Resource(name = "productionDAOImpl")
////	private ProductionManagementDAOrudwn productionDAO;
//
//	@Resource(name = "userServiceImpl")
//	private UserService userService;
//
//	@Autowired
//	@Qualifier("humanResourceDAOImpl")
//	private HumanResourceDAO humanResourceDAO;
//
//	@Autowired
//	@Qualifier("accountingDAOImpl")
//	private AccountingDAO accountingDAO;
//
//	@Autowired
//	@Qualifier("businessSupportDAOImpl")
//	private BusinessSupportDAO businessSupportDAO;
//
//
//
//	@Test
//	public void testSelectUserList() throws Exception {
//
//		Search search = new Search();
//
//		List<User> list 
//		= (List<User>)userDAO.selectUserList(search);
//
//		for(int i = 0; i<list.size(); i++) {
//			User user = list.get(i);
//			System.out.println(user);
//		}
//
//	}
//
//	//@Test
//	public void testSelectUser() throws Exception{
//		String userId = "U1000";
//
//		//User user = userDAO.selectUser(userId);
//
//		//System.out.println(user);
//	}
//
//	//@Test
//	public void updatePassword() throws Exception{
//
//		String userId = "U1001";
//		//User user = userDAO.selectUser(userId);
//
//		//System.out.println(user);
//
//		//user.setPassword("1234");
//
//		//System.out.println(user);
//		//userDAO.updatePassword(user);
//
//	}
//
//	//@Test
//	public void proofMySelfForId1() throws Exception{
//
//		HumanResourceCard hrcInfo = new HumanResourceCard();
//
//		hrcInfo.setEmployeeName("박경주");
//		hrcInfo.setEmployeePhone("010-2255-5786");
//
////		User forId = userDAO.proofMySelfForId1(hrcInfo);
//
////		System.out.println("forId :: " + forId);
//
//	}
//
//	//@Test
//	public void proofMySelfForPassword1() throws Exception{
//
//		HumanResourceCard hrcInfo = new HumanResourceCard();
//
//		hrcInfo.setEmployeeName("박경주");
//		hrcInfo.setEmployeePhone("010-2255-5786");
//
//		//User forPassword = userDAO.proofMySelfForPassword1(hrcInfo);
//
//		//System.out.println("forPassword :: " + forPassword);
//
//	}
//
//	//@Test
//	public void proofMySelfForId2() throws Exception{
//
//		Branch branch = new Branch();
//
//		branch.setBranchManagerName("홍길동");
//		branch.setBranchManagerPhone("010-1111-1000");
//
//
////		User forId = userDAO.proofMySelfForId2(branch);
//
////		System.out.println("forId :: " + forId);
//
//	}
//
//	//@Test
//	public void proofMySelfForPassword2() throws Exception{
//
//		Branch branch = new Branch();
//
//		branch.setBranchManagerName("홍길동");
//		branch.setBranchManagerPhone("010-1111-1000");
//
//		//User forPassword = userDAO.proofMySelfForPassword2(branch);
//
//		//System.out.println("forPassword :: " + forPassword);
//
//	}
//
//	//@Test
//	public void addUser() throws Exception{
//
//		User user = new User();
//
//		user.setUserId("U1007");
//		user.setEmployeeNo("1004");
//		user.setPassword("0000");
//		user.setMemberCodeNo("01");
//		user.setMemberUsageStatusCodeNo("01");
//		user.setAccessMenuCodeNo("04");
//
//		System.out.println(user);
//
////		userDAO.addUser(user);
//	}
//
//	//service Test =============================================================================================
//	//service Test =============================================================================================
//	//service Test =============================================================================================
//
//	//@Test
//	public void addUserService() throws Exception{
//
//		User user = new User();
//
//		user.setUserId("U1008");
//		user.setEmployeeNo("1005");
//		user.setPassword("0000");
//		user.setMemberCodeNo("01");
//		user.setMemberUsageStatusCodeNo("01");
//		user.setAccessMenuCodeNo("04");
//
//		System.out.println(user);
//
//		userService.addUser(user);
//	}
//
//	//@Test
//	public void UserInfoMapService() throws Exception{
//
//		Search search = new Search();
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		search.setSearchKeyword("박경주");
//		
//		
//
//		HumanResourceCard humanResourceCard = humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo("1000");
//		List<Salary> salary = accountingDAO.selectSalaryList(search);
//		List<WorkAttitude> workAttitude = humanResourceDAO.selectWorkAttitudeList(search);
//		Branch branch = businessSupportDAO.selectBranchDetail("b1003");
//
//		map.put("humanResourceCard", humanResourceCard);
//		map.put("salary", salary);
//		map.put("workAttitude", workAttitude);
//		map.put("branch", branch);
//
//		System.out.println(map.toString());
//	}
//
//	//@Test
//	public void updatePasswordService() throws Exception{
//
//		User user = new User();
//		user.setUserId("U1008");
//		user.setPassword("0000");
//		
////		userService.updatePassword(user);
//
//	}
//	
//	//@Test
//	public void findIdService1() throws Exception {
//		HumanResourceCard hrcInfo = new HumanResourceCard();
//		
//		hrcInfo.setEmployeeName("박경주");
//		hrcInfo.setEmployeePhone("010-2255-5786");
//		
////		User id = userService.proofMySelfForId1(hrcInfo);
////		System.out.println("check :: " + id);
//	}
//	
//	//@Test
//	public void findIdService2() throws Exception {
//		Branch branch = new Branch();
//		
//		branch.setBranchManagerName("홍길동");
//		branch.setBranchManagerPhone("010-1111-1000");
//		
////		User id = userService.proofMySelfForId2(branch);
////		System.out.println("check :: " + id);
//	}
//	
//	
//	//@Test
//		public void findPasswordService1() throws Exception {
//			HumanResourceCard hrcInfo = new HumanResourceCard();
//			
//			hrcInfo.setEmployeeName("박경주");
//			hrcInfo.setEmployeePhone("010-2255-5786");
//			
//			//User password = userService.proofMySelfForPassword1(hrcInfo);
//			//System.out.println("check :: " + password);
//		}
//		
//		//@Test
//		public void findPasswordService2() throws Exception {
//			Branch branch = new Branch();
//			
//			branch.setBranchManagerName("홍길동");
//			branch.setBranchManagerPhone("010-1111-1000");
//			
//			//User password = userService.proofMySelfForPassword2(branch);
//			//System.out.println("check :: " + password);
//		}
//
//
//
//
//}
