package org.sawcha.service;

import org.sawcha.model.Account;
import org.sawcha.model.Customer;

public interface AtmService {

    double getAtmCash();
    void setAtmCash(double atmCash);

    boolean verifyCustomer(Customer customer, Account account);

    boolean checkATMCash(Customer customer);

    boolean checkAccountBalance(Customer customer, Account account);

    boolean checkLimit(Customer customer, Account account);

    double withdraw(Customer customer, Account account);

    double deposit(Customer customer, Account account);
}
