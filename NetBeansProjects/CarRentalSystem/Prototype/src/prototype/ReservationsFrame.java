package prototype;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationsFrame extends JPanel {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";

    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    private final JLabel reservationsLabel;
    private final JPanel reservationsContainer;
    private final JButton cancelButton;

    public ReservationsFrame(int clientId, CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());

        reservationsLabel = new JLabel("Reservations", JLabel.CENTER);
        add(reservationsLabel, BorderLayout.NORTH);

        reservationsContainer = new JPanel();
        reservationsContainer.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(reservationsContainer);
        add(scrollPane, BorderLayout.CENTER);

        loadReservations(clientId, cardLayout, mainPanel);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cardLayout.show(mainPanel, "Filters"));

        add(cancelButton, BorderLayout.SOUTH);
    }

    private void loadReservations(int clientId, CardLayout cardLayout, JPanel mainPanel) {
        String query = "SELECT * FROM RESERVATION WHERE cID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            showReservations(stmt, cardLayout, mainPanel, clientId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showReservations(PreparedStatement stmt, CardLayout cardLayout, JPanel mainPanel, int clientId) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        reservationsContainer.removeAll();

        int count = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] headers = {"Surname", "Model", "License Plate", "Days", "Reservation Date", "Return Date", "Total Cost", "Edit", "Delete"};
        for (String header : headers) {
            reservationsContainer.add(new JLabel(header), gbc);
            gbc.gridx++;
        }

        gbc.gridy++;

        while (rs.next()) {
            count++;
            gbc.gridx = 0;

            int reservationId = rs.getInt("rID");
            String surname = rs.getString("rSURNAME");
            String model = rs.getString("rMODEL");
            String licensePlate = rs.getString("rLICENSE_PLATE");
            int days = rs.getInt("rDAYS");
            Date reservationDate = rs.getDate("rRESERVATION_DATE");
            Date returnDate = rs.getDate("rRETURN_DATE");
            int totalCost = rs.getInt("rTOTAL_COST");

            reservationsContainer.add(new JLabel(surname), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(model), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(licensePlate), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(String.valueOf(days)), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(reservationDate.toString()), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(returnDate.toString()), gbc);
            gbc.gridx++;
            reservationsContainer.add(new JLabel(String.valueOf(totalCost)), gbc);
            gbc.gridx++;

            // Keep the edit button enabled
            JButton editButton = new JButton("Edit");
            editButton.addActionListener(e -> {
                handleEdit(reservationDate, reservationId, days, licensePlate, cardLayout, mainPanel, clientId);
            });
            reservationsContainer.add(editButton, gbc);
            gbc.gridx++;

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                handleDelete(reservationId, cardLayout, mainPanel, clientId);
            });
            reservationsContainer.add(deleteButton, gbc);

            gbc.gridy++;
        }

        reservationsLabel.setText(count + " Reservations");

        reservationsContainer.revalidate();
        reservationsContainer.repaint();
    }

    // Updated handleEdit method
    private void handleEdit(Date reservationDate, int reservationId, int days, String licensePlate, CardLayout cardLayout, JPanel mainPanel, int clientId) {
        // Check if the reservation date is in the past
        if (reservationDate.before(new Date())) {
            // Show a message indicating that the reservation cannot be edited
            JOptionPane.showMessageDialog(this, "This reservation is already underway and cannot be edited.", "Edit Not Allowed", JOptionPane.WARNING_MESSAGE);
        } else {
            // Proceed with the edit if the reservation has not started
            AvailableCarsFrame reservationsFrame = new AvailableCarsFrame(reservationId, days, licensePlate, cardLayout, mainPanel, clientId);
            mainPanel.add(reservationsFrame, "ReservationsFrame");
            cardLayout.show(mainPanel, "ReservationsFrame");
        }
    }

    private void handleDelete(int reservationId, CardLayout cardLayout, JPanel mainPanel, int clientId) {
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this reservation?", "Delete Reservation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            String selectQuery = "SELECT vID FROM RESERVATION WHERE rID = ?";
            String deleteQuery = "DELETE FROM RESERVATION WHERE rID = ?";
            String updateVehicleStatusQuery = "UPDATE VEHICLE SET vSTATUS = 'available' WHERE vID = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement selectStmt = conn.prepareStatement(selectQuery); PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery); PreparedStatement updateStmt = conn.prepareStatement(updateVehicleStatusQuery)) {

                selectStmt.setInt(1, reservationId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    int vehicleId = rs.getInt("vID");

                    deleteStmt.setInt(1, reservationId);
                    deleteStmt.executeUpdate();

                    updateStmt.setInt(1, vehicleId);
                    updateStmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Reservation deleted and vehicle status updated successfully.");
                    loadReservations(clientId, cardLayout, mainPanel);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservationsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
