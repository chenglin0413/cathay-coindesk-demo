package com.cathay.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	// 測試：查詢幣別並顯示內容 
    @Test
    public void testGetCurrencies() throws Exception {
        mockMvc.perform(get("/api/currencies"))
               .andExpect(status().isOk())
               .andDo(print()); 
    }
	
	
    // 測試：新增幣別 API 
    @Test
    public void testCreateCurrency() throws Exception {
        String json = "{\"code\":\"JPY\",\"chineseName\":\"日幣\"}";
        mockMvc.perform(post("/api/currencies")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value("JPY"));
    }
    // 測試：修改幣別 API (Update)
    @Test
    public void testUpdateCurrency() throws Exception {
        // 假設 ID 為 1 的資料已存在（由 data.sql 建立）
        String updateJson = "{\"code\":\"USD\",\"chineseName\":\"美金修改版\"}";
        
        mockMvc.perform(put("/api/currencies/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(updateJson))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.chineseName").value("美金修改版"))
               .andDo(print()); // 顯示修改後的內容 
    }
    
    // 測試：刪除幣別 API (Delete)
    @Test
    public void testDeleteCurrency() throws Exception {
        // 執行刪除 ID 為 1 的幣別
        mockMvc.perform(delete("/api/currencies/1"))
               .andExpect(status().isOk())
               .andDo(print());

        // 刪除後再次查詢，驗證是否已不存在 (預期回傳 404)
        mockMvc.perform(get("/api/currencies/1"))
	        .andExpect(status().isNotFound())
	        .andDo(print());
        
        
        // 刪除後再次查詢，驗證是否已不存在 (預期回傳剩餘的資料)
        mockMvc.perform(get("/api/currencies"))
               .andExpect(status().isOk())
               .andDo(print());
    }

}
