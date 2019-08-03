package test.sms;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})

public class AccountingDAOImplNewTest {

		@Resource(name = "accountingDAOImpl")
		private AccountingDAO accountingDAO;
		
		private Search search;
		private Vendor vendor;
		private Account account;
		private Salary salary;
		private Statement statement;
		private Card card;
		
/////////////////////////////////////Vendor/////////////////////////////////		
		
		//@Test
		public void testSelectVendorList() throws Exception{
			
			search = new Search();
//			search.setSearchKeyword("체");
//			search.setUsageCondition("01");
			
			List<Vendor> list = accountingDAO.selectVendorList(search);
			
			for(Vendor vendor : list) {
				System.out.println(vendor);
			}
		}
		
		//@Test
		public void testInsertVendor() throws Exception{
		
			
			vendor = new Vendor();
			vendor.setVendorName("매포코");
			vendor.setVendorCategoryCodeNo("01");
			vendor.setVendorPhone("01011111111");
			vendor.setVendorTel("021111111");
			vendor.setRepresentativeName("홍반장");
			vendor.setAddress("강남 비트캠프");
			vendor.setZipCode("11111");
			vendor.setDetailAddress("서울특별시");
			
			account = new Account();
			account.setBankCodeNo("01");
			account.setAccountNo("1111111111111");
			account.setAccountHolder("홍반장");
			
			vendor.setVendorAccount(account);
			
			//Insert
			accountingDAO.insertVendor(vendor);
			
			//AfterInsert
			search = new Search();
			
			List<Vendor> list = accountingDAO.selectVendorList(search);
			
			for(Vendor vendor : list) {
				System.out.println(vendor);
			}

		}
		
		//@Test
		public void testSelectVendorDetail() throws Exception{
			
			String vendorNo = "v1021";
			
			vendor = accountingDAO.selectVendorDetail(vendorNo);
			System.out.println(vendor);
			
		}
		
		//@Test
		public void testUpdateVendor() throws Exception{
		
			
			vendor = new Vendor();
			vendor.setVendorNo("v1021");
			vendor.setVendorName("비젼");
			vendor.setVendorCategoryCodeNo("01");
			vendor.setVendorPhone("01022222222");
			vendor.setVendorTel("02222222");
			vendor.setRepresentativeName("문반장");
			vendor.setAddress("강남 10번 출구");
			vendor.setZipCode("22222");
			vendor.setDetailAddress("서울특별시 동대문구");
			
			account = new Account();
			account.setBankCodeNo("02");
			account.setAccountNo("222222222222");
			account.setAccountHolder("문반장");
			
			vendor.setVendorAccount(account);
			
			//Insert
			accountingDAO.updateVendor(vendor);
			
			//AfterInsert
			search = new Search();
			
			List<Vendor> list = accountingDAO.selectVendorList(search);
			
			for(Vendor vendor : list) {
				System.out.println(vendor);
			}

		}
		
		//@Test
		public void testUpdateVendorUsageStatus() throws Exception{
			
			vendor = new Vendor();
			vendor.setVendorUsageStatusCodeNo("02");
			vendor.setVendorNo("v1021");
			
			//Update
//			accountingDAO.updateVendorUsageStatus(vendor);
			
		}
		
/////////////////////////////////////Account/////////////////////////////////
		
		//@Test
		public void testSelectAccountList() throws Exception{
			
			search = new Search();
			search.setSearchKeyword("1000");
			search.setUsageCondition("01");
			
			List<Account> list = accountingDAO.selectAccountList(search);
			
			for(Account account : list) {
				System.out.println(account);
			}
		}
		
		//@Test
		public void testInsertAccount() throws Exception{
			
			account = new Account();
			account.setAccountNo("1111111111111");
			account.setBankCodeNo("01");
			account.setAccountHolder("멍멍이");
			account.setAccountCategoryCodeNo("01");
			account.setReference("발주");
			
			//Insert
			accountingDAO.insertAccount(account);
			
			//CheckSelectKey
			System.out.println("SelectKey 확인 : "+account);
			
			//AfterInsert
			search = new Search();
			
			List<Account> list = accountingDAO.selectAccountList(search);
			
			for(Account account : list) {
				System.out.println(account);
			}

		}
		
		//@Test
		public void testSelectAccountDetail() throws Exception{
			
			String accountRegNo = "1000";
			
			account = accountingDAO.selectAccountDetail(accountRegNo);
			System.out.println(account);
			
		}
		
