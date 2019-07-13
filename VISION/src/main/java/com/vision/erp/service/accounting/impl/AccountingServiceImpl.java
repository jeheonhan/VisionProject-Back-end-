package com.vision.erp.service.accounting.impl;

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
	public void convertVendorUsageStatus(Vendor vendor) throws Exception {
		accountingDAO.updateVendorUsageStatus(vendor);
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
	public void convertAccountUsageStatus(Account account) throws Exception {
		accountingDAO.updateAccountUsageStatus(account);
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
	public void convertStatementUsageStatus(Statement statement) throws Exception {
		accountingDAO.updateStatementUsageStatus(statement);
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
	public void convertCardUsageStatus(Card card) throws Exception {
		accountingDAO.updateCardUsageStatus(card);
	}


	@Override
	public List<Card> getCardList(Search search) throws Exception {
		return accountingDAO.selectCardList(search);
	}


	@Override
	public int checkDuplicateCard(String cardNo) throws Exception {
		return accountingDAO.checkDuplicateCard(cardNo);
	}
	
}
