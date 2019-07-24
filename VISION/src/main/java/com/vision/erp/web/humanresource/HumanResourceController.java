package com.vision.erp.web.humanresource;

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
import com.vision.erp.service.domain.Appointment;
import com.vision.erp.service.domain.Commute;
import com.vision.erp.service.domain.Department;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.SimpleHumanResourceCard;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.domain.WorkAttitudeCode;
import com.vision.erp.service.humanresource.HumanResourceService;


@RestController
public class HumanResourceController {

	@Resource(name = "humanResourceServiceImpl")
	private HumanResourceService humanResourceService;
	
	//�λ�ī�� ���
	@RequestMapping(value = "/hr/addHumanResourceCard",method = RequestMethod.POST)
	public void addHumanResourceCard(@RequestBody HumanResourceCard humanResourceCard) throws Exception{
		System.out.println("/hr/addHumanResourceCard");
		
		
		Map<String, Object> profileMap = humanResourceCard.getProfileFile();
		Map<String, Object> signatureMap = humanResourceCard.getSignatureFile();
		
		humanResourceCard.setProfileImage(ImageFileUpload.fileUpload(profileMap));
		humanResourceCard.setSignatureImage(ImageFileUpload.fileUpload(signatureMap));
		
		
		humanResourceService.addHumanResourceCard(humanResourceCard);
		
	}
	
	//�λ�ī�� �����ȸ
	@RequestMapping(value = "/hr/getHumanResourceCardList", method = RequestMethod.POST)
	public List<HumanResourceCard> getHumanResourceCardList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getHumanResourceCardList");
		System.out.println(search);
		
		List<HumanResourceCard> list 
				= humanResourceService.getHumanResourceCardList(search);
		
		System.out.println(list);
		
