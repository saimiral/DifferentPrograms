package prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationChecker {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";

    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    public void checkAndUpdateReservations() {
        String query = "SELECT rID, vID, rRETURN_DATE FROM RESERVATION WHERE rRETURN_DATE < ?";
        String updateVehicleStatusQuery = "UPDATE VEHICLE SET vSTATUS = 'available' WHERE vID = ?";
        String deleteReservationQuery = "DELETE FROM RESERVATION WHERE rID = ?";

        LocalDate currentDate = LocalDate.now();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(currentDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int reservationId = rs.getInt("rID");
                    int vehicleId = rs.getInt("vID");

                    try (PreparedStatement updateStmt = conn.prepareStatement(updateVehicleStatusQuery)) {
                        updateStmt.setInt(1, vehicleId);
                        updateStmt.executeUpdate();
                    }

                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteReservationQuery)) {
                        deleteStmt.setInt(1, reservationId);
                        deleteStmt.executeUpdate();
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}