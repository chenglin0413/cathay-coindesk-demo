package com.cathay.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeInfo {
	private String updated;
	private String updatedISO; // 我們需要這個欄位來做時間轉換 
    private String updateduk;
    
    public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedISO() {
		return updatedISO;
	}
	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}
	public String getUpdateduk() {
		return updateduk;
	}
	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}
	
}
