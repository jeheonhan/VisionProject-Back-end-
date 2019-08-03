package com.vision.erp.service.accounting.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.accounting.AccountingService;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

@Service("accountingServiceImpl")
public class AccountingServiceImpl implements AccountingService{

	@Resource(name="accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	
	public AccountingServiceImpl(){
		super();
	}


	@Override
	public void addVendor(Vendor vendor) throws Exception {
		accountingDAO.insertVendor(vendor);
	}


	@Override
	public Vendor getVendorDetail(String vendorNo) throws Exception {
		return accountingDAO.selectVendorDetail(vendorNo);
	}


	@Override
	public void modifyVendor(Vendor vendor) throws Exception {
		accountingDAO.updateVendor(vendor);
	}

	@Override
	public void convertVendorUsageStatus(List<Vendor> vendorList) throws Exception {
		accountingDAO.updateVendorUsageStatus(vendorList);
	}


	@Override
	public List<Vendor> getVendorList(Search search) throws Exception {
		return accountingDAO.selectVendorList(search);
	}


	@Override
	public void addAccount(Account account) throws Exception {
		accountingDAO.insertAccount(account);
	}


	@Override
	public Account getAccountDetail(String accountRegNo) throws Exception {
		return accountingDAO.selectAccountDetail(accountRegNo);
	}


	@Override
	public void modifyAccount(Account account) throws Exception {
		accountingDAO.updateAccount(account);
	}


	@Override
	public void convertAccountUsageStatus(List<Account> accountList) throws Exception {
		accountingDAO.updateAccountUsageStatus(accountList);
	}


	@Override
	public List<Account> getAccountList(Search search) throws Exception {
		return accountingDAO.selectAccountList(search);
	}


	@Override
	public int checkDuplicateAccount(String accountNo) throws Exception {
		return accountingDAO.checkDuplicateAccount(accountNo);
	}


	@Override
	public void addSalary(String salaryDate) throws Exception {
		List<Salary> list = accountingDAO.calculateSalary(salaryDate);
		
		for(Salary salary : list) {
			salary.setSalaryDate(salaryDate);
			accountingDAO.insertSalary(salary);	
		}
		
	}

	@Override
	public Salary getSalaryDetail(String salaryNumbering) throws Exception {
		return accountingDAO.selectSalaryDetail(salaryNumbering);
	}


	@Override
	public void modifySalary(Salary salary) throws Exception {
		accountingDAO.updateSalary(salary);
	}


	@Override
	public void modifySalaryStatus(Salary salary) throws Exception {
		
		if(salary.getSalaryStatusCodeNo().equals("01")) {
			salary.setSalaryStatusCodeNo("02");
		}else if(salary.getSalaryStatusCodeNo().equals("02")) {
			salary.setSalaryStatusCodeNo("03");
		}
		
		accountingDAO.updateSalaryStatus(salary);
	}


	@Override
	public List<Salary> getSalaryList(Search search) throws Exception {
		return accountingDAO.selectSalaryList(search);
	}


	@Override
	public int checkDuplicateSalaryDate(String salaryDate) throws Exception {
		return accountingDAO.checkDuplicateSalaryDate(salaryDate);
	}


	@Override
	public List<SalaryBook> getSalaryBookList(Search search) throws Exception {
		return accountingDAO.selectSalaryBookList(search);
	}


	@Override
	public void addStatement(Statement statement) throws Exception {
		accountingDAO.insertStatement(statement);
	}


	@Override
	public Statement getStatementDetail(String statementNo) throws Exception {
		return accountingDAO.selectStatementDetail(statementNo);
	}


	@Override
	public void modifyStatement(Statement statement) throws Exception {
		accountingDAO.updateStatement(statement);
	}


	@Override
	public void convertStatementUsageStatus(List<Object> objectList) throws Exception {
		
		List<Statement> statementList = new ArrayList<Statement>();
		
		for(int i = 0; i<objectList.size(); i++) {
			
			Statement statement = new Statement();
			
			statement.setStatementNo((String)objectList.get(i));
			statement.setStatementUsageStatusCodeNo("02");
			statementList.add(statement);
		}
		
		accountingDAO.updateStatementUsageStatusList(statementList);
	}

	@Override
	public List<Statement> getStatementList(Search search) throws Exception {
		return accountingDAO.selectStatementList(search);
	}


	@Override
	public void addCard(Card card) throws Exception {
		accountingDAO.insertCard(card);
	}


	@Override
	public Card getCardDetail(String cardRegNo) throws Exception {
		return accountingDAO.selectCardDetail(cardRegNo);
	}


	@Override
	public void modifyCard(Card card) throws Exception {
		accountingDAO.updateCard(card);
	}


	@Override
	public void convertCardUsageStatus(List<Card> cardList) throws Exception {
		accountingDAO.updateCardUsageStatus(cardList);
	}


	@Override
	public List<Card> getCardList(Search search) throws Exception {
		return accountingDAO.selectCardList(search);
	}


	@Override
	public int checkDuplicateCard(String cardNo) throws Exception {
		return accountingDAO.checkDuplicateCard(cardNo);
	}


	@Override
	public List<SalaryBook> getAnalyzeDepartmentSalary(String salaryDate) throws Exception {
		return accountingDAO.selectAnalyzeDepartmentSalary(salaryDate);
	}


	@Override
	public List<SalaryBook> getAnalyzeRankSalary(String salaryDate) throws Exception {
		return accountingDAO.selectAnalyzeRankSalary(salaryDate);
	}


	
}
