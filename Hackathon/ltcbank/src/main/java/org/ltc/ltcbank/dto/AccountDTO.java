package org.ltc.ltcbank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private Double balance;
    private UserDTO user;
}
