package prototype;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsFrame extends JPanel {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";

    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    private final JLabel resultsLabel;
    private final JPanel resultsContainer;
    private final JButton newSearchButton;

    public ResultsFrame(CardLayout cardLayout, JPanel mainPanel, Filters filters, int clientId) {
        this.setLayout(new BorderLayout());

        resultsLabel = new JLabel("Results", JLabel.CENTER);
        add(resultsLabel, BorderLayout.NORTH);

        resultsContainer = new JPanel();
        resultsContainer.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(resultsContainer);
        add(scrollPane, BorderLayout.CENTER);

        executeSearch(filters, cardLayout, mainPanel, clientId);

        newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(e -> cardLayout.show(mainPanel, "Filters"));

        add(newSearchButton, BorderLayout.SOUTH);
    }

    private void executeSearch(Filters filters, CardLayout cardLayout, JPanel mainPanel, int clientId) {
        String baseQuery = "SELECT * FROM VEHICLE WHERE 1=1";
        StringBuilder queryBuilder = new StringBuilder(baseQuery);

        if (filters.getMake() != null) {
            queryBuilder.append(" AND vMAKE = ?");
        }
        if (filters.getModel() != null) {
            queryBuilder.append(" AND vMODEL = ?");
        }
        if (filters.getYear() != null) {
            queryBuilder.append(" AND vYEAR = ?");
        }
        if (filters.getCost() != null) {
            queryBuilder.append(" AND vCOST <= ?");
        }
        if (filters.getDays() != null) {
            queryBuilder.append(" AND vDAYS <= ?");
        }

        String sqlQuery = queryBuilder.toString();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {

            int paramIndex = 1;

            if (filters.getMake() != null) {
                stmt.setString(paramIndex++, filters.getMake());
            }
            if (filters.getModel() != null) {
                stmt.setString(paramIndex++, filters.getModel());
            }
            if (filters.getYear() != null) {
                stmt.setInt(paramIndex++, filters.getYear());
            }
            if (filters.getCost() != null) {
                stmt.setInt(paramIndex++, filters.getCost());
            }
            if (filters.getDays() != null) {
                stmt.setInt(paramIndex++, filters.getDays());
            }

            showRes(stmt, cardLayout, mainPanel, clientId);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showRes(PreparedStatement stmt, CardLayout cardLayout, JPanel mainPanel, int clientId) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        resultsContainer.removeAll();

        int count = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add headers
        String[] headers = {"Make", "Model", "Year", "License Plate", "Cost", "Days", "Status", "Min Age", "Reserve"};
        for (String header : headers) {
            resultsContainer.add(new JLabel(header), gbc);
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

            resultsContainer.add(new JLabel(make), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(model), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(String.valueOf(year)), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(licensePlate), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(String.valueOf(cost)), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(String.valueOf(days)), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(status), gbc);
            gbc.gridx++;
            resultsContainer.add(new JLabel(String.valueOf(minAge)), gbc);
            gbc.gridx++;

            JButton reserveButton = new JButton("Reserve");
            reserveButton.addActionListener(e -> {
                try {
                    Vehicle vehicle = new Vehicle(id, make, model, year, licensePlate, cost, days, status, minAge);
                    handleReserve(vehicle, cardLayout, mainPanel, clientId);
                } catch (SQLException ex) {
                    Logger.getLogger(ResultsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            resultsContainer.add(reserveButton, gbc);

            gbc.gridy++;
        }

        resultsLabel.setText(count + " Results");

        resultsContainer.revalidate();
        resultsContainer.repaint();
    }

    private void handleReserve(Vehicle vehicle, CardLayout cardLayout, JPanel mainPanel, int clientId) throws SQLException {

        String query = "SELECT cAGE FROM CLIENT WHERE cID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int age = rs.getInt("cAGE");
                    
                    if(age < vehicle.getMinAge()){
                        JOptionPane.showMessageDialog(null, "Prohibited Client's Age", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "You have selected to reserve the " + vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getYear() + ") with license plate " + vehicle.getLicensePlate() + ".");
                        mainPanel.add(new PaymentFrame(cardLayout, mainPanel, vehicle, clientId), "Payment");
                        cardLayout.show(mainPanel, "Payment");
                    }
                }
            }
        }
    }
}
