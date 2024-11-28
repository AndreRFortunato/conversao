package com.moeda_convesao.conversao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moeda_convesao.conversao.entities.Currency;
import com.moeda_convesao.conversao.repositories.CurrencyRepository;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void run(String... args) throws Exception {
        Currency c1 = new Currency();
        c1.setCode("USD");
        c1.setName("Dollar");
        
        Currency c2 = new Currency();
        c2.setCode("BRL");
        c2.setName("Real");

        Currency c3 = new Currency();
        c3.setCode("EUR");
        c3.setName("Euro");

        Currency c4 = new Currency();
        c4.setCode("GBP");
        c4.setName("Libra");

        Currency c5 = new Currency();
        c5.setCode("ARS");
        c5.setName("Peso Argentino");

        currencyRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
    }
}
