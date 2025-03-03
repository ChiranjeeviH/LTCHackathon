package org.ltc.ltcbank.controller;

import org.junit.jupiter.api.Test;
import org.ltc.ltcbank.entity.Account;
import org.ltc.ltcbank.entity.User;
import org.ltc.ltcbank.service.AccountService;
import org.ltc.ltcbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBalance() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(1000.0);

        when(accountService.getBalance(1L)).thenReturn(account);

        mockMvc.perform(get("/api/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.balance").value(1000.0));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAccounts() throws Exception {
        User user = new User();
        user.setId(1L);

        Account account1 = new Account();
        account1.setId(1L);
        account1.setAccountNumber("123456");

        Account account2 = new Account();
        account2.setId(2L);
        account2.setAccountNumber("654321");

        when(userService.findById(1L)).thenReturn(user);
        when(accountService.getAccounts(user)).thenReturn(Arrays.asList(account1, account2));

        mockMvc.perform(get("/api/accounts/1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].accountNumber").value("123456"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].accountNumber").value("654321"));
    }
}