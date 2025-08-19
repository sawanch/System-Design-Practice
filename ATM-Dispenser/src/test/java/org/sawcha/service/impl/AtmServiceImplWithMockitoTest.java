package org.sawcha.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sawcha.model.Account;
import org.sawcha.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtmServiceImplWithMockitoTest {
    @Mock
    private Account mockAccount;

    @Mock
    private Customer mockCustomer;

    @InjectMocks
    private AtmServiceImpl atmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        atmService = new AtmServiceImpl(1000);
    }

    @Test
    void testDepositIncreasesBalance_withMocks() {
        // Arrange
        when(mockAccount.getBalance()).thenReturn(500.0);
        when(mockCustomer.getDeposit()).thenReturn(200.0);

        // Act
        double newBalance = atmService.deposit(mockCustomer, mockAccount);

        // Assert
        assertEquals(700, newBalance);
        assertEquals(1200, atmService.getAtmCash());
    }

    @Test
    void testCheckAccountBalanceFails_withMocks() {
        // Arrange
        when(mockCustomer.getWithdraw()).thenReturn(600.0);
        when(mockAccount.getBalance()).thenReturn(500.0);

        // Act
        boolean result = atmService.checkAccountBalance(mockCustomer, mockAccount);

        // Assert
        assertFalse(result);
    }
}
