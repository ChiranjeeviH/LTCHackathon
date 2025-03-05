package org.ltc.ltcbank.dto;

import lombok.Data;

@Data
public class BlockChainTransferResponse {
    /*
    {
    "message": "10 gbp successfully converted and transferred as 12.108213403984833819 euro",
    "senderAddress": "0x91b8C3B78f753E047643e09F03A53847CA77b249",
    "receiverAddress": "0xc41A9cf66DF678456b3B39f396b5d480fACf8B39",
    "receiverFiatBalance": "12.108213403984833819",
    "receiverFiatCurrnecy": "euro",
    "transactions": {
        "fromToStable": "0x9118a9db1e19076ecdb328932cd6d99cfa1215b818c7e11fc0f6d28769ffee83",
        "transfer": "0x3c6e6acf5078e4e3e83cf5884876883fd21ed4d09d3fad0814ab715b5157ee30",
        "stableToTo": "0x43a07d3a195a7e8d014f53248600265d3d6f9771cc8f96940773e6125da4aa01"
    }
}
     */
    private String message;
    private String senderAddress;
    private String receiverAddress;
    private String receiverFiatBalance;
    private String receiverFiatCurrency;
    private BlockChainTransaction transactions;
}
