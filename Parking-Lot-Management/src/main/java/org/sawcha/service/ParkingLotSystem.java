package org.sawcha.service;

import org.sawcha.model.Customer;
import org.sawcha.model.ParkingSpot;

public interface ParkingLotSystem {

    ParkingSpot assignSpot(Customer customer);
    void releaseSpot(Customer customer);
}
