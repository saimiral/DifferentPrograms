package prototype;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AvailableCarsFrame extends JPanel {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";

    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    private final JLabel availableCarsLabel;
    private final JPanel availableCarsContainer;
    private final JButton cancelButton;

    AvailableCarsFrame(int reservationId, int days, String licensePlate, CardLayout cardLayout, JPanel mainPanel, int clientId) {

        setLayout(new BorderLayout());

        availableCarsLabel = new JLabel("Results", JLabel.CENTER);
        add(availableCarsLabel, BorderLayout.NORTH);

        availableCarsContainer = new JPanel();
        availableCarsContainer.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(availableCarsContainer);
        add(scrollPane, BorderLayout.CENTER);

        loadAvailableCars(days, cardLayout, mainPanel, clientId, reservationId, licensePlate);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cardLayout.show(mainPanel, "Filters"));

        add(cancelButton, BorderLayout.SOUTH);
    }

    private void loadAvailableCars(int days, CardLayout cardLayout, JPanel mainPanel, int clientId, int reservationId, String licensePlatePrev) {
        String query = "SELECT * FROM VEHICLE WHERE vSTATUS = 'available' AND vDAYS = ? AND vID NOT IN (SELECT vID FROM RESERVATION WHERE rID = ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, days);
            stmt.setInt(2, reservationId); // Assuming reservationId is what links reservations to vehicles.
            showAvailableCars(stmt, cardLayout, mainPanel, clientId, reservationId, licensePlatePrev);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showAvailableCars(PreparedStatement stmt, CardLayout cardLayout, JPanel mainPanel, int clientId, int reservationId, String licensePlatePrev) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        availableCarsContainer.removeAll();

        int count = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        String[] headers = {"Make", "Model", "Year", "License Plate", "Cost", "Days", "Status", "Min Age", "Reserve"};
        for (String header : headers) {
            availableCarsContainer.add(new JLabel(header), gbc);
            gbc.gridx++;
        }

        gbc.gridy++;

        while (rs.next()) {
            count++;
            gbc.gridx = 0;

            int id = rs.getInt("vID");
            String make = rs.getString("vMAKE");
            String model = rs.getString("vMODEL");
            int year = rs.getInt("vYEAR");
            String licensePlate = rs.getString("vLICENSE_PLATE");
            int cost = rs.getInt("vCOST");
            int days = rs.getInt("vDAYS");
            String status = rs.getString("vSTATUS");
            int minAge = rs.getInt("vMIN_AGE");

            availableCarsContainer.add(new JLabel(make), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(model), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(String.valueOf(year)), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(licensePlate), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(String.valueOf(cost)), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(String.valueOf(days)), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(status), gbc);
            gbc.gridx++;
            availableCarsContainer.add(new JLabel(String.valueOf(minAge)), gbc);
            gbc.gridx++;

            JButton changeButton = new JButton("Change");
            changeButton.addActionListener(e -> {
                Vehicle vehicle = new Vehicle(id, make, model, year, licensePlate, cost, days, status, minAge);
                try {
                    handleChange(vehicle, cardLayout, mainPanel, clientId, reservationId, licensePlatePrev);
                } catch (SQLException ex) {
                    Logger.getLogger(AvailableCarsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            availableCarsContainer.add(changeButton, gbc);

            gbc.gridy++;
        }

        availableCarsLabel.setText(count + " Results");

        availableCarsContainer.revalidate();
        availableCarsContainer.repaint();
    }

    private void handleChange(Vehicle vehicle, CardLayout cardLayout, JPanel mainPanel, int clientId, int reservationId, String licensePlatePrev) throws SQLException {
        String query = "SELECT cAGE FROM CLIENT WHERE cID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int age = rs.getInt("cAGE");

                    if (age < vehicle.getMinAge()) {
                        JOptionPane.showMessageDialog(null, "Prohibited Client's Age", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "You have selected to reserve the " + vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ") with license plate " + vehicle.getLicensePlate() + ".");

                        String updateNewVehicleStatusQuery = "UPDATE VEHICLE SET vSTATUS = 'rented' WHERE vID = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateNewVehicleStatusQuery)) {
                            updateStmt.setInt(1, vehicle.getId());
                            updateStmt.executeUpdate();
                        }

                        String updateOldVehicleStatusQuery = "UPDATE VEHICLE SET vSTATUS = 'available' WHERE vLICENSE_PLATE = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateOldVehicleStatusQuery)) {
                            updateStmt.setString(1, licensePlatePrev);
                            updateStmt.executeUpdate();
                        }

                        int totalCost = vehicle.getDays() * vehicle.getCost();

                        String updateReservationQuery = "UPDATE RESERVATION SET vID = ?, rMODEL = ?, rLICENSE_PLATE = ?, rTOTAL_COST = ? WHERE rID = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateReservationQuery)) {
                            updateStmt.setInt(1, vehicle.getId());
                            updateStmt.setString(2, vehicle.getModel());
                            updateStmt.setString(3, vehicle.getLicensePlate());
                            updateStmt.setInt(4, totalCost);
                            updateStmt.setInt(5, reservationId);

                            updateStmt.executeUpdate();
                        }

                        cardLayout.show(mainPanel, "Filters");
                    }
                }
            }
        }
    }
}
