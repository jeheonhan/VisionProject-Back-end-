package com.vision.erp.web.accounting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.ImageFileUpload;
import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingService;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Card;
import com.vision.erp.service.domain.Salary;
import com.vision.erp.service.domain.SalaryBook;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.domain.Vendor;

@RestController
public class AccountingController {

	@Resource(name = "accountingServiceImpl")
	private AccountingService accountingService;
	
	public AccountingController() {
		super();
	}

	//거래처등록
	@RequestMapping(value = "/accounting/addVendor", method = RequestMethod.POST)
	public void addVendor(@RequestBody Vendor vendor) throws Exception{
		
		System.out.println("/accounting/addVendor");
		System.out.println("들어온 vendor domain 값"+vendor);
		
		vendor.setVendorTel(vendor.getLocalPhoneCode()+"-"+vendor.getVendorTel());
		
		accountingService.addVendor(vendor);
	}
	
	//거래처 상세조회
	@RequestMapping(value = "/accounting/getVendorDetail/{vendorNo}", method = RequestMethod.GET)
	public Vendor getVendorDetail(@PathVariable String vendorNo) throws Exception{
		
		System.out.println("/accounting/getVendorDetail");
		System.out.println(vendorNo);
		
		Vendor vendor = accountingService.getVendorDetail(vendorNo);
		
		vendor.setLocalPhoneCode(vendor.getVendorTel().substring(0, vendor.getVendorTel().indexOf("-")));
		vendor.setVendorTel(vendor.getVendorTel().substring(vendor.getVendorTel().indexOf("-")+1));
		System.out.println(vendor);
		
		return vendor;
	}
	
	//거래처 수정
	@RequestMapping(value = "/accounting/modifyVendor", method = RequestMethod.POST)
	public void modifyVendor(@RequestBody Vendor vendor) throws Exception{
		
		System.out.println("/accounting/modifyVendor");
		vendor.setVendorTel(vendor.getLocalPhoneCode()+"-"+vendor.getVendorTel());
		
		accountingService.modifyVendor(vendor);
	}
	
	//거래처 사용상태 변경
	@RequestMapping(value = "/accounting/convertVendorUsageStatus", method = RequestMethod.POST)
	public void convertVendorUsageStatus(@RequestBody List<Object> objectList) throws Exception{
		
		System.out.println("/accounting/convertVendorUsageStatus");
		System.out.println("들어온 거래처 번호 :: "+objectList);
		
		List<Vendor> vendorList = new ArrayList<Vendor>();
		for(int i = 0; i<objectList.size(); i++) {
			Vendor vendor = new Vendor();
			vendor.setVendorNo((String)objectList.get(i));
			vendor.setVendorUsageStatusCodeNo("02");
			vendorList.add(vendor);
		}
		System.out.println(vendorList);
		accountingService.convertVendorUsageStatus(vendorList);
	}
	
	//거래처 목록 가져오기
	@RequestMapping(value = "/accounting/getVendorList", method = RequestMethod.POST)
	public List<Vendor> getVendorList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getVendorList");
		System.out.println("search 정보 :: "+search);
		
		List<Vendor> list = accountingService.getVendorList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//계좌 등록
	@RequestMapping(value = "/accounting/addAccount", method = RequestMethod.POST)
	public void addAccount(@RequestBody Account account) throws Exception{
		
		System.out.println("/accounting/addAccount");
		
		accountingService.addAccount(account);
	}
	
	//계좌 상세조회
	@RequestMapping(value = "/accounting/getAccountDetail/{accountRegNo}", method = RequestMethod.GET)
	public Account getAccountDetail(@PathVariable String accountRegNo) throws Exception{
		
		System.out.println("/accounting/getAccountDetail");
		System.out.println(accountRegNo);
		
		Account account = accountingService.getAccountDetail(accountRegNo);
		
		return account;
	}
	
	//계좌 수정
	@RequestMapping(value = "/accounting/modifyAccount", method = RequestMethod.POST)
	public void modifyAccount(@RequestBody Account account) throws Exception{
		
		System.out.println("/accounting/modifyAccount");
		
		accountingService.modifyAccount(account);
	}
	
	//계좌 사용상태 수정
	@RequestMapping(value = "/accounting/convertAccountUsageStatus", method = RequestMethod.POST)
	public void convertAccountUsageStatus(@RequestBody List<Object> objectList) throws Exception{
		
		System.out.println("/accounting/convertAccountUsageStatus");
		System.out.println("들어온 거래처 번호 :: "+objectList);
		
		List<Account> accountList = new ArrayList<Account>();
		for(int i = 0; i<objectList.size(); i++) {
			Account account = new Account();
			account.setAccountRegNo((String)objectList.get(i));
			account.setAccountUsageStatusCodeNo("02");
			accountList.add(account);
		}
		accountingService.convertAccountUsageStatus(accountList);
	}
	
	//계좌 목록조회
	@RequestMapping(value = "/accounting/getAccountList", method = RequestMethod.POST)
	public List<Account> getAccountList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getAccountList");
		System.out.println("search 정보 :: "+search);
		
