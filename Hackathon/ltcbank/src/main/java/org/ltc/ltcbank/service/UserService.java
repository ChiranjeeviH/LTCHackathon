package org.ltc.ltcbank.service;

import org.ltc.ltcbank.dto.UserAccount;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.repository.AccountRepository;
import org.ltc.ltcbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AccountRepository accountRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserAccount login(String userName, String password) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        List<Account> accounts = accountRepository.findByUser(user);
        if (accounts.isEmpty()) {
            throw new RuntimeException("Account not found for user");
        }
        Account account = accounts.get(0);

        return getUserAccount(user, account);
    }

    public String logout() {
        return "Logout successful";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public UserAccount findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Account> accounts = accountRepository.findByUser(user);
        if (accounts.isEmpty()) {
            throw new RuntimeException("Account not found for user");
        }
        Account account = accounts.get(0);

        return getUserAccount(user, account);
    }

    private static UserAccount getUserAccount(User user, Account account) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(user.getId());
        userAccount.setEmail(user.getEmail());
        userAccount.setPhone(user.getPhone());
        userAccount.setFullName(user.getFullName());
        userAccount.setDateOfBirth(user.getDateOfBirth());
        userAccount.setAddress(user.getAddress());
        userAccount.setAccountNumber(account.getAccountNumber());
        userAccount.setBalance(account.getBalance());
        userAccount.setSuccessTokem(UUID.randomUUID().toString());

        return userAccount;
    }
}