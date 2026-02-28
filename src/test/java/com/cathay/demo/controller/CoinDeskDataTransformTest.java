package com.cathay.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cathay.demo.dto.NewCoinDeskResponse;
import com.cathay.demo.service.CoinDeskService;

@SpringBootTest
@AutoConfigureMockMvc
public class CoinDeskDataTransformTest {
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private CoinDeskService coinDeskService;

    @Test
    public void testDataTransformation() {
    	// 執行轉換邏輯
        NewCoinDeskResponse response = coinDeskService.getConvertedData();

        // 1. 驗證時間格式是否正確 (必須符合 yyyy/MM/dd HH:mm:ss，共 19 個字元)
        assertNotNull(response.getUpdateTime());
        assertEquals(19, response.getUpdateTime().length());
        assertTrue(response.getUpdateTime().contains("/"));
        
        // 2. 驗證幣別資訊是否包含中文 (假設資料庫已有 USD -> 美金)
        assertNotNull(response.getCurrencies());
        assertFalse(response.getCurrencies().isEmpty());
        
        // 印出結果
        System.out.println("轉換後的時間: " + response.getUpdateTime());
        response.getCurrencies().forEach(c -> 
            System.out.println("幣別: " + c.getCode() + ", 中文: " + c.getChineseName() + ", 匯率: " + c.getRate())
        );
    }
    @Test
    public void testTransformedApi() throws Exception {
        mockMvc.perform(get("/api/coindesk/transformed"))
               .andExpect(status().isOk())
               .andDo(print()); // 在 Console 印出完整的 JSON 內容
    }

}
