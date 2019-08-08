package com.vision.erp.service.humanresource.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.ImageFileUpload;
import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.code.CodeDAO;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Appointment;
import com.vision.erp.service.domain.Code;
import com.vision.erp.service.domain.Commute;
import com.vision.erp.service.domain.Department;
import com.vision.erp.service.domain.DutyHours;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.SimpleHumanResourceCard;
import com.vision.erp.service.domain.User;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.domain.WorkAttitudeCode;
import com.vision.erp.service.humanresource.HumanResourceDAO;
import com.vision.erp.service.humanresource.HumanResourceService;
import com.vision.erp.service.user.UserDAO;

@Service("humanResourceServiceImpl")
public class HumanResourceServiceImpl implements HumanResourceService {

	@Resource(name = "humanResourceDAOImpl")
	private HumanResourceDAO humanResourceDAO;
	
	@Resource(name = "accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	@Resource(name = "userDAOImpl")
	private UserDAO userDAO;
	
	@Resource(name = "codeDAOImpl")
	private CodeDAO codeDAO;
	
	
	@Override
	public void addHumanResourceCard(HumanResourceCard humanResourceCard) throws Exception {
		
		//시급 콤마 제거
		humanResourceCard.setWage(humanResourceCard.getWage().replaceAll(",", ""));
		
		//파일 업로
		Map<String, Object> profileMap = humanResourceCard.getProfileFile();
		Map<String, Object> signatureMap = humanResourceCard.getSignatureFile();
		
		if(profileMap != null) {
			humanResourceCard.setProfileImage(ImageFileUpload.fileUpload(profileMap));
		}
		if(signatureMap != null) {
			humanResourceCard.setSignatureImage(ImageFileUpload.fileUpload(signatureMap));
		}
		
		Account account = humanResourceCard.getAccount();
		account.setAccountHolder(humanResourceCard.getEmployeeName());
		account.setAccountCategoryCodeNo("05");
		account.setReference("참고없음");
		account.setAccountNo(account.getAccountNo().replaceAll("-", ""));
		accountingDAO.insertAccount(account);
		
		
		humanResourceCard.setAccount(account);
		humanResourceCard.setResignation("N");
		humanResourceDAO.insertHumanResourceCard(humanResourceCard);
		
		
		User user = new User();
		user.setEmployeeNo(humanResourceCard.getEmployeeNo());
		user.setAccessMenuCodeNo(humanResourceCard.getDepartCodeNo());
		user.setMemberCodeNo("01");
		user.setMemberUsageStatusCodeNo("01");
		user.setPassword("0000");
		user.setUserId("U"+humanResourceCard.getEmployeeNo());
		user.setProfileImage(humanResourceCard.getProfileImage());
		userDAO.insertUser(user);
	}

	@Override
	public List<HumanResourceCard> getHumanResourceCardList(Search search) throws Exception {
		return humanResourceDAO.selectHumanResourceCardList(search);
	}

	@Override
	public HumanResourceCard getHumanResourceCardDetailByEmployeeNo(String employeeNo) throws Exception {
		return humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo(employeeNo);
	}

	@Override
	public SimpleHumanResourceCard getSimpleHumanResourceCardByEmployeeNo(String employeeNo) throws Exception {
		return humanResourceDAO.selectSimpleHumanResourceCardByEmployeeNo(employeeNo);
	}

	@Override
	public List<SimpleHumanResourceCard> getSimpleHumanResourceCardList(Search search) throws Exception {
		return humanResourceDAO.selectSimpleHumanResourceCardList(search);
	}

	@Override
	public void modifyHumanResourceCard(HumanResourceCard humanResourceCard) throws Exception {
		
		Map<String, Object> profileMap = humanResourceCard.getProfileFile();
		Map<String, Object> signatureMap = humanResourceCard.getSignatureFile();
		
		if(profileMap != null) {
			humanResourceCard.setProfileImage(ImageFileUpload.fileUpload(profileMap));
		}
		if(signatureMap != null) {
			humanResourceCard.setSignatureImage(ImageFileUpload.fileUpload(signatureMap));
		}
		
		humanResourceDAO.updateHumanResourceCard(humanResourceCard);		
	}

	@Override
	public void addWorkAttitude(WorkAttitude workAttitude) throws Exception {
		workAttitude.setUsageStatusCodeNo("01");
		humanResourceDAO.insertWorkAttitude(workAttitude);
	}

	@Override
	public List<WorkAttitude> getWorkAttitudeList(Search search) throws Exception {
		return humanResourceDAO.selectWorkAttitudeList(search);
	}

	@Override
	public void modifyWorkAttitude(WorkAttitude workAttitude) throws Exception {
		humanResourceDAO.updateWorkAttitude(workAttitude);
	}

	@Override
	public void convertWorkAtttidueUsageStatus(List<WorkAttitude> workAttitudeList) throws Exception {
		humanResourceDAO.updateWorkAttitudeUsageStatus(workAttitudeList);
	}

	@Override
	public void addWorkAttitudeCode(WorkAttitudeCode workAttitudeCode) throws Exception {
		workAttitudeCode.setUsageStatusCode("01");
		humanResourceDAO.insertWorkAttitudeCode(workAttitudeCode);
	}
	
	@Override
	public WorkAttitudeCode getWorkAttitudeCodeByWorkAttitudeCodeNo(String workAttitudeCodeNo) throws Exception {
		
		return humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo(workAttitudeCodeNo);
	}

	@Override
	public List<WorkAttitudeCode> getWorkAttitudeCodeList(Search search) throws Exception {
		return humanResourceDAO.selectWorkAttitudeCodeList(search);
	}

	@Override
	public void modifyWorkAttitudeCode(WorkAttitudeCode workAttitudeCode) throws Exception {
		humanResourceDAO.updateWorkAttitudeCode(workAttitudeCode);
	}

	@Override
	public void convertWorkAttitudeCodeUsageStatus(List<WorkAttitudeCode> workAttitudeCodeList) throws Exception {
		humanResourceDAO.updateWorkAttitudeCodeUsageStatus(workAttitudeCodeList);
	}

	@Override
	public void addAppointment(Appointment appointment) throws Exception {
		humanResourceDAO.insertAppointment(appointment);
	}

	@Override
	public List<Appointment> getAppointmentList(Search search) throws Exception {
		return humanResourceDAO.selectAppointmentList(search);
	}

	@Override
	public Appointment getAppointmentDetailByAppointNo(String appointNo) throws Exception {
		return humanResourceDAO.selectAppointmentDetailByAppointNo(appointNo);
	}

	@Override
	public void modifyAppointment(Appointment appointment) throws Exception {
		humanResourceDAO.updateAppointment(appointment);
	}

	@Override
	public void convertAppointmentStatus(Appointment appointment) throws Exception {
		humanResourceDAO.updateAppointmentStatus(appointment);
	}

	@Override
	public void addDepartment(Department department) throws Exception {
		
		Code code = new Code();
		code.setGroupCode("depart");
		code.setGroupCodeName("부서");
		code.setCodeNo(department.getDepartCodeNo());
		code.setCodeName(department.getDepartCodeName());
		codeDAO.insertCode(code);
		
		department.setDepartUsageStatusCodeNo("01");
		humanResourceDAO.insertDepartment(department);
	}

	@Override
	public List<Department> getDepartmentList(Search search) throws Exception {
		return humanResourceDAO.selectDepartmentList(search);
	}

	@Override
	public void modifyDepartment(Department department) throws Exception {
		Code code = new Code();
		code.setGroupCode("depart");
		code.setCodeNo(department.getDepartCodeNo());
		code.setCodeUsageStatus("N");
		codeDAO.updateCodeUsageStatus(code);
		
		department.setDepartUsageStatusCodeNo("02");
		humanResourceDAO.updateDepartmentUsageStatus(department);
	}

	@Override
	public void convertDepartmentUsageStatus(Department department) throws Exception {
		humanResourceDAO.updateDepartmentUsageStatus(department);
	}

	@Override
	public List<Commute> getCommuteListByEmployeeNo(String employeeNo) throws Exception {
		return humanResourceDAO.selectCommuteList(employeeNo);
	}

	@Override
	public void addCommute(Commute commute) throws Exception {
		

		SimpleDateFormat format = new SimpleDateFormat ("YYYY-MM-dd HH:mm:ss");
		SimpleDateFormat commuteFormat = new SimpleDateFormat("YYYY/MM/dd");
	    String date = format.format (System.currentTimeMillis());
	    String commuteDate = commuteFormat.format(System.currentTimeMillis());
		commute.setGoToWorkTime(date);
		commute.setCommuteDate(commuteDate);
		
		humanResourceDAO.insertCommute(commute);
	}

	@Override
	public void modifyCommuteForLeaveWorkTime(Commute commute) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String LeaveWorkDate = format.format (System.currentTimeMillis());
	    String currentDate = DateFormat.format(System.currentTimeMillis());
	    
	    
	    WorkAttitudeCode regularTimeCode 
	    		= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("100");
	    WorkAttitudeCode extendTimeCode
	    		= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("101");
	    
	    Date date = new Date();
	    
	    date =format.parse(currentDate+" "+regularTimeCode.getApplyStartTime()+":00");
	    int regularStartInt = (int)(date.getTime()/1000);
	    
	    date = format.parse(currentDate+" "+regularTimeCode.getApplyEndTime()+":00");
	    int regularEndInt = (int)(date.getTime()/1000);
	    
	    System.out.println("regularStartInt :: "+regularStartInt+" regularEndInt :: "+regularEndInt);
	    
	    date =format.parse(currentDate+" "+extendTimeCode.getApplyStartTime()+":00");
	    int extendStartInt = (int)(date.getTime()/1000);
	    
	    date =format.parse(currentDate+" "+extendTimeCode.getApplyEndTime()+":00");
	    int extendEndInt = (int)(date.getTime()/1000);
	    	   
	    int intGoToWork = (int) (format.parse(commute.getGoToWorkTime()).getTime()/1000);
	    int intLeaveWork = (int)(new Date().getTime()/1000);
	    
	    System.out.println("intGoToWork :: "+intGoToWork+" intLeaveWork :: "+intLeaveWork);
	
	    int regularDutyHours=0;
	    int extendDutyHours=0;
	    if(intGoToWork - regularStartInt < 0) {
	    	if((regularEndInt-regularStartInt) < (intLeaveWork-regularStartInt)) {
	    		 regularDutyHours = regularEndInt - regularStartInt;
	    		 extendDutyHours = intLeaveWork - regularEndInt;
	    	}else {
	    		 regularDutyHours = intLeaveWork - regularStartInt;
	    	}
	    }else {
	    	if(regularEndInt-intGoToWork < intLeaveWork-intGoToWork) {
	    		 regularDutyHours = regularEndInt - intGoToWork;
	    		 extendDutyHours = intLeaveWork - regularEndInt;
	    	}else {
	    		 regularDutyHours = intLeaveWork - intGoToWork; 
	    	}
	    }
	    
	    if(extendDutyHours > 0) {
	    	WorkAttitude workAttitude = new WorkAttitude();
	    	workAttitude.setEmployeeNo(commute.getEmployeeNo());
	    	workAttitude.setWorkAttitudeCodeNo("101");
	    	workAttitude.setWorkAttitudeDate(commute.getCommuteDate());
	    	workAttitude.setWorkAttitudeTime(Integer.toString((int)(Math.ceil((double)extendDutyHours/60))));
	    	workAttitude.setUsageStatusCodeNo("01");
	    	humanResourceDAO.insertWorkAttitude(workAttitude);
	    }
	    
	    
	    System.out.println("regularDutyHours :: "+(int)(Math.ceil((double)regularDutyHours/60))+" extendDutyHours :: "+extendDutyHours/60);
	    
	    DutyHours dutyHours = new DutyHours();
	    dutyHours.setEmployeeNo(commute.getEmployeeNo());
	    dutyHours.setExtendWorkTime(Integer.toString((int)(Math.ceil((double)extendDutyHours/60))));
	    dutyHours.setRegularWorkTime(Integer.toString((int)(Math.ceil((double)regularDutyHours/60))));
	    dutyHours.setWorkDate(commute.getCommuteDate());
	    
	    humanResourceDAO.insertDutyHours(dutyHours);
	    
	    System.out.println("dutyHours "+dutyHours);
	    
		commute.setLeaveWorkTime(LeaveWorkDate);
		
		humanResourceDAO.updateCommuteForLeaveWorkTime(commute);
	}

