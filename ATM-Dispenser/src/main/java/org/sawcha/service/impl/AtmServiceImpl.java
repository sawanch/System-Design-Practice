package org.sawcha.service.impl;

import org.sawcha.model.Account;
import org.sawcha.model.Customer;

import org.sawcha.service.AtmService;

public class AtmServiceImpl implements AtmService {

    private double atmCash;

    public AtmServiceImpl() {}

    public AtmServiceImpl(double atmCash) {
        this.atmCash = atmCash;
    }

    @Override
    public double getAtmCash() {
        return atmCash;
    }

    @Override
    public void setAtmCash(double atmCash) {
        this.atmCash = atmCash;
    }

    @Override
    public boolean verifyCustomer(Customer customer, Account account) {
        return customer.getPIN() == account.getAccountPIN();
    }

    @Override
    public boolean checkATMCash(Customer customer) {
        return customer.getWithdraw() <= atmCash;
    }

    @Override
    public boolean checkAccountBalance(Customer customer, Account account) {
        return customer.getWithdraw() <= account.getBalance();
    }

    @Override
    public boolean checkLimit(Customer customer, Account account) {
        return customer.getWithdraw() <= account.getLimit();
    }

    @Override
    public double withdraw(Customer customer, Account account) {
        double newBalance = account.getBalance() - customer.getWithdraw();
        account.setBalance(newBalance);
        atmCash -= customer.getWithdraw();  // update ATM balance
        return newBalance;
    }

    @Override
    public double deposit(Customer customer, Account account) {
        double newBalance = account.getBalance() + customer.getDeposit();
        account.setBalance(newBalance);
        atmCash += customer.getDeposit();  // update ATM balance
        return newBalance;
    }
}
