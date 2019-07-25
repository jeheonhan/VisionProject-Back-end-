package com.vision.erp.web.approval;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.Search;
import com.vision.erp.service.approval.ApprovalService;
import com.vision.erp.service.domain.Approval;
import com.vision.erp.service.domain.ApprovalForm;

@RestController
public class ApprovalController {
	//field
	@Resource(name = "approvalServiceImpl")
	private ApprovalService approvalService;

	//constructor
	public ApprovalController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//method
	//[결재서양식목록조회] - 결재서양식목록조회
	@RequestMapping(value="/approval/getApprovalFormList")
	public List<ApprovalForm> getApprovalFormList() throws Exception{
		return approvalService.getApprovalFormList();
	}
	
	//[결재서양식목록조회] - 결재서양식삭제
	//approvalFormNo, approvalFormUsageStatusCodeNo 필요(01 사용중, 02 삭제)
	@RequestMapping(value="/approval/convertApprovalFormUsageStatus")
	public void convertApprovalFormUsageStatus(@RequestBody ApprovalForm approvalForm) throws Exception{
		approvalService.convertApprovalFrom(approvalForm);
	}
	
	//[결재서양식등록] - 결재서양식등록, 복제
	//approvalFormTitle, approvalForm, registrantEmployeeNo, 사원명 registrantEmployeeName
	@RequestMapping(value="/approval/addApprovalForm")
	public void addApprovalForm(@RequestBody ApprovalForm approvalForm) throws Exception{
		approvalService.addApprovalForm(approvalForm);
	}
	
	//[결재서양식목록조회] - 결재서양식상세조회
	@RequestMapping(value="/approval/getApprovalFormDetail/{formNo}")
	public ApprovalForm getApprovalFormDetail(@PathVariable("formNo") String approvalFormNo) throws Exception{
		return approvalService.getApprovalFormDetail(approvalFormNo);
	}
	
	//[결재서양식수정] - 결재서양식수정
	//approvalFormNo, approvalFormTitle, approvalForm, registrantEmployeeNo, registrantEmployeeName 필요
	@RequestMapping(value="/approval/modifyApprovalForm")
	public void modifyApprovalForm(@RequestBody ApprovalForm approvalForm) throws Exception{
		approvalService.modifyApprovalForm(approvalForm);
	}
	
	//[결재요청] - 결재상신(등록)
	//approvalTitle, approvalContent, approvalFormNo, approvalStatusCodeNo, approverCount(Approver ordinal, employeeNo 채워져있어야함) 필요
	@RequestMapping(value="/approval/addApproval")
	public void addApproval(@RequestBody Approval approval) throws Exception{
		approvalService.addApproval(approval);
	}
	
	//[결재목록조회] - 선택된 결재함에 따른 결과 출력
	//SearchCondition에는 진행2, 반려3, 완료4, 대기도 2
	//	  SearchCondition에는<if test="searchCondition==1">
	//	  AND al.approver_count = er.ordinal
	//	  AND al.approval_status_code IN ('02')
	//</if>
	//<if test="searchCondition==2">
	//	  AND er.ordinal = '0'
	//	  AND al.approval_status_code IN ('02')
	//</if>
	//<if test="searchCondition==4">
	//	  AND er.ordinal = '0'
	//	  AND al.approval_status_code IN ('04')
	//</if>
	//<if test="searchCondition==3">
	//	  AND er.ordinal = '0'
	//	  AND al.approval_status_code IN ('03')
	//</if>
	//SearchKeyword에는 사원번호
	@RequestMapping(value="/approval/getApprovalList")
	public List<Approval> getApprovalList(@RequestBody Search search) throws Exception{
		return approvalService.getApprovalList(search);
	}
	
	//[결재목록조회] - 결재상세조회
	@RequestMapping(value="/approval/getApprovalDetail/{approvalNo}")
	public Approval getApprovalDetail(@PathVariable("approvalNo") String approvalNo) throws Exception{
		return approvalService.getApprovalDetail(approvalNo);
	}
	
	//[결재상세조회] - 결재/반려하기
	@RequestMapping(value="/approval/modifyApprovalStatus/{approvalNo}/{employeeNo}/{approvalOrReturn}")
	public void modifyApprovalStatus(@PathVariable("approvalNo") String approvalNo, @PathVariable("employeeNo") String employeeNo, @PathVariable("approvalOrReturn") String approvalOrReturn) throws Exception{
		approvalService.modifyApprovalStatus(approvalService.getApprovalDetail(approvalNo), employeeNo, approvalOrReturn);
	}
	
}
