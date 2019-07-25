package com.vision.erp.service.domain;

public class Approval {
	//field
	private String approvalNo;
	private String approvalTitle;
	private String approvalContent;
	private String submitDate;
	private Approver firstApprover = new Approver();
	private Approver secondApprover = new Approver();
	private Approver thirdApprover = new Approver();
	private Approver fourthApprover = new Approver();
	private Approver fifthApprover = new Approver();
	private String approvalFormNo;
	private String approvalStatusCodeNo;
	private String approvalStatusCodeName;
	private String approverCount;
	private String totalApproverCount;
	
	//constructor
	public Approval() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Approval(String approvalTitle, String approvalContent, String approvalFormNo, String approvalStatusCodeNo, String approverCount) {
		super();
		this.approvalTitle = approvalTitle;
		this.approvalContent = approvalContent;
		this.approvalFormNo = approvalFormNo;
		this.approvalStatusCodeNo = approvalStatusCodeNo;
		this.approverCount = approverCount;
	}


	//getter, setter
	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	public String getApprovalTitle() {
		return approvalTitle;
	}
	public void setApprovalTitle(String approvalTitle) {
		this.approvalTitle = approvalTitle;
	}
	public String getApprovalContent() {
		return approvalContent;
	}
	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public Approver getFirstApprover() {
		return firstApprover;
	}

	public void setFirstApprover(Approver firstApprover) {
		this.firstApprover = firstApprover;
	}

	public Approver getSecondApprover() {
		return secondApprover;
	}

	public void setSecondApprover(Approver secondApprover) {
		this.secondApprover = secondApprover;
	}

	public Approver getThirdApprover() {
		return thirdApprover;
	}

	public void setThirdApprover(Approver thirdApprover) {
		this.thirdApprover = thirdApprover;
	}

	public Approver getFourthApprover() {
		return fourthApprover;
	}

	public void setFourthApprover(Approver fourthApprover) {
		this.fourthApprover = fourthApprover;
	}

	public Approver getFifthApprover() {
		return fifthApprover;
	}

	public void setFifthApprover(Approver fifthApprover) {
		this.fifthApprover = fifthApprover;
	}

	public String getApprovalFormNo() {
		return approvalFormNo;
	}
	public void setApprovalFormNo(String approvalFormNo) {
		this.approvalFormNo = approvalFormNo;
	}
	public String getApprovalStatusCodeNo() {
		return approvalStatusCodeNo;
	}
	public void setApprovalStatusCodeNo(String approvalStatusCodeNo) {
		this.approvalStatusCodeNo = approvalStatusCodeNo;
	}
	public String getApprovalStatusCodeName() {
		return approvalStatusCodeName;
	}
	public void setApprovalStatusCodeName(String approvalStatusCodeName) {
		this.approvalStatusCodeName = approvalStatusCodeName;
	}
	public String getApproverCount() {
		return approverCount;
	}

	public void setApproverCount(String approverCount) {
		this.approverCount = approverCount;
	}
	

	public String getTotalApproverCount() {
		if(fifthApprover!=null&&fifthApprover.getEmployeeNo()!=null) {
			return "5";
		}else if(fourthApprover!=null&&fourthApprover.getEmployeeNo()!=null) {
			return "4";
		}else if(thirdApprover!=null&&thirdApprover.getEmployeeNo()!=null) {
			return "3";
		}else if(secondApprover!=null&&secondApprover.getEmployeeNo()!=null) {
			return "2";
		}else{
			return totalApproverCount;
		}
	}


	public void setTotalApproverCount(String totalApproverCount) {
		this.totalApproverCount = totalApproverCount;
	}


	@Override
	public String toString() {
		return "Approval [approvalNo=" + approvalNo + ", approvalTitle=" + approvalTitle + ", approvalContent="
				+ approvalContent.isEmpty() + ", submitDate=" + submitDate + ", firstApprover=" + firstApprover
				+ ", secondApprover=" + secondApprover + ", thirdApprover=" + thirdApprover + ", fourthApprover="
				+ fourthApprover + ", fifthApprover=" + fifthApprover + ", approvalFormNo=" + approvalFormNo
				+ ", approvalStatusCodeNo=" + approvalStatusCodeNo + ", approvalStatusCodeName="
				+ approvalStatusCodeName + ", approverCount=" + approverCount + ", totalApproverCount="
				+ totalApproverCount + "]";
	}
	
	
	
}
