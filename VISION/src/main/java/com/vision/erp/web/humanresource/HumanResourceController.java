package com.vision.erp.web.humanresource;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.vision.erp.common.SendSMS;
import com.vision.erp.service.domain.Appointment;
import com.vision.erp.service.domain.Commute;
import com.vision.erp.service.domain.Department;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.SMS;
import com.vision.erp.service.domain.SimpleHumanResourceCard;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.domain.WorkAttitudeCode;
import com.vision.erp.service.humanresource.HumanResourceService;


@RestController
public class HumanResourceController {

	@Resource(name = "humanResourceServiceImpl")
	private HumanResourceService humanResourceService;
	
	@RequestMapping(value = "/hr/addHumanResourceCard",method = RequestMethod.POST)
	public void addHumanResourceCard(@RequestBody HumanResourceCard humanResourceCard) throws Exception{
		System.out.println("/hr/addHumanResourceCard");
		
		humanResourceService.addHumanResourceCard(humanResourceCard);
		
	}
	
	@RequestMapping(value = "/hr/getHumanResourceCardList", method = RequestMethod.POST)
	public List<HumanResourceCard> getHumanResourceCardList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getHumanResourceCardList");
		
		List<HumanResourceCard> list 
				= humanResourceService.getHumanResourceCardList(search);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/getHumanResourceCardDetail/{employeeNo}", method = RequestMethod.GET)
	public HumanResourceCard getHumanResourceCardDetail(@PathVariable String employeeNo) throws Exception{
		System.out.println("hr/getHumanResourceCardDetail");
		
		
		HumanResourceCard humanResourceCard
					= humanResourceService.getHumanResourceCardDetailByEmployeeNo(employeeNo);
	
		return humanResourceCard;
	}
	
	@RequestMapping(value = "/hr/getSimpleHumanResourceCardList", method = RequestMethod.POST)
	public List<SimpleHumanResourceCard> getSimpleHumanResourceCardList(
									@RequestBody Search search) throws Exception{
		
		List<SimpleHumanResourceCard> list
					= humanResourceService.getSimpleHumanResourceCardList(search);
		
		
		return list;
	}
	
	@RequestMapping(value = "/hr/getSimpleHumanResourceCardDetail/{employeeNo}",method = RequestMethod.GET)
	public SimpleHumanResourceCard getSimpleHumanResourceCardDetail(
									@PathVariable String employeeNo) throws Exception{
		System.out.println("hr/getSimpleHumanResourceCardDetail/");
		
		SimpleHumanResourceCard simpleHumanResourceCard
					= humanResourceService.getSimpleHumanResourceCardByEmployeeNo(employeeNo);
		
		return simpleHumanResourceCard;
	}
	
	@RequestMapping(value = "/hr/modifyHumanResourceCard",method = RequestMethod.POST)
	public void modifyHumanResourceCard(@RequestBody HumanResourceCard humanResourceCard) throws Exception{
		System.out.println("hr/modifyHumanResourceCard");

		
		humanResourceService.modifyHumanResourceCard(humanResourceCard);
		
	}
	
	@RequestMapping(value = "/hr/addWorkAttitude", method = RequestMethod.POST)
	public void addWorkAttitude(@RequestBody WorkAttitude workAttitude) throws Exception{
		System.out.println("/hr/addWorkAttitude");
		
		humanResourceService.addWorkAttitude(workAttitude);
	}
	
	@RequestMapping(value = "/hr/getWorkAttitudeList", method = RequestMethod.POST)
	public List<WorkAttitude> getWorkAttitudeList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getWorkAttitudeList");
		
		List<WorkAttitude> list 
				=	humanResourceService.getWorkAttitudeList(search);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/modifyWorkAttitude", method = RequestMethod.POST)
	public void modifyWorkAttitude(@RequestBody WorkAttitude workAttitude) throws Exception{
		System.out.println("/hr/modifyWorkAttitude");
		
		humanResourceService.modifyWorkAttitude(workAttitude);
	}
	
