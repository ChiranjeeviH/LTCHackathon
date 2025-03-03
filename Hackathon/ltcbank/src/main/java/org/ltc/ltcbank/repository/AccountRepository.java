package org.ltc.ltcbank.repository;

import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);
    Account findByAccountNumber(Long accountNumber);
}