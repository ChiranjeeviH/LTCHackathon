package org.ltc.ltcbank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String timestamp;
    private Long transactionId;
    private String blockChainReceiverLink;
    private String blockChainSenderLink;
    private String blockChaonStableCoinLink;
   // private Long accountId;
}
