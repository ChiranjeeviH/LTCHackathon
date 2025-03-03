package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.dto.LoginRequest;
import org.ltc.ltcbank.dto.UserDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
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

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        return UserInfoUtil.convertToDTO(userService.findById(userId));
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        Account account = new Account();
        account.setUser(registeredUser);
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(user.getInitialBalance()!=null ? user.getInitialBalance() : 0.0);
        Account savedAccount = accountService.createAccount(account);
        return "Account Created Successfully" + savedAccount.getAccountNumber();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }

    private Long generateAccountNumber() {
        long timestamp = System.currentTimeMillis();
        int randomNumber = new Random().nextInt(900000) + 100000;
        return timestamp + randomNumber;
    }
}