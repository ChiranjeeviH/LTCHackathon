package org.ltc.ltcbank.repository;

import org.ltc.ltcbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    Transaction findByTransactionId(Long transactionId);
}