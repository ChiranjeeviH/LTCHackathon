package org.ltc.ltcbank.utility;

import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionUtil {

    public static List<TransactionDTO> getAlTransactionDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(Transaction transaction : transactions) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setFromAccountId(transaction.getFromAccountId());
            transactionDTO.setToAccountId(transaction.getToAccountId());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setTimestamp(transaction.getTimestamp().toString());
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