		//@Test
		public void testUpdateAccount() throws Exception{
		
			
			account = new Account();
			account.setAccountRegNo("1021");
			account.setAccountNo("222222222222");
			account.setBankCodeNo("02");
			account.setAccountHolder("고양이");
			account.setAccountCategoryCodeNo("02");
			account.setReference("주문");
			
			//Insert
			accountingDAO.updateAccount(account);
			
			//AfterInsert
			search = new Search();
			
			List<Account> list = accountingDAO.selectAccountList(search);
			
			for(Account account : list) {
				System.out.println(account);
			}

		}
		
		//@Test
		public void testUpdateAccountUsageStatus() throws Exception{
			
			account = new Account();
			account.setAccountUsageStatusCodeNo("02");
			account.setAccountRegNo("1021");
			
			//Update
//			accountingDAO.updateAccountUsageStatus(account);
			
		}
		
		//@Test
		public void testCheckDuplicateAccount() throws Exception{
			
			String accountNo = "222222222222";
			
			int i = accountingDAO.checkDuplicateAccount(accountNo);
			
			System.out.println("중복된 갯수 : "+i);
		}
		
/////////////////////////////////////Salary/////////////////////////////////
		
		
		//@Test
		public void testCheckDuplicateSalary() throws Exception{
			
			String salaryDate = "2019/06";
			
			int i = accountingDAO.checkDuplicateSalaryDate(salaryDate);
			
			System.out.println("중복된 갯수 : "+i);
		}
		
		//@Test
		public void testCalculateSalary() throws Exception{
			
			String salaryDate = "2019/04";
			
			List<Salary> list = accountingDAO.calculateSalary(salaryDate);
			
			for(Salary salary : list) {
				System.out.println(salary);
			}
		}
		
		//@Test
		public void testInsertSalary() throws Exception{
			
			salary = new Salary();
			salary.setSalaryDate("2019/04");
			salary.setEmployeeNo("1000");
			salary.setIndividualTotalSalary("93667");
			salary.setWage("10000");
			salary.setTotalRegularWorkTime("540");
			salary.setTotalExtendWorkTime("20");
			
			//Insert
			accountingDAO.insertSalary(salary);
			
			//AfterInsert
			search = new Search();
			
			List<Salary> list = accountingDAO.selectSalaryList(search);
			
			for(Salary salary : list) {
				System.out.println(salary);
			}
			
		}
		
		//@Test
		public void testSelectSalaryList() throws Exception{
			
			search = new Search();
			search.setSearchKeyword("1000");
			search.setMaxDate("2019/05");
			search.setMinDate("2019/05");
			
			List<Salary> list = accountingDAO.selectSalaryList(search);
			
			for(Salary salary : list) {
				System.out.println(salary);
			}
		}
		
		//@Test
		public void testSelectSalaryDetail() throws Exception{
			
			String salaryNumbering = "1008";
			
			salary = accountingDAO.selectSalaryDetail(salaryNumbering);
			System.out.println(salary);
			
		}
		
		//@Test
		public void testUpdateSalary() throws Exception{
			
			salary = new Salary();
			salary.setSalaryNumbering("1021");
			salary.setIndividualTotalSalary("15000");
			
			//Update
			accountingDAO.updateSalary(salary);
			
			//AfterUpdate
			search = new Search();
			
			List<Salary> list = accountingDAO.selectSalaryList(search);
			
			for(Salary salary : list) {
				System.out.println(salary);
			}
		}
		
		//@Test
		public void testUpdateSalaryStatus() throws Exception{
			
			salary = new Salary();
			salary.setSalaryNumbering("1003");
			salary.setSalaryStatusCodeNo("01");
			
			//UpdateStatus
			accountingDAO.updateSalaryStatus(salary);
			
			//AfterUpdate
			search = new Search();
			
			List<Salary> list = accountingDAO.selectSalaryList(search);
			
			for(Salary salary : list) {
				System.out.println(salary);
			}
		}
		
//////////////////////////////////SalaryBook/////////////////////////////////

		//@Test
		public void testSelectSalaryBookList() throws Exception{
			
			search = new Search();
			
			List<SalaryBook> list = accountingDAO.selectSalaryBookList(search);
			
			for(SalaryBook salaryBook : list) {
				System.out.println(salaryBook);
			}
		}

//////////////////////////////////Statement/////////////////////////////////
		
		//@Test
		public void testSelectStatementList() throws Exception{
			
			search = new Search();
			search.setSearchCondition("01");
			search.setMinDate("2015/07/01");
			search.setMaxDate("2019/08/01");
			
			List<Statement> list = accountingDAO.selectStatementList(search);
			
			for(Statement statement : list) {
				System.out.println(statement);
			}
		}
		
