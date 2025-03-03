package org.ltc.ltcbank.utility;

import org.ltc.ltcbank.dto.AccountDTO;
import org.ltc.ltcbank.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountUtil {

    public static List<AccountDTO> convertToDTO(List<Account> account) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Account acc : account) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(acc.getId());
            accountDTO.setAccountNumber(acc.getAccountNumber());
            accountDTO.setBalance(acc.getBalance());
            accountDTO.setUser(UserInfoUtil.convertToDTO(acc.getUser()));
            accountDTOs.add(accountDTO);
        }
        return accountDTOs;
    }
}
