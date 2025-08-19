package org.sawcha.exception;


// when ATM does not have enough cash
public class AtmOutOfCashException extends RuntimeException {
    public AtmOutOfCashException(String message) {
        super(message);
    }
}
