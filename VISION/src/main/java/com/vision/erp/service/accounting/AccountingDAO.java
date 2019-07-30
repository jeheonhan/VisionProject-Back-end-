package com.vision.erp.service.accounting;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

public interface AccountingDAO {
	
	public void insertVendor(Vendor vendor) throws Exception;
	
	public Vendor selectVendorDetail(String vendorNo) throws Exception;
	
	public void updateVendor(Vendor vendor) throws Exception;
	
	public void updateVendorUsageStatus(List<Vendor> vendorList) throws Exception;
	
	public List<Vendor> selectVendorList(Search search) throws Exception;

	public void insertAccount(Account account) throws Exception;
	
	public Account selectAccountDetail(String accountRegNo) throws Exception;
	
	public void updateAccount(Account account) throws Exception;
	
	public void updateAccountUsageStatus(Account account) throws Exception;
	
	public List<Account> selectAccountList(Search search) throws Exception;
	
	public int checkDuplicateAccount(String accountNo) throws Exception;
	
	public void insertSalary(Salary salary) throws Exception;
	
	public Salary selectSalaryDetail(String salaryNumbering) throws Exception;
	
	public void updateSalary(Salary salary) throws Exception;
	
	public void updateSalaryStatus(Salary salary) throws Exception;
	
	public List<Salary> selectSalaryList(Search search) throws Exception;
	
	public List<Salary> calculateSalary(String salaryDate) throws Exception;
	
	public int checkDuplicateSalaryDate(String salaryDate) throws Exception;
	
	public List<SalaryBook> selectSalaryBookList(Search search) throws Exception;
	
	public void insertStatement(Statement statement) throws Exception;
	
	public Statement selectStatementDetail(String statementNo) throws Exception;
	
	public void updateStatement(Statement statement) throws Exception;
	
	public void updateStatementUsageStatus(Statement statement) throws Exception;
	
	public List<Statement> selectStatementList(Search search) throws Exception;
	
	public void insertCard(Card card) throws Exception;
	
	public Card selectCardDetail(String cardRegNo) throws Exception;
	
	public void updateCard(Card card) throws Exception;
	
	public void updateCardUsageStatus(Card card) throws Exception;
	
	public List<Card> selectCardList(Search search) throws Exception;
	
	public int checkDuplicateCard(String cardNo) throws Exception;
	
	public List<SalaryBook> selectAnalyzeDepartmentSalary(String salaryDate) throws Exception;
	
	public List<SalaryBook> selectAnalyzeRankSalary(String salaryDate) throws Exception;
	
}
