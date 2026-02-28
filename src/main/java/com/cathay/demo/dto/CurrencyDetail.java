package com.cathay.demo.dto;

/**
 * 此 DTO 用於新 API 的幣別明細，包含代碼、中文名稱與匯率
 */
public class CurrencyDetail {
	private String code;         // 幣別代碼 (例如: USD)
    private String chineseName;  // 幣別中文名稱 (由資料庫對照產生)
    private String rate;         // 匯率 (來自 CoinDesk API)
    
    
    public CurrencyDetail() {
    }

    // 全參數建構子 (方便 Service 快速建立物件)
    public CurrencyDetail(String code, String chineseName, String rate) {
        this.code = code;
        this.chineseName = chineseName;
        this.rate = rate;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
    
}
