package com.moeda_convesao.conversao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moeda_convesao.conversao.entities.Currency;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCode(String code);
}