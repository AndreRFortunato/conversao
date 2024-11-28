package com.moeda_convesao.conversao.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moeda_convesao.conversao.entities.ConversionHistory;
import com.moeda_convesao.conversao.entities.Currency;
import com.moeda_convesao.conversao.repositories.ConversionHistoryRepository;

@Service
public class ConversionHistoryService {

    private final ConversionHistoryRepository conversionHistoryRepository;
    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    public ConversionHistoryService(ConversionHistoryRepository conversionHistoryRepository,
                                    CurrencyService currencyService,
                                    ExchangeRateService exchangeRateService) {
        this.conversionHistoryRepository = conversionHistoryRepository;
        this.currencyService = currencyService;
        this.exchangeRateService = exchangeRateService;
    }

    public ConversionHistory convert(String fromCurrencyCode, String toCurrencyCode, BigDecimal amount) {
        Currency fromCurrency = currencyService.getCurrencyByCode(fromCurrencyCode);
        Currency toCurrency = currencyService.getCurrencyByCode(toCurrencyCode);
        BigDecimal rate = exchangeRateService.getRate(fromCurrency, toCurrency);

        ConversionHistory conversionHistory = new ConversionHistory();
        conversionHistory.setFromCurrency(fromCurrency);
        conversionHistory.setToCurrency(toCurrency);
        conversionHistory.setAmount(amount);
        conversionHistory.setConvertedAmount(amount.multiply(rate));
        conversionHistory.setTimestamp(LocalDateTime.now());

        return conversionHistoryRepository.save(conversionHistory);
    }

    public List<ConversionHistory> getConversionHistory() {
        return conversionHistoryRepository.findAll();
    }
}