package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.dto.TransferRequest;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.Transaction;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        Account account = accountService.findById(accountId);
        return transactionService.getTransactions(account);
    }

    @PostMapping("/transfer")
    public Transaction transferFunds(@RequestBody TransferRequest transferRequest) {
        Account fromAccount = accountService.findById(transferRequest.getFromAccountId());
        Account toAccount = accountService.findById(transferRequest.getToAccountId());
        return transactionService.transferFunds(fromAccount, toAccount, transferRequest.getAmount());
    }
}