		//@Test
		public void testInsertStatement() throws Exception{
			
			statement = new Statement();
			statement.setTradeTargetName("나무발발이점");
			statement.setTradeDate("2019/07/12");
			statement.setAccountNo("138294382947");
			statement.setTradeAmount("555555");
			statement.setStatementCategoryCodeNo("01");
			statement.setStatementDetail("주문");
			
			//Insert
			accountingDAO.insertStatement(statement);
			
			//afterInsert
			search = new Search();
			
			List<Statement> list = accountingDAO.selectStatementList(search);
			
			for(Statement statement : list) {
				System.out.println(statement);
			}
		}
		
		//@Test
		public void testSelectStatementDetail() throws Exception{
			
			String statementNo = "1020";
			statement = new Statement();
			
			statement = accountingDAO.selectStatementDetail(statementNo);
			
			System.out.println(statement);
		}
		
		//@Test
		public void testUpdateStatement() throws Exception{
			
			statement = new Statement();
			statement.setStatementNo("1020");
			statement.setTradeTargetName("매포코");
			statement.setTradeDate("2019/04/24");
			statement.setAccountNo("138294382947");
			statement.setTradeAmount("88888888");
			statement.setStatementCategoryCodeNo("01");
			statement.setStatementDetail("주문");
			
			//Update
			accountingDAO.updateStatement(statement);;
			
			//afterUpdate
			search = new Search();
			
			List<Statement> list = accountingDAO.selectStatementList(search);
			
			for(Statement statement : list) {
				System.out.println(statement);
			}
		}
		
		//@Test
		public void testUpdateStatementUsageStatus() throws Exception{
			
			statement = new Statement();
			statement.setStatementNo("1020");
			statement.setStatementUsageStatusCodeNo("02");
			
//			accountingDAO.updateStatementUsageStatus(statement);
		}
	
//////////////////////////////////Card/////////////////////////////////
		
		//@Test
		public void testCheckDuplicateCard() throws Exception{
			
			String cardNo = "1029384927384938";
			
			int i = accountingDAO.checkDuplicateCard(cardNo);
			
			System.out.println("중복된 카드 수 : "+i);
		}
		
		//@Test
		public void testSelectCardList() throws Exception{
			
			search = new Search();
			search.setSearchKeyword("카드");
			search.setUsageCondition("01");
			
			List<Card> list = accountingDAO.selectCardList(search);
			
			for(Card card : list) {
				System.out.println(card);
			}
		}
		
		//@Test
		public void testInsertCard() throws Exception{
			
			card = new Card();
			card.setCardNo("1111111111111111");
			card.setCardManager("1002");
			card.setCardCategoryCodeNo("01");
			card.setCardName("썸타는 우리체크 카드");
			card.setCardCompanyCodeNo("01");
			card.setCardImage("http://placehold.it/320x100");
			card.setAccountNo("1002338475637");
			
			//Insert
			accountingDAO.insertCard(card);
			
			//AfterInsert
			search = new Search();
			
			List<Card> list = accountingDAO.selectCardList(search);
			
			for(Card card : list) {
				System.out.println(card);
			}
		}
		
		//@Test
		public void testSelectCardDetail() throws Exception{
			
			String cardRegNo = "1020";
			card = new Card();
			
			card = accountingDAO.selectCardDetail(cardRegNo);
			
			System.out.println(card);
		}
		
		//@Test
		public void testUpdateCard() throws Exception{
			
			card = new Card();
			card.setCardRegNo("1020");
			card.setCardNo("2222222222222222");
			card.setCardManager("1002");
			card.setCardCategoryCodeNo("01");
			card.setCardName("썸타는 우리체크 카드");
			card.setCardCompanyCodeNo("01");
			card.setCardImage("http://placehold.it/320x100");
			card.setAccountNo("1002338475637");
			
			//Update
			accountingDAO.updateCard(card);
			
			//AfterUpdate
			search = new Search();
			
			List<Card> list = accountingDAO.selectCardList(search);
			
			for(Card card : list) {
				System.out.println(card);
			}
		}
		
		//@Test
		public void testUpdateCardUsageStatus() throws Exception{
			
			card = new Card();
			card.setCardRegNo("1020");
			card.setCardUsageStatusCodeNo("02");
			
//			accountingDAO.updateCardUsageStatus(card);
			
		}
		
}
