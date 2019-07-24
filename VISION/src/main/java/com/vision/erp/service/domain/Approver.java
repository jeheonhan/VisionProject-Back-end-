package com.vision.erp.service.domain;

public class Approver {
	//field
	private String approverNumbering;
	private String approvalNo;
	private String employeeNo;
	private String signatureImage;
	private String rankCodeName;
	private String ordinal;
	private String approvalStatus;
	
	//constructor
	public Approver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Approver(String approvalNo, String employeeNo, String ordinal) {
		super();
		this.approvalNo = approvalNo;
		this.employeeNo = employeeNo;
		this.ordinal = ordinal;
	}

	//getter, setter
	public String getApproverNumbering() {
		return approverNumbering;
	}
	public void setApproverNumbering(String approverNumbering) {
		this.approverNumbering = approverNumbering;
	}
	public String getApprovalNo() {
		return approvalNo;
	}
	public Approver setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
		return this;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeSignatureImage() {
		return signatureImage;
	}
	public void setEmployeeSignatureImage(String employeeSignatureImage) {
		this.signatureImage = employeeSignatureImage;
	}
	public String getEmployeeRankCodeName() {
		return rankCodeName;
	}
	public void setEmployeeRankCodeName(String employeeRankCodeName) {
		this.rankCodeName = employeeRankCodeName;
	}
	public String getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public Approver setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
		return this;
	}
	
	//toString
	@Override
	public String toString() {
		return "Approver [approverNumbering=" + approverNumbering + ", approvalNo=" + approvalNo + ", employeeNo="
				+ employeeNo + ", employeeSignatureImage=" + signatureImage + ", employeeRankCodeName=" + rankCodeName + ", ordinal=" + ordinal
				+ ", approvalStatus=" + approvalStatus + "]";
	}
	
}
