package org.ltc.ltcbank.dto;

import lombok.Data;

@Data
public class BlockChainTransferRequest {
    /*
    {
    "amount": 10,
    "currency": "gbp",
    "senderCountryId":1,
    "senderBankId":1,
    "senderAccountId": 1234,
    "receiverCountryId":2,
    "receiverBankId":2,
    "receiverAccountId":2345,
    "fromCurrency": "gbp",
    "toCurrency":"euro"
}
     */
    private Long amount;
    private String currency;
    private Long senderCountryId;
    private Long senderBankId;
    private Long senderAccountId;
    private Long receiverAccountId;
    private Long receiverBankId;
    private Long receiverCountryId;
    private String fromCurrency;
    private String toCurrency;
}
