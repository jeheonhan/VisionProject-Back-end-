package com.vision.erp.service.domain;

public class Approver {
	//field
	private String approverNumbering;
	private String approvalNo;
	private String employeeNo;
	private String employeeName;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getSignatureImage() {
		return signatureImage;
	}
	public void setSignatureImage(String signatureImage) {
		this.signatureImage = signatureImage;
	}
	public String getRankCodeName() {
		return rankCodeName;
	}
	public Approver setRankCodeName(String rankCodeName) {
		this.rankCodeName = rankCodeName;
		return this;
	}

	@Override
	public String toString() {
		return "Approver [approverNumbering=" + approverNumbering + ", approvalNo=" + approvalNo + ", employeeNo="
				+ employeeNo + ", employeeName=" + employeeName + ", signatureImage=" + signatureImage
				+ ", rankCodeName=" + rankCodeName + ", ordinal=" + ordinal + ", approvalStatus=" + approvalStatus
				+ "]";
	}
	
}
