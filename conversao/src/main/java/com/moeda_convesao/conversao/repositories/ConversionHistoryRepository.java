package com.moeda_convesao.conversao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moeda_convesao.conversao.entities.ConversionHistory;

public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

}