	//Sample Data : [{"workAttitudeNo":"1"},{"usageStatus":"01"}]
	@RequestMapping(value = "/hr/convertWorkAttitudeUsageStatus", method = RequestMethod.POST)
	public void convertWorkAttitudeUsageStatus(@RequestBody List<Object> objList) throws Exception{
		System.out.println("/hr/convertWorkAttitudeUsageStatus");
		List<WorkAttitude> workAttitudeList = new ArrayList<WorkAttitude>();
		for(int i = 0; i<objList.size(); i++) {
			WorkAttitude workAttitude = new WorkAttitude();
			workAttitude.setWorkAttitudeNo((String)objList.get(i));
			workAttitude.setUsageStatusCodeNo("02");
			workAttitudeList.add(workAttitude);
		}
		
		humanResourceService.convertWorkAtttidueUsageStatus(workAttitudeList);
	}
	
	@RequestMapping(value = "/hr/addWorkAttitudeCode", method = RequestMethod.POST)
	public void addWorkAttitudeCode(
				@RequestBody WorkAttitudeCode workAttitudeCode) throws Exception{
		System.out.println("/hr/addWorkAttitudeCode");
		
		humanResourceService.addWorkAttitudeCode(workAttitudeCode);
		
	}
	
	@RequestMapping(value = "/hr/getWorkAttitudeCodeDetail/{workAttitudeCodeNo}", method = RequestMethod.GET)
	public WorkAttitudeCode getWorkAttitudeCodeDetail(@PathVariable String workAttitudeCodeNo) throws Exception{
		System.out.println("/hr/getWorkAttitudeCodeDetail/{workAttitudeCodeNo}");
		
		return humanResourceService.getWorkAttitudeCodeByWorkAttitudeCodeNo(workAttitudeCodeNo);
	}
	
	@RequestMapping(value = "/hr/getWorkAttitudeCodeList", method = RequestMethod.POST)
	public List<WorkAttitudeCode> getWorkAttitudeCodeList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getWorkAttitudeCodeList");
		
		List<WorkAttitudeCode> list = humanResourceService.getWorkAttitudeCodeList(search);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/modifyWorkAttitudeCode", method = RequestMethod.POST)
	public void modifyWorkAttitudeCode(@RequestBody WorkAttitudeCode workAttitudeCode) throws Exception{
		System.out.println("/hr/modifyWorkAttitudeCode");
		
		humanResourceService.modifyWorkAttitudeCode(workAttitudeCode);
	}
	
	@RequestMapping(value = "/hr/convertWorkAttitudeCodeUsageStatus", method = RequestMethod.POST)
	public void convertWorkAttitudeCodeUsageStatus(@RequestBody List<Object> objList) throws Exception{
		System.out.println("/hr/convertWorkAttitudeCodeUsageStatus");
		
		List<WorkAttitudeCode> workAttitudeCodeList = new ArrayList<WorkAttitudeCode>();
		
		for(int i = 0; i < objList.size(); i++) {
			WorkAttitudeCode workAttitudeCode = new WorkAttitudeCode();
			workAttitudeCode.setWorkAttitudeCodeNo((String)objList.get(i));
			workAttitudeCode.setUsageStatusCodeNo("02");
			workAttitudeCodeList.add(workAttitudeCode);
		}
		
		humanResourceService.convertWorkAttitudeCodeUsageStatus(workAttitudeCodeList);
	}
	
	@RequestMapping(value = "/hr/addAppointment", method = RequestMethod.POST)
	public void addAppointment(@RequestBody Appointment appointment) throws Exception{
		System.out.println("/hr/addAppointment");
		
		if(appointment.getReference() == null) {
			appointment.setReference("ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½");
		}
		humanResourceService.addAppointment(appointment);
	}
	
	@RequestMapping(value = "/hr/getAppointmentList", method = RequestMethod.POST)
	public List<Appointment> getAppointmentList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getAppointmentList");
		
		List<Appointment> list 
					= humanResourceService.getAppointmentList(search);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/modifyAppointment", method = RequestMethod.POST)
	public void modifyAppointment(@RequestBody Appointment appointment) throws Exception{
		System.out.println("/hr/modifyAppointment");
		
		humanResourceService.modifyAppointment(appointment);
	}
	
