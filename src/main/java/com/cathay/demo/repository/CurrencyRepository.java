package com.cathay.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cathay.demo.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	/**
     * 透過幣別代碼尋找資料。
     */
    Optional<Currency> findByCode(String code);
}
