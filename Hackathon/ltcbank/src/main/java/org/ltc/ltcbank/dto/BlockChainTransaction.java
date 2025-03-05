package org.ltc.ltcbank.dto;

import lombok.Data;

@Data
public class   BlockChainTransaction {
    /*
    "transactions": {
        "fromToStable": "0x9118a9db1e19076ecdb328932cd6d99cfa1215b818c7e11fc0f6d28769ffee83",
        "transfer": "0x3c6e6acf5078e4e3e83cf5884876883fd21ed4d09d3fad0814ab715b5157ee30",
        "stableToTo": "0x43a07d3a195a7e8d014f53248600265d3d6f9771cc8f96940773e6125da4aa01"
    }
     */
    private String fromToStable;
    private String transfer;
    private String stableToTo;
}
