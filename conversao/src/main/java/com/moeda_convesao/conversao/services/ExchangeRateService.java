package com.moeda_convesao.conversao.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.moeda_convesao.conversao.entities.Currency;
import com.moeda_convesao.conversao.entities.ExchangeRate;
import com.moeda_convesao.conversao.repositories.ExchangeRateRepository;

@Service
public class ExchangeRateService {

    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public BigDecimal getRate(Currency fromCurrency, Currency toCurrency) {
        ExchangeRate exchangeRate = exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
                .orElseGet(() -> fetchAndSaveExchangeRate(fromCurrency, toCurrency));
        return exchangeRate.getRate();
    }

    private ExchangeRate fetchAndSaveExchangeRate(Currency fromCurrency, Currency toCurrency) {
        RestTemplate restTemplate = new RestTemplate();

        // Construção da URL com os parâmetros
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + fromCurrency.getCode())
            .queryParam("apikey", apiKey)
            .toUriString();

        // Fazendo requisição GET
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Verifica se a resposta contém as taxas de câmbio
        if (response != null && response.containsKey("conversion_rates")) {
            Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");
            Double rate = rates.get(toCurrency.getCode());

            if (rate != null) {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setFromCurrency(fromCurrency);
                exchangeRate.setToCurrency(toCurrency);
                exchangeRate.setRate(BigDecimal.valueOf(rate));
                exchangeRate.setTimestamp(LocalDateTime.now());

                return exchangeRateRepository.save(exchangeRate);
            } else {
                throw new RuntimeException("Taxa de câmbio não encontrada para a moeda destino.");
            }
        } else {
            throw new RuntimeException("Taxa de câmbio não encontrada para as moedas solicitadas.");
        }
    }

    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.findAll();
    }

    public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }
}