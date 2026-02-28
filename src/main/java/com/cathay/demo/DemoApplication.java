package com.cathay.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/**
     * 定義 RestTemplate Bean
     * Spring 啟動時會執行此方法，並將回傳的物件放入 IoC 容器管理
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