	//Sample Data : [{"appointmentNo":"1"},{"status":"02"}]
	@RequestMapping(value = "/hr/modifyAppointmentStatus", method = RequestMethod.POST)
	public void modifyAppointmentStatus(@RequestBody Appointment appointment) throws Exception{
		System.out.println("/hr/modifyAppointmentStatus");
		
		if(appointment.getAppointmentStatusCodeNo().equals("02")) {
			System.out.println("02ÀÔ´Ï´Ù");
			SMS sms = new SMS();
			String reciever = humanResourceService.getHumanResourceCardDetailByEmployeeNo
													(appointment.getEmployeeNo()).getEmployeePhone();
			String content = appointment.getEmployeeName()+"´ÔÀº "+"¹ß·É³¯Â¥("+appointment.getAppointDate()
							+")¿¡ "+appointment.getAppointDepartCodeName()+", "+appointment.getAppointRankCodeName()
							+"À¸·Î ¹ß·ÉµÇ¾ú½À´Ï´Ù. ";
			
			sms.setSender("010-4468-0115");
			sms.setReciever(reciever.replaceAll("-", ""));
			sms.setContent(content);
			
			SendSMS sendSMS = SendSMS.getSendSMSInstance();
			sendSMS.sendSMS(sms);
		}
	
		humanResourceService.convertAppointmentStatus(appointment);
	}
	
	@RequestMapping(value = "/hr/removeAppointment", method = RequestMethod.GET)
	public void removeAppointment(@RequestBody Appointment appointment) throws Exception{
		
	}
	
	@RequestMapping(value = "/hr/addDepartment", method = RequestMethod.POST)
	public void addDepartment(@RequestBody Department department) throws Exception{
		System.out.println("/hr/addDepartment");
		
		humanResourceService.addDepartment(department);
	}
	
	@RequestMapping(value = "/hr/getDepartmentList", method = RequestMethod.POST)
	public List<Department> getDepartmentList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getDepartmentList");
		
		List<Department> list = humanResourceService.getDepartmentList(search);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/modifyDepartment", method = RequestMethod.POST)
	public void modifyDepartment(@RequestBody Department department) throws Exception{
		System.out.println("/hr/modifyDepartment");
		
		humanResourceService.modifyDepartment(department);
	}
	
	@RequestMapping(value = "/hr/convertDepartmentUsageStatus", method = RequestMethod.POST)
	public void convertDepartmentUsageStatus(@RequestBody Department department) throws Exception{
		System.out.println("/hr/convertDepartmentUsageStatus");
		
		humanResourceService.convertDepartmentUsageStatus(department);
	}
	
	@RequestMapping(value = "/hr/getCommuteList/{employeeNo}", method = RequestMethod.GET)
	public List<Commute> getCommuteList(@PathVariable String employeeNo) throws Exception{
		System.out.println("/hr/getCommuteList");
		
		List<Commute> list = humanResourceService.getCommuteListByEmployeeNo(employeeNo);
		
		return list;
	}
	
	@RequestMapping(value = "/hr/addCommute", method = RequestMethod.POST)
	public void addCommute(@RequestBody Commute commute) throws Exception{
		System.out.println("/hr/addCommute");
		
		humanResourceService.addCommute(commute);
	}
	
	@RequestMapping(value = "/hr/updateLeaveWorkTime", method = RequestMethod.POST)
	public void updateLeaveWorkTime(@RequestBody Commute commute) throws Exception{
		System.out.println("/hr/updateLeaveWorkTime");
		
		
		
		humanResourceService.modifyCommuteForLeaveWorkTime(commute);
	}
	
	@RequestMapping(value = "/hr/sampleCommuteData", method = RequestMethod.POST)
	public void sampleCommuteData(@RequestBody Map<String, String> paramMap) throws Exception{
		System.out.println("/hr/sampleCommuteData");
		
		int untilMonth = Integer.parseInt(paramMap.get("untilMonth"));
		String employeeNo = paramMap.get("employeeNo");
		
		System.out.println("untilMonth :: "+untilMonth+", employeeNo :: "+employeeNo);
		
		humanResourceService.getMakeCommuteSample(untilMonth, employeeNo);
		
	}
}
