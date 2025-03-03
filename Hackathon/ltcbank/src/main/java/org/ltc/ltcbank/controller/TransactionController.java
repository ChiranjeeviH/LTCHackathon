package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.dto.TransferRequest;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.Transaction;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        Optional<Account> account = accountService.findById(accountId);
        return account.map(transactionService::getTransactions).orElse(null);
    }

    @PostMapping("/transfer")
    public Transaction transferFunds(@RequestBody TransferRequest transferRequest) {
        Optional<Account> fromAccount = accountService.findById(transferRequest.getFromAccountId());
        Optional<Account> toAccount = accountService.findById(transferRequest.getToAccountId());
        if (fromAccount.isPresent() && toAccount.isPresent()) {
            return transactionService.transferFunds(fromAccount.get(), toAccount.get(), transferRequest.getAmount());
        }
        return null;
    }

    @GetMapping("/getAllTransactions")
    public List<TransactionDTO> getAllTransaction() {
        return transactionService.getAllTransaction();
    }
}