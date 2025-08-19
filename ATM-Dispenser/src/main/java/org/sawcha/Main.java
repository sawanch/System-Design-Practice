package org.sawcha;

import org.sawcha.exception.AtmOutOfCashException;
import org.sawcha.exception.InsufficientFundsException;
import org.sawcha.model.Account;
import org.sawcha.model.Customer;
import org.sawcha.service.impl.AtmServiceImpl;
import org.sawcha.service.AtmService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1001, 5000, 1234, 2000);
        Customer customer = new Customer(1, 1234, 0, 0);
        AtmService atmSystem = new AtmServiceImpl(200000);

        if (!atmSystem.verifyCustomer(customer, account)) {
            System.out.println("Invalid Pin. Exiting .......");
            return;
        }

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter 1 for Withdraw and 2 for Deposit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    customer.setWithdraw(withdrawAmount);

                    if (!atmSystem.checkATMCash(customer)) {
                        throw new AtmOutOfCashException("ATM does not have enough cash.");
                    }
                    if (!atmSystem.checkAccountBalance(customer, account)) {
                        throw new InsufficientFundsException("Your account balance is too low.");
                    }
                    if (!atmSystem.checkLimit(customer, account)) {
                        System.out.println("Withdraw limit reached. Exiting .......");
                        return;
                    }
                    System.out.println("Amount withdrawn, new account balance: " + atmSystem.withdraw(customer, account));
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    customer.setDeposit(depositAmount);
                    System.out.println("Amount deposited, new account balance: " + atmSystem.deposit(customer, account));
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (Exception e) {
            System.out.println(" Unexpected error: " + e.getMessage());
        } finally {
            sc.close(); // always close scanner
            System.out.println("ATM session ended.");
        }
    }
}
