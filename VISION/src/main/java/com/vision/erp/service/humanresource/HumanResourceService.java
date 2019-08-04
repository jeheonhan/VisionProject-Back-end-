package com.vision.erp.service.humanresource;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Appointment;
import com.vision.erp.service.domain.Commute;
import com.vision.erp.service.domain.Department;
import com.vision.erp.service.domain.DutyHours;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.SimpleHumanResourceCard;
import com.vision.erp.service.domain.WorkAttitude;
import com.vision.erp.service.domain.WorkAttitudeCode;

public interface HumanResourceService {

	public void addHumanResourceCard(HumanResourceCard humanResourceCard) throws Exception;
	
	public List<HumanResourceCard> getHumanResourceCardList(Search search) throws Exception;
	
	public HumanResourceCard getHumanResourceCardDetailByEmployeeNo(String employeeNo) throws Exception;
	
	public SimpleHumanResourceCard getSimpleHumanResourceCardByEmployeeNo(String employeeNo) throws Exception;
	
	public List<SimpleHumanResourceCard> getSimpleHumanResourceCardList(Search search) throws Exception;
	
	public void modifyHumanResourceCard(HumanResourceCard humanResourceCard) throws Exception;
	
	public void addWorkAttitude(WorkAttitude workAttitude) throws Exception;
	
	public List<WorkAttitude> getWorkAttitudeList(Search search) throws Exception;
	
	public void modifyWorkAttitude(WorkAttitude workAttitude) throws Exception;
	
	public void convertWorkAtttidueUsageStatus(List<WorkAttitude> workAttitudeList) throws Exception;
	
	public void addWorkAttitudeCode(WorkAttitudeCode workAttitudeCode) throws Exception;
	
	public WorkAttitudeCode getWorkAttitudeCodeByWorkAttitudeCodeNo(String workAttitudeCodeNo) throws Exception;
	
	public List<WorkAttitudeCode> getWorkAttitudeCodeList(Search search) throws Exception;
	
	public void modifyWorkAttitudeCode(WorkAttitudeCode workAttitudeCode) throws Exception;
	
	public void convertWorkAttitudeCodeUsageStatus(List<WorkAttitudeCode> workAttitudeCodeList) throws Exception;
	
	public void addAppointment(Appointment appointment) throws Exception;
	
	public List<Appointment> getAppointmentList(Search search) throws Exception;
	
	public Appointment getAppointmentDetailByAppointNo(String appointNo) throws Exception;
	
	public void modifyAppointment(Appointment appointment) throws Exception;
	
	public void convertAppointmentStatus(Appointment appointment) throws Exception;
	
	public void addDepartment(Department department) throws Exception;
	
	public List<Department> getDepartmentList(Search search) throws Exception;
	
	public void modifyDepartment(Department department) throws Exception;
	
	public void convertDepartmentUsageStatus(Department department) throws Exception;
	
	public List<Commute> getCommuteListByEmployeeNo(String employeeNo) throws Exception;
	
	public void addCommute(Commute commute) throws Exception;
	
	public void modifyCommuteForLeaveWorkTime(Commute commute) throws Exception;
	
	public void addDutyHours(DutyHours dutyHours) throws Exception;
	
	public void getMakeCommuteSample(int month, int endDate ,String employeeNo) throws Exception;
	
}
