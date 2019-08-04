package test.mmh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Account;
import com.vision.erp.service.domain.Appointment;
import com.vision.erp.service.domain.Commute;
import com.vision.erp.service.domain.Department;
import com.vision.erp.service.domain.DutyHours;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.SimpleHumanResourceCard;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.domain.WorkAttitudeCode;
import com.vision.erp.service.humanresource.HumanResourceDAO;
import com.vision.erp.service.humanresource.HumanResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})
public class HumanResourceCardTest{

	@Resource(name = "humanResourceDAOImpl")
	private HumanResourceDAO humanResourceDAO;
	
	@Resource(name = "humanResourceServiceImpl")
	private HumanResourceService humanResourceService;

	
	private HumanResourceCard humanResourceCard;
	private Search search;
	private Account account;
	private SimpleHumanResourceCard simpleHumanResourceCard;
	private Appointment appointment;
	private WorkAttitude workAttitude;
	private WorkAttitudeCode workAttitudeCode;
	private Department department;
	private Commute commute;
	private DutyHours dutyHours;
	
	@Test
	public void testAddCommute() throws Exception{
		Commute commute;

		String employeeNo = "1000";
		
		String rndDate = "";
		String rndTime = "";
		String goToWorkTime = "";
		String leaveWorkTime = "";
		
		WorkAttitudeCode regularTimeCode 
 			= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("100");
		WorkAttitudeCode extendTimeCode
 			= humanResourceDAO.selectWorkAttitudeCodeByWorkAttitudeCodeNo("101");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		for(int i=1;i<=31;i++) {
			commute = new Commute();
			
			rndDate = "2019/07/";
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
			
			humanResourceService.addCommute(commute);
			
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
	
	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	
	//@Test
	public void testSelectHumanResourceCardList() throws Exception {
		
		search = new Search();
		search.setSearchKeyword("1020");
		
		List<HumanResourceCard> list 
				= (List<HumanResourceCard>)humanResourceDAO.selectHumanResourceCardList(search);
		
		for(int i = 0; i<list.size(); i++) {
			HumanResourceCard humanResourceCard = list.get(i);
			System.out.println(humanResourceCard);
		}
		
	}
	
	//@Test
	public void testInsertHumanResourceCard() throws Exception{
		
		humanResourceCard = new HumanResourceCard();
		account = new Account();
		
		//humanResourceCard.setAccount(account);
		humanResourceCard.setAddress("");
		humanResourceCard.setContractFile("");
		humanResourceCard.setDepartCodeNo("01");
		humanResourceCard.setDetailAddress("");
		humanResourceCard.setEmployeeEmail("qhdqhdekd261@gmail.com");
		humanResourceCard.setEmployeeName("");
		humanResourceCard.setEmployeePhone("010-2234-5566");
		humanResourceCard.setEmployeeTel("02-8897-2441");
		humanResourceCard.setJoinDate("2017/02/05");
		humanResourceCard.setProfileImage("");
		humanResourceCard.setRankCodeNo("03");
		humanResourceCard.setResignation("N");
		humanResourceCard.setRefer("");
		humanResourceCard.setSignatureImage("");
		humanResourceCard.setSsn("930424-1120394");
		humanResourceCard.setWage("13000");
		humanResourceCard.setZipCode("08790");
		
		//Insert
		humanResourceDAO.insertHumanResourceCard(humanResourceCard);
		
		//Select List
		search = new Search();
		
		List<HumanResourceCard> list 
				= (List<HumanResourceCard>)humanResourceDAO.selectHumanResourceCardList(search);
		
		for(int i = 0; i<list.size(); i++) {
			HumanResourceCard humanResourceCard = list.get(i);
			System.out.println(humanResourceCard);
		}
	}
	
	//@Test
	public void testSelectSimpleHumanResourceCardByEmployeeNo() throws Exception{
		
		simpleHumanResourceCard = 
				(SimpleHumanResourceCard)humanResourceDAO.selectSimpleHumanResourceCardByEmployeeNo("1001");
		
		System.out.println(simpleHumanResourceCard);
		
	}
	
	//@Test
	public void testUpdateHumanResourceCard() throws Exception{
		
		humanResourceCard = new HumanResourceCard();
		humanResourceCard = 
					(HumanResourceCard)humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo("1001");
		
		humanResourceCard.setEmployeeName("");
		
		//System.out.println("Test :: "+humanResourceCard);
		humanResourceDAO.updateHumanResourceCard(humanResourceCard);
		
	}
	
	//@Test
	public void testInsertAppointment() throws Exception{
		
		humanResourceCard = 
				(HumanResourceCard)humanResourceDAO.selectHumanResourceCardDetailByEmployeeNo("1001");
		
		appointment = new Appointment();
		appointment.setAppointDate("2019/02/03");
		appointment.setAppointDepartCodeNo("02");
		appointment.setAppointmentStatusCodeNo("02");
		appointment.setAppointRankCodeNo("03");
		appointment.setEmployeeNo(humanResourceCard.getEmployeeNo());
		appointment.setPreDepartCodeNo(humanResourceCard.getDepartCodeNo());
		appointment.setPreRankCodeNo(humanResourceCard.getRankCodeNo());
		appointment.setReference("");
		
		humanResourceDAO.insertAppointment(appointment);
		
	}
	
	//@Test
	public void testSelectAppoinmentList() throws Exception{
		
		search = new Search();
		search.setSearchKeyword("1001");
		
		List<Appointment> list = (List<Appointment>)humanResourceDAO.selectAppointmentList(search);
		
		for(int i = 0; i<list.size(); i++) {
			appointment = list.get(i);
			System.out.println(appointment);
		}
		
	}
	
	//@Test
	public void testUpdateAppointment() throws Exception{
		
		appointment = humanResourceDAO.selectAppointmentDetailByAppointNo("21");
		
		appointment.setAppointDepartCodeNo("04");
		
		humanResourceDAO.updateAppointment(appointment);
		
	}
	
	//@Test
	public void testUpdateAppointmentStatus() throws Exception{
		
		appointment = new Appointment();
		appointment.setAppointDepartCodeNo("04");
		
		//humanResourceDAO.updateAppointmentStatus("21", "01");
	}
	
	//@Test
	public void testInsertWorkAttitudeCode() throws Exception{
		
		workAttitudeCode = new WorkAttitudeCode();
		workAttitudeCode.setWorkAttitudeCodeName("소정근로");
		workAttitudeCode.setCommuteApplyCode("02");
		workAttitudeCode.setWorkType("01");
		workAttitudeCode.setWorkDayOfWeek("02");
		workAttitudeCode.setApplyStartTime("09:00");
		workAttitudeCode.setApplyEndTime("18:00");
		workAttitudeCode.setUsageStatusCode("01");
		
		humanResourceDAO.insertWorkAttitudeCode(workAttitudeCode);
	}
	
	//@Test
	public void testSelectWorkAttitudeCode() throws Exception{
		
		workAttitudeCode = new WorkAttitudeCode();
		workAttitudeCode.setWorkAttitudeCodeNo("100");
		workAttitudeCode.setWorkAttitudeCodeName("소정근로");
		workAttitudeCode.setCommuteApplyCode("02");
		workAttitudeCode.setWorkType("01");
		workAttitudeCode.setWorkDayOfWeek("02");
		workAttitudeCode.setApplyStartTime("09:00");
		workAttitudeCode.setApplyEndTime("18:00");
		workAttitudeCode.setUsageStatusCode("01");
		
		humanResourceDAO.updateWorkAttitudeCode(workAttitudeCode);
		
	}
	
	//@Test
	public void testSelectWorkAttitudeCodeList() throws Exception{
		
		search = new Search();
		search.setSearchKeyword("박");
		
		List<WorkAttitudeCode> list = humanResourceDAO.selectWorkAttitudeCodeList(search);
		
		for(int i = 0; i<list.size(); i++) {
			workAttitudeCode = list.get(i);
			System.out.println(workAttitudeCode);
		}
	}
	
	//@Test
	public void testUpdateWorkAttitudeCodeUsageStatus() throws Exception{
		
		//humanResourceDAO.updateWorkAttitudeCodeUsageStatus("100", "01");		
	}
	
	//@Test
	public void testInsertWorkAttitude() throws Exception{
		
		workAttitude = new WorkAttitude();
		workAttitude.setEmployeeNo("1001");
		workAttitude.setWorkAttitudeDate("2019/05/07");
		workAttitude.setWorkAttitudeNo("1");
		workAttitude.setWorkAttitudeCodeNo("101");
		workAttitude.setWorkAttitudeTime("60");
		workAttitude.setUsageStatusCodeNo("01");
		
		humanResourceDAO.insertWorkAttitude(workAttitude);
		
	}
	
	//@Test
	public void testSelectWorkAttitudeList() throws Exception{
		
		search = new Search();
		search.setSearchKeyword("");
		
		List<WorkAttitude> list = humanResourceDAO.selectWorkAttitudeList(search);
		
		for(int i = 0; i<list.size(); i++) {
			workAttitude = list.get(i);
			System.out.println(workAttitude);
		}
		
	}
	
	//@Test
	public void testUpdateWorkAttitude() throws Exception{
		
		
		workAttitude = new WorkAttitude();
		workAttitude.setEmployeeNo("1000");
		workAttitude.setWorkAttitudeDate("2019/05/07");
		workAttitude.setWorkAttitudeNo("1");
		workAttitude.setWorkAttitudeCodeNo("101");
		workAttitude.setWorkAttitudeTime("60");
		
		humanResourceDAO.updateWorkAttitude(workAttitude);
		
	}
	
	//@Test
	public void testInsertCommute() throws Exception{
		
		commute = new Commute();
		commute.setCommuteDate("2019/05/08");
		commute.setEmployeeNo("1002");
		commute.setGoToWorkTime("2019/05/09 08:50:30");
		humanResourceDAO.insertCommute(commute);
		
	}
	
	//@Test
	public void testUpdateCommuteForLeaveWorkTime() throws Exception{
		
		commute = new Commute();
		commute.setCommuteDate("2019/07/11");
		commute.setEmployeeNo("1002");
		commute.setLeaveWorkTime("2019/05/09 18:50:30");
		
		humanResourceDAO.updateCommuteForLeaveWorkTime(commute);
		
	}
	
	//@Test
	public void testInsertDutyHours() throws Exception{
		
		dutyHours = new DutyHours();
		dutyHours.setEmployeeNo("1001");
		dutyHours.setRegularWorkTime("540");
		dutyHours.setExtendWorkTime("50");
		dutyHours.setWorkDate("2019/05/07");
		
		humanResourceDAO.insertDutyHours(dutyHours);
	}
	
	//@Test
	public void testSelectDepartmentList() throws Exception{
		
		search = new Search();
		search.setSearchCondition("02");
		
		List<Department> list = humanResourceDAO.selectDepartmentList(search);
		
		for(int i = 0; i<list.size(); i++) {
			department = list.get(i);
			System.out.println(department);
		}
		
	}

}
