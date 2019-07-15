package com.vision.erp.service.domain;

public class SMS {
	//field
	private String content;
	private String sender;
	private String reciever;
	
	//constructor
	public SMS() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getter, setter
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	//toString
	@Override
	public String toString() {
		return "SMS [content=" + content + ", sender=" + sender + ", reciever=" + reciever + "]";
	}
	
	
}
