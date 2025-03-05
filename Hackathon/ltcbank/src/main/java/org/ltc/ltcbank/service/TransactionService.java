package org.ltc.ltcbank.service;

import org.ltc.ltcbank.dto.BlockChainTransferRequest;
import org.ltc.ltcbank.dto.BlockChainTransferResponse;
import org.ltc.ltcbank.dto.TransactionDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.Transaction;
import org.ltc.ltcbank.repository.AccountRepository;
import org.ltc.ltcbank.repository.TransactionRepository;
import org.ltc.ltcbank.utility.AccountUtil;
import org.ltc.ltcbank.utility.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getTransactions(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }

    public Transaction getTransactionDetails(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    public TransactionDTO transferFunds(Account fromAccount, Account toAccount, Double amount) {
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds to transfer");
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(fromAccount.getAccountNumber());
        transaction.setToAccountId(toAccount.getAccountNumber());
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        // include from blockchain if present if not hard code it
        BlockChainTransferResponse response = makeBlockChainRequest(fromAccount,toAccount,amount);
        transaction.setTransactionId(AccountUtil.generateAccountNumber());
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        transactionRepository.save(transaction);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return TransactionUtil.getTransactionDTO(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactionsList =  transactionRepository.findAll();

        return TransactionUtil.getAlTransactionDTO(transactionsList);
    }

    public BlockChainTransferResponse makeBlockChainRequest(Account fromAccount, Account toAccount, Double amount) {

        //Make REST template call
        BlockChainTransferRequest request = new BlockChainTransferRequest();
        request.setCurrency("gbp");
        request.setFromCurrency("gbp");
        request.setToCurrency("eur");
        request.setReceiverAccountId(toAccount.getAccountNumber());
        request.setReceiverCountryId(1l);
        request.setReceiverBankId(1l);
        request.setSenderAccountId(fromAccount.getAccountNumber());
        request.setSenderCountryId(2l);
        request.setSenderBankId(2l);
        request.setAmount(amount.longValue());
        String url = "http://34.67.43.115:3000/stablecoin/exchange";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BlockChainTransferRequest> requestEntity = new HttpEntity<>(request,httpHeaders);
//        requestEntity .
//        URI uriLink = URI.create(url);
//
        try {
            HttpEntity<BlockChainTransferResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, BlockChainTransferResponse.class);
            return response.getBody();
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
        return null;
    }
}