package com.moeda_convesao.conversao.resources;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moeda_convesao.conversao.dto.ConversionDTO;
import com.moeda_convesao.conversao.entities.ConversionHistory;
import com.moeda_convesao.conversao.services.ConversionHistoryService;

@RestController
public class ConversionResource {
    private final ConversionHistoryService conversionHistoryService;

    public ConversionResource(ConversionHistoryService conversionHistoryService) {
        this.conversionHistoryService = conversionHistoryService;
    }

    @PostMapping("/convert")
    public ConversionHistory convert(@RequestParam String fromCurrencyCode,
                                     @RequestParam String toCurrencyCode,
                                     @RequestParam BigDecimal amount) {
        return conversionHistoryService.convert(fromCurrencyCode, toCurrencyCode, amount);
    }

    @PostMapping("/convertDTO")
    public ConversionHistory convert(@RequestBody ConversionDTO conversionRequestDTO) {
        return conversionHistoryService.convert(
            conversionRequestDTO.getFromCurrencyCode(),
            conversionRequestDTO.getToCurrencyCode(),
            conversionRequestDTO.getAmount()
        );
    }

    @GetMapping("/history")
    public List<ConversionHistory> getConversionHistory() {
        return conversionHistoryService.getConversionHistory();
    }
}