	@Override
	public void addDutyHours(DutyHours dutyHours) throws Exception {
		humanResourceDAO.insertDutyHours(dutyHours);
	}

	@Override
	public void getMakeCommuteSample(int untilMonth, String employeeNo) throws Exception {
		Commute commute;

		
		String rndDate = "";
		String rndTime = "";
		String goToWorkTime = "";
		String leaveWorkTime = "";
		int month = 0;
		int endDate = 0;
		int[] monthEndDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		WorkAttitudeCode regularTimeCode 
				= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("100");
		WorkAttitudeCode extendTimeCode
				= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("101");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		
		
		for(int j=1;j<=untilMonth;j++) {
			
			month = j;
			endDate = monthEndDate[j-1];
			
			for(int i=1;i<=endDate;i++) {
				
				if(month == 1) {
					if(i == 5 || i == 6 || i == 12 || i == 13|| i == 19 || i == 20 || i == 26 || i == 27) {
						continue;
					}
				}else if(month == 2) {
					if(i == 2 || i == 3 || i == 9 || i == 10|| i == 16 || i == 17 || i == 23 || i == 24) {
						continue;
					}
				}else if(month == 3) {
					if(i == 2 || i == 3 || i == 9 || i == 10|| i == 16 || i == 17 || i == 23 || i == 24 || i == 30 || i == 31) {
						continue;
					}
				}else if(month == 4) {
					if(i == 6 || i == 7 || i == 13 || i == 14|| i == 20 || i == 21 || i == 27 || i == 28 ) {
						continue;
					}				
				}else if(month == 5) {
					if(i == 4 || i == 5 || i == 11 || i == 12|| i == 18 || i == 19 || i == 25 || i == 26 ) {
						continue;
					}	
				}else if(month == 6) {
					if(i == 1 || i == 2 || i == 8 || i == 9|| i == 15 || i == 16 || i == 22 || i == 23 || i == 29 || i == 30) {
						continue;
					}	
				}else if(month == 7) {
					if(i == 6 || i == 7 || i == 13 || i == 14|| i == 20 || i == 21 || i == 27 || i == 28 ) {
						continue;
					}	
				}
				
				commute = new Commute();
				
				rndDate = "2019/"+String.format("%02d", month)+"/";
				rndDate = rndDate + String.format("%02d", i);
				
				int hours = randomRange(8, 9);
				int minutes = 0;
				int seconds = randomRange(0, 59);
				
				if(hours == 8) {
					minutes = randomRange(47, 59);
				}else if(hours == 9) {
					minutes = randomRange(0, 10);
				}
				
				rndTime = String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds);
				
				goToWorkTime = rndDate + " " + rndTime;
				//System.out.println("date : "+rndDate + "  goToWorkTime :: "+goToWorkTime);
				
				commute.setEmployeeNo(employeeNo);
				commute.setGoToWorkTime(goToWorkTime);
				commute.setCommuteDate(rndDate);
				
				humanResourceDAO.insertCommute(commute);
				
				////////////////////////퇴근///////////////////////
				hours = randomRange(18, 21);
				minutes = randomRange(0, 59);;
				seconds = randomRange(0, 59);
				
				rndTime = String.format("%02d", hours)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds);
				
				leaveWorkTime = rndDate + " " + rndTime;
				System.out.println("date : "+rndDate + "\ngoToWorkTime :: " +goToWorkTime +"\n  leaveWorkTime :: "+leaveWorkTime);
				
				commute.setLeaveWorkTime(leaveWorkTime);
				
				Date date = new Date();
				
				date =format.parse(rndDate.replaceAll("/", "-")+" "+regularTimeCode.getApplyStartTime()+":00");
				int regularStartInt = (int)(date.getTime()/1000);
				
				date = format.parse(rndDate.replaceAll("/", "-")+" "+regularTimeCode.getApplyEndTime()+":00");
				int regularEndInt = (int)(date.getTime()/1000);
				
				int intGoToWork = (int) (format.parse((commute.getGoToWorkTime()).replaceAll("/", "-")).getTime()/1000);
				int intLeaveWork = (int)(format.parse((commute.getLeaveWorkTime()).replaceAll("/", "-")).getTime()/1000);
				
				int regularDutyHours=0;
				int extendDutyHours=0;
				
				System.out.println("regularStartInt :: "+regularStartInt+" regularEndInt :: "+regularEndInt);
				System.out.println("intGoToWork :: "+intGoToWork+" intLeaveWork :: "+intLeaveWork);
				
				if(intGoToWork - regularStartInt < 0) {
					if((regularEndInt-regularStartInt) < (intLeaveWork-regularStartInt)) {
						regularDutyHours = regularEndInt - regularStartInt;
						extendDutyHours = intLeaveWork - regularEndInt;
					}else {
						regularDutyHours = intLeaveWork - regularStartInt;
					}
				}else {
					if(regularEndInt-intGoToWork < intLeaveWork-intGoToWork) {
						regularDutyHours = regularEndInt - intGoToWork;
						extendDutyHours = intLeaveWork - regularEndInt;
					}else {
						regularDutyHours = intLeaveWork - intGoToWork; 
					}
				}
				
				if(extendDutyHours > 0) {
					WorkAttitude workAttitude = new WorkAttitude();
					workAttitude.setEmployeeNo(commute.getEmployeeNo());
					workAttitude.setWorkAttitudeCodeNo("101");
					workAttitude.setWorkAttitudeDate(commute.getCommuteDate());
					workAttitude.setWorkAttitudeTime(Integer.toString((int)(Math.ceil((double)extendDutyHours/60))));
					workAttitude.setUsageStatusCodeNo("01");
					humanResourceDAO.insertWorkAttitude(workAttitude);
				}
				
				System.out.println("regularDutyHours :: "+(int)(Math.ceil((double)regularDutyHours/60))+" extendDutyHours :: "+extendDutyHours/60);
				
				DutyHours dutyHours = new DutyHours();
				dutyHours.setEmployeeNo(commute.getEmployeeNo());
				dutyHours.setExtendWorkTime(Integer.toString((int)(Math.ceil((double)extendDutyHours/60))));
				dutyHours.setRegularWorkTime(Integer.toString((int)(Math.ceil((double)regularDutyHours/60))));
				dutyHours.setWorkDate(commute.getCommuteDate());
				
				humanResourceDAO.insertDutyHours(dutyHours);
				
				System.out.println("dutyHours "+dutyHours);
				
				humanResourceDAO.updateCommuteForLeaveWorkTime(commute);
				
			}
			
		}
		
		
		
	}
	
	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	

}
