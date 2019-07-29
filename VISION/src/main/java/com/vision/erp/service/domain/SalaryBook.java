package com.vision.erp.service.domain;

public class SalaryBook {

	public SalaryBook() {
		super();
	}
	
	private String salaryDate;
	private String salaryBookName;
	private String totalSalary;
	private String totalEmpolyeeNumber;
	private String totalRegularWorkTime;
	private String totalExtendWorkTime;
	private String avgWage;
	private String departCodeName;
	private String rankCodeName;
	
	public String getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}
	public String getSalaryBookName() {
		return salaryBookName;
	}
	public void setSalaryBookName(String salaryBookName) {
		this.salaryBookName = salaryBookName;
	}
	public String getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(String totalSalary) {
		this.totalSalary = totalSalary;
	}
	public String getTotalEmpolyeeNumber() {
		return totalEmpolyeeNumber;
	}
	public void setTotalEmpolyeeNumber(String totalEmpolyeeNumber) {
		this.totalEmpolyeeNumber = totalEmpolyeeNumber;
	}
	public String getTotalRegularWorkTime() {
		return totalRegularWorkTime;
	}
	public void setTotalRegularWorkTime(String totalRegularWorkTime) {
		this.totalRegularWorkTime = totalRegularWorkTime;
	}
	public String getTotalExtendWorkTime() {
		return totalExtendWorkTime;
	}
	public void setTotalExtendWorkTime(String totalExtendWorkTime) {
		this.totalExtendWorkTime = totalExtendWorkTime;
	}
	public String getAvgWage() {
		return avgWage;
	}
	public void setAvgWage(String avgWage) {
		this.avgWage = avgWage;
	}
	public String getDepartCodeName() {
		return departCodeName;
	}
	public void setDepartCodeName(String departCodeName) {
		this.departCodeName = departCodeName;
	}
	public String getRankCodeName() {
		return rankCodeName;
	}
	public void setRankCodeName(String rankCodeName) {
		this.rankCodeName = rankCodeName;
	}
	@Override
	public String toString() {
		return "SalaryBook [salaryDate=" + salaryDate + ", salaryBookName=" + salaryBookName + ", totalSalary="
				+ totalSalary + ", totalEmpolyeeNumber=" + totalEmpolyeeNumber + ", totalRegularWorkTime="
				+ totalRegularWorkTime + ", totalExtendWorkTime=" + totalExtendWorkTime + ", avgWage=" + avgWage
				+ ", departCodeName=" + departCodeName + ", rankCodeName=" + rankCodeName + "]";
	}
}
