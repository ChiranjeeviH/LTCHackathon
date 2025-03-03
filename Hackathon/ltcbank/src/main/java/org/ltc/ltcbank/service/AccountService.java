package org.ltc.ltcbank.service;

import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getBalance(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public List<Account> getAccounts(User user) {
        return accountRepository.findByUser(user);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }


    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }
}