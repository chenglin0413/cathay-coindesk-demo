package com.cathay.demo.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 輸入接收使用(DTO)
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinDeskRaw {
	private TimeInfo time;
    private String disclaimer;
    private String chartName;
    private Map<String, BpiDetail> bpi; // 使用 Map 對應 "USD", "GBP", "EUR" 鍵值
    
	public TimeInfo getTime() {
		return time;
	}
	public void setTime(TimeInfo time) {
		this.time = time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public Map<String, BpiDetail> getBpi() {
		return bpi;
	}
	public void setBpi(Map<String, BpiDetail> bpi) {
		this.bpi = bpi;
	}
    
}

