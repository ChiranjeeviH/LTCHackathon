package org.ltc.ltcbank.service;

import org.ltc.ltcbank.dto.AccountDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.repository.AccountRepository;
import org.ltc.ltcbank.utility.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Double getBalance(Long accountId) {
        return accountRepository.findByAccountNumber(accountId).getBalance();
    }

    public List<AccountDTO> getAccounts(User user) {
         List<Account> accounts =  accountRepository.findByUser(user);

         return AccountUtil.convertToDTO(accounts);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }


    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Account findByAccountNumber(Long accountNumber) {
         return accountRepository.findByAccountNumber(accountNumber);
    }
}