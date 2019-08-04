package com.vision.erp.service.domain;

public class Notice {
	//field
	private String noticeNo;
	private String noticeRegDate;
	private String noticeTitle;
	private String completeTitle;
	private String content;
	private String employeeNo;
	private String employeeName;
	private String departCodeName;
	private String readAuthority;
	private String viewCount;
	private String noticeStatusCodeNo;
	private String noticeStatusCodeName;
	private String noticeHeaderCodeNo;
	private String noticeHeaderCodeName;
	
	//constructor
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeRegDate() {
		return noticeRegDate;
	}

	public void setNoticeRegDate(String noticeRegDate) {
		this.noticeRegDate = noticeRegDate;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getCompleteTitle() {
		return completeTitle;
	}

	public void setCompleteTitle(String completeTitle) {
		this.completeTitle = completeTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartCodeName() {
		return departCodeName;
	}

	public void setDepartCodeName(String departCodeName) {
		this.departCodeName = departCodeName;
	}

	public String getReadAuthority() {
		return readAuthority;
	}

	public void setReadAuthority(String readAuthority) {
		this.readAuthority = readAuthority;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public String getNoticeStatusCodeNo() {
		return noticeStatusCodeNo;
	}

	public void setNoticeStatusCodeNo(String noticeStatusCodeNo) {
		this.noticeStatusCodeNo = noticeStatusCodeNo;
	}

	public String getNoticeStatusCodeName() {
		return noticeStatusCodeName;
	}

	public void setNoticeStatusCodeName(String noticeStatusCodeName) {
		this.noticeStatusCodeName = noticeStatusCodeName;
	}

	public String getNoticeHeaderCodeNo() {
		return noticeHeaderCodeNo;
	}

	public void setNoticeHeaderCodeNo(String noticeHeaderCodeNo) {
		this.noticeHeaderCodeNo = noticeHeaderCodeNo;
	}

	public String getNoticeHeaderCodeName() {
		return noticeHeaderCodeName;
	}

	public void setNoticeHeaderCodeName(String noticeHeaderCodeName) {
		this.noticeHeaderCodeName = noticeHeaderCodeName;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeRegDate=" + noticeRegDate + ", noticeTitle=" + noticeTitle
				+ ", completeTitle=" + completeTitle + ", content=" + content + ", employeeNo=" + employeeNo
				+ ", employeeName=" + employeeName + ", departCodeName=" + departCodeName + ", readAuthority="
				+ readAuthority + ", viewCount=" + viewCount + ", noticeStatusCodeNo=" + noticeStatusCodeNo
				+ ", noticeStatusCodeName=" + noticeStatusCodeName + ", noticeHeaderCodeNo=" + noticeHeaderCodeNo
				+ ", noticeHeaderCodeName=" + noticeHeaderCodeName + "]";
	}

}