		return list;
	}
	
	//�λ�ī�� ����ȸ
	@RequestMapping(value = "/hr/getHumanResourceCardDetail/{employeeNo}", method = RequestMethod.GET)
	public HumanResourceCard getHumanResourceCardDetail(@PathVariable String employeeNo) throws Exception{
		System.out.println("hr/getHumanResourceCardDetail");
		
		System.out.println(employeeNo);
		
		HumanResourceCard humanResourceCard
					= humanResourceService.getHumanResourceCardDetailByEmployeeNo(employeeNo);
	
		return humanResourceCard;
	}
	
	//�λ�ī�� �����ȸ without �ΰ��� ����
	@RequestMapping(value = "/hr/getSimpleHumanResourceCardList", method = RequestMethod.POST)
	public List<SimpleHumanResourceCard> getSimpleHumanResourceCardList(
									@RequestBody Search search) throws Exception{
		
		List<SimpleHumanResourceCard> list
					= humanResourceService.getSimpleHumanResourceCardList(search);
		
		System.out.println(list);
		
		return list;
	}
	
	//�λ�ī�� ����ȸ without �ΰ��� ����
	@RequestMapping(value = "/hr/getSimpleHumanResourceCardDetail/{employeeNo}",method = RequestMethod.GET)
	public SimpleHumanResourceCard getSimpleHumanResourceCardDetail(
									@PathVariable String employeeNo) throws Exception{
		System.out.println("hr/getSimpleHumanResourceCardDetail/");
		
		SimpleHumanResourceCard simpleHumanResourceCard
					= humanResourceService.getSimpleHumanResourceCardByEmployeeNo(employeeNo);
		
		return simpleHumanResourceCard;
	}
	
	//�λ�ī�� ����
	@RequestMapping(value = "/hr/modifyHumanResourceCard",method = RequestMethod.POST)
	public void modifyHumanResourceCard(@RequestBody HumanResourceCard humanResourceCard) throws Exception{
		System.out.println("hr/modifyHumanResourceCard");
		
		humanResourceService.modifyHumanResourceCard(humanResourceCard);
		
	}
	
	//���� ���
	@RequestMapping(value = "/hr/addWorkAttitude", method = RequestMethod.POST)
	public void addWorkAttitude(@RequestBody WorkAttitude workAttitude) throws Exception{
		System.out.println("/hr/addWorkAttitude");
		
		humanResourceService.addWorkAttitude(workAttitude);
	}
	
	//���� �����ȸ
	@RequestMapping(value = "/hr/getWorkAttitudeList", method = RequestMethod.POST)
	public List<WorkAttitude> getWorkAttitudeList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getWorkAttitudeList");
		
		List<WorkAttitude> list 
				=	humanResourceService.getWorkAttitudeList(search);
		
		return list;
	}
	
	//���� ����
	@RequestMapping(value = "/hr/modifyWorkAttitude", method = RequestMethod.POST)
	public void modifyWorkAttitude(@RequestBody WorkAttitude workAttitude) throws Exception{
		System.out.println("/hr/modifyWorkAttitude");
		
		humanResourceService.modifyWorkAttitude(workAttitude);
	}
	
	//���� ������ ����
	//Sample Data : [{"workAttitudeNo":"1"},{"usageStatus":"01"}]
	@RequestMapping(value = "/hr/convertWorkAttitudeUsageStatus", method = RequestMethod.POST)
	public void convertWorkAttitudeUsageStatus(@RequestBody Map<String, String>[] arrayMap) throws Exception{
		System.out.println("/hr/convertWorkAttitudeUsageStatus");
		
		humanResourceService.convertWorkAtttidueUsageStatus(arrayMap[0].get("workAttitudeNo")
													, arrayMap[1].get("usageStatus"));
	}
	
	//�����ڵ� ���
	@RequestMapping(value = "/hr/addWorkAttitudeCode", method = RequestMethod.POST)
	public void addWorkAttitudeCode(
				@RequestBody WorkAttitudeCode workAttitudeCode) throws Exception{
		System.out.println("/hr/addWorkAttitudeCode");
		
		humanResourceService.addWorkAttitudeCode(workAttitudeCode);
		
	}
	
	//�����ڵ� ����ȸ
	@RequestMapping(value = "/hr/getWorkAttitudeCodeDetail/{workAttitudeCodeNo}", method = RequestMethod.GET)
	public WorkAttitudeCode getWorkAttitudeCodeDetail(@PathVariable String workAttitudeCodeNo) throws Exception{
		System.out.println("/hr/getWorkAttitudeCodeDetail/{workAttitudeCodeNo}");
		
		return humanResourceService.getWorkAttitudeCodeByWorkAttitudeCodeNo(workAttitudeCodeNo);
	}
	
	//�����ڵ� �����ȸ
	@RequestMapping(value = "/hr/getWorkAttitudeCodeList", method = RequestMethod.POST)
	public List<WorkAttitudeCode> getWorkAttitudeCodeList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getWorkAttitudeCodeList");
		
		List<WorkAttitudeCode> list = humanResourceService.getWorkAttitudeCodeList(search);
		
		return list;
	}
	
	//�����ڵ� ����
	@RequestMapping(value = "/hr/modifyWorkAttitudeCode", method = RequestMethod.POST)
	public void modifyWorkAttitudeCode(@RequestBody WorkAttitudeCode workAttitudeCode) throws Exception{
		System.out.println("/hr/modifyWorkAttitudeCode");
		
		humanResourceService.modifyWorkAttitudeCode(workAttitudeCode);
	}
	
	//�����ڵ� ������ ����
	@RequestMapping(value = "/hr/convertWorkAttitudeCodeUsageStatus", method = RequestMethod.GET)
	public void convertWorkAttitudeCodeUsageStatus(@RequestBody Map<String, String>[] arrayMap) throws Exception{
		System.out.println("/hr/convertWorkAttitudeCodeUsageStatus");
		
		humanResourceService.convertWorkAttitudeCodeUsageStatus(arrayMap[0].get("workAttitudeCodeNo"), arrayMap[1].get("usageStatus"));
	}
	
	//�߷ɵ��
	@RequestMapping(value = "/hr/addAppointment", method = RequestMethod.POST)
	public void addAppointment(@RequestBody Appointment appointment) throws Exception{
		System.out.println("/hr/addAppointment");
		
		if(appointment.getReference() == null) {
			appointment.setReference("���� ����");
		}
		humanResourceService.addAppointment(appointment);
	}
	
	//�߷� �����ȸ
	@RequestMapping(value = "/hr/getAppointmentList", method = RequestMethod.POST)
	public List<Appointment> getAppointmentList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getAppointmentList");
		
		List<Appointment> list 
					= humanResourceService.getAppointmentList(search);
		
		return list;
	}
	
	//�߷� ����
	@RequestMapping(value = "/hr/modifyAppointment", method = RequestMethod.POST)
	public void modifyAppointment(@RequestBody Appointment appointment) throws Exception{
		System.out.println("/hr/modifyAppointment");
		
		humanResourceService.modifyAppointment(appointment);
	}
	
	//�߷ɻ��� ����
	//Sample Data : [{"appointmentNo":"1"},{"status":"02"}]
	@RequestMapping(value = "/hr/modifyAppointmentStatus", method = RequestMethod.GET)
	public void modifyAppointmentStatus(@RequestBody Map<String, String>[] arrayMap) throws Exception{
		System.out.println("/hr/modifyAppointmentStatus");
	
		humanResourceService.convertAppointmentStatus(arrayMap[0].get("appointmentNo")
												, arrayMap[1].get("status"));
	}
	
	//�߷� ����
	@RequestMapping(value = "/hr/removeAppointment", method = RequestMethod.GET)
	public void removeAppointment(@RequestBody Appointment appointment) throws Exception{
		
	}
	
	//�μ� ���
	@RequestMapping(value = "/hr/addDepartment", method = RequestMethod.POST)
	public void addDepartment(@RequestBody Department department) throws Exception{
		System.out.println("/hr/addDepartment");
		
		humanResourceService.addDepartment(department);
	}
	
	//�μ� �����ȸ
	@RequestMapping(value = "/hr/getDepartmentList", method = RequestMethod.POST)
	public List<Department> getDepartmentList(@RequestBody Search search) throws Exception{
		System.out.println("/hr/getDepartmentList");
		
		List<Department> list = humanResourceService.getDepartmentList(search);
		
		return list;
	}
	
	//�μ� ��
	@RequestMapping(value = "/hr/modifyDepartment", method = RequestMethod.POST)
	public void modifyDepartment(@RequestBody Department department) throws Exception{
		System.out.println("/hr/modifyDepartment");
		
		humanResourceService.modifyDepartment(department);
	}
	
	//�μ������� ����
	@RequestMapping(value = "/hr/convertDepartmentUsageStatus", method = RequestMethod.POST)
	public void convertDepartmentUsageStatus(@RequestBody Department department) throws Exception{
		System.out.println("/hr/convertDepartmentUsageStatus");
		
		humanResourceService.convertDepartmentUsageStatus(department);
	}
	
	//�ٹ��ð� ��ȸ
	@RequestMapping(value = "/hr/getCommuteList/{employeeNo}", method = RequestMethod.GET)
	public List<Commute> getCommuteList(@PathVariable String employeeNo) throws Exception{
		System.out.println("/hr/getCommuteList");
		
		List<Commute> list = humanResourceService.getCommuteListByEmployeeNo(employeeNo);
		
		return list;
	}
	
	//��ٽð� ���
	@RequestMapping(value = "/hr/addCommute", method = RequestMethod.POST)
	public void addCommute(@RequestBody Commute commute) throws Exception{
		System.out.println("/hr/addCommute");
		
		humanResourceService.addCommute(commute);
	}
}
