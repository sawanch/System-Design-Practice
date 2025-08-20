package org.sawcha;

import org.sawcha.dao.ParkingSpotDAO;
import org.sawcha.model.Customer;
import org.sawcha.model.ParkingSpot;
import org.sawcha.model.VehicleType;
import org.sawcha.service.ParkingLotSystem;
import org.sawcha.service.impl.ParkingLotSystemImpl;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingSpotDAO dao = new ParkingSpotDAO();

        // Initialize spots only once if DB is empty
        if (dao.findAll().isEmpty()) {
            dao.addSpot(new ParkingSpot(1, VehicleType.SMALL, null));
            dao.addSpot(new ParkingSpot(2, VehicleType.MEDIUM, null));
            dao.addSpot(new ParkingSpot(3, VehicleType.LARGE, null));
        }

        // Use DAO-backed system
        ParkingLotSystem parkingLotSystem = new ParkingLotSystemImpl(dao);

        // Create a customer
        Customer c1 = new Customer("A123", VehicleType.SMALL, null, null);

        // Assign spot
        System.out.println("Assigning spot to customer " + c1.getLicense());
        ParkingSpot assigned = parkingLotSystem.assignSpot(c1);
        if (assigned != null) {
            System.out.println("Spot " + assigned.getId() + " assigned.");
        } else {
            System.out.println("No available spot!");
        }

        // Simulate exit after a while
        c1.setExitTime(LocalDateTime.now());

        // Show DB state
        System.out.println("\nCurrent DB state:");
        List<ParkingSpot> spots = dao.findAll();
        for (ParkingSpot spot : spots) {
            System.out.println("Spot " + spot.getId() +
                    " | Type=" + spot.getVehicleType() +
                    " | Occupied=" + spot.isOccupied() +
                    " | License=" + spot.getVehicleLicense());
        }

        // Release spot
        System.out.println("Releasing spot for customer " + c1.getLicense());
        parkingLotSystem.releaseSpot(c1);
    }
}
