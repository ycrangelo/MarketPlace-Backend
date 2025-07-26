package com.donedeal.repository;

import com.donedeal.schema.TransactionsSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionsSchema,Integer> {
}
