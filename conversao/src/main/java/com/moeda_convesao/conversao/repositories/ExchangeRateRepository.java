package com.moeda_convesao.conversao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moeda_convesao.conversao.entities.Currency;
import com.moeda_convesao.conversao.entities.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByFromCurrencyAndToCurrency(Currency fromCurrency, Currency toCurrency);
}

