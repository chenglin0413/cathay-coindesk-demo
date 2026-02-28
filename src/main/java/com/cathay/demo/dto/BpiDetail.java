package com.cathay.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BpiDetail {
	 private String code;
	private String symbol;
   private String rate;
   private String description;
   
   @JsonProperty("rate_float")
   private float rateFloat;
	
   public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getRateFloat() {
		return rateFloat;
	}
	public void setRateFloat(float rateFloat) {
		this.rateFloat = rateFloat;
	}
}
