package org.sawcha.model;

public class Account {
    private int accountNumber;
    private double balance;
    private int accountPIN;
    private double limit;

    public Account(int accountNumber, double balance, int accountPIN, double limit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountPIN = accountPIN;
        this.limit = limit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountPIN() {
        return accountPIN;
    }

    public void setAccountPIN(int accountPIN) {
        this.accountPIN = accountPIN;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
