package org.ltc.ltcbank.controller;

import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public String logout() {
        return userService.logout();
    }
}