package com.moeda_convesao.conversao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moeda_convesao.conversao.entities.Currency;
import com.moeda_convesao.conversao.repositories.CurrencyRepository;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Currency not found"));
    }
}

