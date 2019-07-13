package com.vision.erp.service.domain;

public class OrderToVendor {
	//field
	private String orderToVendorNo;
	private String statementNo;
	private String totalAmount;
	private String orderToVendorDate;
	private String orderToVenStatusCodeNo;
	private String orderToVenStatusCodeName;
	
	//constructor
	public OrderToVendor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderToVendorNo() {
		return orderToVendorNo;
	}

	public void setOrderToVendorNo(String orderToVendorNo) {
		this.orderToVendorNo = orderToVendorNo;
	}

	public String getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(String statementNo) {
		this.statementNo = statementNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderToVendorDate() {
		return orderToVendorDate;
	}

	public void setOrderToVendorDate(String orderToVendorDate) {
		this.orderToVendorDate = orderToVendorDate;
	}

	public String getOrderToVenStatusCodeNo() {
		return orderToVenStatusCodeNo;
	}

	public void setOrderToVenStatusCodeNo(String orderToVenStatusCodeNo) {
		this.orderToVenStatusCodeNo = orderToVenStatusCodeNo;
	}

	public String getOrderToVenStatusCodeName() {
		return orderToVenStatusCodeName;
	}

	public void setOrderToVenStatusCodeName(String orderToVenStatusCodeName) {
		this.orderToVenStatusCodeName = orderToVenStatusCodeName;
	}

	@Override
	public String toString() {
		return "OrderToVendor [orderToVendorNo=" + orderToVendorNo + ", statementNo=" + statementNo + ", totalAmount="
				+ totalAmount + ", orderToVendorDate=" + orderToVendorDate + ", orderToVenStatusCodeNo="
				+ orderToVenStatusCodeNo + ", orderToVenStatusCodeName=" + orderToVenStatusCodeName + "]";
	}
	
	
	
	

}
