package org.ltc.ltcbank.service;

import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAccounts(User user) {
        return accountRepository.findByUserId(user.getId());
    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}