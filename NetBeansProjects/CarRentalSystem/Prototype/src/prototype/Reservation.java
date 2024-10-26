package prototype;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int id;
    private int clientId;
    private int vehicleId;
    private String surname;
    private String model;
    private String licensePlate;
    private int days;
    private Date reservationDate;
    private Date returnDate;
    private int totalCost;

    // Constructor
    public Reservation(int id, int clientId, int vehicleId, String surname, String model, String licensePlate, int days, Date reservationDate, Date returnDate, int totalCost) {
        this.id = id;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.surname = surname;
        this.model = model;
        this.licensePlate = licensePlate;
        this.days = days;
        this.reservationDate = reservationDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }
    public Date getReservationDate() { return reservationDate; }
    public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public int getTotalCost() { return totalCost; }
    public void setTotalCost(int totalCost) { this.totalCost = totalCost; }

    // Fetch all reservations for a specific client ID
    public static List<Reservation> getReservationsByClientId(int clientId) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM RESERVATION WHERE cID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("rID");
                int vehicleId = rs.getInt("vID");
                String surname = rs.getString("rSURNAME");
                String model = rs.getString("rMODEL");
                String licensePlate = rs.getString("rLICENSE_PLATE");
                int days = rs.getInt("rDAYS");
                Date reservationDate = rs.getDate("rRESERVATION_DATE");
                Date returnDate = rs.getDate("rRETURN_DATE");
                int totalCost = rs.getInt("rTOTAL_COST");
                reservations.add(new Reservation(id, clientId, vehicleId, surname, model, licensePlate, days, reservationDate, returnDate, totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Fetch a specific reservation by reservation ID
    public static Reservation getReservationById(int reservationId) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM RESERVATION WHERE rID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int clientId = rs.getInt("cID");
                int vehicleId = rs.getInt("vID");
                String surname = rs.getString("rSURNAME");
                String model = rs.getString("rMODEL");
                String licensePlate = rs.getString("rLICENSE_PLATE");
                int days = rs.getInt("rDAYS");
                Date reservationDate = rs.getDate("rRESERVATION_DATE");
                Date returnDate = rs.getDate("rRETURN_DATE");
                int totalCost = rs.getInt("rTOTAL_COST");
                return new Reservation(reservationId, clientId, vehicleId, surname, model, licensePlate, days, reservationDate, returnDate, totalCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the reservation is not found
    }

    // Add Car to Reservation
    public static boolean addCarToReservation(int reservationId, int vehicleId) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Check if the car is available
            String checkAvailabilityQuery = "SELECT vSTATUS FROM VEHICLE WHERE vID = ? AND vSTATUS = 'available'";
            PreparedStatement checkStmt = conn.prepareStatement(checkAvailabilityQuery);
            checkStmt.setInt(1, vehicleId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Car is available, proceed to add to reservation
                String addCarQuery = "UPDATE VEHICLE SET vSTATUS = 'rented' WHERE vID = ?";
                PreparedStatement pstmt = conn.prepareStatement(addCarQuery);
                pstmt.setInt(1, vehicleId);
                pstmt.executeUpdate();

                // Update the reservation's vehicle ID
                String updateReservationQuery = "UPDATE RESERVATION SET vID = ? WHERE rID = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateReservationQuery);
                updateStmt.setInt(1, vehicleId);
                updateStmt.setInt(2, reservationId);
                updateStmt.executeUpdate();

                return true;
            } else {
                System.out.println("The car is not available.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove Car from Reservation and set vehicle status to 'available'
    public static boolean removeCarFromReservation(int reservationId, int vehicleId) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Delete the reservation from the RESERVATIONS table
            String deleteReservationQuery = "DELETE FROM RESERVATION WHERE rID = ? AND vID = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteReservationQuery);
            deleteStmt.setInt(1, reservationId);
            deleteStmt.setInt(2, vehicleId);
            deleteStmt.executeUpdate();

            // Update the vehicle status back to 'available'
            String updateVehicleStatus = "UPDATE VEHICLE SET vSTATUS = 'available' WHERE vID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateVehicleStatus);
            updateStmt.setInt(1, vehicleId);
            updateStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update the reservation vehicle
    public static boolean updateReservationVehicle(int reservationId, int newVehicleId) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Update the reservation with the new vehicle
            String query = "UPDATE RESERVATION SET vID = ? WHERE rID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, newVehicleId);
            pstmt.setInt(2, reservationId);
            int updatedRows = pstmt.executeUpdate();

            if (updatedRows > 0) {
                // Update the status of the old and new vehicles
                String updateNewVehicleStatus = "UPDATE VEHICLE SET vSTATUS = 'rented' WHERE vID = ?";
                PreparedStatement updateNewStmt = conn.prepareStatement(updateNewVehicleStatus);
                updateNewStmt.setInt(1, newVehicleId);
                updateNewStmt.executeUpdate();

                // Set the old vehicle to available if needed
                // You'll need to update the status of the old vehicle here by retrieving the old vehicle ID

                return true; // Success
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Failure
    }
}

