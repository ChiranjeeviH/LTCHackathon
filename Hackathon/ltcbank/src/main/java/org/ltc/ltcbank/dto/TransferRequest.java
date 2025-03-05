package org.ltc.ltcbank.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String fromCurrency = "gbp";
    private String toCurrency = "euro";

}