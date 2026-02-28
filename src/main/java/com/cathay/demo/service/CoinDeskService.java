package com.cathay.demo.service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathay.demo.dto.BpiDetail;
import com.cathay.demo.dto.CoinDeskRaw;
import com.cathay.demo.dto.CurrencyDetail;
import com.cathay.demo.dto.NewCoinDeskResponse;
import com.cathay.demo.entity.Currency;
import com.cathay.demo.repository.CurrencyRepository;

@Service
public class CoinDeskService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyRepository repository;

	private static final String COINDESK_URL = "https://kengp3.github.io/blog/coindesk.json";

	/**
	 * 核心邏輯：呼叫 CoinDesk API 並轉換為需求格式
	 */
	public NewCoinDeskResponse getConvertedData() {
		// 1. 呼叫外部 API 並自動解析成 CoinDeskRaw 物件
		CoinDeskRaw rawData = restTemplate.getForObject(COINDESK_URL, CoinDeskRaw.class);

		NewCoinDeskResponse response = new NewCoinDeskResponse();

		if (rawData != null) {
			// 2. 處理時間轉換 (API 需求格式: yyyy/MM/dd HH:mm:ss)
			// 原始格式為 ISO 8601 (例如: 2024-05-10T12:00:00+00:00)
			if (rawData.getTime() != null && rawData.getTime().getUpdatedISO() != null) {
				OffsetDateTime odt = OffsetDateTime.parse(rawData.getTime().getUpdatedISO());
				String formattedTime = odt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
				response.setUpdateTime(formattedTime);
			}

			// 3. 處理幣別資訊與中文名稱對照
			List<CurrencyDetail> currencyList = new ArrayList<>();
			Map<String, BpiDetail> bpiMap = rawData.getBpi();

			if (bpiMap != null) {
				for (Map.Entry<String, BpiDetail> entry : bpiMap.entrySet()) {
					String code = entry.getKey(); // 例如 "USD"
					BpiDetail bpi = entry.getValue(); // 原始匯率資訊

					CurrencyDetail detail = new CurrencyDetail();
					detail.setCode(code);
					detail.setRate(bpi.getRate());

					// 從資料庫 (H2) 尋找對應的中文名稱
					String chineseName = repository.findByCode(code).map(Currency::getChineseName).orElse("未知幣別"); // 如果資料庫沒這筆，給予預設值
					detail.setChineseName(chineseName);
					currencyList.add(detail);
				}
			}
			response.setCurrencies(currencyList);
		}
		return response;
	}

}
