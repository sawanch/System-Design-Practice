package org.sawcha.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sawcha.dao.ParkingSpotDAO;
import org.sawcha.exception.InvalidLicenseException;
import org.sawcha.exception.InvalidVehicleTypeException;
import org.sawcha.model.Customer;
import org.sawcha.model.ParkingSpot;
import org.sawcha.model.VehicleType;
import org.sawcha.service.ParkingLotSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotSystemTest {

    private ParkingLotSystem parkingLotSystem;
    private ParkingSpotDAO dao;  // mock DAO
    private List<ParkingSpot> spots;

    @BeforeEach
    void setUp() {
        dao = Mockito.mock(ParkingSpotDAO.class);

        spots = new ArrayList<>();
        spots.add(new ParkingSpot(1, VehicleType.SMALL, null));
        spots.add(new ParkingSpot(2, VehicleType.MEDIUM, null));
        spots.add(new ParkingSpot(3, VehicleType.LARGE, null));

        when(dao.findAll()).thenReturn(spots);

        parkingLotSystem = new ParkingLotSystemImpl(dao);
    }

    @Test
    void testAssignSpotSuccess() {
        Customer c1 = new Customer("ABC123", VehicleType.SMALL, LocalDateTime.now(), null);

        ParkingSpot assigned = parkingLotSystem.assignSpot(c1);

        assertNotNull(assigned);
        assertEquals(1, assigned.getId());
        assertTrue(assigned.isOccupied());
        assertEquals("ABC123", assigned.getVehicleLicense());

        verify(dao).updateSpot(any(ParkingSpot.class)); // verify persistence
    }

    @Test
    void testAssignSpotNoAvailable() {
        Customer c1 = new Customer("ABC123", VehicleType.SMALL, LocalDateTime.now(), null);
        parkingLotSystem.assignSpot(c1);

        Customer c2 = new Customer("XYZ999", VehicleType.SMALL, LocalDateTime.now(), null);

        assertThrows(InvalidVehicleTypeException.class, () -> parkingLotSystem.assignSpot(c2));
    }

    @Test
    void testReleaseSpotFreesIt() {
        Customer c1 = new Customer("ABC123", VehicleType.SMALL, LocalDateTime.now(), null);
        parkingLotSystem.assignSpot(c1);

        c1.setExitTime(LocalDateTime.now());
        parkingLotSystem.releaseSpot(c1);

        assertFalse(spots.get(0).isOccupied());
        assertNull(spots.get(0).getVehicleLicense());

        verify(dao, atLeastOnce()).updateSpot(any(ParkingSpot.class));
    }

    @Test
    void testAssignSpotThrowsInvalidLicense() {
        Customer c1 = new Customer(null, VehicleType.SMALL, LocalDateTime.now(), null);

        assertThrows(InvalidLicenseException.class, () -> parkingLotSystem.assignSpot(c1));
    }

    @Test
    void testAssignSpotThrowsInvalidVehicleType() {
        Customer c1 = new Customer("LARGE123", VehicleType.LARGE, LocalDateTime.now(), null);

        assertThrows(InvalidVehicleTypeException.class, () -> parkingLotSystem.assignSpot(c1));
    }
}
