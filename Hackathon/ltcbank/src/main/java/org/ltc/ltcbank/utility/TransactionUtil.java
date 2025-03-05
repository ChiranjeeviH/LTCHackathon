package org.ltc.ltcbank.utility;

import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionUtil {

    public static List<TransactionDTO> getAlTransactionDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        String testnetUrl = "https://testnet.bscscan.com/tx/";
        for(Transaction transaction : transactions) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setFromAccountId(transaction.getFromAccountId());
            transactionDTO.setToAccountId(transaction.getToAccountId());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setTimestamp(transaction.getTimestamp().toString());
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setBlockChainReceiverLink(testnetUrl+transaction.getTransferLink());
            transactionDTO.setBlockChaonStableCoinLink(testnetUrl+transaction.getStableToToLink());
            transactionDTO.setBlockChainSenderLink(testnetUrl+transaction.getFromToStableLink());
            transactionDTOS.add(transactionDTO);
        }

        return transactionDTOS;
    }

    public static TransactionDTO getTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setFromAccountId(transaction.getFromAccountId());
        transactionDTO.setToAccountId(transaction.getToAccountId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTimestamp(transaction.getTimestamp().toString());
        return transactionDTO;
    }
}
