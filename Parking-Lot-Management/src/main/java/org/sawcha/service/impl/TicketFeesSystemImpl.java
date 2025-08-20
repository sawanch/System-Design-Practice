package org.sawcha.service.impl;

import org.sawcha.model.Customer;
import org.sawcha.service.TicketFeesSystem;

import java.time.Duration;

public class TicketFeesSystemImpl implements TicketFeesSystem {

    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFees(Customer customer) {
        if (customer.getEntryTime() == null || customer.getExitTime() == null) {
            throw new IllegalArgumentException("Entry and Exit time must be set.");
        }

        // Calculate duration in hours
        long hours = Duration.between(customer.getEntryTime(), customer.getExitTime()).toHours();

        // Minimum 1 hour charge
        if (hours == 0) {
            hours = 1;
        }

        return hours * RATE_PER_HOUR;
    }
}