		List<Account> list = accountingService.getAccountList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//계좌번호 중복체크
	@RequestMapping(value = "/accounting/checkDuplicateAccount/{accountNo}", method = RequestMethod.GET)
	public boolean checkDuplicateAccount(@PathVariable String accountNo) throws Exception{
		
		System.out.println("/accounting/checkDuplicateAccount");
		System.out.println("들어온 accountNo ::"+accountNo);
		
		int checkAccount = accountingService.checkDuplicateAccount(accountNo);
		
		if(checkAccount == 0) {
			return true;
		}else {
			return false;
		}
				
	}
	
	//급여 등록
	@RequestMapping(value = "/accounting/addSalary/{salaryDate}", method = RequestMethod.GET)
	public void addSalary(@PathVariable String salaryDate) throws Exception{
		
		System.out.println("/accounting/addSalary");
		System.out.println("들어온 salaryDate :: "+salaryDate);
		salaryDate = salaryDate.substring(0, 4)+"/"+salaryDate.substring(4);
		System.out.println(salaryDate);
		accountingService.addSalary(salaryDate);
	}
	
	//급여 상세조회
	@RequestMapping(value = "/accounting/getSalaryDetail/{salaryNumbering}", method = RequestMethod.GET)
	public Salary getSalaryDetail(@PathVariable String salaryNumbering) throws Exception{
		
		System.out.println("/accounting/getSalaryDetail");
		System.out.println("들어온 salaryNumbering :: "+salaryNumbering);
		
		Salary salary = accountingService.getSalaryDetail(salaryNumbering);
		
		return salary;
	}
	
	//급여 수정
	@RequestMapping(value = "/accounting/modifySalary", method = RequestMethod.POST)
	public void modifySalary(@RequestBody Salary salary) throws Exception{
		
		System.out.println("/accounting/modifySalary");
		
		accountingService.modifySalary(salary);
	}
	
	//급여 상태 수정
	@RequestMapping(value = "/accounting/modifySalaryStatus", method = RequestMethod.POST)
	public void modifySalaryStatus(@RequestBody Salary salary) throws Exception{
		
		System.out.println("/accounting/modifySalaryStatus");
		
		accountingService.modifySalaryStatus(salary);
	}
	
	//급여 목록조회
	@RequestMapping(value = "/accounting/getSalaryList", method = RequestMethod.POST)
	public List<Salary> getSalaryList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getSalaryList");
		System.out.println("search 정보 :: "+search);
		
		List<Salary> list = accountingService.getSalaryList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//급여월 중복체크
	@RequestMapping(value = "/accounting/checkDuplicateSalaryDate/{salaryDate}", method = RequestMethod.GET)
	public boolean checkDuplicateSalaryDate(@PathVariable String salaryDate) throws Exception{
		
		System.out.println("/accounting/checkDuplicateSalaryDate");
		System.out.println("들어온 salaryDate ::"+salaryDate);
		salaryDate = salaryDate.substring(0, 4)+"/"+salaryDate.substring(4);
		System.out.println(salaryDate);
		int checkSalaryDate = accountingService.checkDuplicateSalaryDate(salaryDate);
		
		if(checkSalaryDate == 0) {
			return true;
		}else {
			return false;
		}
				
	}
	
	//급여대장 목록조회
	@RequestMapping(value = "/accounting/getSalaryBookList", method = RequestMethod.POST)
	public List<SalaryBook> getSalaryBookList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getSalaryBookList");
		System.out.println("search 정보 :: "+search);
		
		List<SalaryBook> list = accountingService.getSalaryBookList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//전표 등록
	@RequestMapping(value = "/accounting/addStatement", method = RequestMethod.POST)
	public void addStatement(@RequestBody Statement statement) throws Exception{
		
		System.out.println("/accounting/addStatement");
		
		//특수문자 변환
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"; 
		statement.setTradeAmount(statement.getTradeAmount().replaceAll(match, ""));
		
		accountingService.addStatement(statement);
	}
	
	//전표 상세조회
	@RequestMapping(value = "/accounting/getStatementDetail/{statementNo}", method = RequestMethod.GET)
	public Statement getStatementDetail(@PathVariable String statementNo) throws Exception{
		
		System.out.println("/accounting/getStatementDetail");
		System.out.println("들어온 statementNo :: "+statementNo);
		
		Statement statement = accountingService.getStatementDetail(statementNo);
		
		return statement;
	}
	
	//전표 수정
	@RequestMapping(value = "/accounting/modifyStatement", method = RequestMethod.POST)
	public void modifyStatement(@RequestBody Statement statement) throws Exception{
		
		System.out.println("/accounting/modifyStatement");
		
		//특수문자 변환
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"; 
		statement.setTradeAmount(statement.getTradeAmount().replaceAll(match, ""));
		
		accountingService.modifyStatement(statement);
	}
	
