package org.ltc.ltcbank.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ltc.ltcbank.dto.AccountDTO;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testGetBalance() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);

        when(accountService.getBalance(1L)).thenReturn(account.getBalance());

        mockMvc.perform(get("/api/accounts/1/balance"))
                .andExpect(status().isOk());

        verify(accountService, times(1)).getBalance(1L);
    }

    @Test
    public void testGetAccounts() throws Exception {
        User user = new User();
        user.setId(1L);

        when(userService.findById(1L)).thenReturn(user);
        when(accountService.getAccounts(user)).thenReturn(Collections.singletonList(new AccountDTO()));

        mockMvc.perform(get("/api/accounts/1/accounts"))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(1L);
        verify(accountService, times(1)).getAccounts(user);
    }

    @Test
    public void testCreateAccount() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setAccountNumber("ACC123456");
        account.setBalance(0.0);

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/api/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\":\"ACC123456\",\"balance\":0.0}"))
                .andExpect(status().isOk());

        verify(accountService, times(1)).createAccount(any(Account.class));
    }
}