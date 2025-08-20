package org.sawcha.dao;

import org.sawcha.model.ParkingSpot;
import org.sawcha.model.VehicleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotDAO {
    private static final String URL = "jdbc:sqlite:parkinglot.db";

    public ParkingSpotDAO() {
        // create table if not exists
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sql = """
                CREATE TABLE IF NOT EXISTS parking_spots (
                    id INTEGER PRIMARY KEY,
                    vehicle_type TEXT NOT NULL,
                    vehicle_license TEXT,
                    occupied INTEGER NOT NULL
                )
            """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSpot(ParkingSpot spot) {
        String sql = "INSERT INTO parking_spots (id, vehicle_type, vehicle_license, occupied) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, spot.getId());
            ps.setString(2, spot.getVehicleType().name());
            ps.setString(3, spot.getVehicleLicense());
            ps.setInt(4, spot.isOccupied() ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ParkingSpot> findAll() {
        List<ParkingSpot> spots = new ArrayList<>();
        String sql = "SELECT * FROM parking_spots";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ParkingSpot spot = new ParkingSpot(
                        rs.getInt("id"),
                        VehicleType.valueOf(rs.getString("vehicle_type")),
                        rs.getString("vehicle_license")
                );
                spot.setParkingSpotAssignedFlag(rs.getInt("occupied") == 1);
                spots.add(spot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spots;
    }

    public void updateSpot(ParkingSpot spot) {
        String sql = "UPDATE parking_spots SET vehicle_license = ?, occupied = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, spot.getVehicleLicense());
            ps.setInt(2, spot.isOccupied() ? 1 : 0);
            ps.setInt(3, spot.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