	//전표 사용상태 수정
	@RequestMapping(value = "/accounting/convertStatementUsageStatus", method = RequestMethod.POST)
	public void convertStatementUsageStatus(@RequestBody List<Object> objectList) throws Exception{
		
		System.out.println("/accounting/convertStatementUsageStatus");
		
		
		accountingService.convertStatementUsageStatus(objectList);
	}
	
	//전표 목록조회
	@RequestMapping(value = "/accounting/getStatementList", method = RequestMethod.POST)
	public List<Statement> getStatementList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getStatementList");
		System.out.println("search 정보 :: "+search);
		
		List<Statement> list = accountingService.getStatementList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//카드 등록
	@RequestMapping(value = "/accounting/addCard", method = RequestMethod.POST)
	public void addCard(@RequestBody Card card) throws Exception{
		
		System.out.println("/accounting/addCard");
		System.out.println("들어론 카드 정보 :: "+card);
		System.out.println(card.getCardImageFile());
		
		if(card.getCardImageFile() != null) {
			Map<String, Object> cardImageMap = card.getCardImageFile();
			card.setCardImage(ImageFileUpload.fileUpload(cardImageMap));
		}
		accountingService.addCard(card);
	}
	
	//카드 상세조회
	@RequestMapping(value = "/accounting/getCardDetail/{cardRegNo}", method = RequestMethod.GET)
	public Card getCardDetail(@PathVariable String cardRegNo) throws Exception{
		
		System.out.println("/accounting/getCardDetail");
		System.out.println("들어온 statementNo :: "+cardRegNo);
		
		Card card = accountingService.getCardDetail(cardRegNo);
		
		return card;
	}
	
	//카드 수정
	@RequestMapping(value = "/accounting/modifyCard", method = RequestMethod.POST)
	public void modifyCard(@RequestBody Card card) throws Exception{
		
		System.out.println("/accounting/modifyCard");
		System.out.println("들어론 카드 정보 :: "+card);
		System.out.println(card.getCardImageFile());
		
		if(card.getCardImageFile() != null) {
			Map<String, Object> cardImageMap = card.getCardImageFile();
			card.setCardImage(ImageFileUpload.fileUpload(cardImageMap));
		}
		
		accountingService.modifyCard(card);
	}
	
	//카드 사용상태 수정
	@RequestMapping(value = "/accounting/convertCardUsageStatus", method = RequestMethod.POST)
	public void convertCardUsageStatus(@RequestBody List<Object> objectList) throws Exception{
		
		System.out.println("/accounting/convertCardUsageStatus");
		System.out.println("들어온 카드 번호 :: "+objectList);
		
		List<Card> cardList = new ArrayList<Card>();
		for(int i = 0; i<objectList.size(); i++) {
			Card card = new Card();
			card.setCardRegNo((String)objectList.get(i));
			card.setCardUsageStatusCodeNo("02");
			cardList.add(card);
		}
		System.out.println(cardList);
		
		accountingService.convertCardUsageStatus(cardList);	
		
	}
	
	//카드 목록조회
	@RequestMapping(value = "/accounting/getCardList", method = RequestMethod.POST)
	public List<Card> getCardList(@RequestBody Search search) throws Exception{
		
		System.out.println("/accounting/getCardList");
		System.out.println("search 정보 :: "+search);
		
		List<Card> list = accountingService.getCardList(search);
		
		System.out.println("불러온 list 정보 :: "+list);
		
		return list;
	}
	
	//카드번호 중복조회
	@RequestMapping(value = "/accounting/checkDuplicateCard/{cardNo}", method = RequestMethod.GET)
	public boolean checkDuplicateCard(@PathVariable String cardNo) throws Exception{
		
		System.out.println("/accounting/checkDuplicateCard");
		System.out.println("들어온 cardNo ::"+cardNo);
		
		int checkCardNo = accountingService.checkDuplicateCard(cardNo);
		
		if(checkCardNo == 0) {
			return true;
		}else {
			return false;
		}
				
	}
	
	//급여대장 분석
	@RequestMapping(value = "/accounting/getAnalyzeSalaryBook/{salaryDate}", method = RequestMethod.GET)
	public List<Object> getAnalyzeSalaryBook(@PathVariable String salaryDate) throws Exception{

		System.out.println("/accounting/getAnalyzeSalaryBook");
		System.out.println("들어온 salaryDate ::"+salaryDate);
		salaryDate = salaryDate.substring(0, 4)+"/"+salaryDate.substring(4);
		System.out.println(salaryDate);
		
		List<SalaryBook> analyzeDepartmentSalary = accountingService.getAnalyzeDepartmentSalary(salaryDate);
		List<SalaryBook> analyzerankSalary = accountingService.getAnalyzeRankSalary(salaryDate);
		
		List<Object> analyzeSalaryBook = new ArrayList<Object>();
		analyzeSalaryBook.add(analyzeDepartmentSalary);
		analyzeSalaryBook.add(analyzerankSalary);
		
		return analyzeSalaryBook;
	}
}
