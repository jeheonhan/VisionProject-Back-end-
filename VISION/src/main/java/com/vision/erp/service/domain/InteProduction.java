package com.vision.erp.service.domain;

import java.util.List;

public class InteProduction {
	//field
	private String orderToVendorNo;
	private String statementNo;
	private String totalAmount;
	private String orderToVendorDate;
	private String purchasePrice;
	private String vendorNo;
	private List<OrderToVendorProduct> orderToVendorProduct;
	
	
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
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public List<OrderToVendorProduct> getOrderToVendorProduct() {
		return orderToVendorProduct;
	}
	public void setOrderToVendorProduct(List<OrderToVendorProduct> orderToVendorProduct) {
		this.orderToVendorProduct = orderToVendorProduct;
	}
	@Override
	public String toString() {
		return "InteProduction [orderToVendorNo=" + orderToVendorNo + ", statementNo=" + statementNo + ", totalAmount="
				+ totalAmount + ", orderToVendorDate=" + orderToVendorDate + ", purchasePrice=" + purchasePrice
				+ ", vendorNo=" + vendorNo + ", orderToVendorProduct=" + orderToVendorProduct + "]";
	}
	
	

}
