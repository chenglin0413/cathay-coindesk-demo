package com.cathay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.demo.dto.NewCoinDeskResponse;
import com.cathay.demo.service.CoinDeskService;

@RestController
@RequestMapping("/api/coindesk")
public class CoinDeskController {
	@Autowired
    private CoinDeskService coinDeskService;
	/**
     * 呼叫轉換後的新 API
     * 包含：更新時間 (yyyy/MM/dd HH:mm:ss) 與 幣別相關資訊 (含中文)
     */
    @GetMapping("/transformed")
    public NewCoinDeskResponse getTransformedData() {
        return coinDeskService.getConvertedData();
    }
}
