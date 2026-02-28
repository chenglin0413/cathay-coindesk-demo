package com.cathay.demo.dto;

import java.util.List;

/**
 * 回傳DTO
 */
public class NewCoinDeskResponse {
	// A. 更新時間 (格式: 1990/01/01 00:00:00) 
    private String updateTime;
    
    // B. 幣別相關資訊 (包含幣別、中文名稱、匯率)
    private List<CurrencyDetail> currencies;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<CurrencyDetail> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<CurrencyDetail> currencies) {
		this.currencies = currencies;
	}
    	
}