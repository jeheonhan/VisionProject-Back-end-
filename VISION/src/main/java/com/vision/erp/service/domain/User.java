package com.vision.erp.service.domain;

public class User {
	//field
	private String userId;
	private String employeeNo;
	private String branchNo;
	private String password;
	private String memberCodeNo;
	private String memberCodeName;
	private String memberUsageStatusCodeNo;
	private String accessMenuCodeNo;
	private String profileImage;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberCodeNo() {
		return memberCodeNo;
	}
	public void setMemberCodeNo(String memberCodeNo) {
		this.memberCodeNo = memberCodeNo;
	}
	public String getMemberCodeName() {
		return memberCodeName;
	}
	public void setMemberCodeName(String memberCodeName) {
		this.memberCodeName = memberCodeName;
	}
	public String getMemberUsageStatusCodeNo() {
		return memberUsageStatusCodeNo;
	}
	public void setMemberUsageStatusCodeNo(String memberUsageStatusCodeNo) {
		this.memberUsageStatusCodeNo = memberUsageStatusCodeNo;
	}
	public String getAccessMenuCodeNo() {
		return accessMenuCodeNo;
	}
	public void setAccessMenuCodeNo(String accessMenuCodeNo) {
		this.accessMenuCodeNo = accessMenuCodeNo;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", employeeNo=" + employeeNo + ", branchNo=" + branchNo + ", password="
				+ password + ", memberCodeNo=" + memberCodeNo + ", memberCodeName=" + memberCodeName
				+ ", memberUsageStatusCodeNo=" + memberUsageStatusCodeNo + ", accessMenuCodeNo=" + accessMenuCodeNo
				+ ", profileImage=" + profileImage + "]";
	}
	
	
	
	
}
