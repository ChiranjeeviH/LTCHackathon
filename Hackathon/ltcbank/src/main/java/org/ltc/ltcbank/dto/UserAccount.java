package org.ltc.ltcbank.dto;

import lombok.Getter;
import lombok.Setter;
import org.ltc.ltcbank.entity.Address;

import java.time.LocalDate;

@Getter
@Setter
public class UserAccount {

    private Long id;
    private String email;
    private String phone;
    private String fullName;
    private LocalDate dateOfBirth;

    private Address address;

    private Long accountNumber;
    private Double balance;
    private String successTokem;


}
