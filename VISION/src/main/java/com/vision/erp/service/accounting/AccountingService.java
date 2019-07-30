package com.vision.erp.service.accounting;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

public interface AccountingService {

	public void addVendor(Vendor vendor) throws Exception;
	
	public Vendor getVendorDetail(String vendorNo) throws Exception;
	
	public void modifyVendor(Vendor vendor) throws Exception;
	
	public void convertVendorUsageStatus(List<Vendor> vendorList) throws Exception;
	
	public List<Vendor> getVendorList(Search search) throws Exception;

	public void addAccount(Account account) throws Exception;
	
	public Account getAccountDetail(String accountRegNo) throws Exception;
	
	public void modifyAccount(Account account) throws Exception;
	
	public void convertAccountUsageStatus(Account account) throws Exception;
	
	public List<Account> getAccountList(Search search) throws Exception;
	
	public int checkDuplicateAccount(String accountNo) throws Exception;
	
	public void addSalary(String salaryDate) throws Exception;
	
	public Salary getSalaryDetail(String salaryNumbering) throws Exception;
	
	public void modifySalary(Salary salary) throws Exception;
	
	public void modifySalaryStatus(Salary salary) throws Exception;
	
	public List<Salary> getSalaryList(Search search) throws Exception;
	
	public int checkDuplicateSalaryDate(String salaryDate) throws Exception;
	
	public List<SalaryBook> getSalaryBookList(Search search) throws Exception;
	
	public void addStatement(Statement statement) throws Exception;
	
	public Statement getStatementDetail(String statementNo) throws Exception;
	
	public void modifyStatement(Statement statement) throws Exception;
	
	public void convertStatementUsageStatus(Statement statement) throws Exception;
	
	public List<Statement> getStatementList(Search search) throws Exception;
	
	public void addCard(Card card) throws Exception;
	
	public Card getCardDetail(String cardRegNo) throws Exception;
	
	public void modifyCard(Card card) throws Exception;
	
	public void convertCardUsageStatus(Card card) throws Exception;
	
	public List<Card> getCardList(Search search) throws Exception;
	
	public int checkDuplicateCard(String cardNo) throws Exception;
	
	public List<SalaryBook> getAnalyzeDepartmentSalary(String salaryDate) throws Exception;
	
	public List<SalaryBook> getAnalyzeRankSalary(String salaryDate) throws Exception;
	
}
