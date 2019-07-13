package com.vision.erp.service.accounting.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

@Repository("accountingDAOImpl")
public class AccountingDAOImpl implements AccountingDAO {

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	public AccountingDAOImpl() {
		super();
	}
	
	//vendor
	@Override
	public void insertVendor(Vendor vendor) throws Exception {
		sqlSession.insert("VendorMapper.insertVendor", vendor);
	}

	@Override
	public Vendor selectVendorDetail(String vendorNo) throws Exception {
		return sqlSession.selectOne("VendorMapper.selectVendorDetail", vendorNo);
	}

	@Override
	public void updateVendor(Vendor vendor) throws Exception {
		sqlSession.update("VendorMapper.updateVendor", vendor);
	}

	@Override
	public void updateVendorUsageStatus(Vendor vendor) throws Exception {
		sqlSession.update("VendorMapper.updateVendorUsageStatus", vendor);
	}

	@Override
	public List<Vendor> selectVendorList(Search search) throws Exception {
		return sqlSession.selectList("VendorMapper.selectVendorList", search);
	}
	
	//account
	@Override
	public void insertAccount(Account account) throws Exception {
		sqlSession.insert("AccountMapper.insertAccount", account);
	}

	@Override
	public Account selectAccountDetail(String accountRegNo) throws Exception {
		return sqlSession.selectOne("AccountMapper.selectAccountDetail", accountRegNo);
	}

	@Override
	public void updateAccount(Account account) throws Exception {
		sqlSession.update("AccountMapper.updateAccount", account);
	}

	@Override
	public void updateAccountUsageStatus(Account account) throws Exception {
		sqlSession.update("AccountMapper.updateAccountUsageStatus", account);
	}

	@Override
	public List<Account> selectAccountList(Search search) throws Exception {
		return sqlSession.selectList("AccountMapper.selectAccountList", search);
	}

	@Override
	public int checkDuplicateAccount(String accountNo) throws Exception {
		return sqlSession.selectOne("AccountMapper.checkDuplicateAccount", accountNo);
	}
	
	//Salary
	@Override
	public void insertSalary(Salary salary) throws Exception {
		sqlSession.insert("SalaryMapper.insertSalary", salary);
		
	}

	@Override
	public Salary selectSalaryDetail(String salaryNumbering) throws Exception {
		return sqlSession.selectOne("SalaryMapper.selectSalaryDetail", salaryNumbering);
	}

	@Override
	public void updateSalary(Salary salary) throws Exception {
		sqlSession.update("SalaryMapper.updateSalary", salary);
	}

	@Override
	public void updateSalaryStatus(Salary salary) throws Exception {
		sqlSession.update("SalaryMapper.updateSalaryStatus", salary);
	}

	@Override
	public List<Salary> selectSalaryList(Search search) throws Exception {
		return sqlSession.selectList("SalaryMapper.selectSalaryList", search);
	}

	@Override
	public List<Salary> calculateSalary(String salaryDate) throws Exception {
		return sqlSession.selectList("SalaryMapper.calculateSalary", salaryDate);
	}

	@Override
	public int checkDuplicateSalaryDate(String salaryDate) throws Exception {
		return sqlSession.selectOne("SalaryMapper.checkDuplicateSalary", salaryDate);
	}

	//SalaryBook
	@Override
	public List<SalaryBook> selectSalaryBookList(Search search) throws Exception {
		return sqlSession.selectList("SalaryBookMapper.selectSalaryBookList", search);
	}
	
	//statement
	@Override
	public void insertStatement(Statement statement) throws Exception {
		sqlSession.insert("StatementMapper.insertStatement", statement);
	}

	@Override
	public Statement selectStatementDetail(String statementNo) throws Exception {
		return sqlSession.selectOne("StatementMapper.selectStatementDetail", statementNo);
	}

	@Override
	public void updateStatement(Statement statement) throws Exception {
		sqlSession.update("StatementMapper.updateStatement", statement);
	}

	@Override
	public void updateStatementUsageStatus(Statement statement) throws Exception {
		sqlSession.update("StatementMapper.updateStatementUsageStatus", statement);
	}

	@Override
	public List<Statement> selectStatementList(Search search) throws Exception {
		return sqlSession.selectList("StatementMapper.selectStatementList", search);
	}
	
	//Card
	@Override
	public void insertCard(Card card) throws Exception {
		sqlSession.insert("CardMapper.insertCard", card);
	}

	@Override
	public Card selectCardDetail(String cardRegNo) throws Exception {
		return sqlSession.selectOne("CardMapper.selectCardDetail", cardRegNo);
	}

	@Override
	public void updateCard(Card card) throws Exception {
		sqlSession.update("CardMapper.updateCard", card);
	}

	@Override
	public void updateCardUsageStatus(Card card) throws Exception {
		sqlSession.update("CardMapper.updateCardUsageStatus", card);
	}

	@Override
	public List<Card> selectCardList(Search search) throws Exception {
		return sqlSession.selectList("CardMapper.selectCardList", search);
	}

	@Override
	public int checkDuplicateCard(String cardNo) throws Exception {
		return sqlSession.selectOne("CardMapper.checkDuplicateCard", cardNo);
	}

}
