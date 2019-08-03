package com.vision.erp.service.domain;

public class OrderToVendorProduct {
	//field
	private String orderToVendorNumber;
	private String orderToVendorNo;
	private String productNo;
	private String purchasePrice;
	private String quantity;
	private String amount;
	private String orderToVendorProductStatusCodeNo;
	private String orderToVendorProductStatusCodeName;
	private String productName;
	private String countForcode;
	private String vendorName;
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getProductName() {
		return productName;
	}

	public String getCountForcode() {
		return countForcode;
	}

	public void setCountForcode(String countForcode) {
		this.countForcode = countForcode;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	//constructor
	public OrderToVendorProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderToVendorNumber() {
		return orderToVendorNumber;
	}

	public void setOrderToVendorNumber(String orderToVendorNumber) {
		this.orderToVendorNumber = orderToVendorNumber;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderToVendorRegNo() {
		return orderToVendorNumber;
	}

	public void setOrderToVendorRegNo(String orderToVendorRegNo) {
		this.orderToVendorNumber = orderToVendorRegNo;
	}

	public String getOrderToVendorNo() {
		return orderToVendorNo;
	}

	public void setOrderToVendorNo(String orderToVendorNo) {
		this.orderToVendorNo = orderToVendorNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public String getOrderToVendorProductStatusCodeNo() {
		return orderToVendorProductStatusCodeNo;
	}

	public void setOrderToVendorProductStatusCodeNo(String orderToVendorProductStatusCodeNo) {
		this.orderToVendorProductStatusCodeNo = orderToVendorProductStatusCodeNo;
	}

	public String getOrderToVendorProductStatusCodeName() {
		return orderToVendorProductStatusCodeName;
	}

	public void setOrderToVendorProductStatusCodeName(String orderToVendorProductStatusCodeName) {
		this.orderToVendorProductStatusCodeName = orderToVendorProductStatusCodeName;
	}

	@Override
	public String toString() {
		return "OrderToVendorProduct [orderToVendorNumber=" + orderToVendorNumber + ", orderToVendorNo="
				+ orderToVendorNo + ", productNo=" + productNo + ", purchasePrice=" + purchasePrice + ", quantity="
				+ quantity + ", amount=" + amount + ", orderToVendorProductStatusCodeNo="
				+ orderToVendorProductStatusCodeNo + ", orderToVendorProductStatusCodeName="
				+ orderToVendorProductStatusCodeName + ", productName=" + productName + ", countForcode=" + countForcode
				+ ", vendorName=" + vendorName + "]";
	}

	

	
	

	
	
	

}
