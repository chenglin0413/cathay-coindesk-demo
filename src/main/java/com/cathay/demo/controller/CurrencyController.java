package com.cathay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.demo.entity.Currency;
import com.cathay.demo.repository.CurrencyRepository;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
	@Autowired
    private CurrencyRepository repository;

    /**
     * 1. 查詢所有幣別 (Read All)
     * 對應需求：查詢功能 API 
     */
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return repository.findAll();
    }

    /**
     * 2. 查詢單一幣別 (Read One)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 3. 新增幣別 (Create)
     * 對應需求：新增功能 API 
     */
    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return repository.save(currency);
    }

    /**
     * 4. 修改幣別 (Update)
     * 對應需求：修改功能 API 
     */
    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency currencyDetails) {
        return repository.findById(id).map(currency -> {
            currency.setCode(currencyDetails.getCode());
            currency.setChineseName(currencyDetails.getChineseName());
            Currency updatedCurrency = repository.save(currency);
            return ResponseEntity.ok(updatedCurrency);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * 5. 刪除幣別 (Delete)
     * 對應需求：刪除功能 API 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}	
