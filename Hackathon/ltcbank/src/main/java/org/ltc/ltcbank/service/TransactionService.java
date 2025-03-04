package org.ltc.ltcbank.service;

import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.Transaction;
import org.ltc.ltcbank.repository.TransactionRepository;
import org.ltc.ltcbank.utility.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }

    public TransactionDTO transferFunds(Account fromAccount, Account toAccount, Double amount) {
        // Implement the logic for transferring funds
        // For now, just returning a new Transaction object
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccount.getId());
        transaction.setToAccountId(toAccount.getId());
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        return TransactionUtil.getTransactionDTO(transactionRepository.save(transaction));
    }

    public List<TransactionDTO> getAllTransaction() {
        List<Transaction> transactionsList =  transactionRepository.findAll();

        return TransactionUtil.getAlTransactionDTO(transactionsList);
    }
}