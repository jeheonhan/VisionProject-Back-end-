package com.vision.erp.service.domain;

public class Local {
	
	private String localCodeNo;
	private String localCodeName;

	public Local() {		
	}

	public String getLocalCodeNo() {
		return localCodeNo;
	}

	public void setLocalCodeNo(String localCodeNo) {
		this.localCodeNo = localCodeNo;
	}

	public String getLocalCodeName() {
		return localCodeName;
	}

	public void setLocalCodeName(String localCodeName) {
		this.localCodeName = localCodeName;
	}

	@Override
	public String toString() {
		return "Local [localCodeNo=" + localCodeNo + ", localCodeName=" + localCodeName + "]";
	}

}
