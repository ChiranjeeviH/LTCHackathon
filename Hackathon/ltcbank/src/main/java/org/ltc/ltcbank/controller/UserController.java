package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.dto.LoginRequest;
import org.ltc.ltcbank.dto.UserAccount;
import org.ltc.ltcbank.dto.UserDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
import org.ltc.ltcbank.utility.AccountUtil;
import org.ltc.ltcbank.utility.UserInfoUtil;
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

    @GetMapping("/getUser/{userEmail}")
    public UserAccount getUser(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        Account account = new Account();
        account.setUser(registeredUser);
        account.setAccountNumber(AccountUtil.generateAccountNumber());
        account.setBalance(user.getInitialBalance()!=null ? user.getInitialBalance() : 0.0);
        Account savedAccount = accountService.createAccount(account);
        return "Account Created Successfully " + savedAccount.getAccountNumber();
    }

    @PostMapping("/login")
    public UserAccount login(@RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }

}