package org.sawcha.model;

public class Customer {
    private int id;
    private int PIN;

    private double withdraw;

    private double deposit;

    public Customer(int id, int PIN, double withdraw, double deposit) {
        this.id = id;
        this.PIN = PIN;
        this.withdraw = withdraw;
        this.deposit = deposit;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }
}
