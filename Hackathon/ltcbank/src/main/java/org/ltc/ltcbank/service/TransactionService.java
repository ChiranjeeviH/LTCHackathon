package org.ltc.ltcbank.service;

import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.Transaction;
import org.ltc.ltcbank.repository.AccountRepository;
import org.ltc.ltcbank.repository.TransactionRepository;
import org.ltc.ltcbank.utility.AccountUtil;
import org.ltc.ltcbank.utility.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getTransactions(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }

    public Transaction getTransactionDetails(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    public TransactionDTO transferFunds(Account fromAccount, Account toAccount, Double amount) {
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds to transfer");
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccount.getAccountNumber());
        transaction.setToAccountId(toAccount.getAccountNumber());
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        // include from blockchain if present if not hard code it
        transaction.setTransactionId(AccountUtil.generateAccountNumber());
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        transactionRepository.save(transaction);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return TransactionUtil.getTransactionDTO(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactionsList =  transactionRepository.findAll();

        return TransactionUtil.getAlTransactionDTO(transactionsList);
    }
}