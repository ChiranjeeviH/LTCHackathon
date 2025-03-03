package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @GetMapping("/{accountId}/balance")
    public Account getBalance(@PathVariable Long accountId) {
        return accountService.getBalance(accountId);
    }

    @GetMapping("/{userId}/accounts")
    public List<Account> getAccounts(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return accountService.getAccounts(user);
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}