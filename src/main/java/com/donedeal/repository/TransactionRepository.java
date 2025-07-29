package com.donedeal.repository;

import com.donedeal.schema.TransactionsSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionsSchema,Integer> {

    Optional<TransactionsSchema> findByBuyerId(int buyerId);

    List<TransactionsSchema> findAllByBuyerId(int buyerId);
}
