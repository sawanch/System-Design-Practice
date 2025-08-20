package org.sawcha.service.impl;

import org.sawcha.dao.ParkingSpotDAO;
import org.sawcha.exception.InvalidLicenseException;
import org.sawcha.exception.InvalidVehicleTypeException;
import org.sawcha.model.Customer;
import org.sawcha.model.ParkingSpot;
import org.sawcha.service.ParkingLotSystem;
import org.sawcha.service.TicketFeesSystem;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotSystemImpl implements ParkingLotSystem {
    private final ParkingSpotDAO dao;

    public ParkingLotSystemImpl(ParkingSpotDAO dao) {
        this.dao = dao;
    }

    @Override
    public ParkingSpot assignSpot(Customer customer) {
        if (customer.getLicense() == null || customer.getLicense().isBlank()) {
            throw new InvalidLicenseException("License plate cannot be null or empty.");
        }

        List<ParkingSpot> spots = dao.findAll();

        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getVehicleType() != customer.getVehicleType()) {
                throw new InvalidVehicleTypeException(
                        "Vehicle type mismatch: Spot is for " + spot.getVehicleType() +
                                ", but customer has " + customer.getVehicleType()
                );
            }

            if (!spot.isOccupied() && spot.getVehicleType() == customer.getVehicleType()) {
                spot.assign(customer);
                customer.setEntryTime(LocalDateTime.now());
                dao.updateSpot(spot); // persist in DB
                return spot;
            }
        }
        return null;
    }

    @Override
    public void releaseSpot(Customer customer) {
        List<ParkingSpot> spots = dao.findAll();

        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && customer.getLicense().equals(spot.getVehicleLicense())) {
                customer.setExitTime(LocalDateTime.now());

                TicketFeesSystem ticketFees = new TicketFeesSystemImpl();
                double amount = ticketFees.calculateFees(customer);

                System.out.println("Parking fee for vehicle " + customer.getLicense() + ": $" + amount);

                spot.release();
                dao.updateSpot(spot); // persist release
                break;
            }
        }
    }
}
