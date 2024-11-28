package com.moeda_convesao.conversao.dto;

import java.math.BigDecimal;

public class ConversionDTO {
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private BigDecimal amount;

    // Construtor padrão
    public ConversionDTO() {}

    // Construtor com parâmetros
    public ConversionDTO(String fromCurrencyCode, String toCurrencyCode, BigDecimal amount) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.amount = amount;
    }

    // Getters e Setters
    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}