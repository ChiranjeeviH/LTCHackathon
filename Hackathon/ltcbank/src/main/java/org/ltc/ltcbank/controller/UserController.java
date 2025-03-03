package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        Account account = new Account();
        account.setUser(registeredUser);
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(0.0);
        accountService.createAccount(account);
        return registeredUser;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }

    private String generateAccountNumber() {
        String prefix = "ACC";
        long timestamp = System.currentTimeMillis();
        int randomNumber = new Random().nextInt(900000) + 100000; // 6-digit random number
        return prefix + timestamp + randomNumber;
    }
}