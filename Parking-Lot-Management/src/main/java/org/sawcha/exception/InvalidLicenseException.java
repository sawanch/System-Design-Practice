package org.sawcha.exception;

public class InvalidLicenseException extends RuntimeException {
    public InvalidLicenseException(String message) {
        super(message);
    }
}
