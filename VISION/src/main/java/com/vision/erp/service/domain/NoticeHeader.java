package com.vision.erp.service.domain;

public class NoticeHeader {
	
	private String noticeHeaderCodeNo;
	private String noticeHeaderCodeName;
	
	public NoticeHeader() {
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
		return "NoticeHeader [noticeHeaderCodeNo=" + noticeHeaderCodeNo + ", noticeHeaderCodeName="
				+ noticeHeaderCodeName + "]";
	}



}
