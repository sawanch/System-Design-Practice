package org.sawcha.service.impl;

import org.junit.jupiter.api.Test;
import org.sawcha.model.Customer;
import org.sawcha.model.VehicleType;
import org.sawcha.service.TicketFeesSystem;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketFeesSystemTest {

    private final TicketFeesSystem ticketFees = new TicketFeesSystemImpl();

    @Test
    void testMinimumFeeWhenUnderOneHour() {
        Customer c = new Customer("ABC123", VehicleType.SMALL, LocalDateTime.now(), LocalDateTime.now());

        double fee = ticketFees.calculateFees(c);

        assertEquals(10.0, fee); // minimum 1 hour charge
    }

    @Test
    void testFeeForMultipleHours() {
        Customer c = new Customer("XYZ999", VehicleType.MEDIUM,
                LocalDateTime.now().minusHours(3), LocalDateTime.now());

        double fee = ticketFees.calculateFees(c);

        assertEquals(30.0, fee); // 3 hours * $10
    }
}
