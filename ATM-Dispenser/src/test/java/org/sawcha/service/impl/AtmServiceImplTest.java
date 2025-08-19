package org.sawcha.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sawcha.model.Account;
import org.sawcha.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AtmServiceImplTest {
    private AtmServiceImpl atmService;
    private Account account;
    private Customer customer;

    @BeforeEach
    void setUp() {
        // setup fresh objects before each test
        atmService = new AtmServiceImpl(1000);          // ATM has 1000 cash
        account = new Account(1001, 500, 1234, 300);    // accNo, balance, PIN, limit
        customer = new Customer(1, 1234, 0, 0);         // id, PIN, withdraw, deposit
    }

    @Test
    void testDepositIncreasesBalance() {
        customer.setDeposit(200);
        double newBalance = atmService.deposit(customer, account);

        assertEquals(700, newBalance);
        assertEquals(700, account.getBalance()); // also check account updated
        assertEquals(1200, atmService.getAtmCash()); // ATM cash also updated
    }

    @Test
    void testWithdrawDecreasesBalance() {
        customer.setWithdraw(300);
        double newBalance = atmService.withdraw(customer, account);

        assertEquals(200, newBalance);
        assertEquals(200, account.getBalance()); // also check account updated
        assertEquals(700, atmService.getAtmCash()); // ATM cash also updated
    }

    @Test
    void testCheckAccountBalanceFails() {
        customer.setWithdraw(600); // more than balance
        assertFalse(atmService.checkAccountBalance(customer, account));
    }
}
